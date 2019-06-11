package container;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import configuration.Configuration;

public class MainContainer extends JFrame{

    private void setStyle() {
        this.setBackground(Color.WHITE);
    }
    
    public MainContainer() {
        this.setSize(Configuration.DIMENSION_OF_MAIN_CONTAINER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setStyle();
        this.setLayout(null);
        this.setVisible(true); 
    }
}