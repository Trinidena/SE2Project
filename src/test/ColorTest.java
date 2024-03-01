package test;

import model.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void testFromDisplayNameRed() {
        assertEquals(Color.RED, Color.fromDisplayName("Red"));
    }

    // Repeat for other valid colors

    @Test
    void testFromDisplayNameInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Color.fromDisplayName("NoColor"));
    }
}
