package test;
import model.Color;
import model.Material;
import model.NeckStyle;
import model.ShirtAttributes;
import model.Size;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class ShirtAttributesTest {

	private ShirtAttributes attributes;

    @BeforeEach
    void setUp() {
        attributes = new ShirtAttributes();
    }

    @Test
    void testSetSize() {
        attributes.setSize(Size.M);
        assertEquals(Size.M, attributes.getSize());
    }

    @Test
    void testSetMaterial() {
        attributes.setMaterial(Material.COTTON);
        assertEquals(Material.COTTON, attributes.getMaterial());
    }

    @Test
    void testSetColor() {
        attributes.setColor(Color.BLUE);
        assertEquals(Color.BLUE, attributes.getColor());
    }

    @Test
    void testSetDesign() {
        String design = "Floral";
        attributes.setDesign(design);
        assertEquals(design, attributes.getDesign());
    }

    @Test
    void testSetNeckStyle() {
        attributes.setNeckStyle(NeckStyle.V_NECK);
        assertEquals(NeckStyle.V_NECK, attributes.getNeckStyle());
    }

    @Test
    void testSetSleeveLength() {
        double sleeveLength = 25.0;
        attributes.setSleeveLength(sleeveLength);
        assertEquals(sleeveLength, attributes.getSleeveLength());
    }

    @Test
    void testSetPrice() {
        double price = 19.99;
        attributes.setPrice(price);
        assertEquals(price, attributes.getPrice());
    }

    @Test
    void testSetQuantity() {
        int quantity = 100;
        attributes.setQuantity(quantity);
        assertEquals(quantity, attributes.getQuantity());
    }

    @Test
    void testSetBrand() {
        String brand = "BrandName";
        attributes.setBrand(brand);
        assertEquals(brand, attributes.getBrand());
    }

    @Test
    void testSetDescription() {
        String description = "This is a description.";
        attributes.setDescription(description);
        assertEquals(description, attributes.getDescription());
    }

    @Test
    void testSetID() {
        int id = 123;
        attributes.setID(id);
        assertEquals(id, attributes.getID());
    }

    @Test
    void testSetShoulderWidth() {
        double shoulderWidth = 18.5;
        attributes.setShoulderWidth(shoulderWidth);
        assertEquals(shoulderWidth, attributes.getShoulderWidth());
    }

    @Test
    void testSetBackLength() {
        double backLength = 30.0;
        attributes.setBackLength(backLength);
        assertEquals(backLength, attributes.getBackLength());
    }

    @Test
    void testSetHasPockets() {
        attributes.setHasPockets(true);
        assertTrue(attributes.hasPocket());
    }

	@Test
    void testShirtAttributesFullConstructor() {
        int id = 1;
        Size size = Size.M;
        Material material = Material.COTTON;
        Color color = Color.BLUE;
        double backLength = 30.0;
        double shoulderWidth = 15.0;
        boolean pockets = true;

        ShirtAttributes attributes = new ShirtAttributes(id, size, material, color, backLength, shoulderWidth, pockets);

        assertEquals(id, attributes.getID());
        assertEquals(size, attributes.getSize());
        assertEquals(material, attributes.getMaterial());
        assertEquals(color, attributes.getColor());
        assertEquals(backLength, attributes.getBackLength());
        assertEquals(shoulderWidth, attributes.getShoulderWidth());
        assertEquals(pockets, attributes.hasPocket());
    }
	
	@Test
    void testShirtAttributesConstructor() {
        ShirtAttributes attributes = new ShirtAttributes();
        assertNotNull(attributes);
        assertNull(attributes.getSize());
        assertNull(attributes.getMaterial());
        assertNull(attributes.getColor());
        assertNull(attributes.getDesign());
        assertNull(attributes.getNeckStyle());
        assertEquals(0.0, attributes.getSleeveLength());
        assertEquals(0.0, attributes.getPrice());
        assertEquals(0, attributes.getQuantity());
        assertNull(attributes.getBrand());
        assertNull(attributes.getDescription());
        assertEquals(0.0, attributes.getShoulderWidth());
        assertEquals(0.0, attributes.getBackLength());
        assertEquals(0, attributes.getID());
        assertFalse(attributes.hasPocket());
    }
	
    @Test
    void testCreatePresetForSizeXS() {
        ShirtAttributes xsAttributes = new ShirtAttributes().createPresetForSize(Size.XS);
        assertNotNull(xsAttributes);
        assertEquals(17.0, xsAttributes.getShoulderWidth());
        assertEquals(28.0, xsAttributes.getBackLength());
    }

    @Test
    void testCreatePresetForSizeS() {
        ShirtAttributes sAttributes = new ShirtAttributes().createPresetForSize(Size.S);
        assertNotNull(sAttributes);
        assertEquals(18.0, sAttributes.getShoulderWidth());
        assertEquals(29.0, sAttributes.getBackLength());
    }

    @Test
    void testCreatePresetForSizeM() {
        ShirtAttributes mAttributes = new ShirtAttributes().createPresetForSize(Size.M);
        assertNotNull(mAttributes);
        assertEquals(19.0, mAttributes.getShoulderWidth());
        assertEquals(30.0, mAttributes.getBackLength());
    }

    @Test
    void testCreatePresetForSizeL() {
        ShirtAttributes lAttributes = new ShirtAttributes().createPresetForSize(Size.L);
        assertNotNull(lAttributes);
        assertEquals(20.0, lAttributes.getShoulderWidth());
        assertEquals(31.0, lAttributes.getBackLength());
    }

    @Test
    void testCreatePresetForSizeXL() {
        ShirtAttributes xlAttributes = new ShirtAttributes().createPresetForSize(Size.XL);
        assertNotNull(xlAttributes);
        assertEquals(21.0, xlAttributes.getShoulderWidth());
        assertEquals(32.0, xlAttributes.getBackLength());
    }

    @Test
    void testCreatePresetForSizeXXL() {
        ShirtAttributes xxlAttributes = new ShirtAttributes().createPresetForSize(Size.XXL);
        assertNotNull(xxlAttributes);
        assertEquals(22.0, xxlAttributes.getShoulderWidth());
        assertEquals(33.0, xxlAttributes.getBackLength());
    }

    @Test
    void testCreatePresetForSizeXXXL() {
        ShirtAttributes xxxlAttributes = new ShirtAttributes().createPresetForSize(Size.XXXL);
        assertNotNull(xxxlAttributes);
        assertEquals(23.0, xxxlAttributes.getShoulderWidth());
        assertEquals(34.0, xxxlAttributes.getBackLength());
    }

    @Test
    void testCreatePresetForSizeInvalid() {
        assertThrows(NullPointerException.class, () -> new ShirtAttributes().createPresetForSize(null));
    }
}
