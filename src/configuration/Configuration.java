package configuration;

import java.awt.Dimension;

public class Configuration {
    public static final Integer LENGTH_OF_BUTTON = 80;
	public static final Integer PADDING = 5;
    public static final Dimension DIMENSION_OF_MAIN_CONTAINER = new Dimension(1500, 1000);
    public enum Mode {
        AssociationLine("AssociationLine"),
        CompositionLine("CompositionLine"),
        DashLine("DashLine"),
        GeneralizationLine("GeneralizationLine"),
        Select("Select"),
        UseCase("UseCase"), 
        Class("Class");
        
        private String stringMode;

        private Mode(String mode) {
            this.stringMode = mode;
        }
        
        public String getStringMode(){
            return this.stringMode;
        }
    }
}