package gfx;


public class DoorTile extends Tile{

    private boolean locked;


    private DoorTile to;
    private int to_level;

    public DoorTile(int x, int y, int width, int height, boolean walkable, int to_level) {
        super(x,y,width,height,walkable);
        this.locked = false;
        this.to_level = to_level;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setTo(DoorTile to) {
        this.to = to;
    }

    public DoorTile getTo() {
        return to;
    }

    public int getTo_level() {
        return to_level;
    }



}
