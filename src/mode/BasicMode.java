package mode;

import configuration.Configuration.Mode;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class BasicMode extends JPanel{
	
	protected Mode mode;
	
	protected BasicMode(Mode m) {
		this.setMode(m);
	}

	public void performActionOnClick(MouseEvent mouseEvent) { }

	public void performActionOnDrag(MouseEvent mouseEvent) { }

	public void performActionOnRelease(MouseEvent mouseEvent) { }

	protected void setMode(Mode m) {
		this.mode = m;
	}
	
	public Mode getMode() {
		return this.mode;
	}
}