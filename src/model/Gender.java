package model;

/**
 * Enum representing gender categories.
 * This enumeration defines a set of gender constants, each associated with a specific string label.
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String gender; // The string representation of the gender.

    /**
     * Constructor for the Gender enum.
     * Initializes the enum instance with the specified gender label.
     *
     * @param gender The label representing the gender.
     */
    Gender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the string representation of the gender.
     * 
     * @return The string label of the gender.
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Returns the string representation of the gender when the enum is used in a string context.
     * 
     * @return The string label of the gender.
     */
    @Override
    public String toString() {
        return gender;
    }
}
