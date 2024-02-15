package model;

public enum Size {
    XS("Extra Small"),
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Double Extra Large"),
    XXXL("Triple Extra Large");

    private final String description;

    // Constructor
    Size(String description) {
        this.description = description;
    }

    // Getter
    public String getDescription() {
        return description;
    }
}

