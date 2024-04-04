package model.shirt;

import java.util.Objects;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

/**
 * Represents a general abstract concept of a Shirt. Defines common properties
 * and functionalities for shirts, such as size, material, color, design, neck style,
 * sleeve length, and more. It provides a foundation for more specific shirt types by
 * enforcing the implementation of certain abstract methods.
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
     * @param neckStyle     The neck style of the shirt.
     * @param material      The material of the shirt.
     * @param backLength    The back length size of the shirt.
     * @param shirtText     The text printed on the shirt, if any.
     */
    public Shirt(String name, boolean hasPocket, Size shoulderWidth, Size size, Size sleeveLength, Color color,
            NeckStyle neckStyle, Material material, Size backLength, String shirtText) {
        this.hasPocket = hasPocket;
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.shoulderWidth = Objects.requireNonNull(shoulderWidth, "Shoulder width cannot be null");
        this.size = Objects.requireNonNull(size, "Size cannot be null");
        this.sleeveLength = Objects.requireNonNull(sleeveLength, "Sleeve length cannot be null");
        this.color = Objects.requireNonNull(color, "Color cannot be null");
        this.neckStyle = Objects.requireNonNull(neckStyle, "Neck style cannot be null");
        this.material = Objects.requireNonNull(material, "Material cannot be null");
        this.backLength = Objects.requireNonNull(backLength, "Back length cannot be null");
        this.shirtText = shirtText;
    }

    /**
     * Displays detailed information about the shirt, including all properties.
     */
    public void displayInformation() {
        System.out.println("Has Pocket: " + this.hasPocket);
        System.out.println("Name: " + this.name);
        System.out.println("Shoulder Width: " + this.shoulderWidth);
        System.out.println("Size: " + this.size);
        System.out.println("Sleeve Length: " + this.sleeveLength);
        System.out.println("Back Length: " + this.backLength);
        System.out.println("Material: " + this.material);
        System.out.println("Color: " + this.color);
        System.out.println("Neck Style: " + this.neckStyle);
        if (this.shirtText != null && !this.shirtText.isBlank()) {
            System.out.println("Shirt Text: " + this.shirtText);
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.shoulderWidth, this.size, this.sleeveLength, this.backLength, this.color,
        		this.neckStyle, this.material, this.hasPocket, this.shirtText);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
        	return false;
        }
        Shirt shirt = (Shirt) obj;
        return this.hasPocket == shirt.hasPocket 
        		&& Objects.equals(this.name, shirt.name) 
        		&& Objects.equals(this.shoulderWidth, shirt.shoulderWidth) 
                && Objects.equals(this.size, shirt.size) 
                && Objects.equals(this.sleeveLength, shirt.sleeveLength) 
                && Objects.equals(this.backLength, shirt.backLength) 
                && Objects.equals(this.color, shirt.color) 
                && Objects.equals(this.neckStyle, shirt.neckStyle) 
                && Objects.equals(this.material, shirt.material) 
                && Objects.equals(this.shirtText, shirt.shirtText);
    }
        
    /**
     * Checks if the shirt has a pocket.
     *
     * @return true if the shirt has a pocket, false otherwise.
     */
    public boolean hasPocket() {
        return this.hasPocket;
    }

    /**
     * Sets whether the shirt has a pocket.
     *
     * @param hasPocket true to indicate the shirt has a pocket, false otherwise.
     */
    public void setHasPocket(boolean hasPocket) {
        this.hasPocket = hasPocket;
    }

    /**
     * Retrieves the name of the shirt.
     *
     * @return The name of the shirt.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the shirt.
     *
     * @param name The name to be set for the shirt.
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
    }

    /**
     * Retrieves the shoulder width size of the shirt.
     *
     * @return The shoulder width size.
     */
    public Size getShoulderWidth() {
        return this.shoulderWidth;
    }

    /**
     * Sets the shoulder width size of the shirt.
     *
     * @param shoulderWidth The shoulder width size to be set.
     */
    public void setShoulderWidth(Size shoulderWidth) {
        this.shoulderWidth = Objects.requireNonNull(shoulderWidth, "Shoulder width cannot be null");
    }

    /**
     * Retrieves the overall size of the shirt.
     *
     * @return The overall size.
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * Sets the overall size of the shirt.
     *
     * @param size The overall size to be set.
     */
    public void setSize(Size size) {
        this.size = Objects.requireNonNull(size, "Size cannot be null");
    }

    /**
     * Retrieves the back length size of the shirt.
     *
     * @return The back length size.
     */
    public Size getBackLength() {
        return this.backLength;
    }

    /**
     * Sets the back length size of the shirt.
     *
     * @param backLength The back length size to be set.
     */
    public void setBackLength(Size backLength) {
        this.backLength = Objects.requireNonNull(backLength, "Back length cannot be null");
    }

    /**
     * Retrieves the sleeve length size of the shirt.
     *
     * @return The sleeve length size.
     */
    public Size getSleeveLength() {
        return this.sleeveLength;
    }

    /**
     * Sets the sleeve length size of the shirt.
     *
     * @param sleeveLength The sleeve length size to be set.
     */
    public void setSleeveLength(Size sleeveLength) {
        this.sleeveLength = Objects.requireNonNull(sleeveLength, "Sleeve length cannot be null");
    }

    /**
     * Retrieves the color of the shirt.
     *
     * @return The color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the shirt.
     *
     * @param color The color to be set.
     */
    public void setColor(Color color) {
        this.color = Objects.requireNonNull(color, "Color cannot be null");
    }

    /**
     * Retrieves the neck style of the shirt.
     *
     * @return The neck style.
     */
    public NeckStyle getNeckStyle() {
        return this.neckStyle;
    }

    /**
     * Sets the neck style of the shirt.
     *
     * @param neckStyle The neck style to be set.
     */
    public void setNeckStyle(NeckStyle neckStyle) {
        this.neckStyle = Objects.requireNonNull(neckStyle, "Neck style cannot be null");
    }

    /**
     * Retrieves the material of the shirt.
     *
     * @return The material.
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Sets the material of the shirt.
     *
     * @param material The material to be set.
     */
    public void setMaterial(Material material) {
        this.material = Objects.requireNonNull(material, "Material cannot be null");
    }

    /**
     * Retrieves the text printed on the shirt, if any.
     *
     * @return The shirt text.
     */
    public String getShirtText() {
        return this.shirtText;
    }

    /**
     * Sets the text to be printed on the shirt.
     *
     * @param shirtText The text to be set.
     */
    public void setShirtText(String shirtText) {
        this.shirtText = shirtText;
    }


}
