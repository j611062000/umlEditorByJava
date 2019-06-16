package listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import components.Shape;
import handler.CanvasContainerHandler;
import handler.CoreHandler;

/**
 * ClassDiagramListener
 */
public class ClassDiagramListener extends MouseAdapter implements MouseMotionListener{

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        CoreHandler.routeMouseClickedEventToMode(mouseEvent);
    }   

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        CoreHandler.routeMouseDragEventToMode(mouseEvent);
    }
}   