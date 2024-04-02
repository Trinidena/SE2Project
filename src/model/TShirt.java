package model;

import java.util.Objects;

/**
 * Represents a T-Shirt, extending the Shirt class with specific behaviors tailored for T-Shirts.
 * This class provides implementations for customizing the design and calculating the price, 
 * along with any additional T-Shirt specific functionalities.
 */
public class TShirt extends Shirt {

    /**
     * Initializes a new TShirt object using provided shirt attributes.
     * 
     * @param attributes The attributes to initialize the TShirt with.
     */
    public TShirt(ShirtAttributes attributes) {
        super(attributes);
    }

    /**
     * Updates the design of the T-Shirt.
     * This method allows for the customization of the T-Shirt's design.
     * 
     * @param design The new design for the T-Shirt.
     */
    @Override
    public void customizeDesign(String design) {
        this.design = design;
    }

    /**
     * Calculates and returns the price of the T-Shirt.
     * The price is determined based on material, design, and other relevant attributes.
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
     * Displays T-Shirt specific information along with the common shirt information.
     */
    @Override
    public void displayInformation() {
        super.displayInformation();
        System.out.println("This T-Shirt is ready to make a statement!");
    }
    
    /**
     * Returns the displayed name of the shirt.
     */
    @Override
    public String toString() {
       
       return this.name;
    }
    
    /**
     * Returns the displayed name of the shirt.
     */
    @Override
    public int hashCode() {
       
       return Objects.hash(this.name);
    }

}
