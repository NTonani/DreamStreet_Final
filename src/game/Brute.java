package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Brute extends Monster{

	public Brute() {
		super(new BaseStats(90,.05,"Brute"), new Sprite(SpriteFactory.getSprite(6), Game.SCALE));
		// TODO Auto-generated constructor stub
	}

	@Override
	public int dealFirstAttack() {
		return 15;
	}

	@Override
	public int dealSecondAttack() {
		return 20;
	}

	@Override
	public int dealThirdAttack() {
		return 0;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[2];
		temp[0] = "Bash";
		temp[1] = "Smash";
		return temp;
	}
}
