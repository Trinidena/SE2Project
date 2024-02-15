package model;

public class ShirtAttributes {
    private Size size;
    private Material material;
    private String color;
    private String design;
    private NeckStyle neckStyle;
    private String sleeveLength;
    private double price;
    private int quantity;
    private String brand;
    private String description;

    // Constructor
    public ShirtAttributes(Size size, Material material, String color, String design, NeckStyle neckStyle, String sleeveLength, double price, int quantity, String brand, String description) {
        this.size = size;
        this.material = material;
        this.color = color;
        this.design = design;
        this.neckStyle = neckStyle;
        this.sleeveLength = sleeveLength;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.description = description;
    }

    // Getters and setters for all fields
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    public String getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(String sleeveLength) {
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
}

