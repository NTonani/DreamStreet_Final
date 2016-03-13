package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Spirit extends Hero {

	public Spirit() {
		super(new BaseStats(110,.3,"Spirit"), new Sprite(SpriteFactory.getSprite(1), Game.WIDTH * Game.SCALE / 2 - 30, Game.HEIGHT * Game.SCALE/2 - 80, Game.SCALE));
	}
	
	@Override
	public int dealFirstAttack() {
		return 100 + this.weapon.getDmg();
	}

	@Override
	public int dealSecondAttack() {
		return 30 + (this.weapon.getDmg()*5);
	}

	@Override
	public int dealThirdAttack() {
		return 0;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[2];
		temp[0] = "Gaze";
		temp[1] = "Curse";
		return temp;
	}

}
