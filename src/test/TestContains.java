package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.shirt.Shirt;
import model.shirt.ShirtCollection;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

public class TestContains {

	@Test
	void testWhenDoesNotContain() {
		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");
		ShirtCollection testCollection = new ShirtCollection();

		boolean expected = testCollection.containsKey(newShirt.hashCode());

		assertFalse(expected);

	}

	@Test
	void testWhenContains() {
		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");
		ShirtCollection testCollection = new ShirtCollection();
		testCollection.put(newShirt);

		boolean expected = testCollection.containsKey(newShirt.hashCode());

		assertTrue(expected);

	}
}
