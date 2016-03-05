package sprites;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
//some general variables and functions that link and monsters have
public class CreatureBasics implements ICreature{
	private Action action=new Action();
	protected List<Animation>animationList;
	protected int facing=1;
	protected int health=1;
    protected double height=1;
    protected int montionDelay,montionDelayCounter;
    protected NextMoves nextMoves=new NextMoves();
	private double posX=0,posY=0;
	protected Animation walkDown;
	protected Animation walkLeft;
	protected Animation walkRight;
	protected Animation walkUp;
	protected Animation stand;
	protected double width=1;
	protected void changeAction(Animation swordUp2, int i, int j, int k, int l, int m) {
		this.action.change(swordUp2, i, j, k, l, m);		
	}
	public void down() {
    	this.action.change(this.walkDown, 3, 1, 0, 1/(double)6, 3);
        this.facing=1;
        this.action.startAnimation();
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
       	this.nextMoves.addNextMove(direction+4);
       	this.nextMoves.addNextMove(direction+4);
       	this.nextMoves.addNextMove(direction+4);
       	this.nextMoves.addNextMove(direction+4);
    }
	public void fallDown() {
    	this.action.change(this.walkUp, 3, 1, 0, 1/(double)6, 3);
        this.facing=0;
        this.action.startAnimation();
    }
    public void fallLeft() {
    	this.action.change(this.walkRight, 3, 1, -1/(double)6, 0, 3);
        this.facing=3;
        this.action.startAnimation();
        //this.action.animation.goNextFrame();
    }
    public void fallRight() {
    	this.action.change(this.walkLeft, 3, 1, 1/(double)6, 0, 3);
        this.facing=2;
        this.action.startAnimation();
    }
    public void fallUp() {
    	this.action.change(this.walkDown, 3, 1, 0, -1/(double)6, 3);
        this.facing=1;
        this.action.startAnimation();
    }
    protected Animation getAnimation() {
		return this.action.getAnimation();
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
    public int getFacing(){
    	return this.facing;
    }
	//public List<Weapon> weapons;
	public BufferedImage getImage() {
        return this.action.getSprite();
    }
	public int getNextMove() {
		return this.nextMoves.getNextMove();
	}
    public double getPosX(){
    	return this.posX;
    }
    public double getPosY(){
    	return this.posY;
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
    public void left() {
    	this.action.change(this.walkLeft, 3, 1, -1/(double)6, 0, 3);
        this.facing=2;
        this.action.startAnimation();
        //this.action.animation.goNextFrame();
    }
    public boolean noNextMoves(){
    	return this.nextMoves.none();
    }
    public int peekNextMove(){
    	return nextMoves.peekNextMove();
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
    protected void restartAnimation() {
		this.action.restartAnimation();
	}
    public int reverseDirection(int direction){
    	return (int)(direction/2*2+1-direction%2);
    }
    public void right() {
    	this.action.change(this.walkRight, 3, 1, 1/(double)6, 0, 3);
        this.facing=3;
        this.action.startAnimation();
    }
    public void setNextMove(int nextMove) {
		this.nextMoves.clear();
		this.nextMoves.addNextMove(nextMove);
	}
    public void setPosX(double x){
    	this.posX=x;
    }
    public void setPosY(double y){
    	this.posY=y;
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
    public void turnDown() {
    	this.action.change(this.walkDown, 0, 0, 0, 0, 0);
        this.facing=1;
        this.action.restartAnimation();
    }
    public void turnLeft() {
    	this.action.change(this.walkLeft, 0, 0, 0, 0, 0);
        this.facing=2;
        this.action.restartAnimation();
    }
    public void turnRight() {
    	this.action.change(this.walkRight, 0, 0, 0, 0, 0);
        this.facing=3;
        this.action.restartAnimation();
    }
    public void turnUp() {
    	this.action.change(this.walkUp, 0, 0, 0, 0, 0);
        this.facing=0;
        this.action.restartAnimation();
    }
    public void up() {
    	this.action.change(this.walkUp, 3, 1, 0, -1/(double)6, 3);
        this.facing=0;
        this.action.startAnimation();
    }
    public List<Object> update() {
        PairDouble aMove=this.action.update();
        if(aMove!=null){
        	this.setPosX(this.getPosX()+aMove.x);
            this.setPosY(this.getPosY()+aMove.y);
        }
        return null;
    }
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getMovePosX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getMovePosY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void hitBy(Weapon weapon) {
		this.health-=weapon.getDamage();
	}
	@Override
	public boolean invincible() {
		if(nextMoves.peekNextMove()>3&&nextMoves.peekNextMove()<8){
			return true;
		}
		return false;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onPoint(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setInvincible(boolean invincible) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean willBeHitBy(Weapon weapon) {
		//System.out.println(this.posX+","+this.posY+" "+weapon.getPosX()+","+weapon.getPosY());
		if(Math.abs(weapon.getPosX()-this.posX)<0.51&&Math.abs(weapon.getPosY()-this.posY)<0.51){
			return true;
		}
		return false;
	}
	@Override
	public boolean dead() {
		return this.health<=0;
	}
	@Override
	public void getDamage(int damage) {
		this.health-=damage;
	}
}
