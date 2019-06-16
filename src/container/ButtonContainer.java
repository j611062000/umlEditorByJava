package container;

import components.UMLButton;
import configuration.Configuration;
import configuration.Configuration.Mode;
import java.util.Vector;
import javax.swing.ImageIcon;


public class ButtonContainer extends Container {

    private Integer lengthOfButton = Configuration.LENGTH_OF_BUTTON;
    private Integer numberOfButtons = Mode.values().length;
    private Integer innerYShift = this.Y_START_POINT_OF_FRAME;

    private Vector<UMLButton> buttons = new Vector<UMLButton>();

    public ButtonContainer() {
        this.setXYLength();        
        this.setInitStyle(this.xLength, this.yLength);
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
            UMLButton b = new UMLButton(new ImageIcon(Configuration.getIconPath(m.getStringMode())), m);
            b.setBounds(this.INNER_PADDING, innerYShift, lengthOfButton, lengthOfButton);
            innerYShift += (lengthOfButton+this.INNER_PADDING);
            this.add(b);
            this.buttons.add(b);
        }
    }

    @Override
    protected void setXYLength() {
        this.xLength = this.INNER_PADDING*2 + Configuration.LENGTH_OF_BUTTON;
        this.yLength = this.INNER_PADDING*(numberOfButtons+1) + numberOfButtons*Configuration.LENGTH_OF_BUTTON;
    }
}