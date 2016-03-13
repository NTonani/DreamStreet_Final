package gfx;

import game.Game;

import java.awt.*;
import java.util.ArrayList;

public class LevelManager {

    private ArrayList<Level> levels = new ArrayList<>();
    private int current_level = 0;

    public LevelManager() {
        Textures.init_textures();
        init_levels();
        connect_doors();
    }

    public void init_levels() {
        levels.add(new Level("overworld_"));
        levels.add(new Level("level1_"));
        levels.add(new Level("level2_"));
        levels.add(new Level("level3_"));
        levels.add(new Level("level4_"));
        levels.add(new Level("level5_"));
        levels.add(new Level("level6_"));

    }

    public void connect_doors() {
        for (int i = 0; i < levels.size(); i++) {
            ArrayList<DoorTile> doors = levels.get(i).getMap().getDoors();

            for (int j = 0; j < doors.size(); j++) {
                DoorTile from = doors.get(j);
                int tolvl = from.getTo_level();

                ArrayList<DoorTile> todoors = levels.get(tolvl).getMap().getDoors();

                for (int k = 0; k < todoors.size(); k++) {
                    DoorTile to = todoors.get(k);
                    int fromlvl = to.getTo_level();

                    if (fromlvl == i) {
                        from.setTo(to);
                        to.setTo(from);
                    }
                }
            }
        }
    }

    public int getCurrent_level() {
        return current_level;
    }

    public void setCurrent_level(int current_level) {
        this.current_level = current_level;
    }

    public TileMap getTileMap() {
        return levels.get(current_level).getMap();
    }

    public ObjectMap getObjects() {
        return levels.get(current_level).getObjects();
    }

    public void setMapDx(int dx) {
        levels.get(current_level).getMap().setDx(dx);
    }

    public void setMapDy(int dy) {
        levels.get(current_level).getMap().setDy(dy);
    }

    public boolean collision(int x, int y, int width, int height) {
        ArrayList<Tile> tiles = getTileMap().getTiles();
        for (int i = 0; i < tiles.size(); i++) {
            Tile check = tiles.get(i);
            if (!check.walkable) {
                int x_rel = check.x * Game.SCALE - getTileMap().getxOffSet();
                int y_rel = check.y * Game.SCALE - getTileMap().getyOffSet();
                if (x_rel - x < 100 && y_rel - y < 100) {
                    if (((x < x_rel + check.width * Game.SCALE & x > x_rel) || (x + width > x_rel & x + width < x_rel + check.width * Game.SCALE)) &
                            ((y < y_rel + check.height * Game.SCALE & y > y_rel) || (y + height > y_rel & y + height < y_rel + check.height * Game.SCALE) || (y + height > y_rel + check.height * Game.SCALE & y < y_rel))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void tick() {
        levels.get(current_level).getMap().tick();
        levels.get(current_level).getObjects().tick();
    }

    public void draw(Graphics g, int scale) {
        levels.get(current_level).getMap().draw(g,scale);
        levels.get(current_level).getObjects().draw(g);
    }

}
