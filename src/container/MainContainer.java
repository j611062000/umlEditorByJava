package container;

import configuration.Configuration;
import java.awt.Color;
import javax.swing.JFrame;

public class MainContainer extends JFrame{

    public MainContainer() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setStyle();
    }
    
    private void setStyle() {
        this.setBackground(Color.WHITE);
        this.setSize(Configuration.DIMENSION_OF_MAIN_CONTAINER);
        this.setLayout(null);
        this.setVisible(true); 
    }
    
}