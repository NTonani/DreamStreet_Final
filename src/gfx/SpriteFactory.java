package gfx;

import game.Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteFactory {

    private static ArrayList<BufferedImage> images;

    public static BufferedImage getSprite(int id) {
        if (images == null) {
            images = new ArrayList<>();

            SpriteSheet spritesheet = new SpriteSheet("/sprites/bear_sprite.png");
            BufferedImage spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/spirit_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/avatar_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/spider_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/skeleton_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/noface_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/brute_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/gauntlet_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/warlock_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

            spritesheet = new SpriteSheet("/sprites/chest_sprite.png");
            spritechar = spritesheet.getSprite(0,0,32,32);
            images.add(spritechar);

        }
        return images.get(id);
    }
}
