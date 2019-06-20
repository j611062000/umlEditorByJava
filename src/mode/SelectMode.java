package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import components.Line;
import components.Shape;
import configuration.Configuration;
import configuration.Configuration.Mode;
import handler.CanvasContainerHandler;

/**
 * Select
 * TODO: There is a bug when grouping
 */
public class SelectMode extends BasicMode {
    
    private Point latestClickedPointInShape;
    private Point latestClickedPointInCanvas;
    private Vector<Shape> selectedShapes   = new Vector<Shape>();
    private Vector<Integer> existingGroups = new Vector<Integer>();
    private boolean isLatestClickOnShape = false;
    
    private boolean isThisShapeInAGroup (Shape shape) {
        if (!shape.getGroupIndex().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInSameGroup(Shape s1, Shape s2) {
        assert !s1.getGroupIndex().isEmpty(): "s1 is not in a group";
        assert !s2.getGroupIndex().isEmpty(): "s2 is not in a group";

        if (s1.getGroupIndex().lastElement() == s2.getGroupIndex().lastElement()) {
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
                CanvasContainerHandler.setShapeToMostTop(shape);
                shape.performActionWhenClicked();
            }
        }
    }

    
    private void highlightClickedGroup(Shape clickedShape) {
        for (Shape shape : CanvasContainerHandler.shapes) {
            if (this.isThisShapeInAGroup(shape)) {
                if (this.isInSameGroup(clickedShape, shape)) {
                    CanvasContainerHandler.setShapeToMostTop(shape);
                    shape.performActionWhenClicked();
                }
            }
        }
    }

    private void unHighlightAllShapes() {
        for (Shape shape : CanvasContainerHandler.shapes) {
            shape.performActionWhenUnclicked();
        }
    }

    private void unHighlightAllLines() {
        for (Line line : CanvasContainerHandler.lines) {
            line.setunhighlighted();
        }
    }

    private void setlatestClickedPointInShape(Point p) {
        this.latestClickedPointInShape = p;
    }

   

    private boolean isShapeInRangeOfTwoPoints(Shape s, Point clickedPoint, Point releasedPoint) {
        
        Point leftUpPointOfSelectedArea    = new Point(Math.min(clickedPoint.x, releasedPoint.x),Math.min(clickedPoint.y, releasedPoint.y));
        Point rightDownPointOfSelectedArea = new Point(Math.max(clickedPoint.x, releasedPoint.x),Math.max(clickedPoint.y, releasedPoint.y));
        Point leftUpPointOfShaepe   = s.getLocation();
        Point rightDownPointOfShape = new Point(leftUpPointOfShaepe.x+(int)s.dimensionOfSize.getWidth(), leftUpPointOfShaepe.y+(int)s.dimensionOfSize.getHeight());
        
        boolean isLeftUpCornerOfShapeInArea = CanvasContainerHandler.isLeftUpAndRightDownRelation(leftUpPointOfSelectedArea, leftUpPointOfShaepe);
        boolean isRightDownCornerOfShapeInArea = CanvasContainerHandler.isLeftUpAndRightDownRelation(rightDownPointOfShape, rightDownPointOfSelectedArea);
        
        if (isLeftUpCornerOfShapeInArea && isRightDownCornerOfShapeInArea) {
            return true;
        }else {
            return false;
        }
    }

    private void makeNewGroup() {

        Integer newGroupIndex = Configuration.FIRST_NEW_GROUP_INDEX;
        
        if (!this.existingGroups.isEmpty()) {
            newGroupIndex = this.existingGroups.lastElement() + 1;
        }

        for (Shape s : this.selectedShapes) {
            s.getGroupIndex().add(newGroupIndex);
        }

        this.existingGroups.add(newGroupIndex);
    }

    private void dissolveAGroup() {

        int indexOfRemovedGroup = 0;

        if (!this.existingGroups.isEmpty()) {
            for (Shape s : this.selectedShapes) {
                indexOfRemovedGroup = s.getGroupIndex().get(0);
                s.getGroupIndex().remove(s.getGroupIndex().size()-1);
            }
            this.existingGroups.removeElement(indexOfRemovedGroup);
        }

    }

    private Vector<Shape> getClickedShapes(MouseEvent mouseEvent) {
        
        Shape clickedShape = (Shape) mouseEvent.getComponent();
        Vector<Shape> clickedShapes = new Vector<Shape>();
        clickedShapes.add(clickedShape);
        
        // clicked shape is in a group
        if (this.isThisShapeInAGroup(clickedShape)) {
            for (Shape s : CanvasContainerHandler.shapes) {
                if (this.isThisShapeInAGroup(s) && (s!=clickedShape)) {
                    if (this.isInSameGroup(s, clickedShape)) {
                        clickedShapes.add(s);
                    }
                }
            }
        }

        return clickedShapes;
    }

    @Override
    // Add a new ClassDiagram to Canvas
    public void performActionOnPressed(MouseEvent mouseEvent) {

        this.setlatestClickedPointInShape(mouseEvent.getPoint());
        
        // Exception will occur when click on a non-shape object
        if (CanvasContainerHandler.isMouseActionOnShape(mouseEvent)) {
            // TODO: remove casting
            Shape clickedShape = (Shape) mouseEvent.getSource();
            this.isLatestClickOnShape = true;
            
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
            this.unHighlightAllLines();
            this.isLatestClickOnShape = false;
            this.latestClickedPointInCanvas = mouseEvent.getPoint();
        
        }else if (CanvasContainerHandler.isMouseActionOnPorts(mouseEvent)) {
            for (Line line : CanvasContainerHandler.lines) {
                if (line.getHeadPort() == mouseEvent.getSource() || line.getTailPort() == mouseEvent.getSource()) {
                    line.setHighlighted();
                }
                else {
                    line.setunhighlighted();
                }
            }
        }
    }

    @Override
    public void performActionOnDrag(MouseEvent mouseEvent) {

        if (CanvasContainerHandler.isMouseActionOnShape(mouseEvent)) {
            Vector<Shape> clickedShapes = this.getClickedShapes(mouseEvent);
            
            int xCoordOfDragPointInShape = mouseEvent.getX();
            int yCoordOfDragPointInShape = mouseEvent.getY();
            int shiftX = xCoordOfDragPointInShape - this.latestClickedPointInShape.x;
            int shiftY = yCoordOfDragPointInShape - this.latestClickedPointInShape.y;
            
            for (Shape s : clickedShapes) {
                int xCoordOfClickedShapeInCanvas = s.getLocation().x;
                int yCoordOfClickedShapeInCanvas = s.getLocation().y;
                // move the clicked shape
                s.move(xCoordOfClickedShapeInCanvas+shiftX, yCoordOfClickedShapeInCanvas+shiftY);
          
                // move all the affiliated shapes of the clicked Shape
                for (Shape shape : s.affiliatedShapes) {
                    shape.move(shape.getLocation().x + shiftX, shape.getLocation().y+shiftY);

                    for (Line line : shape.affiliatedLines) {
                        if (line.getHeadPort() == shape) {
                            line.updateHeadPort(shape);
                        } else if (line.getTailPort() == shape) {
                            line.updateTailPort(shape);
                        }
                    }
                }
                // move all the affiliated lines of the clicked Shape
            }
        }
    }

    @Override
    public void performActionOnRelease(MouseEvent mouseEvent) {
        
        if (!this.isLatestClickOnShape) {
            this.selectedShapes.clear();
            for (Shape s : CanvasContainerHandler.shapes) {
                if (this.isShapeInRangeOfTwoPoints(s, this.latestClickedPointInCanvas, mouseEvent.getPoint())) {
                    this.highlightClickedShape(s);
                    this.selectedShapes.add(s);
                }
            }

            if (this.selectedShapes.size() >= Configuration.MIN_NUMBER_OF_SHAPES_IN_A_GROUP) {
                this.makeNewGroup();
            }
            // System.out.println(this.existingGroups);
        }
    }
}