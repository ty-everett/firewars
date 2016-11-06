package to.us.tyweb.firewars;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    Game g;
    private boolean isMoving = false;
    
    public Mouse(Game game){
        this.g = game;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        if(Game.state == Game.state.menu){
        if(me.getX() >= Game.width-75 && me.getX() <= Game.width + 75){
            if(me.getY() >= 200 && me.getY() <= 250){
                //clicked play
                Game.state = Game.state.game;
            }
        }
        if(me.getX() >= Game.width-75 && me.getX() <= Game.width + 75){
            if(me.getY() >= 400 && me.getY() <= 450){
                //clicked quit
                System.exit(0);
            }
        }
        if(me.getX() >= Game.width-75 && me.getX() <= Game.width + 75){
                if(me.getY() >= 300 && me.getY() <= 350){
                //clicked help
                //ADD Help!!
            }
        }
        }else if(Game.state == Game.state.gameOver){
        if(me.getX() >= Game.width-75 && me.getX() <= Game.width + 75){
            if(me.getY() >= 300 && me.getY() <= 350){
                //clicked playAgaub
                g.reset();
                Game.state = Game.state.game;
            }
        }
        if(me.getX() >= Game.width-75 && me.getX() <= Game.width + 75){
                if(me.getY() >= 400 && me.getY() <= 450){
                //clicked Quit
                System.exit(0);
            }
        }
    }else if (Game.state == Game.state.paused){
        if(me.getX() >= Game.width-75 && me.getX() <= Game.width + 75){
            if(me.getY() >= 300 && me.getY() <= 350){
                //clicked resume
                Game.state = Game.state.game;
            }
        }
    }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    @Override
    public void mouseMoved(MouseEvent me){
    }
    
    public void mouseDragged(MouseEvent me){
    }
}
