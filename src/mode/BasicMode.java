package mode;

import configuration.Configuration.Mode;
import java.awt.event.MouseEvent;

public class BasicMode{
	
	protected Mode mode;
	
	public BasicMode(Mode m) {
		this.setMode(m);
	}

	public void performActionOnPressed(MouseEvent mouseEvent) { }

	public void performActionOnDrag(MouseEvent mouseEvent) { }

	public void performActionOnRelease(MouseEvent mouseEvent) { }

	public void performActionOnMenuItem(String funcOfMenuItem) { }

	public void performActionOnPopWindow(String action) { }
	
	protected void setMode(Mode m) {
		this.mode = m;
	}
	
	public Mode getMode() {
		return this.mode;
	}

}