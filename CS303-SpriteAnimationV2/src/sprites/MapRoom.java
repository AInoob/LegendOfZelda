package sprites;
import java.awt.Graphics;

public class MapRoom
{
    // grid of tiles for the map
    private Tile[][] tiles;
    private Tile[] squares=new Tile[] {Tile.GRAVE, Tile.BROWN_TREE, Tile.GREEN_TREE,Tile.BROWN_STAIRS,Tile.BLANK};
    
    public MapRoom(int[][]map) {
        tiles=new Tile[map.length][map[0].length];
        for (int i=0;i<tiles.length; i++) {
            for (int j=0; j<tiles[i].length; j++) {
                tiles[i][j]=squares[map[i][j]];
            }
        }
    }
    
    public boolean isPassable(int i,int j){
    	if(i<0||j<0||i>=tiles.length||j>=tiles[i].length)
    		return false;
//    	System.out.println("checking if it is passable"+i+" "+j);
    	return tiles[i][j].isPassable();
    }
    
    public void draw(Graphics g) {
        for (int i=0;i<tiles.length; i++) {
            for (int j=0; j<tiles[i].length; j++) {
                int y=j*Tile.imageLength();
                int x=i*Tile.imageLength();
                g.drawImage(tiles[i][j].getImage(), x, y, null);
            }
        }
    }

	public int getWidth() {
		return tiles.length*Tile.imageLength();
	}

	public int getHeight() {
		return tiles[0].length*Tile.imageLength();
	}

}
