package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import components.Shape;
import configuration.Configuration;
import configuration.Configuration.Mode;
import handler.CanvasContainerHandler;

/**
 * Select
 */
public class SelectMode extends BasicMode {
    
    private Point latestClickedPointInShape;
    private Point latestClickedPointInCanvas;
    private Vector<Shape> selectedShapes   = new Vector<Shape>();
    private Vector<Integer> existingGroups = new Vector<Integer>();
    private boolean isLatestClickOnShape = false;
    
    private boolean isThisShapeInAGroup (Shape shape) {
        if (shape.getGroupIndex().size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public SelectMode(Mode m) {
        super(m);
    }
    
    public void isLatestClickOnShape(boolean b) {
        this.isLatestClickOnShape = b;
    }

    private void highlightClickedShape(Shape clickedShape) {
        for (Shape shape : CanvasContainerHandler.shapes) {
            if (clickedShape.equals(shape)) {
                CanvasContainerHandler.setComponentToMostTop(shape);
                shape.performActionWhenClicked();
            }
        }
    }
    
    private void highlightClickedGroup(Shape clickedShape) {
        for (Shape shape : CanvasContainerHandler.shapes) {
            if (clickedShape.getGroupIndex().lastElement() == shape.getGroupIndex().lastElement()) {
                CanvasContainerHandler.setComponentToMostTop(shape);
                shape.performActionWhenClicked();
            }
        }
    }

    private void unHighlightAllShapes() {
        for (Shape shape : CanvasContainerHandler.shapes) {
            shape.performActionWhenUnclicked();
        }
    }

    private void setlatestClickedPointInShape(Point p) {
        this.latestClickedPointInShape = p;
    }

    private boolean isLeftUpAndRightDownRelation(Point p1, Point p2) {
        // p1 is at left-up corner, and p2 is at right-bottom corner
        if ((p1.x <= p2.x) && (p1.y<=p2.y)) {
            return true;
        }

        return false;
    }

    private boolean isShapeInRange(Shape s, Point clickedPoint, Point releasedPoint) {
        
        Point leftUpPointOfSelectedArea    = new Point(Math.min(clickedPoint.x, releasedPoint.x),Math.min(clickedPoint.y, releasedPoint.y));
        Point rightDownPointOfSelectedArea = new Point(Math.max(clickedPoint.x, releasedPoint.x),Math.max(clickedPoint.y, releasedPoint.y));
        Point leftUpPointOfShaepe = s.getLocation();
        Point rightDownPointOfShape = new Point(leftUpPointOfShaepe.x+(int)s.dimensionOfSize.getWidth(), leftUpPointOfShaepe.y+(int)s.dimensionOfSize.getHeight());
        
        if (this.isLeftUpAndRightDownRelation(leftUpPointOfSelectedArea, leftUpPointOfShaepe) && this.isLeftUpAndRightDownRelation(rightDownPointOfShape, rightDownPointOfSelectedArea)) {
            return true;
        }else {
            return false;
        }
    }

    private void makeNewGroup() {

        Integer newGroupIndex = Configuration.FIRST_NEW_GROUP_INDEX;
        
        if (this.existingGroups.size() > 0) {
            newGroupIndex = this.existingGroups.lastElement() + 1;
        }

        for (Shape s : this.selectedShapes) {
            s.getGroupIndex().add(newGroupIndex);
        }

        this.existingGroups.add(newGroupIndex);
    }

    private Vector<Shape> getClickedShapes(MouseEvent mouseEvent) {
        
        Shape clickedShape = (Shape) mouseEvent.getComponent();
        Vector<Shape> clickedShapes = new Vector<Shape>();
        clickedShapes.add(clickedShape);
        
        if (clickedShape.getGroupIndex().size() > 0) {
            for (Shape s : CanvasContainerHandler.shapes) {
                if (s.getGroupIndex().lastElement() == clickedShape.getGroupIndex().lastElement()) {
                    if (s!=clickedShape) {
                        clickedShapes.add(s);
                    }
                }
            }
        }

        return clickedShapes;
    }

    @Override
    // Add a new ClassDiagram to Canvas
    public void performActionOnClick(MouseEvent mouseEvent) {

        this.setlatestClickedPointInShape(mouseEvent.getPoint());
        
        // Exception will occur when click on a non-shape object
        if (CanvasContainerHandler.isMouseActionOnShape(mouseEvent)) {
            Shape clickedShape = (Shape) mouseEvent.getSource();
    
            this.unHighlightAllShapes();
            if (!isThisShapeInAGroup(clickedShape)) {
                this.highlightClickedShape(clickedShape);
            } 
            else if(isThisShapeInAGroup(clickedShape)) {
                this.highlightClickedGroup(clickedShape);
            }
        }
        // Set all shapes to unselected status 
        else if (CanvasContainerHandler.isMouseActionOnCanvas(mouseEvent)) {
            this.unHighlightAllShapes();
            this.isLatestClickOnShape = false;
            this.latestClickedPointInCanvas = mouseEvent.getPoint();
        }
    }

    @Override
    public void performActionOnDrag(MouseEvent mouseEvent) {

        if (CanvasContainerHandler.isMouseActionOnShape(mouseEvent)) {
            Vector<Shape> clickedShapes = this.getClickedShapes(mouseEvent);
            // Shape clickedShape = (Shape) mouseEvent.getComponent();
            
            int xCoordOfDragPointInShape = mouseEvent.getX();
            int yCoordOfDragPointInShape = mouseEvent.getY();
            
            int shiftX = xCoordOfDragPointInShape - this.latestClickedPointInShape.x;
            int shiftY = yCoordOfDragPointInShape - this.latestClickedPointInShape.y;
            
            for (Shape s : clickedShapes) {

                int xCoordOfClickedShapeInCanvas = s.getLocation().x;
                int yCoordOfClickedShapeInCanvas = s.getLocation().y;
                // move the clicked shape
                s.move(xCoordOfClickedShapeInCanvas+shiftX, yCoordOfClickedShapeInCanvas+shiftY);
          
                // move all the affiliates of the clicked Shape
                for (Shape affiliate : s.affiliates) {
                    affiliate.move(affiliate.getLocation().x + shiftX, affiliate.getLocation().y+shiftY);
                }
        
            }
    
        }
        else if (CanvasContainerHandler.isMouseActionOnCanvas(mouseEvent)) {

        }
    }

    @Override
    public void performActionOnRelease(MouseEvent mouseEvent) {
        
        if (!this.isLatestClickOnShape) {
            this.selectedShapes.clear();
            for (Shape s : CanvasContainerHandler.shapes) {
                if (this.isShapeInRange(s, this.latestClickedPointInCanvas, mouseEvent.getPoint())) {
                    this.highlightClickedShape(s);
                    this.selectedShapes.add(s);
                }
            }

            if (this.selectedShapes.size() >= Configuration.MIN_NUMBER_OF_SHAPES_IN_A_GROUP) {
                this.makeNewGroup();
            }
            System.out.println(this.existingGroups);
        }
    }
}