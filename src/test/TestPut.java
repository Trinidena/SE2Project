package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.shirt.Shirt;
import model.shirt.ShirtCollection;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

public class TestPut {

	@Test
	void testValidPut() {
		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");

		ShirtCollection testCollection = new ShirtCollection();

		assertTrue(testCollection.put(newShirt));

	}

	@Test
	void testPutWhenSongIsIdentical() {
		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");

		Shirt newShirt2 = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO,
				Material.BLEND, Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");
		ShirtCollection testCollection = new ShirtCollection();

		testCollection.put(newShirt);
		assertFalse(testCollection.put(newShirt2));

	}

	@Test
	void testWhenSongIsNull() {
		Shirt newShirt = null;

		ShirtCollection testCollection = new ShirtCollection();

		assertThrows(IllegalArgumentException.class, () -> testCollection.put(newShirt));
	}

}
