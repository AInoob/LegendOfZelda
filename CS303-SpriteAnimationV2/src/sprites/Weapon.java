package sprites;

import java.awt.image.BufferedImage;
import java.util.List;

public class Weapon implements IDisplay {
	private Action action;
	private PairDouble position;
	private int damage;
	private int duration;
	private List<PairDouble> pointsFromCenter;
	private ICreature owner;
	private int facing;
	/**
	 * 
	 * @param action	Action
	 * @param position	PairDouble
	 * @param duration	Duration
	 * @param damage	Damage
	 * @param pointsFromCenter	PairDouble
	 * @param owner
	 */
	public Weapon(Action action,int facing,PairDouble position,int duration,int damage,List<PairDouble>pointsFromCenter,ICreature owner){
		this.facing=facing;
		this.action=action;
		this.position=position;
		this.duration=duration;
		this.damage=damage;
		this.pointsFromCenter=pointsFromCenter;
		this.owner=owner;
	}
	public int direction(){
		return this.facing;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void reductDuration(){
		this.duration--;
	}
	public PairDouble getCenter() {
		return position;
	}
	public List<PairDouble> getPointsFromCenter() {
		return pointsFromCenter;
	}
	public ICreature getOwner() {
		return owner;
	}
	public int getDamage() {
		return this.damage;
	}
	public int getMovePosX() {
		return 0;
	}
	public int getMovePosY() {
		return 0;
	}
	public BufferedImage getImage() {
		if(this.action==null){
			return null;
		}
		return this.action.getSprite();
	}
	@Override
	public double getPosX() {
		return this.position.x;
	}
	@Override
	public double getPosY() {
		return this.position.y;
	}
	public void update(){
		if(this.action!=null){
			PairDouble aMove=this.action.update();
	        if(aMove!=null){
	        	this.position.x=this.getPosX()+aMove.x;
	            this.position.y=this.getPosY()+aMove.y;
	        }
		}
	}
}
