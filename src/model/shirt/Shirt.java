package model.shirt;

import java.util.Objects;

import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

/**
 * Represents a general abstract concept of a Shirt. This class defines common
 * properties and functionalities for shirts, such as size, material, color,
 * design, neck style, sleeve length, price, quantity, brand, and description.
 * It provides a foundation for more specific shirt types by enforcing the
 * implementation of certain abstract methods.
 */
public abstract class Shirt {
	protected boolean hasPocket;
	protected String name;
	protected Size shoulderWidth;
	protected Size size;
	protected Size backLength;
	protected Size sleeveLength;
	protected Color color;
	protected NeckStyle neckStyle;
	protected Material material;
	protected String shirtText;

	/**
	 * Initializes a new Shirt object with the specified attributes.
	 * 
	 * @param attributes The attributes used to initialize the shirt.
	 */
	public Shirt(String name, boolean hasPocket, Size shoulderWidth, Size size, Size sleeveLength, Color color,
			NeckStyle collar, Material material, Size backLength, String shirtText) {

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

		if (shoulderWidth == null) {
			isError = true;
			errorMessage += " shoulder ";
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

		if (collar == null) {
			isError = true;
			errorMessage += " collar ";
		}

		if (material == null) {
			isError = true;
			errorMessage += " material ";
		}

		if (backLength == null) {
			isError = true;
			errorMessage += " back length ";
		}

		if (isError) {
			throw new IllegalArgumentException(errorMessage);
		}

		this.setHasPocket(hasPocket);
		this.setName(name);
		this.setShoulderWidth(shoulderWidth);
		this.setSize(size);
		this.setSleeveLength(sleeveLength);
		this.setBackLength(backLength);
		this.setColor(color);
		this.setNeckStyle(collar);
		this.setMaterial(material);

		this.setShirtText(shirtText);
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShirtText() {
		return this.shirtText;
	}

	public void setShirtText(String shirtText) {
		this.shirtText = shirtText;
	}

	public Size getShoulderWidth() {
		return this.shoulderWidth;
	}

	public void setShoulderWidth(Size shoulderWidth) {
		this.shoulderWidth = shoulderWidth;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Size getSleeveLength() {
		return this.sleeveLength;
	}

	public void setSleeveLength(Size sleeveLength) {
		this.sleeveLength = sleeveLength;
	}

	public Size getBackLength() {
		return this.backLength;
	}

	public void setBackLength(Size backLength) {
		this.backLength = backLength;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public NeckStyle getNeckStyle() {
		return neckStyle;
	}

	public void setNeckStyle(NeckStyle neckStyle) {
		this.neckStyle = neckStyle;
	}

	public boolean hasPocket() {
		return hasPocket;
	}

	public void setHasPocket(boolean hasPocket) {
		this.hasPocket = hasPocket;
	}
}
