package to.us.tyweb.firewars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;


public class medKit {
    
    private double x;
    private double y;
    Random r = new Random();
    private BufferedImage medkit;
    
    public medKit(Textures tex){
        this.x = r.nextInt((Game.width*Game.scale) - 200) + 100;
        this.y = r.nextInt(256) - 256;
        this.medkit = tex.medkit;
    }
    
    public void tick(){
        y+=3;
    }
    
    public void render(Graphics g){
        g.drawImage(medkit, (int)x, (int)y, null);
    }
    
    public double getx(){
        return x;
    }
    public double gety(){
        return y;
    }
    public void setx(double x){
        this.x = x;
    }
    public void sety(double y){
        this.y = y;
    }
}
