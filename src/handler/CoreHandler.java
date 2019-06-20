package handler;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;

import configuration.Configuration;
import configuration.Configuration.Mode;
import container.Container;
import container.MainContainer;
import mode.*;
import javax.swing.*;  


public class CoreHandler {
    
    private static MainContainer mc;
    private static Mode currentMode;
    private static Integer xShiftOnFrame = 0;
    private static Vector<Container> containers = new Vector<Container>();
    private static Vector<BasicMode> modes = new Vector<BasicMode>();
    
    public static void init() {

        JMenuBar mb=new JMenuBar();  

        JMenu file =  new JMenu("File");
        JMenu edit =  new JMenu("Edit");

        JMenuItem group = new JMenuItem("Group");  
        JMenuItem unGroup = new JMenuItem("unGroup");  
        JMenuItem changeObjName = new JMenuItem("Change Object Name");  

        registerFuncToMenuItem(group,Configuration.MENU_ITME_FUNC_GROUP);
        registerFuncToMenuItem(unGroup,Configuration.MENU_ITME_FUNC_UNGROUP);

        file.add(group); 
        file.add(unGroup);
        edit.add(changeObjName);  
        mb.add(file);  
        mb.add(edit);  
        
        ButtonContainerHandler.init();
        CanvasContainerHandler.init();
        
        initMainContainer();
        
        containers.add(ButtonContainerHandler.bc);
        containers.add(CanvasContainerHandler.cc);
        
        initAllModes();
        
        // menu bar
        mc.setJMenuBar(mb);  
        addPanelsToFrame(containers , mc);
    }

    private static void registerFuncToMenuItem(JMenuItem jmi, String menuItemFunc) {
        jmi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                routeMenuItemEventToMode(menuItemFunc);
            } 
        });
    }
    
    // Should be appended when this program is extended
    private static void initAllModes() {
        modes.add(new ClassDiagramMode(Mode.ClassDiagram));
        modes.add(new SelectMode(Mode.Select));
        modes.add(new UseCaseMode(Mode.UseCase));
        modes.add(new AssociationLineMode(Mode.AssociationLine));
        modes.add(new CompositionLineMode(Mode.CompositionLine));
        modes.add(new DashLineMode(Mode.DashLine));
        modes.add(new GeneralizationLineMode(Mode.GeneralizationLine));
    }

    public static void routeMenuItemEventToMode(String menuItemFunc) {
        for (BasicMode m : modes) {
            if (m.getMode() == currentMode) {
                m.performActionOnMenuItem(menuItemFunc);
                break;
            }
        }
    }
    
    public static void routeMousePressedEventToMode(MouseEvent mouseEvent) {
        for (BasicMode m : modes) {
            if (m.getMode() == currentMode) {
                m.performActionOnPressed(mouseEvent);
                break;
            }
        }
    }
    
    public static void routeMouseDragEventToMode(MouseEvent mouseEvent) {
        for (BasicMode m : modes) {
            if (m.getMode() == currentMode) {
                m.performActionOnDrag(mouseEvent);
                break;
            }
        }
    }

    public static void routeMouseReleaseEventToMode(MouseEvent mouseEvent) {
        for (BasicMode m : modes) {
            if (m.getMode() == currentMode) {
                m.performActionOnRelease(mouseEvent);
                break;
            }
        }
    }

    public static void updateCurrentMode(Mode mode) {
        currentMode = mode;
    }

    public static Mode getCurrentMode() {
        return currentMode;
    }
    
    private static void initMainContainer() {
        mc = new MainContainer();
    }

    private static void addPanelsToFrame(Vector<Container> containers, JFrame frame) {
        for (Container c : containers) {
            c.xShiftOnFrame = xShiftOnFrame;
            c.setInitStyle();
            frame.add(c);
            xShiftOnFrame += (c.xLength + Configuration.PADDING);
        }
    }
}