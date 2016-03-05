package sprites;

import java.util.LinkedList;
import java.util.List;

public class NextMoves {
	public List<Integer>nextMoves=new LinkedList<Integer>();
	/**
	 * @param	move	0:up 1:down 2:left 3:right 4:fallUp 5:fallDown 6:fallLeft 7:fallRight
	 */
	public void addNextMove(int move){
		nextMoves.add(nextMoves.size(), new Integer(move));
	}
	public void clear(){
		nextMoves.clear();
	}
	public int getNextMove(){
		if(nextMoves.size()>0){
			int r=nextMoves.get(0);
    		nextMoves.remove(0);
    		return r;
		}
		return -1;
	}
	public boolean none() {
		return size()==0;
	}
	public int peekNextMove() {
		if(nextMoves.size()>0){
			return nextMoves.get(0);
		}
		return -1;
	}
	public int size(){
		return nextMoves.size();
	}
}
