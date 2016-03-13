package game;

import gfx.Sprite;

import java.util.ArrayList;

public abstract class CharacterGroup {
    protected ArrayList<Character> group;
	protected Factory factory;
	public void createGroup(int id1, int id2, int id3) {
		group.add(factory.createCharacter(id1));
		group.add(factory.createCharacter(id2));
		group.add(factory.createCharacter(id3));
	}
	
	public Character getCharacter(int id){
		return group.get(id);
	}


    public void center() {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).getSprite().setX(Game.WIDTH * Game.SCALE / 2 - 30);
            group.get(i).getSprite().setY(Game.HEIGHT * Game.SCALE/2 - 80);
        }
    }

	public boolean isAlive(){
		for(Character c: group)
			if(c.isAlive()) return true;
		return false;
	}
	
    public int getX() {
        return group.get(0).getSprite().getX();
    }
    public int getY() {
        return group.get(0).getSprite().getY();
    }

    public Sprite getMainSprite() {
        return group.get(0).getSprite();
    }
    public ArrayList<Character> getGroup() {
        return group;
    }

}
