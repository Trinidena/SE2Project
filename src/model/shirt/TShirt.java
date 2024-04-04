package model.shirt;

import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;
<<<<<<< HEAD
=======
import javafx.scene.image.Image;

import java.util.Objects;

>>>>>>> feature/viewmodel2
import model.shirt.TShirt;

/**
 * Represents a T-Shirt, extending the Shirt class with specific behaviors
 * tailored for T-Shirts. This class provides implementations for customizing
 * the design and calculating the price, along with any additional T-Shirt
 * specific functionalities.
 */
public class TShirt extends Shirt {
<<<<<<< HEAD
	
    /**
     * Initializes a new TShirt object using provided shirt attributes.
     * 
     * @param attributes The attributes to initialize the TShirt with.
     */

	public TShirt(boolean hasPocket, String name, Size shoulderWidth, Size size, Size backLength, Color color, NeckStyle neckStyle, Material material) {
        super(hasPocket, name, shoulderWidth, size, backLength, color, neckStyle, material);
    }
    /**
     * Displays T-Shirt specific information along with the common shirt information.
     */
    @Override
    public void displayInformation() {
        super.displayInformation();
    }
=======

	private Image image;

	/**
	 * Initializes a new TShirt object using provided shirt attributes.
	 * 
	 * @param attributes The attributes to initialize the TShirt with.
	 */

	public TShirt(boolean hasPocket, String name, Size shoulderWidth, Size size, Size sleeveLength, Color color,
			NeckStyle collar, Material material, Size backLength, String shirtText, Image image) {
		super(hasPocket, name, shoulderWidth, size, sleeveLength, color, collar, material, backLength, shirtText);

		this.image = image;

	}

	/**
	 * Displays T-Shirt specific information along with the common shirt
	 * information.
	 */
	@Override
	public void displayInformation() {
		super.displayInformation();
		System.out.println("Image: " + image);
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
>>>>>>> feature/viewmodel2

}
