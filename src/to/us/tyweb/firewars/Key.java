package to.us.tyweb.firewars;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter {
    
    Game game;
    
    public Key(Game game){
        this.game = game;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        game.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e){
        game.keyReleased(e);
    }
                    
    
}
