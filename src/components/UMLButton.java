package components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import listener.UMLButtonListener;

public class UMLButton extends JButton {

    public UMLButton(ImageIcon imageIcon) {
        super(imageIcon);
        this.setInitStyle();
        this.setListener();
    }

    public void setClickedStyle() {
		this.setBackground(Color.BLACK);
    }
    
    private void setListener() {
        this.addMouseListener(new UMLButtonListener());
    }

    public void setInitStyle() {
        this.setVisible(true);
		this.setBackground(Color.WHITE);
    }


}