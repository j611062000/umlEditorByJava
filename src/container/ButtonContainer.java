package container;

import java.awt.Color;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import components.UMLButton;
import configuration.Configuration;
import configuration.Configuration.Mode;

public class ButtonContainer extends Container {

    private Integer lengthOfButton = Configuration.LENGTH_OF_BUTTON;
    private Integer numberOfButtons = Mode.values().length;
    private Integer innerYShift = this.INNER_PADDING;
    private Integer xLength = this.INNER_PADDING*2 + Configuration.LENGTH_OF_BUTTON;
    private Integer yLength = this.INNER_PADDING*(numberOfButtons+1) + numberOfButtons*Configuration.LENGTH_OF_BUTTON;

    private Vector<UMLButton> buttons = new Vector<UMLButton>();

    public ButtonContainer() {
        this.setInitStyle(xLength, yLength);
        this.initChildComponents();
    }

    public void unSelectButtons() {
        for (UMLButton b : this.buttons) {
            b.setInitStyle();
        }
    }

    @Override
    protected void initChildComponents() {
        for (Mode m : Mode.values()) {
            UMLButton b = new UMLButton(new ImageIcon("icon/"+m.getStringMode()+".jpg"));
            b.setBounds(this.INNER_PADDING, innerYShift, lengthOfButton, lengthOfButton);
            innerYShift += (lengthOfButton+padding);
            this.add(b);
            this.buttons.add(b);
        }
    }
}