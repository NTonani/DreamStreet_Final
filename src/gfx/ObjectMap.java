package gfx;

import game.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class ObjectMap {

    private ArrayList<MobGroup> mobs;
    private Boss boss = null;

    private ArrayList<TreasureChest> chests;
	private int mapSize;

    private int xOffSet = 700;
    private int yOffSet = 500;

    private int dx = 0;
    private int dy = 0;


	public ObjectMap(String path){
		mobs = new ArrayList<>();
        chests = new ArrayList<>();
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
                        if (id > 0) {
                            if (id < 10) {

                                if (id == 1) {
                                    mobs.add(new MobGroup(1, 1, 1));
                                } else if (id == 2) {
                                    mobs.add(new MobGroup(2, 2, 2));
                                } else if (id == 3) {
                                    mobs.add(new MobGroup(3, 3, 3));
                                } else if (id == 4) {
                                    mobs.add(new MobGroup(1, 2, 3));
                                } else if (id == 5) {
                                    mobs.add(new MobGroup(1, 1, 2));
                                } else if (id == 6) {
                                    mobs.add(new MobGroup(2, 2, 3));
                                } else if (id == 7) {
                                    mobs.add(new MobGroup(1, 1, 3));
                                } else if (id == 8) {
                                    mobs.add(new MobGroup(3, 3, 1));
                                } else if (id == 9) {
                                    mobs.add(new MobGroup(3, 3, 2));
                                }
                                mobs.get(mobs.size() - 1).getMainSprite().setX(col * 32);
                                mobs.get(mobs.size() - 1).getMainSprite().setY(row * 32);
                            }else if (id == 10) {
                                boss = new NoFace();
                                boss.getSprite().setX(col * 32);
                                boss.getSprite().setY(row * 32);

                            }else if (id == 11) {
                                boss = new Skeleton();
                                boss.getSprite().setX(col * 32);
                                boss.getSprite().setY(row * 32);
                            }else if (id == 12) {
                                boss = new Spider();
                                boss.getSprite().setX(col * 32);
                                boss.getSprite().setY(row * 32);
                            }else if (id == 100) {
                                chests.add(new TreasureChest(new Sprite(SpriteFactory.getSprite(9),col*32, row*32, Game.SCALE)));
                            }
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

	public void draw(Graphics g){
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).getMainSprite().draw(g, xOffSet, yOffSet);
		}
        if (boss != null) {
            boss.getSprite().draw(g, xOffSet, yOffSet);
        }
        for (int i = 0; i < chests.size(); i++) {
            chests.get(i).getSprite().draw(g, xOffSet, yOffSet);
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

    public void setOffSets(int x, int y) {
        this.xOffSet = x;
        this.yOffSet = y;
    }

    public ArrayList<MobGroup> getMobs() {
        return mobs;
    }

    public Boss getBoss() {
        return boss;
    }

    public ArrayList<TreasureChest> getChests() {
        return chests;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
