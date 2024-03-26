package model.shirt_attribute;

/**
 * Enum representing common colors with display names.
 * This enumeration defines a list of color constants, each associated with a specific display name.
 */
public enum Color {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    BLACK("Black"),
    WHITE("White"),
    PURPLE("Purple");

    private final String displayName; // The human-readable name of the color.

    /**
     * Constructor for the Color enum.
     * Initializes the enum instance with the specified display name.
     *
     * @param displayName The display name of the color.
     */
    Color(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the color.
     *
     * @return The display name of the color.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name of the color when the enum is used in a string context.
     *
     * @return The display name of the color.
     */
    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Retrieves a Color enum instance by its display name.
     * This method allows for reverse lookup of the enum constant by its display name, ignoring case sensitivity.
     *
     * @param displayName The display name of the color to be retrieved.
     * @return The Color enum constant matching the specified display name.
     * @throws IllegalArgumentException If no color with the specified display name is found.
     */
    public static Color fromDisplayName(String displayName) {
        for (Color color : values()) {
            if (color.displayName.equalsIgnoreCase(displayName)) {
                return color;
            }
        }
        throw new IllegalArgumentException("No color with display name " + displayName + " found");
    }
}
