package game;


public class HeroFactory extends Factory {

	@Override
	public Character createCharacter(int id) {
		switch(id)
		{
			case 1: return new Bear();

			case 2: return new Spirit();

			case 3: return new GIJoe();

            case 4: return new Avatar();
			

		}
		return null;
	}
	
}
