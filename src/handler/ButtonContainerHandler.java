package handler;

import container.*;

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

