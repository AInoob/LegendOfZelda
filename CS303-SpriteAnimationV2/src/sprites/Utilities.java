package sprites;

import java.io.IOException;
import java.util.Random;


public class Utilities {
	public static SpriteLoader monsterLoader;//load image of monsters
	public static Random rand = new Random();//random generator
    static {
        try {
        	monsterLoader=new SpriteLoader("res/monsters.png", 16, 13, 13);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 
     * @param x a double
     * @return	if the double is equal to an integer or not
     */
    static public boolean isInteger(double x){
    	return x==((int)x);
    }
    /**
     * 
     * @param x a double
     * @return	the closest lower integer to the double
     */
    static public int lowerInt(double x){
    	if(isInteger(x))
    		return (int)x-1;
    	return (int)x;
    }
    /**
     * 
     * @param x a double
     * @return	the closest upper integer to the double
     */
    static public int upperInt(double x){
    	if(isInteger(x))
    		return (int)x;
    	return (int)(x+1);
    }
}