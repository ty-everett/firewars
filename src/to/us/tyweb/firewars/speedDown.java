package to.us.tyweb.firewars;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class speedDown {
    
    private double x;
    private double y;
    private Textures tex;
    private Player p;
    private BufferedImage speedDown = null;
    Random r = new Random();
    
    public speedDown(Game g){
        x = r.nextInt(Game.width*Game.scale - 256) + 128;
        y = r.nextInt(-200) + 200;
        tex = g.getTexture();
        p = g.getPlayer();
        speedDown = tex.speedDown;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        g.drawImage(speedDown, (int)x, (int)y, null);
    }
    
}
