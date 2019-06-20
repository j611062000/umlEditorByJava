package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import configuration.Configuration;
import configuration.Configuration.ClickedPositionInShape;
import handler.CanvasContainerHandler;
import listener.UseCaseListener;


/**
 * UseCase
 */
public class UseCase extends Shape {

    private Vector<Port> ports = new Vector<Port>();
    private int padding        = Configuration.PADDING;

    public UseCase(Point pointOfCreation) {
        super(Configuration.DIMENSION_OF_USECASE, "UseCase");
        super.initPanel(pointOfCreation, dimensionOfSize);
        this.setInitStyle(Color.WHITE);
        this.initPorts(pointOfCreation);
        this.setListener(new UseCaseListener());
    }

    @Override
	protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(padding, padding, (int)dimensionOfSize.getWidth() - padding*2, (int)dimensionOfSize.getHeight() - padding*2);
        g.setColor(Color.BLACK);
        g.drawOval(padding, padding, (int)dimensionOfSize.getWidth() - padding*2, (int)dimensionOfSize.getHeight() - padding*2);
    }

    protected void initPorts(Point pointOfCreation) {

        int widthOfRect  = this.dimensionOfSize.width;
        int heightOfRect = this.dimensionOfSize.height;
        int shiftOfPort  = Configuration.DIMENSION_OF_PORT.width;
        int shifXOfCanvas = pointOfCreation.x;
        int shifYOfCanvas = pointOfCreation.y;
        
        Port topPort    = new Port(new Point(shifXOfCanvas+widthOfRect/2-shiftOfPort/2, shifYOfCanvas-shiftOfPort), ClickedPositionInShape.Top);
        Port bottomPort = new Port(new Point(shifXOfCanvas+widthOfRect/2-shiftOfPort/2, shifYOfCanvas+heightOfRect), ClickedPositionInShape.Bottom);
        Port leftPort   = new Port(new Point(shifXOfCanvas-shiftOfPort, shifYOfCanvas+heightOfRect/2-shiftOfPort/2), ClickedPositionInShape.Left);
        Port rightPort  = new Port(new Point(shifXOfCanvas+widthOfRect, shifYOfCanvas+heightOfRect/2-shiftOfPort/2), ClickedPositionInShape.Right);

        this.ports.add(topPort);
        this.ports.add(bottomPort);
        this.ports.add(leftPort);
        this.ports.add(rightPort);

        this.addShapeToAffiliates(topPort);
        this.addShapeToAffiliates(bottomPort);
        this.addShapeToAffiliates(leftPort);
        this.addShapeToAffiliates(rightPort);

        this.askHandlerToAddPortsToCanvas();
    }
    
    protected void askHandlerToAddPortsToCanvas() {
        for (Port p : this.ports) {
            CanvasContainerHandler.addPanelToCanvas(p);
        }
    }

    @Override
    public void performActionWhenClicked() {
        for (Port p : this.ports) {
            p.setClickedStyle();
        }
    }

    @Override 
    public void performActionWhenUnclicked() {
        for (Port p : this.ports) {
            p.setUnclickedStyle();
        }
    }
    
}