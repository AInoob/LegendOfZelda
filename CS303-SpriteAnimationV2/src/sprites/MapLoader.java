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

    
    //Brown water constants
    static final BufferedImage BROWN_TOP_LEFT_WATER = loader.getSprite(4,0);
    static final BufferedImage BROWN_TOP_CENTER_WATER = loader.getSprite(4,1);
    static final BufferedImage BROWN_TOP_RIGHT_WATER = loader.getSprite(4,2);
    static final BufferedImage BROWN_MIDDLE_LEFT_WATER = loader.getSprite(5,0);
    static final BufferedImage BROWN_MIDDLE_CENTER_WATER = loader.getSprite(5,1);
    static final BufferedImage BROWN_MIDDLE_RIGHT_WATER = loader.getSprite(5,2);
    static final BufferedImage BROWN_BOTTOM_LEFT_WATER = loader.getSprite(6,0);
    static final BufferedImage BROWN_BOTTOM_CENTER_WATER = loader.getSprite(6,1);
    static final BufferedImage BROWN_BOTTOM_RIGHT_WATER = loader.getSprite(6,2);
    
    //Brown Rock wall constants
    static final BufferedImage BROWN_TOP_LEFT_ROCK_WALL = loader.getSprite(2, 0);
    static final BufferedImage BROWN_TOP_CENTER_ROCK_WALL = loader.getSprite(2, 1);
    static final BufferedImage BROWN_TOP_RIGHT_ROCK_WALL = loader.getSprite(2, 2);
    static final BufferedImage BROWN_BOTTOM_LEFT_ROCK_WALL = loader.getSprite(3, 0);
    static final BufferedImage BROWN_BOTTOM_CENTER_ROCK_WALL = loader.getSprite(3, 1);
    static final BufferedImage BROWN_BOTTOM_RIGHT_ROCK_WALL = loader.getSprite(3, 2);
    
    //Green water constants
    static final BufferedImage GREEN_TOP_LEFT_WATER = loader.getSprite(4,6);
    static final BufferedImage GREEN_TOP_CENTER_WATER = loader.getSprite(4,7);
    static final BufferedImage GREEN_TOP_RIGHT_WATER = loader.getSprite(4,8);
    static final BufferedImage GREEN_MIDDLE_LEFT_WATER = loader.getSprite(5,6);
    static final BufferedImage GREEN_MIDDLE_CENTER_WATER = loader.getSprite(5,7);
    static final BufferedImage GREEN_MIDDLE_RIGHT_WATER = loader.getSprite(5,8);
    static final BufferedImage GREEN_BOTTOM_LEFT_WATER = loader.getSprite(6,6);
    static final BufferedImage GREEN_BOTTOM_CENTER_WATER = loader.getSprite(6,7);
    static final BufferedImage GREEN_BOTTOM_RIGHT_WATER = loader.getSprite(6,8);
    
    //Green rock wall constants
    static final BufferedImage GREEN_TOP_LEFT_ROCK_WALL = loader.getSprite(2, 6);
    static final BufferedImage GREEN_TOP_CENTER_ROCK_WALL = loader.getSprite(2, 7);
    static final BufferedImage GREEN_TOP_RIGHT_ROCK_WALL = loader.getSprite(2, 8);
    static final BufferedImage GREEN_BOTTOM_LEFT_ROCK_WALL = loader.getSprite(3, 6);
    static final BufferedImage GREEN_BOTTOM_CENTER_ROCK_WALL = loader.getSprite(3, 7);
    static final BufferedImage GREEN_BOTTOM_RIGHT_ROCK_WALL = loader.getSprite(3, 8);
    
    //Random constants
    static final BufferedImage BROWN_SINGLE_ROCK = loader.getSprite(0, 1);
    static final BufferedImage GREEN_SINGLE_ROCK = loader.getSprite(0,7);
    static final BufferedImage BROWN_CAVE_ENTRANCE = loader.getSprite(1,4);
    static final BufferedImage GREEN_CAVE_ENTRANCE = loader.getSprite(1,10);
    
    private MapLoader() {
        // private constructor
    }
}
