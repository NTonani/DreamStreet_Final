package game;

public class SmallInventory extends Inventory{

	public SmallInventory() {
		super(20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSize() {
		return "Small inventory";
	}

}
