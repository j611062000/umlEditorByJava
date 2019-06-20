package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import configuration.Configuration;
import configuration.Configuration.ClickedPositionInShape;

/**
 * AssociationLine
 */
public class DashLine extends Line{

    public DashLine() {
        super();
    }

    public DashLine(Shape heaPort, Shape tailPort) {
        super(heaPort, tailPort);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void paintArrow(Graphics g, Point headPoint) { 
        
        int halfLengthOfPort = 2;
        Point arrowCenterPoint = new Point(headPoint.x +halfLengthOfPort, headPoint.y+halfLengthOfPort);
        int arrowCenterX = arrowCenterPoint.x +halfLengthOfPort;
        int arrowCenterY = arrowCenterPoint.y +halfLengthOfPort;
        int leftShift  = 0;
        int rightShift = 0;
        int topShift   = 0;
        int bottomShift = 0;

        if (this.getHeadPort().getPosition() == ClickedPositionInShape.Left) {
            arrowCenterX -= (this.shif);
            leftShift    += this.shif;

        } else if (this.getHeadPort().getPosition() == ClickedPositionInShape.Top) {
            arrowCenterY -= (this.shif);
            topShift     += this.shif;
            
        } else if (this.getHeadPort().getPosition() == ClickedPositionInShape.Bottom) {
            arrowCenterY += (this.shif);
            bottomShift  -= this.shif;
            
        } else if (this.getHeadPort().getPosition() == ClickedPositionInShape.Right) {
            arrowCenterX += (this.shif);
            rightShift   -= this.shif;
        }
        
        arrowCenterPoint.setLocation(arrowCenterX, arrowCenterY);
        this.arrow = this.getArrow(arrowCenterPoint,topShift,bottomShift,leftShift,rightShift);
        g.setColor(Color.BLUE);
        g.fillPolygon(this.arrow);
        g.setColor(Color.BLACK);
        g.drawPolygon(this.arrow);
    }

    
    @Override
	public void paintComponent(Graphics g)
	{
        Graphics2D g2D = (Graphics2D) g;	
        Stroke bs  = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{16,   4},   0);     
        g2D.setStroke(bs);
        int shif = Configuration.DIMENSION_OF_PORT.height / 2;
		this.setColor(g);
        g.drawLine(this.innerTailPoint.x + shif,
                   this.innerTailPoint.y + shif,
                   this.innerHeadPoint.x + shif,
                   this.innerHeadPoint.y + shif);
        this.paintArrow(g, this.innerHeadPoint);
    }
    
}