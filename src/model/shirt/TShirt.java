package model.shirt;

import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

import java.util.Objects;

import model.shirt.TShirt;

/**
 * Represents a T-Shirt, extending the Shirt class with specific behaviors
 * tailored for T-Shirts. This class provides implementations for customizing
 * the design and calculating the price, along with any additional T-Shirt
 * specific functionalities.
 */
public class TShirt extends Shirt {

	/**
	 * Initializes a new TShirt object using provided shirt attributes.
	 * 
	 * @param attributes The attributes to initialize the TShirt with.
	 */

	public TShirt(int nextInt, Size size, Material material, Color color, Size sleeveLength, double shoulderWidth,
			boolean hasPocket) {
	}

	public TShirt(String name, Size size, Material material, Color color, String text, NeckStyle neckStyle,
			Integer quantity, boolean pocket, Size sleeveLength) {

		boolean isError = false;
		String errorMessage = "The following cannot be empty:";

		if (name != null) {
			if (name.isBlank()) {
				isError = true;
				errorMessage += " name ";
			}
		} else {
			isError = true;
			errorMessage += " name ";
		}

		if (size == null) {
			isError = true;
			errorMessage += " size ";
		}

		if (sleeveLength == null) {
			isError = true;
			errorMessage += " sleeve length ";
		}

		if (color == null) {
			isError = true;
			errorMessage += " color ";
		}

		if (neckStyle == null) {
			isError = true;
			errorMessage += " collar ";
		}

		if (material == null) {
			isError = true;
			errorMessage += " material ";
		}

		if (isError) {
			throw new IllegalArgumentException(errorMessage);
		}

		this.name = name;
		this.size = size;
		this.material = material;
		this.color = color;
		this.shirtText = text;
		this.neckStyle = neckStyle;
		this.quantity = quantity;
		this.hasPocket = pocket;
		this.sleeveLength = sleeveLength;

	}

	/**
	 * Updates the design of the T-Shirt. This method allows for the customization
	 * of the T-Shirt's design.
	 * 
	 * @param design The new design for the T-Shirt.
	 */
	@Override
	public void customizeDesign(String design) {
		this.design = design;
	}

	/**
	 * Calculates and returns the price of the T-Shirt. The price is determined
	 * based on material, design, and other relevant attributes.
	 * 
	 * @return The price of the T-Shirt.
	 */
	@Override
	public double calculatePrice() {
		double basePrice = 20.0; // Base price for a TShirt
		if (this.material == Material.PREMIUM_COTTON) {
			basePrice += 5.0; // Premium material adjustment
		}
		if (!this.design.isEmpty()) {
			basePrice += 10.0; // Custom design adjustment
		}
		return basePrice * this.quantity; // Price adjusted by quantity
	}

	/**
	 * Displays T-Shirt specific information along with the common shirt
	 * information.
	 */
	@Override
	public void displayInformation() {
		super.displayInformation();
		System.out.println("This T-Shirt is ready to make a statement!");
	}

	/**
	 * what the shirt looks like converted to string
	 */
	@Override
	public String toString() {

		return this.name;
	}

	/**
	 * what the shirt looks like converted to string
	 */
	@Override
	public int hashCode() {

		return Objects.hash(this.name);
	}

}
