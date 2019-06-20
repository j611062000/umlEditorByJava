package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import configuration.Configuration.ClickedPositionInShape;

/**
 * AssociationLine
 */
public class CompositionLine extends Line{

    public CompositionLine() {
        super();
    }

    public CompositionLine(Shape heaPort, Shape tailPort) {
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

        } else if (this.getHeadPort().getPosition() == ClickedPositionInShape.Top) {
            arrowCenterY -= (this.shif);
            
        } else if (this.getHeadPort().getPosition() == ClickedPositionInShape.Bottom) {
            arrowCenterY += (this.shif);
            
        } else if (this.getHeadPort().getPosition() == ClickedPositionInShape.Right) {
            arrowCenterX += (this.shif);
            
        }
        
        arrowCenterPoint.setLocation(arrowCenterX, arrowCenterY);
        this.arrow = this.getArrow(arrowCenterPoint,topShift,bottomShift,leftShift,rightShift);
        g.setColor(Color.BLUE);
        g.fillPolygon(this.arrow);
        g.setColor(Color.BLACK);
        g.drawPolygon(this.arrow);
    }
}