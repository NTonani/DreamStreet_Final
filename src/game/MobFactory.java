package game;

public class MobFactory extends Factory{

	@Override
	public Character createCharacter(int id) {

		switch(id)
		{
			case 1: return new Brute();

			case 2: return new Gauntlet();

			case 3: return new Warlock();

            case 4: return new Spider();

            case 5: return new Skeleton();

            case 6: return new NoFace();
			
		}
		return null;
	}
	
}
