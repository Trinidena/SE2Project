package model.shirt;

import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

/**
 * Represents a T-Shirt, extending the Shirt class with specific behaviors
 * tailored for T-Shirts. This class provides implementations for customizing
 * the design and calculating the price, along with any additional T-Shirt
 * specific functionalities.
 * 
 * @author Trinidad
 */
public class TShirt extends Shirt {
    
    /**
     * Initializes a new TShirt object using provided shirt attributes.
     *
     * @param name          The name of the T-Shirt.
     * @param hasPocket     Indicates if the T-Shirt has a pocket.
     * @param shoulderWidth The shoulder width size of the T-Shirt.
     * @param size          The overall size of the T-Shirt.
     * @param sleeveLength  The length of the T-Shirt's sleeves.
     * @param color         The color of the T-Shirt.
     * @param neckStyle     The neck style of the T-Shirt.
     * @param material      The material of the T-Shirt.
     * @param backLength    The back length size of the T-Shirt.
     * @param shirtText     The text printed on the T-Shirt, if any.
     */
    public TShirt(String name, boolean hasPocket, Size shoulderWidth, Size size, Size sleeveLength, Color color,
                  NeckStyle neckStyle, Material material, Size backLength, String shirtText) {
        super(name, hasPocket, shoulderWidth, size, sleeveLength, color, neckStyle, material, backLength, shirtText);
    }

    /**
     * Displays T-Shirt specific information along with the common shirt
     * information.
     */
    @Override
    public void displayInformation() {
        super.displayInformation();
    }
}
