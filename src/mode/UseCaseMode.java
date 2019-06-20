package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import components.UseCase;
import configuration.Configuration.Mode;
import handler.CanvasContainerHandler;

/**
 * UseCase
 */
public class UseCaseMode extends BasicMode{
	public UseCaseMode(Mode m) {
			super(m);
	}

	@Override
	// Add a new ClassDiagram to Canvas
	public void performActionOnPressed(MouseEvent mouseEvent) {
			
			Point p = mouseEvent.getPoint();

			if (!mouseEvent.getSource().equals(CanvasContainerHandler.cc)) {
					// Map the clicked coordination on shape to the coordination on canvas
					p = CanvasContainerHandler.mapCoordInShapeToCanvas(mouseEvent);
			}
							
			UseCase useCase = new UseCase(p);
			CanvasContainerHandler.addPanelToCanvas(useCase);
			CanvasContainerHandler.registerShapeInCanvas(useCase);
}
}