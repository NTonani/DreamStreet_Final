package game;

public class LargeInventory extends Inventory{

	public LargeInventory() {
		super(40);
	}

	@Override
	public String getSize() {
		return "Large inventory";
	}

}
