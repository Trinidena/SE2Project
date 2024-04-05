package model.shirt_attribute;

/**
 * Enum representing common colors with display names. This enumeration defines
 * a list of color constants, each associated with a specific display name.
 */
public enum Color {
	RED("Red"), GREEN("Green"), BLUE("Blue"), YELLOW("Yellow"), BLACK("Black"), WHITE("White"), PURPLE("Purple");

	private final String displayName; // The human-readable name of the color.

	/**
	 * Constructor for the Color enum. Initializes the enum instance with the
	 * specified display name.
	 *
	 * @param displayName The display name of the color.
	 */
	Color(String displayName) {
		this.displayName = displayName;
	}

}
