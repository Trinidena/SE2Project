package model;

/**
 * Represents the attributes of a shirt in a clothing model.
 * This class includes properties such as size, material, color, design, neck style, 
 * sleeve length, price, quantity, brand, description, shoulder width, back length, chest width,
 * shirt length, ID, and pocket presence.
 */
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
	private int id;
	private boolean hasPockets;

	/**
     * Default constructor. Initializes a new instance of the ShirtAttributes class without setting any properties.
     */
	public ShirtAttributes() {
	}
	
	/**
     * Initializes a new instance of the ShirtAttributes class with specific size, shoulder width, and back length.
     *
     * @param size           The size of the shirt.
     * @param shoulderWidth  The width of the shirt at the shoulders.
     * @param backLength     The length of the shirt from the back.
     */
	public ShirtAttributes(Size size, double shoulderWidth, double backLength) {
		this.size = size;
        this.shoulderWidth = shoulderWidth;
        this.backLength = backLength;
	}
	
    /**
     * Initializes a new instance of the ShirtAttributes class with ID, size, material, color, back length, shoulder width, and pocket presence.
     *
     * @param id             The unique identifier for the shirt.
     * @param size           The size of the shirt.
     * @param material       The material of the shirt.
     * @param color          The color of the shirt.
     * @param backLength     The length of the shirt from the back.
     * @param shoulderWidth  The width of the shirt at the shoulders.
     * @param pockets        Indicates whether the shirt has pockets.
     */
	public ShirtAttributes(int id , Size size, Material material, Color color, double backLength, double shoulderWidth, boolean pockets) {
		this.size = size;
        this.material = material;
        this.color = color;
        this.backLength = backLength;
        this.shoulderWidth = shoulderWidth;
        this.hasPockets = pockets;
        this.id = id;
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
    
    /**
     * Creates a preset ShirtAttributes object for a given size, predefining the shoulder width and back length based on the size.
     * 
     * @param size The size for which to create a preset.
     * @return A new instance of ShirtAttributes with predefined measurements for the specified size.
     * @throws IllegalArgumentException if the provided size is not recognized.
     */
    public ShirtAttributes createPresetForSize(Size size) {
        if (size == null) {
            throw new NullPointerException("Size is null");
        } else if (size == Size.XS) {
            return new ShirtAttributes(Size.XS, 17.0, 28.0);
        } else if (size == Size.S) {
            return new ShirtAttributes(Size.S, 18.0, 29.0);
        } else if (size == Size.M) {
            return new ShirtAttributes(Size.M, 19.0, 30.0);
        } else if (size == Size.L) {
            return new ShirtAttributes(Size.L, 20.0, 31.0);
        } else if (size == Size.XL) {
            return new ShirtAttributes(Size.XL, 21.0, 32.0);
        } else if (size == Size.XXL) {
            return new ShirtAttributes(Size.XXL, 22.0, 33.0);
        } else {
            return new ShirtAttributes(Size.XXXL, 23.0, 34.0);
        }
    }


	public double getShoulderWidth() {
		return shoulderWidth;
	}
	
	/**
     * Checks if the shirt has pockets.
     * 
     * @return Always returns false. Implementation may need to be updated to reflect actual pocket status.
     */
    public boolean hasPocket() {
        return this.hasPockets;
    }

	public double getBackLength() {
		return backLength;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}

	public void setShoulderWidth(double shoulderWidth) {
		this.shoulderWidth = shoulderWidth;
		
	}

	public void setHasPockets(boolean b) {
		this.hasPockets = b;
		
	}

	public void setBackLength(double backLength) {
		this.backLength = backLength;
	}
}

