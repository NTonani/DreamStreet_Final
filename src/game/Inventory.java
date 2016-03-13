package game;

import java.util.ArrayList;

public abstract class Inventory {
	
	private int MAX_WEIGHT;
	
	private ArrayList<Item> items;
	private int weight;
	
	public Inventory(int MAX_WEIGHT){
		this.MAX_WEIGHT = MAX_WEIGHT;
		this.items = new ArrayList<Item>();
		weight = 0;
	}
	
	public boolean add(Item item) {
		if(!isFull()){
			items.add(item);
			weight+=item.getWeight();
			return true;
		}
		return false;
	}
	
	public boolean isFull(){
		return weight>=MAX_WEIGHT;
	}
	
	public int getMaxWeight(){return this.MAX_WEIGHT;}
	
	public boolean transfer(Inventory inv){
		if(weight>inv.getMaxWeight()){
			for(Item item: items)
				inv.add(item);
			
			return true;
		}
		
		return false;
	}
	
	public abstract String getSize();

    public HealthPotion usePotion(){
        for(Item i : items){
            if(i.getName().contains("potion")){
                if(i.getName().contains("Bundle")){
                    BundleHealthPotions potion = (BundleHealthPotions)i;
                    if(potion.getSize() > 0 )
                        items.remove(i);
                        return potion.getPotion();
                }
                else{
                    HealthPotion temp = (HealthPotion)i;
                    items.remove(i);
                    return temp;
                }
            }
        }
        return null;
    }

}
