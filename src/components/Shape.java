package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * Shape
 */
public class Shape extends JPanel{

    public Dimension dimensionOfSize;
    public Vector<Shape> affiliates =new Vector<Shape>();
    public enum ClickedPositionInShape {Top, Bottom, Left, Right};
    
    private Vector<Integer> groupIndex = new Vector<Integer>();

    public Shape(Dimension d) {
        this.dimensionOfSize = d;
    }
    
    protected void initPanel(Point location, Dimension dimensionOfSize) {
        this.setSize(dimensionOfSize);
        this.setLayout(null);
        this.setLocation(location);
        this.setVisible(true);
    }
    
    protected void setInitStyle(Color backGroundColor) {
        this.setBackground(backGroundColor);
    }
    
    
    protected void setListener(MouseAdapter listener) {
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }
    
    protected void addShapeToAffiliates(Shape s) {
        this.affiliates.add(s);
    }

    public void performActionWhenClicked() { }

    public void performActionWhenUnclicked() { }

    public Vector<Integer> getGroupIndex() {
        return this.groupIndex;
    }

    public ClickedPositionInShape getClickedPistionInShape(Point clickedPoint) {
        
        double lefUpRightDownSlope  = clickedPoint.getY() / clickedPoint.getX();
        double rightUpLeftDownSlope = -(clickedPoint.getY() / (this.dimensionOfSize.width - clickedPoint.getX()));
        double leftUpDiagonalSlope  = this.dimensionOfSize.getHeight() / this.dimensionOfSize.getWidth();
        double rightUpDiagonalSlope = -leftUpDiagonalSlope;

        if (lefUpRightDownSlope <= leftUpDiagonalSlope) {
            if (rightUpLeftDownSlope >= rightUpDiagonalSlope) {
                return ClickedPositionInShape.Top;
            } else {
                return ClickedPositionInShape.Right;
            }
        } else {
            if (rightUpLeftDownSlope >= rightUpDiagonalSlope) {
                return ClickedPositionInShape.Left;
            } else {
                return ClickedPositionInShape.Bottom;
            }
        }
    } 
}