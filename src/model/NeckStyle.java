package model;

public enum NeckStyle {
    CREW("Crew Neck"),
    V_NECK("V-Neck"),
    POLO("Polo"),
    SCOOP_NECK("Scoop Neck"),
    HENLEY("Henley"),
    TURTLENECK("Turtleneck");

    private final String style;

    // Constructor
    NeckStyle(String style) {
        this.style = style;
    }

    // Getter
    public String getStyle() {
        return style;
    }
}

