package to.us.tyweb.firewars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class zigZagEnemy {
    
    private double x;
    private double y;
    private int zig = 0;
    private boolean zigging = true;
    private BufferedImage enemy;
    Random r = new Random();
    
    public zigZagEnemy (Textures tex){
        this.enemy = tex.enemy;
        this.x = (r.nextInt((Game.width*Game.scale)-256) + 32);
        this.y = r.nextInt(256) - 256;
    }
    
    public void tick(){
        y+=1;
        if(zigging){
            zig+=1;
            x+=9;
            if(zig>=30) zigging = false;
            if(x > Game.width*Game.height) zigging = false;
        }else if (!zigging){
            zig-=1;
            x-=9;
            if(zig<=-30) zigging = true;
            if(x < 0) zigging = true;
        }
    }
    
    public void render(Graphics g){
        g.drawImage(enemy, (int)x, (int)y, null);
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
