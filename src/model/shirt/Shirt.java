package model.shirt;

import java.util.Objects;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

/**
 * Represents a general abstract concept of a Shirt. Defines common properties
 * and functionalities for shirts, such as size, material, color, design, neck
 * style, sleeve length, and more. It provides a foundation for more specific
 * shirt types by enforcing the implementation of certain abstract methods.
 * 
 * @author Trinidad Dena
 */
public abstract class Shirt {
	private boolean hasPocket;
	private String name;
	private Size shoulderWidth;
	private Size size;
	private Size backLength;
	private Size sleeveLength;
	private Color color;
	private NeckStyle neckStyle;
	private Material material;
	private String shirtText;

	/**
	 * Initializes a new Shirt object with the specified attributes.
	 * 
	 * @param name          The name of the shirt.
	 * @param hasPocket     Indicates if the shirt has a pocket.
	 * @param shoulderWidth The shoulder width size of the shirt.
	 * @param size          The overall size of the shirt.
	 * @param sleeveLength  The length of the shirt's sleeves.
	 * @param color         The color of the shirt.
	 * @param collar     The neck style of the shirt.
	 * @param material      The material of the shirt.
	 * @param backLength    The back length size of the shirt.
	 * @param shirtText     The text printed on the shirt, if any.
	 */
	public Shirt(String name, boolean hasPocket, Size shoulderWidth, Size size, 
			Size sleeveLength, Color color, NeckStyle collar, Material material, 
			Size backLength, String shirtText) {
		
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
		this.setColor(color);
		this.setNeckStyle(collar);
		this.setMaterial(material);
		this.setBackLength(backLength);
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
		return this.name;
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
		return this.size;
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
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public NeckStyle getNeckStyle() {
		return this.neckStyle;
	}

	public void setNeckStyle(NeckStyle neckStyle) {
		this.neckStyle = neckStyle;
	}

	public boolean hasPocket() {
		return this.hasPocket;
	}

	public void setHasPocket(boolean hasPocket) {
		this.hasPocket = hasPocket;
	}
}
