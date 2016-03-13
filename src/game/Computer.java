package game;

import java.util.ArrayList;
import java.util.Random;


public class Computer {

    private ArrayList<Character> mobs;
    private ArrayList<Character> heroes;
    private Boss boss;
    private Arena arena;

    private int delay = 75;



    private boolean ready = false;

    public Computer(Arena arena) {
        this.arena = arena;
        this.heroes = arena.getHeroes().getGroup();
        if (arena.getMobs() != null) {
            this.mobs = arena.getMobs().getGroup();
        } else {
            this.boss = arena.getBoss();
        }

    }

    public void tick() {
        delay--;
        if (delay < 0) {
            ready = true;
            delay = 75;
        }
    }

    public ComputerChoice makePlay() {
        ready = false;
        Random rand = new Random();

        int attacker = 0;
        int ability;
        if (mobs!=null) {
            attacker = rand.nextInt(mobs.size());
            ability = rand.nextInt(mobs.get(attacker).getAbilityList().length);
        }else{
            ability = rand.nextInt(boss.getAbilityList().length);
        }
        int target = 0;

        for (int i = 1; i < heroes.size(); i++) {
            if (heroes.get(i).getStats().getHitPoints() < heroes.get(target).getStats().getHitPoints()) {
                target = i;
            }
        }

        System.out.println(attacker +"," + ability + ", " + target);
        return new ComputerChoice(attacker,ability,target);
    }

    public int getDelay() {
        return delay;
    }

    public boolean isReady() {
        return ready;
    }


}
