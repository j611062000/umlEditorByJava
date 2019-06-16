package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import components.ClassDiagram;
import configuration.Configuration.Mode;
import handler.CanvasContainerHandler;

/**
 * Class
 */
public class ClassDiagramMode extends BasicMode{
    public ClassDiagramMode(Mode m) {
        super(m);
    }

    @Override
    // Add a new ClassDiagram to Canvas
    public void performActionOnClick(MouseEvent mouseEvent) {
        
        Point p = mouseEvent.getPoint();

        if (!mouseEvent.getSource().equals(CanvasContainerHandler.cc)) {
            // Map the clicked coordination on shape to the coordination on canvas
            p = CanvasContainerHandler.mapCoordInShapeToCanvas(mouseEvent);
        }
                
        ClassDiagram classDiagram = new ClassDiagram(p);
        CanvasContainerHandler.addPanelToCanvas(classDiagram);
        CanvasContainerHandler.registerShapeInCanvas(classDiagram);
	}
}