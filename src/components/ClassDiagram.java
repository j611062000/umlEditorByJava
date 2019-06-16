package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import configuration.Configuration;
import handler.CanvasContainerHandler;
import listener.ClassDiagramListener;

/**
 * Class
 * TODO: there is bug when clicking on a classDiagram
 */
public class ClassDiagram extends Shape{

    private Vector<Port> ports        = new Vector<Port>();
    private int padding               = Configuration.PADDING;

    public ClassDiagram(Point pointOfCreation) {
        super(Configuration.DIMENSION_OF_ClASS_DIAGRAM);
        super.initPanel(pointOfCreation, dimensionOfSize);
        this.setInitStyle(Color.WHITE);
        this.initPorts(pointOfCreation);
        this.setListener(new ClassDiagramListener());
    }

    @Override
	protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(padding, padding, (int)dimensionOfSize.getWidth() - padding*2, (int)dimensionOfSize.getHeight() - padding*2);
        g.setColor(Color.BLACK);
        g.drawRect(padding, padding, (int)dimensionOfSize.getWidth() - padding*2, (int)dimensionOfSize.getHeight() - padding*2);
    }

    protected void initPorts(Point pointOfCreation) {

        int widthOfRect  = (int) this.dimensionOfSize.getWidth();
        int heightOfRect = (int) this.dimensionOfSize.getHeight();
        int shiftOfPort = (int) Configuration.DIMENSION_OF_PORT.getWidth();
        int shifXOfCanvas = (int) pointOfCreation.getX();
        int shifYOfCanvas = (int) pointOfCreation.getY();
        
        Port topPort    = new Port(new Point(shifXOfCanvas+widthOfRect/2-shiftOfPort/2, shifYOfCanvas-shiftOfPort));
        Port bottomPort = new Port(new Point(shifXOfCanvas+widthOfRect/2-shiftOfPort/2, shifYOfCanvas+heightOfRect));
        Port leftPort   = new Port(new Point(shifXOfCanvas-shiftOfPort, shifYOfCanvas+heightOfRect/2-shiftOfPort/2));
        Port rightPort  = new Port(new Point(shifXOfCanvas+widthOfRect, shifYOfCanvas+heightOfRect/2-shiftOfPort/2));

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