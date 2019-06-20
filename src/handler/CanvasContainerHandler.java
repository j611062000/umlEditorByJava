package handler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import components.Line;
import components.Shape;
import configuration.Configuration;
import container.*;

public class CanvasContainerHandler {

    public static CanvasContainer cc;
    public static Vector<Shape> shapes= new Vector<Shape>();
    public static Vector<Line> lines= new Vector<Line>();
    public static Map<String, Shape> shapesByName = new HashMap<>();    
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
        shapesByName.put(shape.getName(), shape);
    }

    public static Shape getShapeById(String id) {
        return shapesByName.get(id);
    }

    public static void setShapeToMostTop(Shape shape) {
        if (!shape.affiliatedShapes.isEmpty()) {
            for (Shape affiliate : shape.affiliatedShapes) {
                cc.setComponentZOrder(affiliate, 0);
            }
        }
        cc.setComponentZOrder(shape, 0);
        cc.repaint();
    }

    public static Point mapCoordInShapeToCanvas(MouseEvent mouseEvent) {
        Point locationInCanvas = mouseEvent.getComponent().getLocation();
        Point locationInComponent = mouseEvent.getPoint();
        Point startLocation = new Point((locationInComponent.x+locationInCanvas.x), (locationInComponent.y+locationInCanvas.y));
        
        return startLocation;
    }

    public static boolean isMouseActionOnPorts(MouseEvent mouseEvent) {
        for (Shape s : shapes) {
            for (Shape affiliate : s.affiliatedShapes) {
                if (mouseEvent.getSource() == affiliate) {
                    return true;
                }
            }
        }

        return false;
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

    public static boolean isLeftUpAndRightDownRelation(Point p1, Point p2) {
        // p1 is at left-up corner, and p2 is at right-bottom corner
        if ((p1.x <= p2.x) && (p1.y<=p2.y)) {
            return true;
        }

        return false;
    }

    public static Shape getTheMostFrontShapes(Vector<Shape> shapes) {
        Shape candidateShape = null;
        int minZOrder = Configuration.MAX_Z_ORDER;

        for (Shape shape : shapes) {
            if(cc.getComponentZOrder(shape) <= minZOrder) {
                candidateShape = shape;
                minZOrder = cc.getComponentZOrder(shape);
            }
        }

        return candidateShape;
    }

    public static Shape getMouseReleasedShape(MouseEvent mouseEvent) {

        assert mouseEvent.getComponent() != cc;
        
        Point releasedPointInCanvas = CanvasContainerHandler.mapCoordInShapeToCanvas(mouseEvent);
        Vector<Shape> candidateShapes = new Vector<Shape>();
        
        for (Shape shape : CanvasContainerHandler.shapes) {
            
            Point leftUpCornerOfShape = shape.getLocation();

            if (isLeftUpAndRightDownRelation(leftUpCornerOfShape, releasedPointInCanvas)) {
                int xDifference = releasedPointInCanvas.x - leftUpCornerOfShape.x;
                int yDifference = releasedPointInCanvas.y - leftUpCornerOfShape.y;
                if ((xDifference <= Configuration.DIMENSION_OF_ClASS_DIAGRAM.width) && (yDifference <= Configuration.DIMENSION_OF_ClASS_DIAGRAM.height)) {
                    candidateShapes.add(shape);
                }
            }
        }

        return getTheMostFrontShapes(candidateShapes);
    }
}

