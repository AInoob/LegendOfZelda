package sprites;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Creature {
	//monsters and link
	//return the next move of a creature, 0: up, 1: down, 2: left, 3:right
	//called when the action is performed, called in update() of monster
	public int peekNextMove();
	public int getNextMove();
	public boolean noNextMoves();
	//set the next move, called in timer when the next move is set to current move
	public void setNextMove(int nextMove);
	//only turn, no move or animation
	public void turnDirection(int direction);
    public void turnLeft();
    public void turnRight();
    public void turnUp();
    public void turnDown();
    //turn, move and animation
    //done this by changing the action
    //move half a block
    public void goDirection(int direction);
    public void left();
    public void right();
    public void up();
    public void down();
    public int getFacing();
    public void fallBack();
    public void fallBack(int direction);
    //return whether the current move(action) is done or not
    public boolean readyToAct();
    //call update() in the action
    //for link, reduce the sword cool down 
    //and change the position of link by return value of update() in action
    public void update();
    //return position in terms of blocks
    //or say where the creature is
    public double getPosX();
    public double getPosY();
    //return the current image of the creature
    public BufferedImage getImage();
    //return the changed position in terms of pixels
    //this will make the creature looks good
	public int getMovePosX();
	public int getMovePosY();
	public boolean willBeHitBy(Weapon weapon);
	public boolean onPoint(double x,double y);
	public void attack();
	public void hitBy(Weapon weapon);
	//called in the update(), so the monster can have next move
	//Link does not use this function
	public void move();
	//return a list of blocks that the creature will stand on after given move(x and y)
	public List<PairInt> getBlocks(int x,int y);
	public List<PairInt> getBlocks(int direction);
	public void setPosX(double tempX);
	public void setPosY(double tempY);
	public void setInvincible(boolean invincible);
	public boolean invincible();
}
