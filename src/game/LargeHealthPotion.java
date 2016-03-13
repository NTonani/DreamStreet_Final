package game;

public class LargeHealthPotion extends HealthPotion {

	@Override
	public int getWeight() {
		return 7;
	}

	@Override
	public String getName() {
		return "Large health potion";
	}

    @Override
    public int use() {
        return 100;
    }


}
