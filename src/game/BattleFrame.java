package game;

import gfx.Sprite;
import gfx.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class BattleFrame extends Canvas implements Runnable, MouseListener {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;  // 1920x1080
    //public static final int WIDTH = 480;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 2;
    public static final String NAME = "FIGHT!";

    private boolean running = false;
    private JFrame frame;
    final JPanel groupPanel = new JPanel();

    private int textX;
    private int textY;
    private String display = "";

    int fps = 0;

    private int[] herox = {400, 200, 200};
    private int[] mobx = {750, 950, 950};
    private int[] chary = {300, 450, 150};
    private int buffer = 20;

    private int selected_hero = -1;
    private int selected_mob = -1;

    private int duration = 250;

    private int delay = 120;

    private Arena arena;
    private Computer computer;
    private ArrayList<Character> heroes;
    private ArrayList<Character> mobs;
    private Boss boss;

    TileMap map = new TileMap("res/levels/battle_map.txt");


    private Rectangle[] buttons = {new Rectangle(455, HEIGHT*SCALE - 130, 150, 75), new Rectangle(615, HEIGHT*SCALE - 130, 150, 75)};
    private Game game;

    public BattleFrame(Game game, Arena arena) {
        this.game = game;
        map.setxOffSet(0);
        map.setyOffSet(0);
        this.arena = arena;
        this.computer = new Computer(arena);
        init_frame();
        init_battle();
    }


    public void init_frame() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.setUndecorated(true); //makes borderless window
        frame.pack();

        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        groupPanel.setVisible(true);
        frame.setVisible(true);

        addMouseListener(this);
    }

    public void init_battle() {
        heroes = arena.getHeroes().getGroup();
        if (heroes.size()== 1) {
            selected_hero = 0;
        }
        if (arena.getMobs() != null) {
            mobs = arena.getMobs().getGroup();
            if (mobs != null) {
                if (mobs.size() == 1) {
                    selected_mob = 0;
                }
            }
        }else{
            boss = arena.getBoss();
            if (boss != null) {
                selected_mob = 0;

            }
        }

    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();

    }

    public synchronized void stop() {
        running = false;
    }


    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true; // false here limits to 60 fps

            while (delta >= 1) {
                tick();
                delta--;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                fps = frames;
                frames = 0;
            }
        }

    }

    public void tick() {
        if (duration > 0){
            duration-=2;
        }
        if (!arena.isHeroTurn()) {
            computer.tick();
            if (computer.isReady()) {
                ComputerChoice choice = computer.makePlay();

                int winner = arena.dealDamage(choice.attacker,choice.ability,choice.target);
                if (winner == 0) {
                    if (!arena.getHeroes().getGroup().get(choice.target).isAlive()) {
                        arena.getHeroes().getGroup().remove(choice.target);
                        if (choice.target == selected_hero) {
                            selected_hero = -1;
                        }
                    }
                }else if (winner == 2) {
                    //game.
                    exit(winner);
                }
            }
        }
    }

    public int detectHeroSelection(int mX, int mY) {
        for (int i = 0; i < heroes.size(); i++) {
            int x = herox[i];
            int y = chary[i];

            int width = heroes.get(i).getSprite().getScale() * 32;
            int height = width;

            if (((mX < x + width + buffer & mX > x - buffer) || (mX > x - buffer & mX < x + width + buffer)) & ((mY < y + height + buffer& mY > y - buffer) || (mY > y - buffer & mY < y + height + buffer) || (mY > y + height + buffer & mY < y - buffer))) {
                return i;
            }

        }
        return selected_hero;
    }

    public int detectMobSelection(int mX, int mY) {
        for (int i = 0; i < mobs.size(); i++) {
            int x = mobx[i];
            int y = chary[i];

            int width = mobs.get(i).getSprite().getScale() * 32;
            int height = width;

            if (((mX < x + width + buffer & mX > x - buffer) || (mX > x - buffer & mX < x + width + buffer)) & ((mY < y + height + buffer& mY > y - buffer) || (mY > y - buffer & mY < y + height + buffer) || (mY > y + height + buffer & mY < y - buffer))) {
                return i;
            }

        }
        return selected_mob;
    }

    public int detectButtonClick(int mX, int mY) {
        for (int i = 0; i < buttons.length; i++) {
            int x = buttons[i].x;
            int y = buttons[i].y;

            int width = buttons[i].width;
            int height = buttons[i].height;

            if (((mX < x + width & mX > x) || (mX > x & mX < x + width)) & ((mY < y + height& mY > y) || (mY > y  & mY < y + height) || (mY > y + height & mY < y))) {
                return i;
            }

        }
        return -1;
    }


    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        map.draw(g,Game.SCALE);

        for (int i = 0; i < heroes.size(); i++) {
            Sprite curr = heroes.get(i).getSprite();
            g.drawImage(curr.getImg(), herox[i], chary[i],32*curr.getScale(),32*curr.getScale(), null);
        }
        if (mobs!= null) {
            for (int i = 0; i < mobs.size(); i++) {
                Sprite curr = mobs.get(i).getSprite();
                g.drawImage(curr.getImg(), mobx[i], chary[i],32*curr.getScale(),32*curr.getScale(), null);
            }
        }else if (boss!= null){
            g.drawImage(boss.getSprite().getImg(), mobx[0], chary[0], 32*boss.getSprite().getScale(),32*boss.getSprite().getScale(), null);
        }



        if (selected_hero > -1 && selected_hero < heroes.size()) {
            g.setColor(Color.GREEN);
            Sprite curr = heroes.get(selected_hero).getSprite();
            g.drawRect(herox[selected_hero]- buffer, chary[selected_hero]- buffer, 32*curr.getScale()+ buffer*2, 32*curr.getScale()+ buffer*2);
        }
        if (mobs != null && selected_mob > -1 && selected_mob < mobs.size()) {
            g.setColor(Color.RED);
            Sprite curr = mobs.get(selected_mob).getSprite();
            g.drawRect(mobx[selected_mob]- buffer, chary[selected_mob] - buffer, 32*curr.getScale()+ buffer*2, 32*curr.getScale()+ buffer*2);
        }

        if (boss != null) {
            g.setColor(Color.RED);
            Sprite curr = boss.getSprite();
            g.drawRect(mobx[0]- buffer, chary[selected_mob] - buffer, 32*curr.getScale()+ buffer*2, 32*curr.getScale()+ buffer*2);
        }



        if (selected_hero > -1 && selected_hero < heroes.size()) {
            for (int i = 0; i < buttons.length; i++) {
                g.setColor(Color.darkGray);
                g.fillRect(buttons[i].x, buttons[i].y, buttons[i].width,buttons[i].height);
                g.setColor(Color.WHITE);
                g.drawString(heroes.get(selected_hero).getAbilityList()[i], buttons[i].x + buttons[i].width/4, buttons[i].y + buttons[i].height/5*2);
                if (i == 0) {
                    g.drawString(heroes.get(selected_hero).dealFirstAttack() + " damage", buttons[i].x + buttons[i].width/4, buttons[i].y + buttons[i].height/5*4);
                }else{
                    g.drawString(heroes.get(selected_hero).dealSecondAttack() + " damage", buttons[i].x + buttons[i].width/4, buttons[i].y + buttons[i].height/5*4);
                }
            }
        }

        drawDiagnostic(g);
        drawHUD(g);

        g.dispose();
        bs.show();
    }

    public void drawDiagnostic(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));

        g.drawString(fps + " ", 15, 35);
        if (duration > 0) {
            g.setFont(new Font("TimesRoman", Font.BOLD, 120));
            g.setColor(new Color(254, 254, 254, duration));
            g.drawString("FIGHT! ", 408, 360);
        }
    }

    public void drawHUD(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));

        if (selected_hero > -1 && selected_hero < heroes.size()) {
            g.drawString(heroes.get(selected_hero).getStats().getHitPoints() + " ", 400, 50);
        }
        if (mobs!= null && selected_mob > -1 && selected_mob < mobs.size()) {
            g.drawString(mobs.get(selected_mob).getStats().getHitPoints() + " ", 745, 50);
        }else if (boss != null) {
            g.drawString(boss.getStats().getHitPoints() + " ", 725, 50);
        }

        if (arena.isHeroTurn())  {
            g.setColor(Color.GREEN);
        }else {
            g.setColor(Color.RED);
        }

        g.fillOval(buttons[1].x-10, buttons[1].y-20,10,10);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (arena.isHeroTurn()) {
            int x = e.getX();
            int y = e.getY();
            int clicked = detectButtonClick(x,y);
            if (clicked != -1 && selected_hero > -1 && selected_hero < heroes.size() && ((mobs != null && selected_mob > -1 && selected_mob < mobs.size()) || boss != null)) {
                int winner = arena.dealDamage(selected_hero, clicked, selected_mob);
                if (winner == 0) {
                    if (mobs != null && !arena.getMobs().getGroup().get(selected_mob).isAlive()) {
                        arena.getMobs().getGroup().remove(selected_mob);
                        selected_mob = -1;
                    }
                }else if (winner == 1) {
                    if (boss != null && !boss.isAlive()) {
                        boss = null;
                    }
                    System.out.println("Victory");
                    exit(winner);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        selected_hero = detectHeroSelection(x,y);
        if (mobs != null) {
            selected_mob = detectMobSelection(x,y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }
    public void exit(int winner){
        stop();
        frame.setVisible(false);
        game.returnWinner(winner);
        game.setFrameVisible(true);
        game.start();

    }

}
