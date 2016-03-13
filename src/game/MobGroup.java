package game;

import java.util.ArrayList;

public class MobGroup extends CharacterGroup implements Observer{

    public MobGroup(int id1, int id2, int id3){
        this.factory = new MobFactory();
        this.group = new ArrayList<Character>();
        createGroup(id1, id2, id3);
    }

    @Override
    public boolean update(int herox, int heroy, int xoffset, int yoffset) {
        int x_rel = this.getX() * Game.SCALE - xoffset;
        int y_rel = this.getY() * Game.SCALE - yoffset;

        double dist = Game.calculateDistance(herox - x_rel, heroy - y_rel);
        if(Math.abs(dist) < 150){
            return true;
        }
        return false;
    }


}
