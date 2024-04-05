package model.shirt;

import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;
import javafx.scene.image.Image;
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

	public TShirt(String name, boolean hasPocket, Size shoulderWidth, Size size, Size sleeveLength, Color color,
			NeckStyle neckStyle, Material material, Size backLength, String shirtText) {
		super(name, hasPocket, shoulderWidth, size, sleeveLength, color, neckStyle, material, backLength, shirtText);
	}

}
