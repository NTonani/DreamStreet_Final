package game;

import java.util.ArrayList;

public class HeroGroup extends CharacterGroup implements Observable{

    ArrayList<Observer> observers;

    public HeroGroup(int id1, int id2, int id3) {
        this.factory = new HeroFactory();
        this.group = new ArrayList<Character>();
        createGroup(id1, id2, id3);
        this.observers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void unregisterObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public int notifyObservers(int xoffset, int yoffset) {
        for(int i =0;i<observers.size();i++){
            if(observers.get(i).update(group.get(0).getSprite().getX(),group.get(0).getSprite().getY(),xoffset,yoffset))
                return i;
        }
        return -1;
    }

    @Override
    public void clearObservers(){
        this.observers.clear();
    }
    public int getSize() {
        return this.group.size();
    }


}
