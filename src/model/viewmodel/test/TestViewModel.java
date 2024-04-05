package model.viewmodel.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.server.Server;
import model.server.ShirtCredentialsManager;
import model.shirt.Shirt;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;
import viewmodel.ShirtCreatorViewModel;

public class TestViewModel {
	@Test
	public void testWhenNoIdenticalShirts() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		ShirtCredentialsManager newManager = new ShirtCredentialsManager();
		newModel.setModel(newManager);

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");
		newModel.listProperty().get();

		assertEquals(true, newModel.pocketProperty().get());
		assertTrue(newModel.nameProperty().get().equals("Shirt"));
		assertTrue(newModel.shoulderProperty().get().equals(Size.S));
		assertTrue(newModel.sizeProperty().get().equals(Size.S));
		assertTrue(newModel.sleeveLengthProperty().get().equals(Size.S));
		assertTrue(newModel.colorProperty().get().equals(Color.BLUE));
		assertTrue(newModel.materialProperty().get().equals(Material.SILK));
		assertTrue(newModel.neckStyleProperty().get().equals(NeckStyle.V_NECK));
		assertTrue(newModel.backLengthProperty().get().equals(Size.S));
		assertTrue(newModel.textProperty().get().equals("Tiny Boss"));

	}

	@Test
	public void testAddToListView() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		assertTrue(newModel.addShirtToListView());

	}

	@Test
	public void testAddToListViewWhenDuplicate() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirtToListView();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		assertFalse(newModel.addShirtToListView());

	}

	@Test
	public void testDelete() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirtToListView();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");
		assertTrue(newModel.deleteShirt());

	}

	@Test
	public void testDeleteWhenNoName() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirtToListView();

		assertFalse(newModel.deleteShirt());

	}

	@Test
	public void testDeleteWhenNameAndNoItems() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		assertFalse(newModel.deleteShirt());

	}

	@Test
	public void testDeleteWhenInvalidName() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirtToListView();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt 2");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		assertFalse(newModel.deleteShirt());

	}

	@Test
	public void testDeleteWhenEmpty() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		assertFalse(newModel.deleteShirt());

	}

	@Test
	public void testEdit() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirtToListView();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.XXXL);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");
		assertTrue(newModel.editShirt());
	}

	@Test
	public void testEditWhenEmpty() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		assertFalse(newModel.editShirt());
	}

	@Test
	public void testEditWhenInvalidName() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirtToListView();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt 2");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		assertFalse(newModel.editShirt());
	}

	@Test
	public void testEditWhenNoName() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirtToListView();

		assertFalse(newModel.editShirt());
	}

	@Test
	public void testEditWhenNameAndNoItems() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		assertFalse(newModel.editShirt());
	}

	@Test

	public void testAddingTwoIdenticalShirtsToServer() {

		ShirtCreatorViewModel newModel = new ShirtCreatorViewModel();

		ShirtCredentialsManager shirtManager = new ShirtCredentialsManager();

		newModel.setModel(shirtManager);

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		newModel.addShirt();

		newModel.pocketProperty().set(true);
		newModel.nameProperty().set("Shirt");
		newModel.shoulderProperty().set(Size.S);
		newModel.sizeProperty().set(Size.S);
		newModel.sleeveLengthProperty().set(Size.S);

		newModel.colorProperty().set(Color.BLUE);
		newModel.neckStyleProperty().set(NeckStyle.V_NECK);

		newModel.materialProperty().set(Material.SILK);
		newModel.backLengthProperty().set(Size.S);
		newModel.textProperty().set("Tiny Boss");

		assertThrows(IllegalArgumentException.class, () -> {
			newModel.addShirt();
		});

	}

}
