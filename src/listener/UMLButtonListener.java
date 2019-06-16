package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import components.UMLButton;
import handler.ButtonContainerHandler;

public class UMLButtonListener extends MouseAdapter{
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Set the background of all buttons to Color.WHITE
        ButtonContainerHandler.unselectAllButtons();
        // Set the background of the clicked button to Color.BLACK
        ((UMLButton) e.getSource()).setClickedStyle();
        ((UMLButton) e.getSource()).updateCurrentMode();
    }   
}