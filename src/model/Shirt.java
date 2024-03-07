package model;

/**
 * Represents a general abstract concept of a Shirt. This class defines common properties
 * and functionalities for shirts, such as size, material, color, design, neck style,
 * sleeve length, price, quantity, brand, and description. It provides a foundation
 * for more specific shirt types by enforcing the implementation of certain abstract methods.
 */
public abstract class Shirt {
    protected String name;
    protected String shirtText;
    protected Size size;
    protected Material material;
    protected Color color;
    protected String design;
    protected NeckStyle neckStyle;
    protected Size sleeveLength;
    protected double price;
    protected int quantity;
    protected String brand;
    protected String description;
    protected boolean hasPocket;

    /**
     * Initializes a new Shirt object with the specified attributes.
     * 
     * @param attributes The attributes used to initialize the shirt.
     */
    public Shirt(ShirtAttributes attributes) {
        this.name = attributes.getName();
        this.shirtText = attributes.getShirtText();
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
        this.hasPocket = attributes.hasPocket();
    }

    /**
     * Customizes the design of the shirt.
     * 
     * @param design The new design to be applied to the shirt.
     */
    public abstract void customizeDesign(String design);

    /**
     * Calculates and returns the price of the shirt.
     * 
     * @return The price of the shirt.
     */
    public abstract double calculatePrice();

    /**
     * Displays detailed information about the shirt, including all properties.
     */
    public void displayInformation() {
        System.out.println("Shirt Information:");
        System.out.println("Name: " + name);
        System.out.println("Text: " + shirtText);
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
        System.out.println("Has Pocket: " + hasPocket);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShirtText() {
        return shirtText;
    }

    public void setShirtText(String shirtText) {
        this.shirtText = shirtText;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
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

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public NeckStyle getNeckStyle() {
        return neckStyle;
    }

    public void setNeckStyle(NeckStyle neckStyle) {
        this.neckStyle = neckStyle;
    }

    public Size getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(Size sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasPocket() {
        return hasPocket;
    }

    public void setHasPocket(boolean hasPocket) {
        this.hasPocket = hasPocket;
    }
}
