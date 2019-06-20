package configuration;

import java.awt.Dimension;
import java.awt.Font;

public class Configuration {

    private static Boolean debugMode = true;
    
    public static final Integer PADDING                         = 5;
    public static final Integer LENGTH_OF_BUTTON                = 80;
    public static final Integer FIRST_NEW_GROUP_INDEX           = 1;
    public static final Integer MIN_NUMBER_OF_SHAPES_IN_A_GROUP = 2;
    public static final Integer MAX_Z_ORDER = 99;
    public static final Integer INIT_ID_OF_SHAPE = 0;

    public static final Dimension DIMENSION_OF_CANVAS         = new Dimension(1380,950);
    public static final Dimension DIMENSION_OF_ClASS_DIAGRAM  = new Dimension(150,225);
    public static final Dimension DIMENSION_OF_MAIN_CONTAINER = new Dimension(1500, 1000);
	public static final Dimension DIMENSION_OF_PORT           = new Dimension(10,10);
	public static final Dimension DIMENSION_OF_USECASE        = new Dimension(200,150);
    public static final Dimension DIMENSION_OF_LABEL        = new Dimension(150,100);
    
    public static final String MENU_ITME_FUNC_GROUP = "Group";
    public static final String MENU_ITME_FUNC_UNGROUP = "Ungroup";
    public static final String MENU_ITME_FUNC_CHANGE_OBJ_NAME = "Change Object Name";

    public static final Font FONT_FOR_TEXT = new Font("Verdana",1,20);
    
    public enum ClickedPositionInShape {Top, Bottom, Left, Right};

    public enum Mode {

        Select("Select"),
        AssociationLine("AssociationLine"),
        CompositionLine("CompositionLine"),
        DashLine("DashLine"),
        GeneralizationLine("GeneralizationLine"),
        ClassDiagram("ClassDiagram"),
        UseCase("UseCase");
        
        private String stringMode;

        private Mode(String mode) {
            this.stringMode = mode;
        }
        
        public String getStringMode() {
            return this.stringMode;
        }
    }

    public static String getIconPath(String mode) {
        return "icon/" + mode + ".jpg";
    }

    public static void debugPrint(String prefix, Object o) {
        if (debugMode) {
            System.out.println(prefix);
            System.out.println(o);
        }
    }
}