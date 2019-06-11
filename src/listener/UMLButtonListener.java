package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import components.UMLButton;
import handler.ButtonContainerHandler;

public class UMLButtonListener extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e) {
        ButtonContainerHandler.unselectAllButtons();
        ((UMLButton) e.getSource()).setClickedStyle();
    }   
}