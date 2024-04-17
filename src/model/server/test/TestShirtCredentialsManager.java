package model.server.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import model.server.ShirtCredentialsManager;
import model.shirt.Shirt;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

public class TestShirtCredentialsManager {
	@Test
	public void testAddingShirtToManager() {
		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude");

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();
		assertTrue(newManager.addShirt(newShirt));

	}

	@Test
	public void testAddingNullShirtToManager() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			newManager.addShirt(null);
		});

	}

	@Test
	public void testGettingShirts() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();
		List<TShirt> newList = newManager.getShirts();

		assertEquals(1, newList.size());

	}

	@Test
	public void testDeleteShirt() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();
		Shirt newShirt = new TShirt("ACDC Shirt", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO,
				Material.BLEND, Size.XL, "Cool Dude");

		assertTrue(newManager.addShirt(newShirt));

		assertTrue(newManager.removeShirt(newShirt.getName()));

	}
}
