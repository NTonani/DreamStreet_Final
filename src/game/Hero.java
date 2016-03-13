package game;

import gfx.Sprite;

public abstract class Hero extends Character {
	
	protected Weapon weapon;
	protected Inventory inventory;
	
	public Hero(BaseStats stats, Sprite sprite) {
		super(stats, sprite);
		this.inventory = new SmallInventory();
		this.weapon = new Fists();
	}
	
	public void setWeapon(Weapon weapon){this.weapon = weapon;}
	
	public Weapon getWeapon(){return this.weapon;}
	
	public boolean addItem(Item item){return this.inventory.add(item);}
	
	public boolean changeInventory(Inventory inv){
		boolean temp = inventory.transfer(inv);
		this.inventory = inv;
		return temp;
	}
	
	public abstract int dealFirstAttack();
	
	public abstract int dealSecondAttack();
	
	public abstract int dealThirdAttack();
	
	public abstract String[] getAbilityList();

    @Override
    public void takeDamage(int dmg){
        this.stats.removeHitPoints(dmg);
        if(this.stats.getHitPoints() < 70){
            HealthPotion temp = inventory.usePotion();
            if (temp != null) {
                stats.setHitPoints(stats.getHitPoints() + temp.use());
            }

        }
    }
	
}
