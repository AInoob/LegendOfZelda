package sprites;

import java.awt.image.BufferedImage;
import java.util.List;


public class MonsterA extends CreatureBasics implements ICreature {
	private BufferedImage[] walkingDown= {Utilities.monsterLoader.loadFromLocation(56, 8, 16, 16), Utilities.monsterLoader.loadFromLocation(80, 8, 48-32, 24-8)}; 
    private BufferedImage[] walkingLeft = {Utilities.monsterLoader.loadFromLocation(8, 8, 24-8, 24-8), Utilities.monsterLoader.loadFromLocation(32, 8, 48-32, 24-8)}; 
    private BufferedImage[] walkingRight = {Utilities.monsterLoader.loadFromLocation(103, 8, 119-103, 24-8), Utilities.monsterLoader.loadFromLocation(127, 8, 16, 24-8)}; 
    private BufferedImage[] walkingUp= {Utilities.monsterLoader.loadFromLocation(152, 8, 16, 24-8), Utilities.monsterLoader.loadFromLocation(176, 8, 16, 24-8)}; 
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
	public MonsterA(double x,double y,int montionDelay){
		this.health=200;
		walkLeft = new Animation(walkingLeft, 1);
        walkRight = new Animation(walkingRight, 1);
        walkUp= new Animation(walkingUp, 1);
        walkDown= new Animation(walkingDown, 1);
		this.setPosX(x);
		this.setPosY(y);
		changeAction(walkDown,1,1,0,0,1);
		this.montionDelay=montionDelay;
		this.montionDelayCounter=0;
	}
	@Override
	public void move() {
		this.nextMoves.addNextMove(Utilities.rand.nextInt(4));
	}
	@Override
	public List<Object> update() {
		this.montionDelayCounter++;
		if(this.montionDelayCounter>this.montionDelay){
			if(readyToAct()){
				move();
			}
			this.montionDelayCounter=0;
		}
		//if(this.action.none())

		return super.update();
	}
}
