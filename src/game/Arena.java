package game;

public class Arena {

    private HeroGroup heroes;
	private MobGroup mobs;
	private Boss boss;
	
	private boolean heroTurn;
	
	public Arena(HeroGroup heroes, MobGroup mobs){
		this.heroes=heroes;
		this.mobs = mobs;
		this.boss = null;

        heroTurn = Math.random() > .5;
	}
	
	public Arena(HeroGroup heroes, Boss boss){
		this.heroes = heroes;
		this.mobs = null;
		this.boss = boss;
		
		heroTurn = Math.random() > .5;

	}
	
	
	public int dealDamage(int id, int attack, int id2){

			if(heroTurn) {
				if(mobs!=null) {
                    receiveDamage(mobs.getCharacter(id2),heroes.getCharacter(id).dealDamage(attack));
                } else {
                    receiveDamage(boss,heroes.getCharacter(id).dealDamage(attack));
                }
			} else {
				if(mobs!=null) {
                    receiveDamage(heroes.getCharacter(id2),mobs.getCharacter(id).dealDamage(attack));
                } else {
                    receiveDamage(heroes.getCharacter(id2),boss.dealDamage(attack));
                }
			}
			
			heroTurn = !heroTurn;
			return isWinner();

	}
	
	private void receiveDamage(Character c, int dmg){
		c.takeDamage(dmg);
	}
	
	private int isWinner(){
		if(heroes.isAlive() && ((mobs != null && mobs.isAlive()) || (boss!=null && boss.isAlive()))) {
            return 0;
        }else if(heroes.isAlive()) {
            return 1;
        }else {
            return 2;
        }
	}

    public HeroGroup getHeroes() {
        return heroes;
    }

    public MobGroup getMobs() {
        return mobs;
    }

    public Boss getBoss() {
        return boss;
    }

    public boolean isHeroTurn() {
        return heroTurn;
    }



}