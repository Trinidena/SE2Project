package test;
import model.ShirtAttributes;
import model.Size;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShirtAttributesTest {

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
        assertThrows(IllegalArgumentException.class, () -> new ShirtAttributes().createPresetForSize(null));
    }
}
