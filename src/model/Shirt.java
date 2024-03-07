package model;

/**
 * Abstract class representing a general shirt.
 * This class defines common properties and functionalities for shirts, such as size, material, color,
 * design, neck style, sleeve length, price, quantity, brand, and description.
 */
public abstract class Shirt {
    // Class fields
	protected String name;
	protected String shirtText;
    protected Size size;
    protected Material material;
    protected Color color;
    protected String design;
    protected NeckStyle neckStyle;
    protected double sleeveLength;
    protected double price;
    protected int quantity;
    protected String brand;
    protected String description;

    /**
     * Constructor that initializes a Shirt object with attributes defined in a ShirtAttributes object.
     * @param attributes ShirtAttributes object containing initialization values for the Shirt object.
     */
    public Shirt(ShirtAttributes attributes) {
        this.size = attributes.getSize();
        this.material = attributes.getMaterial();
        this.color = attributes.getColor();
        this.design = attributes.getDesign();
        this.neckStyle = attributes.getNeckStyle();
        this.sleeveLength = attributes.getSleeveLength();
        this.price = attributes.getPrice();
        this.quantity = attributes.getQuantity();
        this.brand = attributes.getBrand();
        this.description = attributes.getDescription();
    }

    /**
     * Abstract method for customizing the design of the shirt.
     * Implementations should define how the shirt design is customized.
     * @param design The new design of the shirt.
     */
    public abstract void customizeDesign(String design);
    
    /**
     * Abstract method to calculate the price of the shirt.
     * Implementations should define the pricing logic based on shirt properties.
     * @return The price of the shirt.
     */
    public abstract double calculatePrice();

    /**
     * Displays information about the shirt.
     * Prints details of the shirt's properties to the console.
     */
    public void displayInformation() {
        System.out.println("Shirt Information:");
        System.out.println("Size: " + size);
        System.out.println("Material: " + material);
        System.out.println("Color: " + color);
        System.out.println("Design: " + design);
        System.out.println("Neck Style: " + neckStyle);
        System.out.println("Sleeve Length: " + sleeveLength);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Brand: " + brand);
        System.out.println("Description: " + description);
    }
    
    public String getName() {
    	return name;
    }
    
    public String getShirtText() {
    	return this.shirtText;
    }
    public Size getSize() {
        return size;
    }

    public Material getMaterial() {
        return material;
    }

    public Color getColor() {
        return color;
    }

    public String getDesign() {
        return design;
    }

    public NeckStyle getNeckStyle() {
        return neckStyle;
    }

    public double getSleeveLength() {
        return sleeveLength;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
    	this.name = name;
    }
    public void setShirtText(String shirtText) {
    	this.shirtText = shirtText;
    }
    public void setSize(Size size) {
        this.size = size;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public void setNeckStyle(NeckStyle neckStyle) {
        this.neckStyle = neckStyle;
    }

    public void setSleeveLength(double sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
