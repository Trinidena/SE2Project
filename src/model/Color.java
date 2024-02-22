package model;

public enum Color {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    BLACK("Black"),
    WHITE("White"), 
    PURPLE("Purple");

    private final String displayName;

    Color(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    // Optional: If you want to get a Color by its display name
    public static Color fromDisplayName(String displayName) {
        for (Color color : values()) {
            if (color.displayName.equalsIgnoreCase(displayName)) {
                return color;
            }
        }
        throw new IllegalArgumentException("No color with display name " + displayName + " found");
    }
}
