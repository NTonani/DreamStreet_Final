package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class GIJoe extends Hero {

	public GIJoe() {
		super(new BaseStats(160,.8,"GI Joe"), new Sprite(SpriteFactory.getSprite(4), Game.WIDTH * Game.SCALE / 2 - 30, Game.HEIGHT * Game.SCALE/2 - 80, Game.SCALE));
	}

	@Override
	public int dealFirstAttack() {
		return 10 + this.weapon.getDmg()*8;
	}

	@Override
	public int dealSecondAttack() {
		return 17 + this.weapon.getDmg()*6;
	}

	@Override
	public int dealThirdAttack() {
		return 0;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[2];
		temp[0] = "Shoot";
		temp[1] = "Bazooka";
		return temp;
	}


}
