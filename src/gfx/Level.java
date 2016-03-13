package gfx;

public class Level {

    private TileMap map;
    private ObjectMap objects;

    public Level(String level) {
        map = new TileMap("res/levels/" + level + "map.txt");
        objects = new ObjectMap("res/levels/" + level + "objects.txt");
    }


    public TileMap getMap() {
        return map;
    }
    public ObjectMap getObjects() { return objects; }


}
