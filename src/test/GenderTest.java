package test;

import model.Gender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenderTest {

    @Test
    public void testGenderLabels() {
        assertEquals("Male", Gender.MALE.toString());
        assertEquals("Female", Gender.FEMALE.toString());
        assertEquals("Other", Gender.OTHER.toString());
    }
}
