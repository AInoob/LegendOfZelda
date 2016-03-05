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
	/**
	 * 
	 * @param action	Action
	 * @param position	PairDouble
	 * @param duration	Duration
	 * @param damage	Damage
	 * @param pointsFromCenter	PairDouble
	 * @param owner
	 */
	public Weapon(Action action,PairDouble position,int duration,int damage,List<PairDouble>pointsFromCenter,ICreature owner){
		this.action=action;
		this.position=position;
		this.duration=duration;
		this.damage=damage;
		this.pointsFromCenter=pointsFromCenter;
		this.owner=owner;
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
}
