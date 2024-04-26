package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.user.User;

public class TestUser {

	@Test
	public void testGetCreatorName() {

		User newUser = new User("Shirtlover", "password12345", "Shirt creator");

		assertTrue(newUser.getCreatorName().equals("Shirtlover"));

	}

	@Test
	public void testGetPassword() {

		User newUser = new User("Shirtlover", "password12345", "Shirt creator");

		assertTrue(newUser.getPassword().equals("password12345"));

	}

	@Test
	public void testgetCreatorName() {

		User newUser = new User("Shirtlover", "password12345", "Shirt creator");

		assertTrue(newUser.getRole().equals("Shirt creator"));

	}

}
