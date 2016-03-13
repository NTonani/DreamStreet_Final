package gfx;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

	private int x;
    private int y;

    private BufferedImage img;

    private int scale;
	
	public Sprite(BufferedImage img, int x, int y, int scale){
        this.img = img;
        this.setX(x);
		this.setY(y);
        this.scale = scale;
	}
	
	public Sprite(BufferedImage img, int scale){
		this.img = img;
		this.setX(0);
		this.setY(0);
        this.scale = scale;
    }


	public void draw(Graphics g, int xOffSet, int yOffSet){
		g.drawImage(img, this.x * Game.SCALE - xOffSet , this.y * Game.SCALE - yOffSet,32*scale,32*scale, null);
		g.setColor(Color.white);
	}

    public void draw(Graphics g){
        g.drawImage(img, this.x, this.y,32*scale,32*scale, null);
        g.setColor(Color.white);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


    public BufferedImage getImg() {
        return img;
    }

    public int getScale() {
        return scale;
    }



}
