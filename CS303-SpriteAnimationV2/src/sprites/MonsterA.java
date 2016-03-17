package sprites;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


public class MonsterA extends CreatureBasics implements ICreature {
	private int attackCooldown = 0;
	private BufferedImage[] walkingDown_0= {Utilities.monsterLoader.loadFromLocation(56, 8, 16, 16), Utilities.monsterLoader.loadFromLocation(80, 8, 48-32, 24-8)}; 
    private BufferedImage[] walkingLeft_0 = {Utilities.monsterLoader.loadFromLocation(8, 8, 24-8, 24-8), Utilities.monsterLoader.loadFromLocation(32, 8, 48-32, 24-8)}; 
    private BufferedImage[] walkingRight_0 = {Utilities.monsterLoader.loadFromLocation(103, 8, 119-103, 24-8), Utilities.monsterLoader.loadFromLocation(127, 8, 16, 24-8)}; 
    private BufferedImage[] walkingUp_0= {Utilities.monsterLoader.loadFromLocation(152, 8, 16, 24-8), Utilities.monsterLoader.loadFromLocation(176, 8, 16, 24-8)}; 
    private BufferedImage[] walkingDown_1= {Utilities.monsterLoader.loadFromLocation(56+192, 8, 16, 16), Utilities.monsterLoader.loadFromLocation(80+192, 8, 48-32, 24-8)}; 
    private BufferedImage[] walkingLeft_1 = {Utilities.monsterLoader.loadFromLocation(8+192, 8, 24-8, 24-8), Utilities.monsterLoader.loadFromLocation(32+192, 8, 48-32, 24-8)}; 
    private BufferedImage[] walkingRight_1 = {Utilities.monsterLoader.loadFromLocation(103+192, 8, 119-103, 24-8), Utilities.monsterLoader.loadFromLocation(127+192, 8, 16, 24-8)}; 
    private BufferedImage[] walkingUp_1= {Utilities.monsterLoader.loadFromLocation(152+192, 8, 16, 24-8), Utilities.monsterLoader.loadFromLocation(176+192, 8, 16, 24-8)}; 
    private BufferedImage[] balling={LinkLoader.HEART_BLUE,LinkLoader.HEART_RED}; 
    private Animation ball;
    /*private BufferedImage[] standing = {Utilities.monsterLoader.loadFromLocation(8, 8, 24-8, 24-8)};
    private BufferedImage[] attackingLeft = walkingLeft;
    private BufferedImage[] attackingRight = walkingRight;
    private BufferedImage[] attackingUp = walkingUp;
    private BufferedImage[] attackingDown = walkingDown;
    private Animation standingAnim = new Animation(standing, 5);
    private Animation attackLeft=new Animation(attackingLeft,10);
    private Animation attackRight=new Animation(attackingRight,10);
    private Animation attackUp=new Animation(attackingUp,10);
    private Animation attackDown=new Animation(attackingDown,10);*/
	public MonsterA(int type,double x,double y,int montionDelay){
		this.health=20;
		switch(type){
		case 1:
			walkLeft = new Animation(walkingLeft_1, 1);
	        walkRight = new Animation(walkingRight_1, 1);
	        walkUp= new Animation(walkingUp_1, 1);
	        walkDown= new Animation(walkingDown_1, 1);
	        break;
		default:
			walkLeft = new Animation(walkingLeft_0, 1);
	        walkRight = new Animation(walkingRight_0, 1);
	        walkUp= new Animation(walkingUp_0, 1);
	        walkDown= new Animation(walkingDown_0, 1);
		}
		
        ball=new Animation(balling, 10);
		this.setPosX(x);
		this.setPosY(y);
		changeAction(walkDown,1,1,0,0,1);
		this.montionDelay=montionDelay;
		this.montionDelayCounter=0;
	}
	public void attack(int i){
		if(this.attackCooldown==0){
    		switch(this.getFacing()){
    		case 0:	changeAction(this.walkUp, 11, 1, 0, 0, 1);
    				break;
    		case 1: changeAction(this.walkDown, 11, 1, 0, 0, 1);
    				break;
    		case 2: changeAction(this.walkLeft, 11, 1, 0, 0, 1);
    				break;
    		case 3: changeAction(this.walkRight, 11, 1, 0, 0, 1);
    				break;
    		default: System.err.println("Something is wrong");
    		}
    		this.attackCooldown=18;
        	restartAnimation();
    	}
	}
	@Override
	public void move() {
		int temp=Utilities.rand.nextInt(10);
		if(temp<2)
			this.nextMoves.addNextMove(Utilities.rand.nextInt(4));
		else if(temp<3)
			attack(0);
		else 
			this.nextMoves.addNextMove(prevMove);
	}
	@Override
	public List<Object> update() {
		this.montionDelayCounter++;
		if(this.montionDelayCounter>this.montionDelay){
			if(readyToAct()&&nextMoves.none()){
				move();
			}
			this.montionDelayCounter=0;
		}
		List<Object> r=new LinkedList<Object>();
		List<PairDouble> pointsFromCenter=new LinkedList<PairDouble>();
		pointsFromCenter.add(new PairDouble(0,0));
		if(this.attackCooldown==17){
			Action action=new Action();
			PairDouble move=getFacingMagic();
			action.change(this.ball, 100, 1, 0.1*move.x, 0.1*move.y, 1);
			action.startAnimation();
			r.add(new Weapon(action,this.getFacing(),getWeaponPosition(),100,10,pointsFromCenter,this));
		}
    	if(this.attackCooldown>0)
    		this.attackCooldown--;
        super.update();
        return r;
	}
}
