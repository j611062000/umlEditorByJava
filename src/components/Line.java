package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;

import configuration.Configuration;
import configuration.Configuration.ClickedPositionInShape;

/**
 * Line
 */
public class Line extends JPanel{
    
    private boolean isHighlighted = false;
    private Dimension dimensionOfJComponent;
    
    private Shape headPort; // arrow side
    private Shape tailPort; // non-arrow side
    
    private Point outsideHeadPoint;
    private Point outsideTailPoint;
    protected Point innerHeadPoint;
    protected Point innerTailPoint;
    private Point upperLeftPointOfPanel;
    
    protected Polygon arrow;
    protected int shif = Configuration.DIMENSION_OF_PORT.height;
    protected int xCoord[];
    protected int yCoord[];
    
    public Line() { 
        this.setOpaque(false);
    }     

    public Line(Shape heaPort, Shape tailPort) {
        this.tailPort = tailPort;
        this.headPort = heaPort;
        this.refreshGeoInfo();
        this.repaintLine();
        this.setLayout(null);
        this.setOpaque(false);
		this.setVisible(true);
    }
    
    private void repaintLine() {
        this.dimensionOfJComponent = new Dimension(Math.abs(this.tailPort.getX() - this.headPort.getX())+shif*5,
                                                   Math.abs(this.tailPort.getY() - this.headPort.getY())+shif*5);
        this.setSize(dimensionOfJComponent);
        this.setLocation(this.upperLeftPointOfPanel);
        this.repaint();
    }
    
    private void refreshGeoInfo() {
        this.upperLeftPointOfPanel = new Point(Math.min(this.tailPort.getX(), this.headPort.getX())-shif*2, Math.min(this.tailPort.getY(), this.headPort.getY())-shif*2);
        this.outsideHeadPoint      = this.headPort.getLocation();
        this.outsideTailPoint      = this.tailPort.getLocation();
        this.innerHeadPoint = new Point(this.outsideHeadPoint.x - this.upperLeftPointOfPanel.x, this.outsideHeadPoint.y - this.upperLeftPointOfPanel.y);
        this.innerTailPoint = new Point(this.outsideTailPoint.x - this.upperLeftPointOfPanel.x, this.outsideTailPoint.y - this.upperLeftPointOfPanel.y);
    }

    public void updateHeadAndTailPort(Shape newHeadPort, Shape newTailPort) {
        this.headPort = newHeadPort;
        this.tailPort = newTailPort;
        this.refreshGeoInfo();
        this.repaintLine();
    }

    public void updateHeadPort(Shape newHeadPort) {
        this.headPort = newHeadPort;
        this.refreshGeoInfo();
        this.repaintLine();
    }
    
    public void updateTailPort(Shape newTailPort) {
        this.tailPort = newTailPort;
        this.refreshGeoInfo();
        this.repaintLine();
    }

    public Shape getHeadPort() {
        return this.headPort;
    }

    public Shape getTailPort() {
        return this.tailPort;
    }

    @Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
        int shif = Configuration.DIMENSION_OF_PORT.height / 2;
		this.setColor(g);
        g.drawLine(this.innerTailPoint.x + shif,
                   this.innerTailPoint.y + shif,
                   this.innerHeadPoint.x + shif,
                   this.innerHeadPoint.y + shif);
        this.paintArrow(g, this.innerHeadPoint);
    }

    public void setHighlighted() {
        this.isHighlighted = true;
        this.repaint();
    }
    
    public void setunhighlighted() {
        this.isHighlighted = false;
        this.repaint();
    }

    protected void setColor(Graphics g) {
		if (this.isHighlighted) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
    }

    protected Polygon getArrow(Point centerPoint, int topShift, int bottomShift, int leftShift, int rightShift) {
        int xCoord[] = {centerPoint.x - this.shif + leftShift,
            centerPoint.x,
            centerPoint.x+this.shif + rightShift,
            centerPoint.x};
        int yCoord[] = {centerPoint.y,
            centerPoint.y - this.shif + topShift,
            centerPoint.y,
            centerPoint.y + this.shif + bottomShift};

        return new Polygon(xCoord,yCoord, xCoord.length);
    }


    protected void paintArrow(Graphics g, Point headPoint) {
      
    }

}