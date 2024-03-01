package test;

import model.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    @Test
    void testGetDisplayName() {
        assertEquals("Red", Color.RED.getDisplayName());
        assertEquals("Green", Color.GREEN.getDisplayName());
        assertEquals("Blue", Color.BLUE.getDisplayName());
        assertEquals("Yellow", Color.YELLOW.getDisplayName());
        assertEquals("Black", Color.BLACK.getDisplayName());
        assertEquals("White", Color.WHITE.getDisplayName());
        assertEquals("Purple", Color.PURPLE.getDisplayName());
    }

    @Test
    void testFromDisplayNameValid() {
        assertEquals(Color.RED, Color.fromDisplayName("Red"));
        assertEquals(Color.GREEN, Color.fromDisplayName("Green"));
        assertEquals(Color.BLUE, Color.fromDisplayName("Blue"));
        assertEquals(Color.YELLOW, Color.fromDisplayName("Yellow"));
        assertEquals(Color.BLACK, Color.fromDisplayName("Black"));
        assertEquals(Color.WHITE, Color.fromDisplayName("White"));
        assertEquals(Color.PURPLE, Color.fromDisplayName("Purple"));
    }

    @Test
    void testFromDisplayNameInvalid() {
        assertThrows(IllegalArgumentException.class, () -> Color.fromDisplayName("InvalidColor"));
    }

    @Test
    void testFromDisplayNameCaseInsensitive() {
        assertEquals(Color.RED, Color.fromDisplayName("red"));
        assertEquals(Color.GREEN, Color.fromDisplayName("green"));
    }
}
