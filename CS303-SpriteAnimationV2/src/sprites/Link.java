package sprites;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import sprites.Utilities.*;

/**
 * Other sprites available here:
 * http://www.spriters-resource.com/nes/legendofzelda/
 * 
 * @author jspacco
 *
 */
public class Link extends Utilities.CreatureBasics implements Utilities.Creature
{
	// Images for each animation
    private BufferedImage[] walkingLeft = {LinkLoader.GREEN_LEFT2, LinkLoader.GREEN_LEFT1}; 
    private BufferedImage[] walkingRight = {LinkLoader.GREEN_RIGHT1, LinkLoader.GREEN_RIGHT2}; 
    private BufferedImage[] walkingUp= {LinkLoader.GREEN_UP1, LinkLoader.GREEN_UP2}; 
    private BufferedImage[] walkingDown= {LinkLoader.GREEN_DOWN1, LinkLoader.GREEN_DOWN2}; 
    private BufferedImage[] standing = {LinkLoader.GREEN_DOWN1};
    private BufferedImage[] swordingLeft = {LinkLoader.GREEN_SWORD_LEFT,LinkLoader.GREEN_LEFT1};
    private BufferedImage[] swordingRight = {LinkLoader.GREEN_SWORD_RIGHT,LinkLoader.GREEN_RIGHT2};
    private BufferedImage[] swordingUp = {LinkLoader.GREEN_SWORD_UP,LinkLoader.GREEN_UP2};
    private BufferedImage[] swordingDown = {LinkLoader.GREEN_SWORD_DOWN,LinkLoader.GREEN_DOWN1};

    // These are animation states
    // Have not tried changing the 2nd parameter (frameDelay)
    private Animation standingAnim = new Animation(standing, 5);
    private Animation swordLeft=new Animation(swordingLeft,10);
    private Animation swordRight=new Animation(swordingRight,10);
    private Animation swordUp=new Animation(swordingUp,10);
    private Animation swordDown=new Animation(swordingDown,10);

    public Link(double x,double y){
    	walkLeft = new Animation(walkingLeft, 1);
        walkRight = new Animation(walkingRight, 1);
        walkUp= new Animation(walkingUp, 1);
        walkDown= new Animation(walkingDown, 1);
    	animationList=new LinkedList<Animation>();
    	this.posX=x;
    	this.posY=y;
    	animationList.add(walkLeft);
    	animationList.add(walkRight);
    	animationList.add(walkUp);
    	animationList.add(walkDown);
    	animationList.add(standingAnim);
    	animationList.add(swordLeft);
    	animationList.add(swordRight);
    	animationList.add(swordUp);
    	animationList.add(swordDown);
    	this.action.change(this.standingAnim, 0, 0, 0, 0, 0);
    }
    public void sword(){
    	if(this.swordCooldown==0){
    		switch(this.facing){
    		case 0:	this.action.change(this.swordUp, 11, 1, 0, 0, 1);
    				break;
    		case 1: this.action.change(this.swordDown, 11, 1, 0, 0, 1);
    				break;
    		case 2: this.action.change(this.swordLeft, 11, 1, 0, 0, 1);
    				break;
    		case 3: this.action.change(this.swordRight, 11, 1, 0, 0, 1);
    				break;
    		default: System.err.println("Something is wrong");
    		}
    		this.swordCooldown=18;
        	this.action.animation.restart();
    	}
    }
    
    public void update() {
    	if(this.swordCooldown>0)
    		this.swordCooldown--;
        ActionMove aMove=this.action.update();
        if(aMove!=null){
        	this.posX+=aMove.x;
            this.posY+=aMove.y;
        }
    }
	public int getMovePosX() {
		int i=animationList.indexOf(this.action.animation)*1000+this.action.animation.getCurrentFrame();
		int r=0;
//		System.out.println(i);
		switch(i){
			case 5000:	r=-10;
						break;
			default: r=0;
		}
		return r;
	}
	public int getMovePosY() {
		int i=animationList.indexOf(this.action.animation)*1000+this.action.animation.getCurrentFrame();
		int r=0;
//		System.out.println(i);
		switch(i){
			case 7000:	r=-11;
						break;
			case 0:		r=-1;
						break;
			case 3001:		r=-1;
							break;
			default: r=0;
		}
		return r;
	}
	public boolean willBeHitBy(Weapon weapon) {
		for(PairDouble point:weapon.points){
			if(point.x>this.posX&&point.x<this.posX+width&&point.y>this.posY&&point.y<this.posY+height){
				return true;
			}
		}
		return false;
	}
	public void attack() {
		sword();
	}
	public void hitBy(Weapon weapon) {
		this.health-=weapon.damage;
	}
	public void move() {
		;
	}
	
	@Override
	public boolean onPoint(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getNextMove() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setNextMove(int nextMove) {
		// TODO Auto-generated method stub
		
	}
}