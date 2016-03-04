package sprites;
import java.awt.Graphics;

public class MapRoom
{
    // grid of tiles for the map
    private Tile[][] tiles;
    private Tile[] squares=new Tile[] {Tile.GRAVE, //0
    		Tile.BROWN_TREE, //1
    		Tile.GREEN_TREE, //2
    		Tile.BROWN_STAIRS, //3
    		Tile.BLANK, //4
    		Tile.BROWN_TOP_LEFT_WATER, //5
    		Tile.BROWN_TOP_CENTER_WATER, //6
    		Tile.BROWN_TOP_RIGHT_WATER, //7
    		Tile.BROWN_MIDDLE_LEFT_WATER, //8
    		Tile.BROWN_MIDDLE_CENTER_WATER, //9
    		Tile.BROWN_MIDDLE_RIGHT_WATER, //10
    		Tile.BROWN_BOTTOM_LEFT_WATER, //11
    		Tile.BROWN_BOTTOM_CENTER_WATER, //12
    		Tile.BROWN_BOTTOM_RIGHT_WATER, //13
    		Tile.BROWN_TOP_LEFT_ROCK_WALL, //14
    		Tile.BROWN_TOP_CENTER_ROCK_WALL, //15
    		Tile.BROWN_TOP_RIGHT_ROCK_WALL, //16
    		Tile.BROWN_BOTTOM_LEFT_ROCK_WALL, //17
    		Tile.BROWN_BOTTOM_CENTER_ROCK_WALL, //18
    		Tile.BROWN_BOTTOM_RIGHT_ROCK_WALL, //19
    		Tile.GREEN_TOP_LEFT_WATER, //20
    		Tile.GREEN_TOP_CENTER_WATER, //21
    		Tile.GREEN_TOP_RIGHT_WATER, //22
    		Tile.GREEN_MIDDLE_LEFT_WATER, //23
    		Tile.GREEN_MIDDLE_CENTER_WATER, //24
    		Tile.GREEN_MIDDLE_RIGHT_WATER, //25
    		Tile.GREEN_BOTTOM_LEFT_WATER, //26
    		Tile.GREEN_BOTTOM_CENTER_WATER, //27
    		Tile.GREEN_BOTTOM_RIGHT_WATER, //28
    		Tile.GREEN_TOP_LEFT_ROCK_WALL, //29
    		Tile.GREEN_TOP_CENTER_ROCK_WALL, //30
    		Tile.GREEN_TOP_RIGHT_ROCK_WALL, //31
    		Tile.GREEN_BOTTOM_LEFT_ROCK_WALL, //32
    		Tile.GREEN_BOTTOM_CENTER_ROCK_WALL, //33
    		Tile.GREEN_BOTTOM_RIGHT_ROCK_WALL, //34
    		Tile.BROWN_SINGLE_ROCK, //35
    		Tile.GREEN_SINGLE_ROCK, //36
    		Tile.BROWN_CAVE_ENTRANCE, //37
    		Tile.GREEN_CAVE_ENTRANCE //38
    		};
    
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