package sprites;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


/**
 * Other sprites available here:
 * http://www.spriters-resource.com/nes/legendofzelda/
 * 
 * @author jspacco
 * @author AInooob(JAY)
 * @author David
 *
 */
public class Link extends CreatureBasics implements ICreature
{
	private BufferedImage[] standing = {LinkLoader.GREEN_DOWN1}; 
    // These are animation states
    // Have not tried changing the 2nd parameter (frameDelay)
    private Animation standingAnim = new Animation(standing, 5); 
    private int swordCooldown=18; 
    
    private BufferedImage[] swordingDown = {LinkLoader.GREEN_SWORD_DOWN,LinkLoader.GREEN_DOWN1};
    private BufferedImage[] swordingLeft = {LinkLoader.GREEN_SWORD_LEFT,LinkLoader.GREEN_LEFT1};
    private BufferedImage[] swordingRight = {LinkLoader.GREEN_SWORD_RIGHT,LinkLoader.GREEN_RIGHT2};
    private BufferedImage[] swordingUp = {LinkLoader.GREEN_SWORD_UP,LinkLoader.GREEN_UP2};
    private Animation swordDown=new Animation(swordingDown,10); 
    private Animation swordLeft=new Animation(swordingLeft,10);
    private Animation swordRight=new Animation(swordingRight,10);
    private Animation swordUp=new Animation(swordingUp,10);
    private BufferedImage[] walkingDown= {LinkLoader.GREEN_DOWN1, LinkLoader.GREEN_DOWN2};
    // Images for each animation
    private BufferedImage[] walkingLeft = {LinkLoader.GREEN_LEFT2, LinkLoader.GREEN_LEFT1};
    private BufferedImage[] walkingRight = {LinkLoader.GREEN_RIGHT1, LinkLoader.GREEN_RIGHT2};
    
    private BufferedImage[] walkingUp= {LinkLoader.GREEN_UP1, LinkLoader.GREEN_UP2};

    public Link(double x,double y){
    	this.health=200;
    	walkLeft = new Animation(walkingLeft, 1);
        walkRight = new Animation(walkingRight, 1);
        walkUp= new Animation(walkingUp, 1);
        walkDown= new Animation(walkingDown, 1);
    	animationList=new LinkedList<Animation>();
    	this.setPosX(x);
    	this.setPosY(y);
    	animationList.add(walkLeft);
    	animationList.add(walkRight);
    	animationList.add(walkUp);
    	animationList.add(walkDown);
    	animationList.add(standingAnim);
    	animationList.add(swordLeft);
    	animationList.add(swordRight);
    	animationList.add(swordUp);
    	animationList.add(swordDown);
    	changeAction(this.standingAnim, 0, 0, 0, 0, 0);
    }
    public void attack() {
		sword();
	}
    
    public int getMovePosX() {
		int i=animationList.indexOf(getAnimation())*1000+getAnimation().getCurrentFrame();
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
		int i=animationList.indexOf(getAnimation())*1000+getAnimation().getCurrentFrame();
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
	
	public void move() {
		;
	}
	public void sword(){
    	if(this.swordCooldown==0){
    		switch(this.facing){
    		case 0:	changeAction(this.swordUp, 11, 1, 0, 0, 1);
    				break;
    		case 1: changeAction(this.swordDown, 11, 1, 0, 0, 1);
    				break;
    		case 2: changeAction(this.swordLeft, 11, 1, 0, 0, 1);
    				break;
    		case 3: changeAction(this.swordRight, 11, 1, 0, 0, 1);
    				break;
    		default: System.err.println("Something is wrong");
    		}
    		this.swordCooldown=18;
        	restartAnimation();
    	}
    }
	
	
	
	public List<Object> update() {
		List<Object> r=new LinkedList<Object>();
		List<PairDouble> pointsFromCenter=new LinkedList<PairDouble>();
		pointsFromCenter.add(new PairDouble(0,0));
		if(this.swordCooldown==17){
			r.add(new Weapon(null,getSwordPosition(),10,10,pointsFromCenter,this));
		}
    	if(this.swordCooldown>0)
    		this.swordCooldown--;
        super.update();
        return r;
    }
	
	public PairDouble getFacingMagic(){
		PairDouble r=null;
		switch(this.facing){
		case 0:
			r=new PairDouble(0,-1);
			break;
		case 1:
			r=new PairDouble(0,1);
			break;
		case 2:
			r=new PairDouble(-1,0);
			break;
		case 3:
			r=new PairDouble(1,0);
			break;
		}
		return r;
	}
	
	public PairDouble getSwordPosition(){
		return new PairDouble(this.getPosX()+getFacingMagic().x,this.getPosY()+getFacingMagic().y);
	}
	/*public boolean willBeHitBy(Weapon weapon) {
		for(PairDouble point:weapon.centers){
			if(point.x>this.getPosX()&&point.x<this.getPosX()+width&&point.y>this.getPosY()&&point.y<this.getPosY()+height){
				return true;
			}
		}
		return false;
	}*/
}