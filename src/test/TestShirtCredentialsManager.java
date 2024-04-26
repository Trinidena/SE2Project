package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import model.shirt.Shirt;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;
import model.user.User;
import server.ShirtCredentialsManager;

public class TestShirtCredentialsManager {

	@Test
	public void testAddingShirtToManager() {
		Shirt newShirt = new TShirt("Shirt 1", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO, Material.BLEND,
				Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");

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

		Shirt newShirt = new TShirt("Nirvana Shirt", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO,
				Material.BLEND, Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");

		newManager.addShirt(newShirt);

		List<TShirt> newList = newManager.getShirts();

		assertEquals(2, newList.size());

	}

	@Test
	public void testDeleteShirt() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();
		Shirt newShirt = new TShirt("ACDC Shirt", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO,
				Material.BLEND, Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");

		assertTrue(newManager.addShirt(newShirt));

		assertTrue(newManager.removeShirt(newShirt.getName()));

	}

	@Test
	public void testGettingUsers() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();
		User newUser = new User("Shirtlover", "Accepted", "Shirt maker");

		newManager.addUser(newUser);

		List<User> userList = newManager.getUsers();

		assertEquals(1, userList.size());

	}

	@Test
	public void testWhenInvalidUser() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();

		assertThrows(IllegalArgumentException.class, () -> {
			newManager.addUser(null);
		});

	}

	@Test
	public void testUpdateShirt() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();

		Shirt newShirt = new TShirt("Ozzy Shirt", true, Size.XL, Size.S, Size.L, Color.RED, NeckStyle.POLO,
				Material.BLEND, Size.XL, "Cool Dude", "Shirtlover", "Accepted", "Shirt maker");

		newManager.addShirt(newShirt);

		assertFalse(newManager.updateShirt("Ozzy shirt 2", "Accepted", "Shirt maker"));

	}

	@Test
	public void testUpdateWhenInvalidUser() {

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();

		assertThrows(IllegalArgumentException.class, () -> {
			newManager.updateShirt(null, null, null);
		});

	}

}
