package to.us.tyweb.firewars;

import java.awt.image.BufferedImage;

public class Textures {
    
    private BufferedImage sheet = null;
    public BufferedImage player, bullet, enemy, medkit, invisiBullet, speedUp, speedDown, coin, coinSide = null;
    
    public Textures(Game g){
        sheet = g.getSpriteSheet();
        SpriteManager ss = new SpriteManager(sheet);
        player = ss.grab(1, 1, 32, 32);
        bullet = ss.grab(2, 1, 32, 32);
        enemy = ss.grab(3, 1, 32, 32);
        medkit = ss.grab(4, 1, 32, 32);
        invisiBullet = ss.grab(5, 1, 32, 32);
        coin = ss.grab(6, 1, 32, 32);
        coinSide = ss.grab(7, 1, 32, 32);
        speedUp = ss.grab(8, 1, 32, 32);
        speedDown = ss.grab(1, 2, 32, 32);
    }
}
