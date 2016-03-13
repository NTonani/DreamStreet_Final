package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Skeleton extends Boss{

	public Skeleton() {
		super(new BaseStats(200,.6,"Skeleton"), new Sprite(SpriteFactory.getSprite(4), Game.SCALE * 2));
	}

	@Override
	public int dealFirstAttack() {
		return 25;
	}

	@Override
	public int dealSecondAttack() {
		return 40;
	}

	@Override
	public int dealThirdAttack() {
		return 55;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[3];
		temp[0] = "HELP";
		temp[1] = "ME";
		temp[2] = "PLEASES";
		return temp;
	}
	
	

}
