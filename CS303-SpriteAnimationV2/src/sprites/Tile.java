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

}
