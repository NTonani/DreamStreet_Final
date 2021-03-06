package game;

import gfx.Sprite;

public abstract class Monster extends Character{


	public Monster(BaseStats stats, Sprite sprite) {
		super(stats, sprite);
	}
	
	public abstract int dealFirstAttack();
	
	public abstract int dealSecondAttack();
	public abstract int dealThirdAttack();
	
	public abstract String[] getAbilityList();

}
