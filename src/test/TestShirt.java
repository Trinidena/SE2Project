package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.shirt.Shirt;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

public class TestShirt {

	/**
	 * class provides tests for the Song model class
	 * 
	 * @author Trinidad
	 * @version 1.0
	 */

	@Test
	public void testShirtConstructor() {

		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude");

		assertEquals("Shirt 1", newShirt.getName());
		assertTrue(newShirt.hasPocket());
		assertEquals(Size.XL, newShirt.getShoulderWidth());
		assertEquals(Size.S, newShirt.getSize());
		assertEquals(Size.L, newShirt.getSleeveLength());
		assertEquals(Color.RED, newShirt.getColor());
		assertEquals(NeckStyle.POLO, newShirt.getNeckStyle());
		assertEquals(Material.BLEND, newShirt.getMaterial());
		assertEquals(Size.XL, newShirt.getBackLength());
		assertEquals("Cool Dude", newShirt.getShirtText());

		// Test constructor with too low year
		assertThrows(IllegalArgumentException.class, () -> {
			new TShirt(null, true, null, null, null, null, null, null, null, "Cool Dude");
		});

		// Test constructor with too low year
		assertThrows(IllegalArgumentException.class, () -> {
			new TShirt("", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND, Size.XL,
					"Cool Dude");
		});
	}

	@Test
	public void testToString() {

		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude");

		assertEquals("Shirt 1", newShirt.toString());
	}

	@Test
	public void testHashCode() {

		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude");
		assertEquals(-568695674, newShirt.hashCode());
	}
}
