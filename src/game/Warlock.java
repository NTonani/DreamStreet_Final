package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Warlock extends Monster{

	public Warlock() {
		super(new BaseStats(50,.1,"Warlock"), new Sprite(SpriteFactory.getSprite(8), Game.SCALE));
		// TODO Auto-generated constructor stub
	}

	@Override
	public int dealFirstAttack() {
		return 26;
	}

	@Override
	public int dealSecondAttack() {
		return 38;
	}

	@Override
	public int dealThirdAttack() {
		return 0;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[2];
		temp[0] = "Shadow Bolt";
		temp[1] = "Drain";
		return temp;
	}
}
