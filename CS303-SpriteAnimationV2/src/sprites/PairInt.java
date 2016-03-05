package sprites;

public class PairInt {
	public int x,y;
	public PairInt(int x,int y){
		this.x=x;
		this.y=y;
	}
	public boolean equals(Object arg0) {
		if(arg0 instanceof PairInt){
			if(this.x==((PairInt)arg0).x&&this.y==((PairInt)arg0).y){
				return true;
			}
		}
		return false;
	}
	public int hashCode	(){
		int hashCode=0;
		hashCode+=Integer.hashCode(x);
		hashCode+=Integer.hashCode(y+hashCode);
		return hashCode;
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}
