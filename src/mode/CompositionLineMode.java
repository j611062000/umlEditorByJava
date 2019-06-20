package mode;

import java.awt.event.MouseEvent;

import components.CompositionLine;
import configuration.Configuration.Mode;

/**
 * AssociationLineMode
 */
public class CompositionLineMode extends LineMode {

    public CompositionLineMode(Mode m) {
        super(m);
    }

	public void performActionOnRelease(MouseEvent mouseEvent) { 
        super.performActionOnRelease(mouseEvent, new CompositionLine());
    }
}