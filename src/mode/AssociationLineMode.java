package mode;

import java.awt.event.MouseEvent;

import components.AssociationLine;
import configuration.Configuration.Mode;

/**
 * AssociationLineMode
 */
public class AssociationLineMode extends LineMode {

    public AssociationLineMode(Mode m) {
        super(m);
    }

	public void performActionOnRelease(MouseEvent mouseEvent) { 
        super.performActionOnRelease(mouseEvent, new AssociationLine());
    }
}