package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class NoFace extends Boss{

	public NoFace() {
		super(new BaseStats(300,.3,"NoFace"), new Sprite(SpriteFactory.getSprite(5), Game.SCALE*3));
	}

	@Override
	public int dealFirstAttack() {
		return 40;
	}

	@Override
	public int dealSecondAttack() {
		return 60;
	}

	@Override
	public int dealThirdAttack() {
		return 80;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[3];
		temp[0] = "Swipe";
		temp[1] = "Horrify";
		temp[2] = "Eat";
		return temp;
	}

}
