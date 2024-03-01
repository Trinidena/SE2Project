package test;

import model.Material;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaterialTest {

    @Test
    void testGetMaterial() {
        assertEquals("Cotton", Material.COTTON.getMaterial());
        assertEquals("Polyester", Material.POLYESTER.getMaterial());
        assertEquals("Blend", Material.BLEND.getMaterial());
        assertEquals("Wool", Material.WOOL.getMaterial());
        assertEquals("Linen", Material.LINEN.getMaterial());
        assertEquals("Silk", Material.SILK.getMaterial());
        assertEquals("Premium Cotton", Material.PREMIUM_COTTON.getMaterial());
    }
}
