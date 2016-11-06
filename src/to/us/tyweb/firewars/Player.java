package to.us.tyweb.firewars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
    
    private double x;
    private double y;
    private double valx;
    private double valy;
    private int radius;
    BufferedImage player;
    
    public Player(double x, double y, Textures tex){
        
        this.x = x;
        this.y = y;
        this.radius = 16;
        this.player = tex.player;
    }
    
    public void tick(){
        this.x+=valx;
        this.y+=valy;
        if(y < 0) y = 0;
        if(y > Game.height * Game.scale - radius) y = Game.height * Game.scale - radius;
        if(x < 0) x = 0;
        if(x > Game.width * Game.scale - radius) x = Game.width * Game.scale - radius;
    }
    
    public void render(Graphics g){
        g.drawImage(player, (int)x, (int)y, null);
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
    public void setvalx(double valx){
        this.valx = valx;
    }
    public void setvaly(double valy){
        this.valy = valy;
    }
    public double getValx(){
        return valx;
    }
    public double getValy(){
        return valy;
    }
}