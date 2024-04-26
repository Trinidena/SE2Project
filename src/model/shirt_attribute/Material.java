package model.shirt_attribute;

/**
 * Enum representing types of materials. This enumeration defines a set of
 * constants for various materials that can be used in the manufacture of
 * clothing or other textile products.
 * 
 * @author Trinidad Dena
 */
public enum Material {
	COTTON("Cotton"), POLYESTER("Polyester"), BLEND("Blend"), WOOL("Wool"), LINEN("Linen"), SILK("Silk"),
	PREMIUM_COTTON("Premium Cotton");

	private final String material;

	/**
	 * Constructor for the Material enum. Initializes the enum instance with the
	 * specified material name.
	 *
	 * @param material The name of the material.
	 */
	Material(String material) {
		this.material = material;
	}

	public String getMaterial() {
		return this.material;
	}

}
