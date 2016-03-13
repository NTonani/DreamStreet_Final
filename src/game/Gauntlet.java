package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Gauntlet extends Monster{

	public Gauntlet() {
		super(new BaseStats(35,.7,"Gauntlet"), new Sprite(SpriteFactory.getSprite(7), Game.SCALE*1));
		// TODO Auto-generated constructor stub
	}

	@Override
	public int dealFirstAttack() {
		return 25;
	}

	@Override
	public int dealSecondAttack() {
		return 35;
	}

	@Override
	public int dealThirdAttack() {
		return 0;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[2];
		temp[0] = "Demolish";
		temp[1] = "Rice Krispies!";
		return temp;
	}
}
