package model;

public class ShirtAttributes {
    private Size size;
    private Material material;
    private Color color;
    private String design;
    private NeckStyle neckStyle;
    private double sleeveLength;
    private double price;
    private int quantity;
    private String brand;
    private String description;
	private double shoulderWidth;
	private double backLength;
	private Object chestWidth;
	private Object shirtLength;

	public ShirtAttributes(Size size, double shoulderWidth, double backLength) {
		this.size = size;
        this.shoulderWidth = shoulderWidth;
        this.backLength = backLength;
	}

    public ShirtAttributes(Size size, Material material, Color color, double sleeveLength, double backLength, double chestWidth, double shoulderWidth) {
    	 this.size = size;
         this.material = material;
         this.color = color;
         this.shoulderWidth = shoulderWidth;
         this.chestWidth = chestWidth;
         this.backLength = backLength;
         this.sleeveLength = sleeveLength;
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

    public double getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(double sleeveLength) {
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
    
    public static ShirtAttributes createPresetForSize(Size size) {
        switch (size) {
            case XS:
                return new ShirtAttributes(Size.XS, 17.0, 28.0);
            case S:
                return new ShirtAttributes(Size.S, 18.0, 29.0);
            case M:
                return new ShirtAttributes(Size.M, 19.0, 30.0);
            case L:
                return new ShirtAttributes(Size.L, 20.0, 31.0);
            case XL:
                return new ShirtAttributes(Size.XL, 21.0, 32.0);
            case XXL:
                return new ShirtAttributes(Size.XXL, 22.0, 33.0);
            case XXXL:
                return new ShirtAttributes(Size.XXXL, 23.0, 34.0);
            default:
                throw new IllegalArgumentException("No Valid Shirt Size"); // Or throw an exception, based on your error handling strategy
        }
    }


	public double getShoulderWidth() {
		return shoulderWidth;
	}

	public boolean hasPocket() {
		return false;
	}

	public double getBackLength() {
		return backLength;
	}
}

