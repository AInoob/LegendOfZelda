package sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class LinkLoader
{
    private static SpriteLoader loader;
    static {
        try {
            loader=new SpriteLoader("res/link.png", 16, 13, 13);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // constants
    public static final BufferedImage GREEN_DOWN1=loader.getSprite(0, 0);
    public static final BufferedImage GREEN_DOWN2=loader.getSprite(1, 0);
    public static final BufferedImage GREEN_LEFT1=loader.getSprite(0, 1);
    public static final BufferedImage GREEN_LEFT2=loader.getSprite(1, 1);
    public static final BufferedImage GREEN_UP1=loader.loadFromLocation(59, 0, 16, 16);
    public static final BufferedImage GREEN_UP2=loader.loadFromLocation(60, 30, 16, 16);
    public static final BufferedImage GREEN_RIGHT1=loader.loadFromLocation(90, 0, 16, 16);
    public static final BufferedImage GREEN_RIGHT2=loader.loadFromLocation(89, 30, 16, 16);
    
    public static final BufferedImage GREEN_ATTACK_DOWN=loader.getSprite(2, 0);
    public static final BufferedImage GREEN_ATTACK_LEFT=loader.getSprite(2, 1);
    public static final BufferedImage GREEN_ATTACK_UP=loader.getSprite(2, 2);
    public static final BufferedImage GREEN_ATTACK_RIHT=loader.getSprite(2, 3);
    
    public static final BufferedImage GREEN_SWORD_DOWN=loader.loadFromLocation(0, 84, 16, 110-84+1);
    public static final BufferedImage GREEN_SWORD_LEFT=loader.loadFromLocation(24, 90, 50-24, 104-90);
    public static final BufferedImage GREEN_SWORD_UP=loader.loadFromLocation(58, 84, 15, 111-84);
    public static final BufferedImage GREEN_SWORD_RIGHT=loader.loadFromLocation(84, 90, 110-84+1, 104-90);
    
    
    
    public static SpriteLoader getLoader() {
        return loader;
    }
    
    
    
    private LinkLoader() {
        // private constructor so that this class only has static variables
    }

}
