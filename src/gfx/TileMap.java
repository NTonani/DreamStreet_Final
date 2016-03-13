package gfx;

import game.Game;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class TileMap {


    private ArrayList<Tile> tiles;
	private ArrayList<Image> tileImg;
	private int mapSize;

    private ArrayList<DoorTile> doors;

    private int xOffSet = 700;
    private int yOffSet = 500;

    private int dx = 0;
    private int dy = 0;


	public TileMap(String path){
		tiles = new ArrayList<>();
		tileImg = new ArrayList<>();
        doors = new ArrayList<>();
		loadMap(path);
	}
	
	private void loadMap(String path){
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String curLine;
			
			int row = 0;
			while((curLine = br.readLine())!=null){
				if(curLine.isEmpty())
					continue;
				
				String[] values = curLine.trim().split(" ");
				int col = 0;
				for(String val : values){
					if(!val.isEmpty()) {
						int id = Integer.parseInt(val);
						if(id == 1){
							tileImg.add(Textures.TILE_GRASS);
							tiles.add(new Tile(col*32,row*32,32,32, true));
						}else if(id == 2){
							tileImg.add(Textures.TILE_STONE);
							tiles.add(new Tile(col*32,row*32,32,32, true));
						}else if(id == 3){
							tileImg.add(Textures.TILE_WATER);
							tiles.add(new Tile(col*32,row*32,32,32, false));
                        }else if(id == 4){
                            tileImg.add(Textures.TILE_DEAD_GRASS);
                            tiles.add(new Tile(col*32,row*32,32,32, true));
                        }else if(id == 5){
                            tileImg.add(Textures.TILE_WALL);
                            tiles.add(new Tile(col*32,row*32,32,32, false));
                        }else if(id == 6){
                            tileImg.add(Textures.TILE_CHEST);
                            tiles.add(new Tile(col*32,row*32,32,32, true));
                        }else if(id > 99){
                            tileImg.add(Textures.TILE_DOOR);
                            tiles.add(new Tile(col*32,row*32,32,32, true));
                            doors.add(new DoorTile(col*32,row*32,32,32,true, id % 100));
                        }
						col++;
					}
				}
				mapSize = col;
				row++;
			}
			mapSize *= row;
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

    public void tick() {
        this.xOffSet += dx;
        this.yOffSet += dy;
    }

	public void draw(Graphics g,int scale){
		for(int i = 0; i < mapSize; i++) {
			// only draws the tile if its in the bounds of the screen
			if (tiles.get(i).x*scale-xOffSet > (0 - tiles.get(i).height*scale* 1.1)  &&  tiles.get(i).x*scale-xOffSet < (Game.WIDTH*scale * 1.1)
			&& tiles.get(i).y*scale-yOffSet > (0 - tiles.get(i).width*scale * 1.1) && tiles.get(i).y*scale-yOffSet <  (Game.HEIGHT * scale * 1.1))
			{
				g.drawImage(tileImg.get(i),tiles.get(i).x*scale-xOffSet,tiles.get(i).y*scale-yOffSet,tiles.get(i).height*scale,tiles.get(i).width*scale,null);
			}

		}
	}

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }


    public int getxOffSet() {
        return xOffSet;
    }

    public int getyOffSet() {
        return yOffSet;
    }

    public void setxOffSet(int xOffSet) {
        this.xOffSet = xOffSet;
    }

    public void setyOffSet(int yOffSet) {
        this.yOffSet = yOffSet;
    }

    public ArrayList<DoorTile> getDoors() {
        return doors;
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }




}
