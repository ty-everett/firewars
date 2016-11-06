package to.us.tyweb.firewars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOverScreen {
    
    public Rectangle playAgainButton = new Rectangle(1024-75, 300, 150, 50);
    public Rectangle quitButton = new Rectangle(1024-75, 400, 150, 50);
    
    public Game gam;
    
    public GameOverScreen(Game game){
        this.gam = game;
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("arial", Font.BOLD, 60));
        g.setColor(Color.white);
        g.drawString("Game Over!", Game.width-((g.getFontMetrics().stringWidth("Game Over!"))/2), 150);
        g.setFont(new Font("arial", Font.BOLD, 40));
        g.drawString("Score: " + gam.getScore(), Game.width-((g.getFontMetrics().stringWidth("Score: " + gam.getScore()))/2), 250);
        g2d.draw(playAgainButton);
        g2d.draw(quitButton);
        g.setFont(new Font("arial", 20, 20));
        g.drawString("Play Again!", Game.width-((g.getFontMetrics().stringWidth("Play Again!"))/2), playAgainButton.y + 35);
        g.drawString("Rage Quit!", Game.width-((g.getFontMetrics().stringWidth("Rage Quit!"))/2), quitButton.y + 35);
    }
    
}
