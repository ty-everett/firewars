package to.us.tyweb.firewars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class speedUp {
    
    private double x;
    private double y;
    private Textures tex;
    private BufferedImage speedUp = null;
    private Random r = new Random();
    
    public speedUp(Textures tex){
        this.x = r.nextInt(Game.width*Game.scale - 256) + 128;
        this.y = r.nextInt(256) - 256;
        speedUp = tex.speedUp;
    }
    
    public void tick(){
        y+=2;
    }
    
    public void render(Graphics g){
        g.drawImage(speedUp, (int)x, (int)y, null);
    }
    
    public double getx(){
        return x;
    }
    
    public double gety(){
        return y;
    }
}
