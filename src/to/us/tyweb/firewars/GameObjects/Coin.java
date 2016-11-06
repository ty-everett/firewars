package to.us.tyweb.firewars.GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import to.us.tyweb.firewars.Game;
import to.us.tyweb.firewars.Player;
import to.us.tyweb.firewars.Textures;


public class Coin {
    
    private int x, y, counter;
    private boolean flip;
    private Random r = new Random();
    BufferedImage coin = null;
    BufferedImage coinSide = null;
    Player p;
    
    public Coin(Textures tex, Player p){
        this.x = r.nextInt(Game.width*Game.scale);
        this.y = r.nextInt(Game.height*Game.scale);
        flip = false;
        counter = 0;
        coin = tex.coin;
        coinSide = tex.coinSide;
        this.p = p;
    }
    
    public void tick(){
        counter++;
        if(counter == 15) flip = true;
        if(counter == 30){
            flip = false;
            counter = 0;
        }
        if(x < p.getx()) x-=1;
        if(x >= p.getx()) x+=1;
        if(y < p.gety()) y-=1;
        if(y >= p.gety()) y+=1;
    }
    
    public void render(Graphics g){
        if(!flip){
            g.drawImage(coin, x, y, null);
        }else{
            g.drawImage(coinSide, x, y, null);
        }
    }
    
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    
}
