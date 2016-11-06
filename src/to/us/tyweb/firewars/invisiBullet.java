package to.us.tyweb.firewars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class invisiBullet {
    
    private double x;
    private double y;
    private Textures tex;
    private Player p;
    private Random r = new Random();
    private BufferedImage invisiBullet = null;
    
    public invisiBullet(Textures tex, Player p){
        this.x = r.nextInt(Game.width*Game.scale - 128) + 64;
        this.y = r.nextInt(256) - 256;
        this.invisiBullet = tex.invisiBullet; // change this!
        this.p = p;
    }
    
    public void tick(){
        y+=2;
        if(x < p.getx()) x+=2;
        if(x > p.getx()) x-=2;
    }
    public void render (Graphics g){
        g.drawImage(invisiBullet, (int)x, (int)y, null);
    } 
    public void setx(double x){
        this.x = x;
    }
    public void sety(double y){
        this.y = y;
    }
    public double getx(){
        return x;
    }
    public double gety(){
        return y;
    }
}
