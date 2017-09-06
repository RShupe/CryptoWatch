package cryptowatch;

import java.awt.*;
import javax.swing.JFrame;

public class MainGui extends JFrame {
    
    public static void main (String[] args) {
        
        MainGui gui = new MainGui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(640, 480);
        gui.setVisible(true);
        gui.setTitle("CryptoWatch");
        
           }
}
