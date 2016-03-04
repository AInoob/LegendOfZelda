package sprites;

public class MapWithPos {
	//return the name of the map and position, used in teleportation
	public String map;
	public int x,y;
	public MapWithPos(String map,int x,int y){
		this.map=map;
		this.x=x;
		this.y=y;
	}
	public String toString(){
		return map+": ("+x+","+y+")";
	}
}
