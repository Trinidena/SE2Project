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
    
    private String creatorName;
    private String status;
	private String businesssName;

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
     * @param creatorName   The name of the creator of the T-Shirt.
     * @param status        The current status of the T-Shirt.
     */
    public TShirt(String name, boolean hasPocket, Size shoulderWidth, Size size, Size sleeveLength, Color color,
                  NeckStyle neckStyle, Material material, Size backLength, String shirtText, String creatorName, String status, String businessName) {
        super(name, hasPocket, shoulderWidth, size, sleeveLength, color, neckStyle, material, backLength, shirtText);
        this.creatorName = creatorName;
        this.status = status;
        this.businesssName = businessName;
    }

    /**
     * Gets the name of the creator of the T-Shirt.
     * @return The creator name.
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Sets the name of the creator of the T-Shirt.
     * @param creatorName The creator name to set.
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * Gets the current status of the T-Shirt.
     * @return The status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the T-Shirt.
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
