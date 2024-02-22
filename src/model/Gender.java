package model;

public enum Gender {
	MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String gender;

    // Constructor
    Gender(String gender) {
        this.gender = gender;
    }

    // Getter
    public String getMaterial() {
        return gender;
    }
    
    @Override
    public String toString() {
        return gender;
    }
}
