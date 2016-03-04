package sprites;
import java.awt.image.BufferedImage;

public class Tile
{
    private BufferedImage image;
    // add other meta information about a tile
    private boolean isPassable;
    
    private Tile(BufferedImage image,boolean passable) {
        this.image=image;
        this.isPassable=passable;
    }
    public static int imageLength(){
    	return GREEN_TREE.getImage().getWidth();
    }
    public BufferedImage getImage() {
        return image;
    }
    
    public boolean isPassable(){
    	return this.isPassable;
    }
    
    public static final Tile GREEN_TREE=new Tile(MapLoader.GREEN_TREE,false);
    public static final Tile BROWN_TREE=new Tile(MapLoader.BROWN_TREE,false);
    public static final Tile GRAVE=new Tile(MapLoader.GRAVE,false);
    public static final Tile BROWN_STAIRS=new Tile(MapLoader.BROWN_STAIRS,true);
    public static final Tile BLANK=new Tile(MapLoader.BLANK,true);
    
    // Brown water tile constants
    public static final Tile BROWN_TOP_LEFT_WATER = new Tile(MapLoader.BROWN_TOP_LEFT_WATER,false);
    public static final Tile BROWN_TOP_CENTER_WATER = new Tile(MapLoader.BROWN_TOP_CENTER_WATER,false);
    public static final Tile BROWN_TOP_RIGHT_WATER = new Tile(MapLoader.BROWN_TOP_RIGHT_WATER,false);
    public static final Tile BROWN_MIDDLE_LEFT_WATER = new Tile(MapLoader.BROWN_MIDDLE_LEFT_WATER,false);
    public static final Tile BROWN_MIDDLE_CENTER_WATER = new Tile(MapLoader.BROWN_MIDDLE_CENTER_WATER,false);
    public static final Tile BROWN_MIDDLE_RIGHT_WATER = new Tile(MapLoader.BROWN_MIDDLE_RIGHT_WATER,false);
    public static final Tile BROWN_BOTTOM_LEFT_WATER = new Tile(MapLoader.BROWN_BOTTOM_LEFT_WATER,false);
    public static final Tile BROWN_BOTTOM_CENTER_WATER = new Tile(MapLoader.BROWN_BOTTOM_CENTER_WATER,false);
    public static final Tile BROWN_BOTTOM_RIGHT_WATER = new Tile(MapLoader.BROWN_BOTTOM_RIGHT_WATER,false);
    
    // Brown rock wall tile constants
    public static final Tile BROWN_TOP_LEFT_ROCK_WALL = new Tile(MapLoader.BROWN_TOP_LEFT_ROCK_WALL,false);
    public static final Tile BROWN_TOP_CENTER_ROCK_WALL = new Tile(MapLoader.BROWN_TOP_CENTER_ROCK_WALL,false);
    public static final Tile BROWN_TOP_RIGHT_ROCK_WALL = new Tile(MapLoader.BROWN_TOP_RIGHT_ROCK_WALL,false);
    public static final Tile BROWN_BOTTOM_LEFT_ROCK_WALL = new Tile(MapLoader.BROWN_BOTTOM_LEFT_ROCK_WALL,false);
    public static final Tile BROWN_BOTTOM_CENTER_ROCK_WALL = new Tile(MapLoader.BROWN_BOTTOM_CENTER_ROCK_WALL,false);
    public static final Tile BROWN_BOTTOM_RIGHT_ROCK_WALL = new Tile(MapLoader.BROWN_BOTTOM_RIGHT_ROCK_WALL,false);
    
    // Green water tile constants
    public static final Tile GREEN_TOP_LEFT_WATER = new Tile(MapLoader.GREEN_TOP_LEFT_WATER,false);
    public static final Tile GREEN_TOP_CENTER_WATER = new Tile(MapLoader.GREEN_TOP_CENTER_WATER,false);
    public static final Tile GREEN_TOP_RIGHT_WATER = new Tile(MapLoader.GREEN_TOP_RIGHT_WATER,false);
    public static final Tile GREEN_MIDDLE_LEFT_WATER = new Tile(MapLoader.GREEN_MIDDLE_LEFT_WATER,false);
    public static final Tile GREEN_MIDDLE_CENTER_WATER = new Tile(MapLoader.GREEN_MIDDLE_CENTER_WATER,false);
    public static final Tile GREEN_MIDDLE_RIGHT_WATER = new Tile(MapLoader.GREEN_MIDDLE_RIGHT_WATER,false);
    public static final Tile GREEN_BOTTOM_LEFT_WATER = new Tile(MapLoader.GREEN_BOTTOM_LEFT_WATER,false);
    public static final Tile GREEN_BOTTOM_CENTER_WATER = new Tile(MapLoader.GREEN_BOTTOM_CENTER_WATER,false);
    public static final Tile GREEN_BOTTOM_RIGHT_WATER = new Tile(MapLoader.GREEN_BOTTOM_RIGHT_WATER,false);

    //Green rock wall tile constants
    public static final Tile GREEN_TOP_LEFT_ROCK_WALL = new Tile(MapLoader.GREEN_TOP_LEFT_ROCK_WALL,false);
    public static final Tile GREEN_TOP_CENTER_ROCK_WALL = new Tile(MapLoader.GREEN_TOP_CENTER_ROCK_WALL,false);
    public static final Tile GREEN_TOP_RIGHT_ROCK_WALL = new Tile(MapLoader.GREEN_TOP_RIGHT_ROCK_WALL,false);
    public static final Tile GREEN_BOTTOM_LEFT_ROCK_WALL = new Tile(MapLoader.GREEN_BOTTOM_LEFT_ROCK_WALL,false);
    public static final Tile GREEN_BOTTOM_CENTER_ROCK_WALL = new Tile(MapLoader.GREEN_BOTTOM_CENTER_ROCK_WALL,false);
    public static final Tile GREEN_BOTTOM_RIGHT_ROCK_WALL = new Tile(MapLoader.GREEN_BOTTOM_RIGHT_ROCK_WALL,false);

    //Single Rock tiles constants
    public static final Tile BROWN_SINGLE_ROCK = new Tile(MapLoader.BROWN_SINGLE_ROCK,false);
    public static final Tile GREEN_SINGLE_ROCK = new Tile(MapLoader.GREEN_SINGLE_ROCK,false);
    
    //Cave entrance tile constants
    public static final Tile BROWN_CAVE_ENTRANCE = new Tile(MapLoader.BROWN_CAVE_ENTRANCE, true);
    public static final Tile GREEN_CAVE_ENTRANCE = new Tile(MapLoader.GREEN_CAVE_ENTRANCE, true);
}
