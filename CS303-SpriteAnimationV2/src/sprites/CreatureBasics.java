package sprites;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
//some general variables and functions that link and monsters have
public class CreatureBasics {
	private double posX=0,posY=0;
	protected double width=1;
	protected double height=1;
	public int facing=1;
    public Action action=new Action();
	public int health=1;
	public List<Animation>animationList;
	public Animation walkLeft;
	public Animation walkRight;
	public Animation walkUp;
	public Animation walkDown;
	public NextMoves nextMoves=new NextMoves();
	public int montionDelay,montionDelayCounter;
	//public List<Weapon> weapons;
	public BufferedImage getImage() {
        return this.action.animation.getSprite();
    }
	public void turnDirection(int direction){
		switch(direction){
		case 0:
			turnUp();
			break;
		case 1:
			turnDown();
			break;
		case 2:
			turnLeft();
			break;
		case 3:
			turnRight();
			break;
		case 4:
			turnDown();
			break;
		case 5:
			turnUp();
			break;
		case 6:
			turnRight();
			break;
		case 7:
			turnLeft();
			break;
		default:
			System.err.println("Can't turn direction: "+direction);
		}
	}
	public void goDirection(int direction){
		switch(direction){
		case 0:
			up();
			break;
		case 1:
			down();
			break;
		case 2:
			left();
			break;
		case 3:
			right();
			break;
		case 4:
			fallUp();
			break;
		case 5:
			fallDown();
			break;
		case 6:
			fallLeft();
			break;
		case 7:
			fallRight();
			break;
		default:
			System.err.println("Can't go direction: "+direction);
		}
	}
	public List<PairInt> getBlocks(int direction){
		List<PairInt> blocks = new LinkedList<PairInt>();
		switch(direction){
		case 0:
			blocks=getBlocks(0,-1);
			break;
		case 1:
			blocks=getBlocks(0,1);
			break;
		case 2:
			blocks=getBlocks(-1,0);
			break;
		case 3:
			blocks=getBlocks(1,0);
			break;
		case 4:
			blocks=getBlocks(0,-1,true);
			break;
		case 5:
			blocks=getBlocks(0,1,true);
			break;
		case 6:
			blocks=getBlocks(-1,0,true);
			break;
		case 7:
			blocks=getBlocks(1,0,true);
			break;
		default:
			System.err.println("Can't return blocks with direction: "+direction);
		}
		return blocks;
	}
    public void setPosX(double x){
    	this.posX=x;
    }
    public void setPosY(double y){
    	this.posY=y;
    }
    public int getFacing(){
    	return this.facing;
    }
    public int peekNextMove(){
    	return nextMoves.peekNextMove();
    }
    public int reverseDirection(int direction){
    	return (int)(direction/2*2+1-direction%2);
    }
    public double getPosX(){
    	return this.posX;
    }
    public int getNextMove() {
		return this.nextMoves.getNextMove();
	}
	public void setNextMove(int nextMove) {
		this.nextMoves.clear();
		this.nextMoves.addNextMove(nextMove);
	}
	/**
	 * 
	 * @return true:if the action is finished	false: otherwise
	 */
    public boolean readyToAct(){
    	if(this.action.none()){
    		return true;
    	}
    	return false;
    }
    public boolean noNextMoves(){
    	return this.nextMoves.none();
    }
    public double getPosY(){
    	return this.posY;
    }
    public void turnLeft() {
    	this.action.change(this.walkLeft, 0, 0, 0, 0, 0);
        this.facing=2;
        this.action.animation.restart();
    } 
    public void turnRight() {
    	this.action.change(this.walkRight, 0, 0, 0, 0, 0);
        this.facing=3;
        this.action.animation.restart();
    }
    public void turnUp() {
    	this.action.change(this.walkUp, 0, 0, 0, 0, 0);
        this.facing=0;
        this.action.animation.restart();
    }
    public void turnDown() {
    	this.action.change(this.walkDown, 0, 0, 0, 0, 0);
        this.facing=1;
        this.action.animation.restart();
    }
    public void left() {
    	this.action.change(this.walkLeft, 3, 1, -1/(double)6, 0, 3);
        this.facing=2;
        this.action.animation.start();
        //this.action.animation.goNextFrame();
    }
    public void right() {
    	this.action.change(this.walkRight, 3, 1, 1/(double)6, 0, 3);
        this.facing=3;
        this.action.animation.start();
    }
    public void up() {
    	this.action.change(this.walkUp, 3, 1, 0, -1/(double)6, 3);
        this.facing=0;
        this.action.animation.start();
    }
    public void down() {
    	this.action.change(this.walkDown, 3, 1, 0, 1/(double)6, 3);
        this.facing=1;
        this.action.animation.start();
    }
    public void fallLeft() {
    	this.action.change(this.walkRight, 3, 1, -1/(double)6, 0, 3);
        this.facing=2;
        this.action.animation.start();
        //this.action.animation.goNextFrame();
    }
    public void fallRight() {
    	this.action.change(this.walkLeft, 3, 1, 1/(double)6, 0, 3);
        this.facing=3;
        this.action.animation.start();
    }
    public void update() {
        PairDouble aMove=this.action.update();
        if(aMove!=null){
        	this.setPosX(this.getPosX()+aMove.x);
            this.setPosY(this.getPosY()+aMove.y);
        }
    }
    public void fallUp() {
    	this.action.change(this.walkDown, 3, 1, 0, -1/(double)6, 3);
        this.facing=0;
        this.action.animation.start();
    }
    public void fallDown() {
    	this.action.change(this.walkUp, 3, 1, 0, 1/(double)6, 3);
        this.facing=1;
        this.action.animation.start();
    }
    /**
     * fall back base on current facing
     */
    public void fallBack() {
    	//System.out.println(this.facing+" after: "+reverseDirection(this.facing));
    	fallBack(reverseDirection(this.facing));
    }
    /**
     * 
     * @param direction	0:fallUp 1:fallDown 2:fallLeft 3:fallRight
     */
    public void fallBack(int direction) {
    	System.out.println(direction+4);
       	this.nextMoves.addNextMove(direction+4);
       	this.nextMoves.addNextMove(direction+4);
       	this.nextMoves.addNextMove(direction+4);
       	this.nextMoves.addNextMove(direction+4);
    }
    /**
     * 
     * @param change in x coordinate
     * @param change in y coordinate
     * @return	blocks that will be covered by the move
     */
    public List<PairInt> getBlocks(int x, int y){
    	return getBlocks(x,y,false);
    }
    /**
     * 
     * @param x	change in x coordinate
     * @param y	change in y coordinate
     * @param special if special, will not detect half block stuff
     * @return	blocks that will be covered by the move
     */
    public List<PairInt> getBlocks(int x, int y, boolean special) {
    	List<PairInt> blocks=new LinkedList<PairInt>();
    		if(!special&&((x==0&&posY!=((int)posY))||(y==0&&posX!=((int)posX)))){
        		return blocks;
        	}
        	else{
        		blocks.add(new PairInt((int)(posX+x), (int)(posY+y)));
            	if(x==0&&posX-((int)(posX))>0){
            		for(int i=0;i<width;i++){
            			blocks.add(new PairInt((int)(posX+x+1+i), (int)(posY+y)));
            		}
            		//blocks.add(new PairInt((int)(posX+x+1), (int)(posY+y)));
            	}
            	if(y==0&&posY-((int)(posY))>0){
            		for(int i=0;i<height;i++){
            			blocks.add(new PairInt((int)(posX+x), (int)(posY+y+1+i)));
            		}
            		//blocks.add(new PairInt((int)(posX+x), (int)(posY+y+1)));
            	}
        		return blocks;
        	}
	}
}
