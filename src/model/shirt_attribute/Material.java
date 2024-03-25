package model.shirt_attribute;

/**
 * Enum representing types of materials.
 * This enumeration defines a set of constants for various materials that can be used in the manufacture of clothing or other textile products.
 */
public enum Material {
    COTTON("Cotton"),
    POLYESTER("Polyester"),
    BLEND("Blend"),
    WOOL("Wool"),
    LINEN("Linen"),
    SILK("Silk"), 
    PREMIUM_COTTON("Premium Cotton");

    private final String material; // The name of the material.

    /**
     * Constructor for the Material enum.
     * Initializes the enum instance with the specified material name.
     *
     * @param material The name of the material.
     */
    Material(String material) {
        this.material = material;
    }

    /**
     * Gets the name of the material.
     * 
     * @return The name of the material.
     */
    public String getMaterial() {
        return material;
    }
    
    /**
     * Returns the name of the material when the enum is used in a string context.
     * 
     * @return The name of the material.
     */
    @Override
    public String toString() {
        return material;
    }
}
