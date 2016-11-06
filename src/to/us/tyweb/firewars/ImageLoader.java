package to.us.tyweb.firewars;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
    
    private BufferedImage image;
    private String loadpath;
    
    public BufferedImage load(String path) throws IOException{
        this.loadpath = path;
        image = ImageIO.read(getClass().getResource(loadpath));
        return image;
    }
}
