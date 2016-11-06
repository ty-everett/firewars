package to.us.tyweb.firewars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameTitleScreen {
    
    public Rectangle playButton = new Rectangle(1024 - 75, 200, 150, 50);
    public Rectangle helpButton = new Rectangle(1024 - 75, 300, 150, 50);
    public Rectangle quitButton = new Rectangle(1024 - 75, 400, 150, 50);
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Font fnt0 = new Font("arial", Font.BOLD, 60);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("FireWars!", 1024-((g.getFontMetrics().stringWidth("FireWars!"))/2), 100);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
        g.setFont(new Font("arial", Font.BOLD, 25));
        g.drawString("Play", 1024-((g.getFontMetrics().stringWidth("Play"))/2), playButton.y + 30);
        g.drawString("Help", 1024-((g.getFontMetrics().stringWidth("Help"))/2), helpButton.y + 30);
        g.drawString("Quit", 1024-((g.getFontMetrics().stringWidth("Quit"))/2), quitButton.y + 30);
        g.setFont(new Font("arial", Font.BOLD, 12));
        g.drawString("Copyright (C) 2016, Ty Everett, DO NOT DISTRIBUTE!", 10, (1024));
    }
    
}
