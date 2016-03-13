package game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import gfx.*;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640;  // 1920x1080
	//public static final int WIDTH = 480;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 2;
	public static final String NAME = "Dream Street";

	private boolean running = false;
	private JFrame frame;


    private SpriteSheet lightsheet = new SpriteSheet("/sprites/lightradius.png");
	private BufferedImage lightobj = lightsheet.getSprite(0,0,66,36);
    private Lighting lightradius = new Lighting(lightobj, 0, 0); // use this if running on 480 width

	public int dx = 0;
	public int dy = 0;

    public double duration = 255;
    public int lootduration = 254;
    public String looted = "";



    private HeroGroup heroes;


	//Levels
    LevelManager manager;

	int fps = 0;

	public Game(final HeroGroup heroes) {
        this.heroes = heroes;

        heroes.center();
        manager = new LevelManager();
        ArrayList<MobGroup> mobs = manager.getObjects().getMobs();

        for (int j = 0; j < mobs.size(); j++) {
            heroes.registerObserver(mobs.get(j));
        }
        if (manager.getObjects().getBoss() != null) {
            heroes.registerObserver(manager.getObjects().getBoss());
        }


        setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));

		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this,BorderLayout.CENTER);
		//frame.setUndecorated(true); //makes borderless window
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);


		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					dy = -5;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					dy = 5;
				}

				if (e.getKeyCode() == KeyEvent.VK_A) {
					dx = -5;
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					dx = 5;
				}
                manager.setMapDx(dx);
                manager.setMapDy(dy);
                manager.getObjects().setDx(dx);
                manager.getObjects().setDy(dy);

            }

			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_W) {
					if (dy == -5) {
						dy = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					if (dy == 5) {
						dy = 0;
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (dx == -5) {
						dx = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if (dx == 5) {
						dx = 0;
					}
				}
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    ArrayList<DoorTile> doors = manager.getTileMap().getDoors();
                    for (int i = 0; i < doors.size(); i++) {
                        DoorTile exit = doors.get(i);
                        if (!exit.isLocked()) {
                            int x_rel = exit.x * SCALE - manager.getTileMap().getxOffSet();
                            int y_rel = exit.y * SCALE - manager.getTileMap().getyOffSet();

                            double dist = calculateDistance(heroes.getX() - x_rel, heroes.getY() - y_rel);

                            if (Math.abs(dist) < 60) {
                                heroes.clearObservers();
                                manager.setCurrent_level(doors.get(i).getTo_level());
                                ArrayList<MobGroup> mobs = manager.getObjects().getMobs();


                                for (int j = 0; j < mobs.size(); j++) {
                                    heroes.registerObserver(mobs.get(j));
                                }
                                if (manager.getObjects().getBoss() != null) {
                                    heroes.registerObserver(manager.getObjects().getBoss());
                                }

                                if (exit.getTo() != null) {
                                    manager.getTileMap().setxOffSet(-(heroes.getX() - exit.getTo().x * SCALE));
                                    manager.getTileMap().setyOffSet(-(heroes.getY() - exit.getTo().y * SCALE));
                                    manager.getObjects().setOffSets(-(heroes.getX() - exit.getTo().x * SCALE), -(heroes.getY() - exit.getTo().y * SCALE));
                                } else {
                                    lockDoor();
                                }
                            }
                        }
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_F) {
                    ArrayList<TreasureChest> chests = manager.getObjects().getChests();
                    for (int i = 0; i < chests.size(); i++) {
                        TreasureChest chest = chests.get(i);
                        int x_rel = chest.getSprite().getX() * SCALE - manager.getObjects().getxOffSet();
                        int y_rel = chest.getSprite().getY() * SCALE - manager.getObjects().getyOffSet();

                        double dist = calculateDistance(heroes.getX() - x_rel,heroes.getY() - y_rel);

                        if (Math.abs(dist) < 60) {
                            if (Math.random() > .8) {
                                Weapon weapon = chest.spawnWeapon();
                                looted = "looted " + weapon.getName();
                                ((Hero)heroes.getCharacter((int)(Math.random()*heroes.getGroup().size()))).setWeapon(weapon);
                            }else {
                                Item item = chest.spawnItem();
                                looted = "looted " + item.getName();
                                ((Hero)heroes.getCharacter((int)(Math.random()*heroes.getGroup().size()))).addItem(item);
                            }
                            lootduration = 250;
                            chests.remove(i);
                        }
                    }
                }

                manager.setMapDx(dx);
                manager.setMapDy(dy);
                manager.getObjects().setDx(dx);
                manager.getObjects().setDy(dy);

            }

			public void keyTyped(KeyEvent e) {

			}
		});
	}

    private void lockDoor() {
        ArrayList<DoorTile> doors = manager.getTileMap().getDoors();
        for (int i = 0; i < doors.size(); i++) {
            DoorTile exit = doors.get(i);
            int x_rel = exit.x * SCALE - manager.getTileMap().getxOffSet();
            int y_rel = exit.y * SCALE - manager.getTileMap().getyOffSet();

            double dist = calculateDistance(heroes.getX() - x_rel,heroes.getY() - y_rel);

            if (Math.abs(dist) < 60) {
                exit.setLocked(true);
            }
        }
    }

    public static double calculateDistance(int x, int y)
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
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
		//limit fps approx. 60
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = true; // false here limits to 60 fps
			
			while(delta>=1){
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}
			try{
				Thread.sleep(2);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			if(shouldRender){
				frames++;
				render();
			}
			
			if(System.currentTimeMillis()-lastTimer >= 1000){
				lastTimer+=1000;
				fps = frames;
				frames = 0;
				ticks =0;
				
			}
		}
	}

	public void tick(){
        if (duration > 0) {
            duration-=.6;
        }

        if (lootduration > 0) {
            lootduration -= 2;
        }
        checkAggro();

        if (!manager.collision(heroes.getX() + dx + 10,heroes.getY() + dy + 35, 40, 25)) {
            manager.tick();
        }
	}

    public void checkAggro() {
        int aggroed = heroes.notifyObservers(manager.getObjects().getxOffSet(), manager.getObjects().getyOffSet());
        if (aggroed > -1) {

            try{
                Thread.sleep(600);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            this.stop();

            Arena arena;
            if (aggroed < manager.getObjects().getMobs().size()) {
                arena = new Arena(heroes, manager.getObjects().getMobs().get(aggroed));
                manager.getObjects().getMobs().remove(aggroed);
            }else{
                arena = new Arena(heroes, manager.getObjects().getBoss());
                manager.getObjects().setBoss(null);
            }

            heroes.unregisterObserver(heroes.observers.get(aggroed));


            BattleFrame battle = new BattleFrame(this, arena);
            battle.start();
            battle.requestFocus();
            frame.setVisible(false);
        }
    }

    public void drawOverlay(Graphics g) {
        g.fillRect(0, 0, (int)(WIDTH * SCALE *1.1), (int)(HEIGHT * SCALE*1.1));
    }

	public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        manager.draw(g, SCALE);
        heroes.getMainSprite().draw(g);

        Color dim;
        if (manager.getCurrent_level() == 1 || manager.getCurrent_level() == 2) {
            dim = new Color(1, 238, 255, 43);
            g.setColor(dim);
            drawOverlay(g);
        }else if (manager.getCurrent_level() == 3 || manager.getCurrent_level() == 4 || manager.getCurrent_level() == 5) {
            lightradius.draw(g,21);
        }else if (manager.getCurrent_level() > 5) {
            dim = new Color(106, 17, 255, 125);
            g.setColor(dim);
            drawOverlay(g);
        }

		drawDiagnostic(g);
		g.dispose();
		bs.show();
	}

	public void drawDiagnostic(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString(fps + " ", 20, 40);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        g.setColor(new Color(250,250,250,(int)duration));
        g.drawString("Use WASD to move", 750,300);
        g.drawString("E to enter doors", 750,350);
        g.drawString("F to open chests", 750,400);
        g.setColor(new Color(250,250,250, lootduration));
        g.drawString(looted, 530,200);

    }

	public static void main(String[] args){
        SelectionFrame select = new SelectionFrame(500, 500);
        HeroGroup heroes = select.display();
		Game game = new Game(heroes);
		game.start();
		game.requestFocus();
	}

    public void setFrameVisible(boolean visible) {
        this.frame.setVisible(visible);
    }


    public void returnWinner(int winner) {
        if (winner == 1) {
        }
    }

}
