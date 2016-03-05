package sprites;


public class PairDouble {
	public double x,y;
	public PairDouble(double x,double y){
		this.x=x;
		this.y=y;
	}
	public boolean equals(Object arg0) {
		if(arg0 instanceof PairDouble){
			if(this.x==((PairDouble)arg0).x&&this.y==((PairDouble)arg0).y){
				return true;
			}
		}
		return false;
	}
	public int hashCode	(){
		int hashCode=0;
		hashCode+=Double.hashCode(x);
		hashCode+=Double.hashCode(y+hashCode);
		return hashCode;
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}
