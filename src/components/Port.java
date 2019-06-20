package components;

import configuration.Configuration;
import configuration.Configuration.ClickedPositionInShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import listener.PortListener;


/**
 * Port
 */
public class Port extends Shape{

    private Point locationPoint;

    public Port(Point locationPoint, ClickedPositionInShape position) {
        super(Configuration.DIMENSION_OF_PORT, "");
        super.initPanel(locationPoint, dimensionOfSize);
        this.locationPoint = locationPoint;
        this.setInitStyle(Color.BLACK);
        this.position = position;
        this.setListener(new PortListener());
     
    }

    // public ClickedPositionInShape getPosition() {
    //     return this.position;
    // }

    @Override
	protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(this.locationPoint.x, this.locationPoint.y, dimensionOfSize.width, dimensionOfSize.height);
        g.drawRect(this.locationPoint.x, this.locationPoint.y, dimensionOfSize.width, dimensionOfSize.height);
    }

    @Override
    protected void setInitStyle(Color backGroundColor) {
        super.setInitStyle(backGroundColor);
        this.setVisible(false);
    }

    public void setClickedStyle() {
        this.setVisible(true);
    }

    public void setUnclickedStyle() {
        this.setVisible(false);
    }

}