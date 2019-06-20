package mode;

import java.awt.event.MouseEvent;

import components.DashLine;
import configuration.Configuration.Mode;

/**
 */
public class DashLineMode extends LineMode {

    public DashLineMode(Mode m) {
        super(m);
    }

	public void performActionOnRelease(MouseEvent mouseEvent) { 
        super.performActionOnRelease(mouseEvent, new DashLine());
    }
}