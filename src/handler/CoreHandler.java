package handler;

import javax.swing.JFrame;
import javax.swing.JPanel;

import container.MainContainer;

public class CoreHandler {

    private static MainContainer mc;
    
    public static void init() {
        ButtonContainerHandler.init();
        initMainContainer();
        addPanelToFrame(ButtonContainerHandler.bc , mc);
	}
    
    private static void initMainContainer() {
        mc = new MainContainer();
    }

    private static void addPanelsToFrame(Vector<JPanel> panel, JFrame frame) {
        frame.add(panel);
    }
}