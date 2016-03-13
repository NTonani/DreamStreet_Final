package game;

import java.util.Scanner;

public class LootManager {

    public static boolean addItem(HeroGroup group, Item item){
        if(item!=null){
            int hero = heroSelection(group);
            if(((Hero)group.getCharacter(hero-1)).addItem(item))
                return true;
        }
        return false;
    }

    public static boolean addWeapon(HeroGroup group, Weapon weapon){
        try{
            if(weapon!=null){
                int hero = heroSelection(group);
                ((Hero)group.getCharacter(hero-1)).setWeapon(weapon);
                return true;
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static int heroSelection(HeroGroup group){
        Scanner sc = new Scanner(System.in);

        System.out.println("To which hero: ");
        for(int i =0;i<group.getSize();i++){
            System.out.println((i+1) + ". " + group.getCharacter(i).getName());
        }

        int input = sc.nextInt();
        return input;
    }
}