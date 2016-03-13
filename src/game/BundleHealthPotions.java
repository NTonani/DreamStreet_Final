package game;

import java.util.ArrayList;

public class BundleHealthPotions extends HealthPotion{
	ArrayList<HealthPotion> potions;

	//generates 0-3 health pots on bundle spawn
	private void generatePotions() {
		int numPotions = (int)(Math.random() * 3);
		for(int i = 0;i<numPotions;i++){
			if(Math.random() > .7) potions.add(new LargeHealthPotion());
			else potions.add(new SmallHealthPotion());
		}
	}
	
	public void addHealthPotion(HealthPotion hp){
		potions.add(hp);
	}
	
	@Override
	public int getWeight(){
		int total = 0;
		for(HealthPotion hp: potions)
			total+=hp.getWeight();
		return total;
	}

	@Override
	public String getName() {
		return "Bundle of health potions";
	}

    public int getSize(){
        return potions.size();
    }

    public HealthPotion getPotion(){
        HealthPotion temp = potions.get(0);
        potions.remove(0);
        return temp;

    }
    @Override
    public int use() {
        return 0;
    }
}
