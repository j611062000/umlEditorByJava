package container;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import configuration.Configuration;

public class Container extends JPanel{

    public final Integer          INNER_PADDING = Configuration.PADDING;
    public final Integer X_START_POINT_OF_FRAME = Configuration.PADDING;
    public final Integer Y_START_POINT_OF_FRAME = Configuration.PADDING;
    
    public Integer xLength;
    public Integer xShiftOnFrame = 0;

    protected Integer yLength;

    protected void initChildComponents() {}
    
    protected void setXYLength() {}

    protected void setInitStyle(Integer xLength, Integer yLength) {
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(X_START_POINT_OF_FRAME + xShiftOnFrame, Y_START_POINT_OF_FRAME,xLength, yLength);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setBackground(Color.white);
    }

    public void setInitStyle() {
        this.setInitStyle(xLength, yLength);
    }
}