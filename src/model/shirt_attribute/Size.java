package model.shirt_attribute;

/**
 * Enum representing clothing sizes.
 * This enumeration defines a set of size constants for clothing items, each associated with a specific descriptive label.
 * 
 * @author Trinidad Dena
 */
public enum Size {
    XS("Extra Small"),
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Double Extra Large"),
    XXXL("Triple Extra Large");

    private final String description;

    /**
     * Constructor for the Size enum.
     * Initializes the enum instance with the specified descriptive label for the size.
     *
     * @param description The descriptive label of the size.
     */
    Size(String description) {
        this.description = description;
    }

    /**
     * Gets the descriptive label of the size.
     * 
     * @return The descriptive label of the size.
     */
    public String getDescription() {
        return this.description;
    }
}
