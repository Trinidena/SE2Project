package model;

public enum Material {
    COTTON("Cotton"),
    POLYESTER("Polyester"),
    BLEND("Blend"),
    WOOL("Wool"),
    LINEN("Linen"),
    SILK("Silk"), 
    PREMIUM_COTTON("Premium Cotton");

    private final String material;

    // Constructor
    Material(String material) {
        this.material = material;
    }

    // Getter
    public String getMaterial() {
        return material;
    }
}

