package mode;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import components.Line;
import components.Port;
import components.Shape;
import configuration.Configuration.Mode;
import handler.CanvasContainerHandler;

/**
 * AssociationLineMode
 */
public class LineMode extends BasicMode {

    private Point headPoint;
    private Point tailPoint;
    private Shape headShape;
    private Shape tailShape;
    private Shape  headPort; 
    private Shape  tailPort; 


    public LineMode(Mode m) {
        super(m);
        // TODO Auto-generated constructor stub
    }

    public void performActionOnPressed(MouseEvent mouseEvent) { 
        if(CanvasContainerHandler.isMouseActionOnShape(mouseEvent)){
            this.tailPoint = CanvasContainerHandler.mapCoordInShapeToCanvas(mouseEvent);
            this.tailShape = (Shape) mouseEvent.getComponent();
            this.tailPort = this.tailShape.getNearestAffiliate(mouseEvent.getPoint());
        }
    }

	public void performActionOnRelease(MouseEvent mouseEvent, Line line) { 
        if(CanvasContainerHandler.isMouseActionOnShape(mouseEvent)){
            this.headShape = CanvasContainerHandler.getMouseReleasedShape(mouseEvent);
            this.headPoint = CanvasContainerHandler.mapCoordInShapeToCanvas(mouseEvent);
            Point releasePointInShape = new Point(this.headPoint.x - this.headShape.getX(), this.headPoint.y - this.headShape.getY());
            this.headPort = this.headShape.getNearestAffiliate(releasePointInShape);
            
            // Line newLine = new Line(this.headPort, this.tailPort);
            line.updateHeadAndTailPort(this.headPort, this.tailPort);
            CanvasContainerHandler.addPanelToCanvas(line);
            this.tailPort.addLineToAffiliates(line);
            this.headPort.addLineToAffiliates(line);
            CanvasContainerHandler.lines.add(line);
        }
    }
}