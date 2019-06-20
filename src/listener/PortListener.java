package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import handler.CoreHandler;

public class PortListener extends MouseAdapter implements MouseMotionListener{

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        CoreHandler.routeMousePressedEventToMode(mouseEvent);
    }
}