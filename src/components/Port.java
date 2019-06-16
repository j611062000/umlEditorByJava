package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import configuration.Configuration;

/**
 * Port
 */
public class Port extends Shape{

    private Point locationPoint;

    public Port(Point locationPoint) {
        super(Configuration.DIMENSION_OF_PORT);
        super.initPanel(locationPoint, dimensionOfSize);
        this.locationPoint = locationPoint;
        this.setInitStyle(Color.BLACK);
    }

    @Override
	protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(this.locationPoint.x, this.locationPoint.y, (int)dimensionOfSize.getWidth(), (int)dimensionOfSize.getHeight());
        g.drawRect(this.locationPoint.x, this.locationPoint.y, (int)dimensionOfSize.getWidth(), (int)dimensionOfSize.getHeight());
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