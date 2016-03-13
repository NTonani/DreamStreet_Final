package game;

import gfx.*;

public abstract class Character implements Ability{

    protected BaseStats stats;
	protected Sprite sprite;
	
	public Character(BaseStats stats, Sprite sprite){
		this.stats = stats;
		this.sprite = sprite;
	}
	
	public abstract int dealFirstAttack();
	public abstract int dealSecondAttack();
	public abstract int dealThirdAttack();
	
	public abstract String[] getAbilityList();
	
	public int dealDamage(int attack){
		
		switch(attack){
			case 0: return dealFirstAttack();
				
			case 1: return dealSecondAttack();
				
			case 2: return dealThirdAttack();

		}
		return 0;
	}
	
	public void takeDamage(int dmg){
		this.stats.removeHitPoints(dmg);
	}
	
	public boolean isAlive(){
		return stats.isAlive();
	}
	
	public Sprite getSprite(){
		return this.sprite;
	}
    public BaseStats getStats() {
        return stats;
    }

    public String getName() {
        return stats.getName();
    }
}