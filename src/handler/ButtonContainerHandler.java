package handler;

import container.*;

// TODO: When the current mode is changed, the class diagram shoud be unselected
public class ButtonContainerHandler {

    public static ButtonContainer bc;
    
    public static void init() {
        initButtonContainer();
    }

    public static void unselectAllButtons() {
        bc.unSelectButtons();
    }

    public static void repaintComponents() {
        bc.repaint();
    }

    private static void initButtonContainer() {
        bc = new ButtonContainer();
    }
}

