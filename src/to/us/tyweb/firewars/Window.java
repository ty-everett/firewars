package to.us.tyweb.firewars;

import java.awt.Dimension;
import javax.swing.JFrame;


public class Window {
    
    JFrame f;
    
    public Window(Game g){
        f = new JFrame("FireWars");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(1024, 576));
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.add(g);
        f.setVisible(true);
    }
    
    public JFrame getWindow(){
        return f;
    }
    
}
