package game;

public class SmallHealthPotion extends HealthPotion {

	@Override
	public int getWeight() {
		return 3;
	}

	@Override
	public String getName() {
		return "Small health potion";
	}

    @Override
    public int use() {
        return 30;
    }


}
