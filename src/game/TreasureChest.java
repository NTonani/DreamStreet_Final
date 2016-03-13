package game;

import gfx.Sprite;

import java.util.ArrayList;
import java.util.Scanner;

public class TreasureChest {

    private ArrayList<Item> loot;
    private ArrayList<Weapon> weapons;

    private Sprite sprite;
    private HeroGroup heroes;
    private int size;
    public TreasureChest(Sprite sprite){
        this.sprite = sprite;
        this.size = 0;
        loot = new ArrayList<Item>();
        weapons = new ArrayList<Weapon>();
        spawnItems();
    }

    private void spawnItems(){
        int numItems = (int)(Math.random() * 4);
        this.size = numItems;
        for(int i = 0; i<numItems;i++){
            if(Math.random() > .75) weapons.add(spawnWeapon());
            else loot.add(spawnItem());
        }
    }

    public Item spawnItem(){
        double temp = Math.random();
        if(temp>=.50) return new LargeHealthPotion();
        else return new SmallHealthPotion();
    }

    public Weapon spawnWeapon(){
        double temp = Math.random();
        if(temp>=.80)return new Axe();
        else if(temp>=.50) return new Sword();
        else return new Knife();
    }


    public void getLoot(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select loot:");
        int j = 0;
        for(Item i: loot) System.out.println((j+1) + ". " + (i.getName()));
        for(Weapon w: weapons) System.out.println((j+1) + ". " + (w.getName()));
        System.out.println((j+1)+". None");
        int input = sc.nextInt();
        do{
            if(isItem(input)){
                if(LootManager.addItem(heroes, getItem(input)))
                    System.out.println("Success");
                else
                    System.out.println("Inventory is full");
            }else if(isWeapon(input)){
                if(LootManager.addWeapon(heroes,getWeapon(input)))
                    System.out.println("Success");
                else
                    System.out.println("Couldn't add weapon");
            }

        }while(input!=j+1);
    }

    public Weapon getWeapon(int id){
        if(id >= loot.size()){
            Weapon temp = weapons.get(id-(loot.size() - 1));
            weapons.remove(id-(loot.size() -1));

            size--;
            return temp;

        }
        return null;
    }

    public Item getItem(int id){
        if(id<loot.size()){
            Item temp = loot.get(id);
            loot.remove(id);
            size--;
            return temp;
        }
        return null;
    }

    public boolean isItem(int id){
        return id<loot.size();
    }

    public boolean isWeapon(int id){
        return id>=weapons.size();
    }

    public Sprite getSprite() {
        return sprite;
    }

}