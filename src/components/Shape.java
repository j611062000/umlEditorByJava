package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.Configuration;
import configuration.Configuration.ClickedPositionInShape;

/**
 * Shape
 */
public class Shape extends JPanel{

    public Dimension dimensionOfSize;
    public Vector<Shape> affiliatedShapes = new Vector<Shape>();
    public Vector<Line> affiliatedLines = new Vector<Line>();
    
    protected ClickedPositionInShape position;

    private Vector<Integer> groupIndex = new Vector<Integer>();
    private static int id              = Configuration.INIT_ID_OF_SHAPE;
    private String labelName;
    private JLabel label;

    public Shape(Dimension d, String label) {
        this.issueNewId();
        this.dimensionOfSize = d;
        this.labelName = label;
       
    }
    
    public int getId() {
        return id;
    }

    protected void issueNewId() {
        id += 1;
        this.setName(String.valueOf(id));
    }

    protected void initPanel(Point location, Dimension dimensionOfSize) {
        this.setSize(dimensionOfSize);
        this.setLayout(null);
        this.setLocation(location);
        // Label
        this.label = new JLabel(this.labelName);
        this.label.setVisible(true);
        this.add(this.label);
        // TODO: refactor
        this.label.setSize(Configuration.DIMENSION_OF_LABEL);
        this.label.setFont(Configuration.FONT_FOR_TEXT);
        this.label.setLocation(30, 10);
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
        this.affiliatedShapes.add(s);
    }

    public void addLineToAffiliates(Line l) {
        this.affiliatedLines.add(l);
    }

    public void performActionWhenClicked() { }

    public void performActionWhenUnclicked() { }

    public Vector<Integer> getGroupIndex() {
        return this.groupIndex;
    }

    public ClickedPositionInShape getPosition() {
        return this.position;
    }

    public void updateLabelName(String newName) {
        this.label.setText(newName);
        this.repaint();
    }

    public Shape getNearestAffiliate(Point clickedPoint) {
        ClickedPositionInShape clickedPosition = this.getClickedPistionInShape(clickedPoint);
        for (Shape shape : this.affiliatedShapes) {
            if(shape.getPosition() == clickedPosition) {
                return shape;
            }
        }

        return null;
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