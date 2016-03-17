package sprites;

import java.awt.image.BufferedImage;

public interface IDisplay {
	/**
     * this will make the creature looks good
     * @return	the changed x position in terms of pixels
     */
	public int getMovePosX();
    /**
     * this will make the creature looks good
     * @return	the changed y position in terms of pixels
     */
	public int getMovePosY();
	/**
     * 
     * @return	the current image of the creature
     */
	public BufferedImage getImage();
	/**
     * @return	x position in terms of blocks
     */
    public double getPosX();
    /**
     * @return	y position in terms of blocks
     */
    public double getPosY();
}
