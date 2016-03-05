package sprites;

import java.awt.image.BufferedImage;

//including animation and ActionMove
public class Action {
	private Animation animation=null;
	private int restFrames,frameDelay,frameCount=0,animationDelay,animationCount=0;
	private double x,y;
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
	public void changeAnimation(Animation animation){
		this.animation=animation;
	}
	public boolean none(){
		if(this.restFrames==0){
			return true;
		}
		return false;
	}
	public PairDouble update(){
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
				return new PairDouble(this.x,this.y);
			}
		}
		return null;
	}
	public BufferedImage getSprite() {
		return this.animation.getSprite();
	}
	public void restartAnimation() {
		this.animation.restart();
	}
	public void startAnimation() {
		this.animation.start();
	}
	public Animation getAnimation() {
		return this.animation;
	}
}
