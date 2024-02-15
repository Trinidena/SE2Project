package model;

public class TShirt extends Shirt {

    // Constructor that accepts ShirtAttributes object
    public TShirt(ShirtAttributes attributes) {
        super(attributes);
        // Any TShirt specific initializations can be done here
    }

    // Implement customizeDesign method
    @Override
    public void customizeDesign(String design) {
        // Assume this method updates the design and potentially affects the price
        this.design = design;
        // For simplicity, we're just updating the design here
        // In a real scenario, you might also want to adjust the price based on the design complexity
    }

    // Implement calculatePrice method
    @Override
    public double calculatePrice() {
        // Example calculation, adjust logic as necessary
        double basePrice = 20.0; // Base price for a TShirt
        if (this.material == Material.PREMIUM_COTTON) {
            basePrice += 5.0; // Premium material costs more
        }
        if (!this.design.isEmpty()) {
            basePrice += 10.0; // Added cost for custom designs
        }
        // Further adjustments can be made based on size, quantity, etc.
        return basePrice * this.quantity;
    }

    // TShirt specific methods can be added here

    // Example: a method to display TShirt-specific information
    @Override
    public void displayInformation() {
        super.displayInformation();
        // Add any TShirt-specific information here
        System.out.println("This T-Shirt is ready to make a statement!");
    }
}
