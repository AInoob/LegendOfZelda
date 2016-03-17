package sprites;

import java.util.List;
/**
 * monsters and link
 * @author AInooob(JAY)&&David
 *
 */
public interface ICreature extends IDisplay{
	public void attack(int i);
	public void down();
	public void fallBack();
	public void fallBack(int direction);
	public List<PairInt> getBlocks(int direction);
    /**
	 * @param x	move in x coordinate
	 * @param y	move in y coordinate
	 * @return	a list of blocks that the creature will stand on
	 */
	public List<PairInt> getBlocks(int x,int y);
    public int getFacing();
    
    
    public int getNextMove();
    
    /**
     * turn, move and animation
     * move half a block
     * @param direction	0:up 1:down 2:left 3:right 4:fallUp 5:fallDown 6:fallLeft 7:fallRight
     */
    public void goDirection(int direction);
    public void hitBy(Weapon weapon);
    public boolean invincible();
    public void left();
    /**
     * called in the update(), so the monster can have next move
     * Link does not use this function
     */
    public void move();
    public boolean noNextMoves();
    public boolean onPoint(double x,double y);
    /**
	 * called when the action is performed, called in update() of monster
	 * @return	the next move of a creature, 0: up, 1: down, 2: left, 3:right
	 */
	public int peekNextMove();
    /**
	    * 
	    * @return	the current move(action) is done or not
	    */
	    public boolean readyToAct();
    public void right();
	public void setInvincible(int invincableTime);
	/**
	 * set the next move, called in timer when the next move is set to current move
	 * @param nextMove 0:up 1:down 2:left 3:right 4:fallUp 5:fallDown 6:fallLeft 7:fallRight
	 */
	public void setNextMove(int nextMove);
	public void setPosX(double tempX);
	public void setPosY(double tempY);
	/**
	 * only turn, no move or animation
	 * @param direction	0:up 1:down 2:left 3:right 4:down 5:up 6:right 7:left
	 */
	public void turnDirection(int direction);
	public void turnDown();
	public void turnLeft();
	public void turnRight();
	public void turnUp();
	public void up();
	public void getDamage(int damage);
	/**
     * call update() in the action
     * change the position of the creature by return value of update() in action
     * for link, reduce the sword cool down
     */
    public List<Object> update();
	public boolean willBeHitBy(Weapon weapon);
	public boolean dead();
}
