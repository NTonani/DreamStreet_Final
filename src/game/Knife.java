package game;

public class Knife implements Weapon{

	@Override
	public int getDmg() {
		return 10;
	}

	@Override
	public String getName() {
		return "Knife";
	}

}
