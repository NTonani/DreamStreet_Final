package gfx;

import javax.swing.*;
import java.awt.*;

public class Textures {

    public static Image TILE_STONE,TILE_GRASS,TILE_WATER,TILE_DEAD_GRASS,TILE_WALL,TILE_CHEST,TILE_DOOR;
    private static SpriteSheet tilesheet = new SpriteSheet("/tiles/tile_sheet.png");

    public static void init_textures() {
        TILE_GRASS = new ImageIcon(tilesheet.getSprite(41,33,32,32)).getImage();//1
        TILE_STONE = new ImageIcon(tilesheet.getSprite(173,0,32,32)).getImage();//2
        TILE_WATER = new ImageIcon(tilesheet.getSprite(305,165,32,32)).getImage();//3
        TILE_DEAD_GRASS = new ImageIcon(tilesheet.getSprite(107,33,32,32)).getImage();//4
        TILE_WALL = new ImageIcon(tilesheet.getSprite(74,132,32,32)).getImage();//5

        TILE_CHEST = new ImageIcon(tilesheet.getSprite(107,231,32,32)).getImage();//6
        TILE_DOOR = new ImageIcon(tilesheet.getSprite(272,33,32,32)).getImage();//100+
    }

}
