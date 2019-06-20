package mode;

import java.awt.event.MouseEvent;

import components.GeneralizationLine;
import configuration.Configuration.Mode;

/**
 */
public class GeneralizationLineMode extends LineMode {

    public GeneralizationLineMode(Mode m) {
        super(m);
    }

	public void performActionOnRelease(MouseEvent mouseEvent) { 
        super.performActionOnRelease(mouseEvent, new GeneralizationLine());
    }
}