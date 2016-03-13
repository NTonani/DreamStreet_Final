package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Bear extends Hero{


	public Bear() {
        super(new BaseStats(120,.7,"Bear"), new Sprite(SpriteFactory.getSprite(0), Game.SCALE));
	}

	@Override
	public int dealFirstAttack() {
		return 50 + this.weapon.getDmg();
	}

	@Override
	public int dealSecondAttack() {
		return 40 + (this.weapon.getDmg()*3);
	}

	@Override
	public int dealThirdAttack() {
		return 0;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[2];
		temp[0] = "Punch";
		temp[1] = "Kick";
		return temp;
	}
}
