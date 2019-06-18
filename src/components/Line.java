package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Line
 */
public class Line extends JPanel{
    
    private Shape headPort; // arrow side
    private Shape tailPort; // non-arrow side
    private boolean isHighlighted = false;
    private Dimension dimensionOfJComponent;

    private Point upperLeftPointOfPanel;

    public Line(Shape heaPort, Shape tailPort) {
        this.tailPort = tailPort;
        this.headPort = heaPort;
        this.resizeUnderlyingPanel();
        this.setLocation(200, 200);
        this.setLayout(null);
        this.setOpaque(false);
		this.setVisible(true);
    }

    public void updateHeadPort(Shape newHeadPort) {
        this.headPort = newHeadPort;
        this.resizeUnderlyingPanel();
        this.repaint();
    }
    
    public void updateTailPort(Shape newTailPort) {
        this.tailPort = newTailPort;
        this.resizeUnderlyingPanel();
        this.repaint();
    }

    @Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
		Point headPoint = this.headPort.getLocation();
        Point tailPoint = this.tailPort.getLocation();
        
		this.setColor(g);
        this.paintArrow(g, headPoint);
        
        g.drawLine(0,0,Math.abs(headPoint.x-tailPoint.x),Math.abs(headPoint.y-tailPoint.y));
    }

    private void resizeUnderlyingPanel() {
        // this.dimensionOfJComponent = new Dimension(Math.abs(this.tailPort.getX() - this.))
        this.dimensionOfJComponent = new Dimension(600,600);
        this.setSize(dimensionOfJComponent);
        this.repaint();
    }

    private void setColor(Graphics g) {
		if (this.isHighlighted) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
    }
    
    protected void paintArrow(Graphics g, Point p) {

    }
}