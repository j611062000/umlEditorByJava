package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import handler.CoreHandler;

/**
 * ClassDiagramListener
 */
public class UseCaseListener extends MouseAdapter implements MouseMotionListener{

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        CoreHandler.routeMousePressedEventToMode(mouseEvent);
    }   

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        CoreHandler.routeMouseDragEventToMode(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        CoreHandler.routeMouseReleaseEventToMode(mouseEvent);
    }
}   