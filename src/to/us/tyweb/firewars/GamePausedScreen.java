package to.us.tyweb.firewars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GamePausedScreen {
    
    private Rectangle resumeButton = new Rectangle(Game.width-75, 300, 150, 50);
    
    public void render(Graphics g){
        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D)g;
        g.setFont(new Font("arial", Font.BOLD, 40));
        g.drawString("Game Paused", Game.width-((g.getFontMetrics().stringWidth("Game Paused"))/2), 150);
        g2d.draw(resumeButton);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Resume", Game.width-((g.getFontMetrics().stringWidth("Resume"))/2), resumeButton.y + 30);
    }
    
}
