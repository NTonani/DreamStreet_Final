package game;

public class MediumInventory extends Inventory{

	public MediumInventory() {
		super(30);
	}

	@Override
	public String getSize() {
		return "Medium inventory";
	}

}
