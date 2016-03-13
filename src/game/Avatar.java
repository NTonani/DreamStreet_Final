package game;

import gfx.Sprite;
import gfx.SpriteFactory;

public class Avatar extends Hero{


	public Avatar() {
        super(new BaseStats(150,.6,"Avatar"), new Sprite(SpriteFactory.getSprite(2), Game.SCALE));
	}

	@Override
	public int dealFirstAttack() {
		return 70 + (this.weapon.getDmg()* 2);
	}

	@Override
	public int dealSecondAttack() {
		return 50 + (this.weapon.getDmg()*5);
	}

	@Override
	public int dealThirdAttack() {
		return 0;
	}

	@Override
	public String[] getAbilityList() {
		String[] temp = new String[2];
		temp[0] = "Spirit Wave";
		temp[1] = "Fireball";
		return temp;
	}
}
