package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteLoader
{
    private int colgap;
    private int rowgap;
    private BufferedImage spriteSheet;
    private int tileSize;
    
    public SpriteLoader(File file, int tileSize, int rowgap, int colgap) throws IOException {
        this.tileSize=tileSize;
        this.rowgap=rowgap;
        this.colgap=colgap;
        this.spriteSheet=ImageIO.read(file);
    }
    
    public SpriteLoader(String filename, int tileSize, int rowgap, int colgap) throws IOException {
        this(new File(filename), tileSize, rowgap, colgap);
    }
    
    /**
     * Loads from a row and column, assuming 
     * @param row
     * @param col
     * @return
     */
    public BufferedImage getSprite(int row, int col) {
        return spriteSheet.getSubimage(col * tileSize + col * colgap, row * tileSize + row * rowgap, 
                tileSize, tileSize);
    }
    
    public BufferedImage getSprite(int row, int col, int extraRowPixels, int extraColPixels) {
        return spriteSheet.getSubimage(col * tileSize + col*colgap + extraColPixels, 
                row * tileSize + row*rowgap + extraRowPixels, 
                tileSize, tileSize);
    }
    
    public BufferedImage loadFromLocation(int xcoord, int ycoord, int width, int height) {
        return spriteSheet.getSubimage(xcoord, ycoord, width, height);
    }

}
