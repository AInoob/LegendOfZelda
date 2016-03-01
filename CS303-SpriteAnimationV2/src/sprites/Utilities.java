package sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import sprites.Utilities.ActionMove;
import sprites.Utilities.PairInt;

public class Utilities {
	public static Random rand = new Random();
	private static SpriteLoader monsterLoader;
    static {
        try {
        	monsterLoader=new SpriteLoader("res/monsters.png", 16, 13, 13);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	static class PairDouble{
    	public double x,y;
		public PairDouble(double x,double y){
			this.x=x;
			this.y=y;
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
		public boolean equals(Object arg0) {
			if(arg0 instanceof PairDouble){
				if(this.x==((PairDouble)arg0).x&&this.y==((PairDouble)arg0).y){
					return true;
				}
			}
			return false;
		}
    }
	static class PairInt{
    	public int x,y;
		public PairInt(int x,int y){
			this.x=x;
			this.y=y;
		}
		public int hashCode	(){
			int hashCode=0;
			hashCode+=Integer.hashCode(x);
			hashCode+=Integer.hashCode(y+hashCode);
			return hashCode;
		}
		public String toString(){
			return "("+x+","+y+")";
		}
		public boolean equals(Object arg0) {
			if(arg0 instanceof PairInt){
				if(this.x==((PairInt)arg0).x&&this.y==((PairInt)arg0).y){
					return true;
				}
			}
			return false;
		}
    }
    static class MapWithPos{
    	public String map;
    	public int x,y;
		public MapWithPos(String map,int x,int y){
			this.map=map;
			this.x=x;
			this.y=y;
		}
		public String toString(){
			return map+": ("+x+","+y+")";
		}
    }
    static class ActionMove{
    	public double x,y;
		public ActionMove(double x, double y) {
			this.x=x;
			this.y=y;
		}
    	
    }
    static class Weapon{
    	public int damage;
    	public List<PairDouble> points;
    }
    static interface Creature{
    	public int getNextMove();
    	public void setNextMove(int nextMove);
        public void turnLeft();
        public void turnRight();
        public void turnUp();
        public void turnDown();
        public void left();
        public void right();
        public void up();
        public void down();
        public boolean readyToAct();
        public void update();
        public double getPosX();
        public double getPosY();
        public BufferedImage getImage();
    	public int getMovePosX();
    	public int getMovePosY();
    	public boolean willBeHitBy(Weapon weapon);
    	public boolean onPoint(double x,double y);
    	public void attack();
    	public void hitBy(Weapon weapon);
    	public void move();
    	public List<PairInt> getBlocks(int x,int y);
		public void setPosX(double tempX);
		public void setPosY(double tempY);
    }
    
    static class MonsterA extends CreatureBasics implements Creature{
    	private BufferedImage[] walkingLeft = {monsterLoader.loadFromLocation(8, 8, 24-8, 24-8), monsterLoader.loadFromLocation(32, 8, 48-32, 24-8)}; 
        private BufferedImage[] walkingRight = {monsterLoader.loadFromLocation(103, 8, 119-103, 24-8), monsterLoader.loadFromLocation(127, 8, 16, 24-8)}; 
        private BufferedImage[] walkingUp= {monsterLoader.loadFromLocation(152, 8, 16, 24-8), monsterLoader.loadFromLocation(176, 8, 16, 24-8)}; 
        private BufferedImage[] walkingDown= {monsterLoader.loadFromLocation(56, 8, 16, 16), monsterLoader.loadFromLocation(80, 8, 48-32, 24-8)}; 
        private BufferedImage[] standing = {monsterLoader.loadFromLocation(8, 8, 24-8, 24-8)};
        private BufferedImage[] attackingLeft = walkingLeft;
        private BufferedImage[] attackingRight = walkingRight;
        private BufferedImage[] attackingUp = walkingUp;
        private BufferedImage[] attackingDown = walkingDown;

        
        private Animation standingAnim = new Animation(standing, 5);
        private Animation attackLeft=new Animation(attackingLeft,10);
        private Animation attackRight=new Animation(attackingRight,10);
        private Animation attackUp=new Animation(attackingUp,10);
        private Animation attackDown=new Animation(attackingDown,10);
        
        
        
        
    	public MonsterA(double x,double y,int montionDelay){
    		walkLeft = new Animation(walkingLeft, 1);
            walkRight = new Animation(walkingRight, 1);
            walkUp= new Animation(walkingUp, 1);
            walkDown= new Animation(walkingDown, 1);
    		this.nextMove=-1;
    		this.posX=x;
    		this.posY=y;
    		this.action.animation=walkLeft;
    		this.action.animation.start();
    		this.montionDelay=montionDelay;
    		this.montionDelayCounter=0;
    	}
    	public int getNextMove(){
    		return this.nextMove;
    	}
    	public void setNextMove(int nextMove){
    		this.nextMove=nextMove;
    	}

		@Override
		public void update() {
			this.montionDelayCounter++;
			if(this.montionDelayCounter>this.montionDelay){
				if(readyToAct()){
					move();
				}
				this.montionDelayCounter=0;
			}
			ActionMove aMove=this.action.update();
	        if(aMove!=null){
	        	this.posX+=aMove.x;
	            this.posY+=aMove.y;
	        }
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
		public boolean willBeHitBy(Weapon weapon) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onPoint(double x, double y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void attack() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void hitBy(Weapon weapon) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void move() {
			this.nextMove=Utilities.rand.nextInt(4);
			//System.out.println(this.nextMove);
		}
    	
    }
    
    static class Action{
    	public Animation animation=null;
    	public int restFrames,frameDelay,frameCount=0,animationDelay,animationCount=0;
    	public double x,y;
    	public void change(Animation animation, int restFrames,int frameDelay,double x,double y,int animationDelay){
    		this.animation=animation;
    		this.restFrames=restFrames;
    		this.frameDelay=frameDelay;
    		this.x=x;
    		this.y=y;
    		this.animationDelay=animationDelay;
    		this.frameCount=0;
    		this.animationCount=0;
    	}
    	public boolean none(){
    		if(this.restFrames==0){
    			return true;
    		}
    		return false;
    	}
    	public ActionMove update(){
    		if(this.restFrames>0){
    			this.animationCount++;
    			this.frameCount++;
    			if(this.animationCount==this.animationDelay){
    				this.animationCount=0;
    				this.animation.update();
    			}
    			if(this.frameCount==this.frameDelay){
    				this.frameCount=0;
    				restFrames--;
    				return new ActionMove(this.x,this.y);
    			}
    		}
    		return null;
    	}
    }
    
    static class CreatureBasics{
    	public double posX=0,posY=0,width=1,height=1;
    	public int nextMove;
    	public int facing=1;
        public Action action=new Action();
        public int swordCooldown=18;
    	public int health=1;
    	public List<Animation>animationList;
    	public Animation walkLeft;
    	public Animation walkRight;
    	public Animation walkUp;
    	public Animation walkDown;
    	public int montionDelay,montionDelayCounter;
    	public BufferedImage getImage() {
            return this.action.animation.getSprite();
        }
        public void setPosX(double x){
        	this.posX=x;
        }
        public void setPosY(double y){
        	this.posY=y;
        }
        public double getPosX(){
        	return this.posX;
        }
        public boolean readyToAct(){
	    	if(this.action.none()){
	    		return true;
	    	}
	    	return false;
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
        public List<PairInt> getBlocks(int x, int y) {
        	List<PairInt> blocks=new LinkedList<PairInt>();
    		if((x==0&&posY!=((int)posY))||(y==0&&posX!=((int)posX))){
        		return blocks;
        	}
        	else{
        		blocks.add(new PairInt((int)(posX+x), (int)(posY+y)));
            	if(x==0&&posX-((int)(posX))>0){
            		/*for(int i=0;i<width;i++){
            			blocks.add(new PairInt((int)(posX+x+1+i), (int)(posY+y)));
            		}*/
            		blocks.add(new PairInt((int)(posX+x+1), (int)(posY+y)));
            	}
            	if(y==0&&posY-((int)(posY))>0){
            		/*for(int i=0;i<height;i++){
            			blocks.add(new PairInt((int)(posX+x), (int)(posY+y+1+i)));
            		}*/
            		blocks.add(new PairInt((int)(posX+x), (int)(posY+y+1)));
            	}
        		return blocks;
        	}
		}
    }
    
    static class Monster extends CreatureBasics implements Creature{
		@Override
		public boolean willBeHitBy(Weapon weapon) {
			for(PairDouble point:weapon.points){
				if(point.x>this.posX&&point.x<this.posX+width&&point.y>this.posY&&point.y<this.posY+height){
					return true;
				}
			}
			return false;
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
		public void attack() {
			// TODO Auto-generated method stub
		}
		@Override
		public void hitBy(Weapon weapon) {
			this.health-=weapon.damage;
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
		}

		@Override
		public void move() {
			// TODO Auto-generated method stub
		}
		@Override
		public void turnLeft() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void turnRight() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void turnUp() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void turnDown() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void left() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void right() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void up() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void down() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean readyToAct() {
			// TODO Auto-generated method stub
			return false;
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
    
    static public boolean isInteger(double x){
    	return x==((int)x);
    }
    
    static public int upperInt(double x){
    	if(isInteger(x))
    		return (int)x;
    	return (int)(x+1);
    }
    
    static public int lowerInt(double x){
    	if(isInteger(x))
    		return (int)x-1;
    	return (int)x;
    }
}
