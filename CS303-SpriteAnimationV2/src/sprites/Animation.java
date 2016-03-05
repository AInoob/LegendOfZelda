package sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * Code taken from:
 * 
 * http://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system
 * 
 * Animation and Frame are basically the same, but the other classes have been heavily modified.
 * 
 * @author jspacco
 *
 */
public class Animation {

    private int currentFrame;               // animations current frame
    private int frameCount;                 // Counts ticks for change
    private int frameDelay;                 // frame delay 1-12 (You will have to play around with this)
    private List<Frame> frames = new ArrayList<Frame>();    // ArrayList of frames 

    private boolean stopped;                // has animations stopped

    private int totalFrames;                // total amount of frames for your animation

    public Animation(BufferedImage[] frameArr, int frameDelay) {
        this.frameDelay = frameDelay;
        this.stopped = true;

        for (int i = 0; i < frameArr.length; i++) {
            addFrame(frameArr[i], frameDelay);
        }

        this.frameCount = 0;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.totalFrames = this.frames.size();

    }
    
    private void addFrame(BufferedImage frame, int duration) {

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public int getCurrentFrame(){
    	return this.currentFrame;
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    public boolean isStopped() {
		return this.stopped;
	}

    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }
        stopped = false;
        currentFrame = 0;
        this.frameCount=0;
    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }
        stopped = false;
    }
public void stop() {
        if (frames.size() == 0) {
            return;
        }
        stopped = true;
    }
    
    /*public void goNextFrame(){
    	currentFrame += 1;
        currentFrame=currentFrame%totalFrames;
    }*/

	//called by the timer
    public void update() {
        if (!stopped) {
            if (frameCount == frameDelay) {
                frameCount = 0;
                currentFrame += 1;
                currentFrame=currentFrame%totalFrames;
            }
            frameCount++;
        }

    }

}