package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Spider extends Boss{

	public Spider() {
		super(new BaseStats(250,.2,"Spider"), new Sprite(SpriteFactory.getSprite(3), Game.SCALE*2));
		// TODO Auto-generated constructor stub
	}

	@Override
	public int dealFirstAttack() {
		return 36;
	}

	@Override
	public int dealSecondAttack() {
		return 48;
	}

	@Override
	public int dealThirdAttack() {
		return 51;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[3];
		temp[0] = "HELP";
		temp[1] = "HELP";
		temp[2] = "Inject";
		return temp;
	}
}
