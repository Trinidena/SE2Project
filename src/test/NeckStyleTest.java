package test;

import model.NeckStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NeckStyleTest {

    @Test
    void testGetStyleCrewNeck() {
        assertEquals("Crew Neck", NeckStyle.CREW.getStyle());
    }

    @Test
    void testGetStyleVNeck() {
        assertEquals("V-Neck", NeckStyle.V_NECK.getStyle());
    }

    @Test
    void testGetStylePolo() {
        assertEquals("Polo", NeckStyle.POLO.getStyle());
    }

    @Test
    void testGetStyleScoopNeck() {
        assertEquals("Scoop Neck", NeckStyle.SCOOP_NECK.getStyle());
    }

    @Test
    void testGetStyleHenley() {
        assertEquals("Henley", NeckStyle.HENLEY.getStyle());
    }

    @Test
    void testGetStyleTurtleneck() {
        assertEquals("Turtleneck", NeckStyle.TURTLENECK.getStyle());
    }
}
