package model;

public abstract class Shirt {
    // Class fields
    protected Size size;
    protected Material material;
    protected String color;
    protected String design;
    protected NeckStyle neckStyle;
    protected String sleeveLength;
    protected double price;
    protected int quantity;
    protected String brand;
    protected String description;

    // Constructor and other methods...
 // Constructor now accepts a ShirtAttributes object
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

    // Abstract methods that subclasses must implement
    public abstract void customizeDesign(String design);
    public abstract double calculatePrice();

    // Concrete method to display shirt information
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
    // Getters
    public Size getSize() {
        return size;
    }

    public Material getMaterial() {
        return material;
    }

    public String getColor() {
        return color;
    }

    public String getDesign() {
        return design;
    }

    public NeckStyle getNeckStyle() {
        return neckStyle;
    }

    public String getSleeveLength() {
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

    // Setters
    public void setSize(Size size) {
        this.size = size;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public void setNeckStyle(NeckStyle neckStyle) {
        this.neckStyle = neckStyle;
    }

    public void setSleeveLength(String sleeveLength) {
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
