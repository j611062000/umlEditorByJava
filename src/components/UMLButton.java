package components;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import configuration.Configuration.Mode;
import handler.CoreHandler;
import listener.UMLButtonListener;

public class UMLButton extends JButton {

    private Mode mode;

    public UMLButton(ImageIcon imageIcon, Mode mode) {
        super(imageIcon);
        this.setInitStyle();
        this.setListener(new UMLButtonListener());
        this.mode = mode;
    }

    public void updateCurrentMode() {
        CoreHandler.updateCurrentMode(this.mode);
        configuration.Configuration.debugPrint("CurrentMode:", CoreHandler.getCurrentMode());
    }

    public void setClickedStyle() {
        this.setBackground(Color.BLACK);
    }
    
    private void setListener(MouseAdapter listener) {
        this.addMouseListener(listener);
    }

    public void setInitStyle() {
        this.setVisible(true);
		this.setBackground(Color.WHITE);
    }
}