package handler;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import components.Shape;
import container.*;

public class CanvasContainerHandler {

    public static CanvasContainer cc;
    public static Vector<Shape> shapes= new Vector<Shape>();
    
    public static void init() {
        initContainer();
    }
    
    public static void initContainer() {
        cc = new CanvasContainer();
    }

    public static void repaintComponents() {
        cc.repaint();
    }

    public static void addPanelToCanvas(JPanel jp) {
        // Add a JPanel to the top of every JPanel
        cc.add(jp,0);
        cc.repaint();
    }

    public static void registerShapeInCanvas(Shape shape) {
        shapes.add(shape);
    }

    public static void setComponentToMostTop(Component c) {
        cc.setComponentZOrder(c, 0);
        cc.repaint();
    }

    public static Point mapCoordInShapeToCanvas(MouseEvent mouseEvent) {
        Point locationInCanvas = mouseEvent.getComponent().getLocation();
        Point locationInComponent = mouseEvent.getPoint();
        Point startLocation = new Point(((int)(locationInComponent.x+locationInCanvas.x)), ((int)(locationInComponent.y+locationInCanvas.y)));
        
        return startLocation;
    }

    public static boolean isMouseActionOnShape(MouseEvent mouseEvent) {
        for (Shape s : shapes) {
            if (mouseEvent.getSource() == s) {
                return true;
            }
        }

        return false;
    }

    public static boolean isMouseActionOnCanvas(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == cc) {
            return true;
        }else {
            return false;
        }
    }
}

