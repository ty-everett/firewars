package to.us.tyweb.firewars.GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import to.us.tyweb.firewars.Game;
import to.us.tyweb.firewars.Handler;
import to.us.tyweb.firewars.Player;
import to.us.tyweb.firewars.Textures;

public class Alien {

    private double x;
    private double y;
    private int valy;
    private int level;
    Random r = new Random();
    private Player p;
    private boolean isDying;
    private int killCounter = 0;
    private Handler c;
    
    private BufferedImage enemy = null;
    
    public Alien(Textures tex, int level, Game g){
        this.x = r.nextInt((Game.width*Game.scale) - 64) + 32;
        this.y = r.nextInt(200) - 200;
        this.valy = level;
        this.enemy = tex.enemy;
        this.level = level;
        this.p = g.getPlayer();
        c = g.getController();
    }
    
    public void tick(){
        y+=valy;
        if(isDying) kill();
    }
    
    public void kill(){
        isDying = true;
        if(killCounter < 10){
            killCounter++;
            y-=15;
        } else c.removeEnemy(this);
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
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public void setx(double x){
        this.x = x;
    }
    public void sety(double y){
        this.y = y;
    }
}
