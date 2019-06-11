package container;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import configuration.Configuration;

public class Container extends JPanel{

    private Integer xShiftOnFrame;
    public final Integer X_START_POINT_OF_FRAME = Configuration.PADDING;
    public final Integer Y_START_POINT_OF_FRAME = Configuration.PADDING;
    public final Integer INNER_PADDING = Configuration.PADDING;

    protected Integer padding = Configuration.PADDING;

    protected void initChildComponents() {
    }

    protected void setInitStyle(Integer xLength, Integer yLength) {
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(X_START_POINT_OF_FRAME + xShiftOnFrame, Y_START_POINT_OF_FRAME, xLength, yLength);
        this.setBorder(new LineBorder(Color.BLACK));
    }

    public void setxShiftOnFrame(Integer xShiftOnFrame) {
        this.xShiftOnFrame = xShiftOnFrame;
    }
}