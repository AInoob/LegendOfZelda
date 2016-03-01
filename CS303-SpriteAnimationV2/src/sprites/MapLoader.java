package sprites;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapLoader
{
    private static SpriteLoader loader;
    static {
        try {
            loader=new SpriteLoader("res/overworld.png", 16, 1, 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Constants
    static final BufferedImage BROWN_STAIRS=loader.getSprite(0, 0);
    static final BufferedImage BLANK=loader.getSprite(0, 2);
    static final BufferedImage GREEN_TREE=loader.getSprite(1, 7);
    static final BufferedImage GRAVE=loader.getSprite(1, 13);
    static final BufferedImage BROWN_TREE=loader.getSprite(1, 1);

    
    //More constants!
    static final BufferedImage TOP_LEFT_WATER = loader.getSprite(0,4);
    static final BufferedImage TOP_CENTER_WATER = loader.getSprite(1,4);
    static final BufferedImage TOP_RIGHT_WATER = loader.getSprite(2,4);
    static final BufferedImage MIDDLE_LEFT_WATER = loader.getSprite(0,5);
    static final BufferedImage MIDDLE_CENTER_WATER = loader.getSprite(1,5);
    static final BufferedImage MIDDLE_RIGHT_WATER = loader.getSprite(2,5);
    static final BufferedImage BOTTOM_LEFT_WATER = loader.getSprite(0,6);
    static final BufferedImage BOTTOM_CENTER_WATER = loader.getSprite(1,6);
    static final BufferedImage BOTTOM_RIGHT_WATER = loader.getSprite(2,6);
    
    private MapLoader() {
        // private constructor
    }
}
