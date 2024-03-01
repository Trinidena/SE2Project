package model;

public class ShirtAttributes {
	private Size size;
	private Material material;
	private Color color;
	private String design;
	private NeckStyle neckStyle;
	private Size sleeveLength;
	private double price;
	private int quantity;
	private String brand;
	private String text;
	private String name;
	private double shoulderWidth;
	private double backLength;

	private Boolean pocket;

	public ShirtAttributes(Size size, double shoulderWidth, double backLength) {
		this.size = size;
		this.shoulderWidth = shoulderWidth;
		this.backLength = backLength;
	}

	public ShirtAttributes(String name, Size size, Material material, Color color, String text, NeckStyle neckStyle,
			int quantity, boolean pocket, Size sleeveLength) {

		if (name == null) {
			throw new IllegalArgumentException("Name shouldn't be empty");
		}

		if (name.isEmpty() || name.isBlank()) {
			throw new IllegalArgumentException("Name shouldn't be empty");
		}

		if (size == null) {
			throw new IllegalArgumentException("Size shouldn't be empty");
		}

		if (sleeveLength == null) {
			throw new IllegalArgumentException("Sleeve Length shouldn't be empty");
		}

		if (color == null) {
			throw new IllegalArgumentException("Color shouldn't be empty");
		}

		if (neckStyle == null) {
			throw new IllegalArgumentException("Neck Style shouldn't be empty");
		}

		if (material == null) {
			throw new IllegalArgumentException("Material shouldn't be empty");
		}

		this.setName(name);
		this.size = size;
		this.material = material;
		this.color = color;
		this.text = text;
		this.neckStyle = neckStyle;
		this.quantity = quantity;
		this.pocket = pocket;
		this.sleeveLength = sleeveLength;

	}

	public Size getSize() {
		return this.size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getDesign() {
		return this.design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public NeckStyle getNeckStyle() {
		return this.neckStyle;
	}

	public void setNeckStyle(NeckStyle neckStyle) {
		this.neckStyle = neckStyle;
	}

	public Size getSleeveLength() {
		return this.sleeveLength;
	}

	public void setSleeveLength(Size sleeveLength) {
		this.sleeveLength = sleeveLength;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getText() {
		return this.text;
	}

	public void setDescription(String description) {
		this.text = description;
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
			throw new IllegalArgumentException("No Valid Shirt Size"); // Or throw an exception, based on your error
																		// handling strategy
		}
	}

	public double getShoulderWidth() {
		return this.shoulderWidth;
	}

	public boolean hasPocket() {
		return this.pocket;
	}

	public double getBackLength() {
		return this.backLength;
	}

	public void setPocket(Boolean pocket) {
		this.pocket = pocket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
