package game;


import gfx.Sprite;

public abstract class Boss extends Monster implements Observer{

    public Boss(BaseStats stats, Sprite sprite) {
        super(stats, sprite);
    }

    public abstract int dealFirstAttack();
    public abstract int dealSecondAttack();
    public abstract int dealThirdAttack();

    public abstract String[] getAbilityList();

    @Override
    public boolean update(int herox, int heroy, int xoffset, int yoffset) {
        int x_rel = this.getSprite().getX() * Game.SCALE - xoffset;
        int y_rel = this.getSprite().getX() * Game.SCALE - yoffset;

        double dist = Game.calculateDistance(herox - x_rel, heroy - y_rel);
        if(Math.abs(dist) < 500){
            return true;
        }
        return false;
    }

}
