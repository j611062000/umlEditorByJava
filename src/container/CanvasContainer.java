package container;

import java.awt.event.MouseAdapter;

import configuration.Configuration;
import listener.CanvasListener;

public class CanvasContainer extends Container{
    
    public CanvasContainer() {
        this.setXYLength();        
        this.setInitStyle(this.xLength, this.yLength);
        this.initChildComponents();
        this.setListener(new CanvasListener());
        this.setLayout(null);
    }

    @Override
    protected void setXYLength() {
        this.xLength = Configuration.DIMENSION_OF_CANVAS.width;
        this.yLength = Configuration.DIMENSION_OF_CANVAS.height;
    }

    private void setListener(MouseAdapter listener) {
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }
}