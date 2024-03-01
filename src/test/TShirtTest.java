package test;

import model.Material;
import model.ShirtAttributes;
import model.TShirt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TShirtTest {

    @Test
    void calculatePriceWithPremiumCottonAndDesign() {
        ShirtAttributes attributes = new ShirtAttributes();
        attributes.setMaterial(Material.PREMIUM_COTTON);
        attributes.setDesign("Some Design");
        attributes.setQuantity(1); // Assume a single shirt for simplicity

        TShirt tShirt = new TShirt(attributes);
        double price = tShirt.calculatePrice();
        assertEquals(35.0, price); // Base price + premium + design
    }

    @Test
    void calculatePriceWithCottonNoDesign() {
        ShirtAttributes attributes = new ShirtAttributes();
        attributes.setMaterial(Material.COTTON);
        attributes.setDesign(""); // No design
        attributes.setQuantity(1);

        TShirt tShirt = new TShirt(attributes);
        double price = tShirt.calculatePrice();
        assertEquals(20.0, price); // Only base price
    }
}
