package game;

public class Sword implements Weapon{

	@Override
	public int getDmg() {
		return 16;
	}

	@Override
	public String getName() {
		return "Sword";
	}

}
