package to.us.tyweb.firewars;

import java.awt.image.BufferedImage;

public class SpriteManager {
    
    private BufferedImage image;
    
    public SpriteManager(BufferedImage image){
        this.image = image;
    }
    public BufferedImage grab(int col, int row, int height, int width){
        BufferedImage sub = image.getSubimage((col*32)-32, (row*32)-32, width, height);
        return sub;
    }
}
