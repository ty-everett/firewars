package to.us.tyweb.firewars.GameObjects;

import to.us.tyweb.firewars.GameObjects.Alien;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import to.us.tyweb.firewars.Handler;
import to.us.tyweb.firewars.Textures;

public class Bullet {
    
    private double x;
    private double y;
    private LinkedList<Alien> enemies;
    private Handler c;
    private Alien tempEnemy;
    
    private BufferedImage bullet;
    
    
    public Bullet(double x, double y, Textures tex, Handler con){
        this.x = x;
        this.y = y;
        this.bullet = tex.bullet;
        this.c = con;
        this.enemies = con.getEnemyArray();
    }
    public void tick(){
        y-=8;
    }
    
    public void render(Graphics g){
        g.drawImage(bullet, (int)x, (int)y, null);
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
