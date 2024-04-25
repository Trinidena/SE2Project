package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import model.ModelAwareController;
import server.ShirtCredentialsManager;
import model.shirt.Shirt;
import model.shirt.ShirtCollection;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;
import model.user.User;

/**
 * Manages the application logic behind the Shirt Creator UI. It facilitates
 * communication between the view and the model by binding UI components to the
 * underlying data model. This class handles the management of shirt attributes,
 * user input validation, and UI updates.
 * 
 * @author Trinidad Dena
 */
public class ShirtCreatorViewModel implements ModelAwareController {

	private final StringProperty passwordProperty = new SimpleStringProperty();
	private final ListProperty<Shirt> listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
	private final BooleanProperty pocketProperty = new SimpleBooleanProperty();
	private final StringProperty nameProperty = new SimpleStringProperty();
	private final ObjectProperty<Size> shoulderProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Size> sizeProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Size> sleeveProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Color> colorProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<NeckStyle> neckStyleProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Material> materialProperty = new SimpleObjectProperty<>();
	private final ObjectProperty<Size> backLengthProperty = new SimpleObjectProperty<>();
	private final StringProperty textProperty = new SimpleStringProperty();
	private final StringProperty creatorProperty = new SimpleStringProperty();
	private ShirtCredentialsManager shirtManager = new ShirtCredentialsManager();
	private ShirtCollection database = new ShirtCollection();
	private StringProperty roleProperty = new SimpleStringProperty();

	/**
	 * Attempts to add a shirt to the ListView directly, bypassing any database or
	 * external storage.
	 *
	 * @return true if the shirt was added successfully, false otherwise.
	 */
	public boolean addShirtToListView() {
		Shirt newShirt = this.formShirt();
		if (this.database.put(newShirt)) {
			this.update();
			this.clearTextFields();
			return true;
		}
		return false;
	}

	/**
	 * Gets the list property of shirts.
	 * 
	 * @return The observable list property of shirts.
	 */
	public ListProperty<Shirt> listProperty() {
		return this.listProperty;
	}

	/**
	 * Gets the name property of a shirt.
	 * 
	 * @return The string property for the shirt's name.
	 */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 * Gets the material property of a shirt.
	 * 
	 * @return The object property for the shirt's material.
	 */
	public ObjectProperty<Material> materialProperty() {
		return this.materialProperty;
	}

	/**
	 * Gets the color property of a shirt.
	 * 
	 * @return The object property for the shirt's color.
	 */
	public ObjectProperty<Color> colorProperty() {
		return this.colorProperty;
	}

	/**
	 * Gets the pocket presence property of a shirt.
	 * 
	 * @return The boolean property indicating if the shirt has a pocket.
	 */
	public BooleanProperty pocketProperty() {
		return this.pocketProperty;
	}

	/**
	 * Gets the neck style property of a shirt.
	 * 
	 * @return The object property for the shirt's neck style.
	 */
	public ObjectProperty<NeckStyle> neckStyleProperty() {
		return this.neckStyleProperty;
	}

	/**
	 * Gets the sleeve length property of a shirt.
	 * 
	 * @return The object property for the shirt's sleeve length.
	 */
	public ObjectProperty<Size> sleeveLengthProperty() {
		return this.sleeveProperty;
	}

	/**
	 * Gets the size property of a shirt.
	 * 
	 * @return The object property for the shirt's size.
	 */
	public ObjectProperty<Size> sizeProperty() {
		return this.sizeProperty;
	}

	/**
	 * Gets the shoulder width property of a shirt.
	 * 
	 * @return The object property for the shirt's shoulder width.
	 */
	public ObjectProperty<Size> shoulderProperty() {
		return this.shoulderProperty;
	}

	/**
	 * Gets the back length property of a shirt.
	 * 
	 * @return The object property for the shirt's back length.
	 */
	public ObjectProperty<Size> backLengthProperty() {
		return this.backLengthProperty;
	}

	/**
	 * Gets the custom text property of a shirt.
	 * 
	 * @return The string property for the custom text on the shirt.
	 */
	public StringProperty textProperty() {
		return this.textProperty;
	}

	/**
	 * Gets the creator of the shirt.
	 * 
	 * @return The string property for the shirt.
	 */
	public StringProperty creatorProperty() {
		return this.creatorProperty;
	}
	
	public StringProperty passwordProperty() {
		return this.passwordProperty;
	}

	public StringProperty roleProperty() {
		return this.roleProperty;
	}

	/**
	 * Attempts to add a new TShirt to the database based on the current property
	 * values. Validates the input before adding and updates the view accordingly.
	 *
	 * @return true if the shirt was added successfully, false otherwise.
	 */
	public Shirt addShirt() {
		Shirt newShirt = this.formShirt();
		shirtManager.addShirt(newShirt);
		clearTextFields();

		return newShirt;
	}
	
	public User addUser() {
		User newUser = this.formUser();
		shirtManager.addUser(newUser);
		clearTextFields();

		return newUser;
	}

	/**
	 * Deletes a tshirt from the database
	 * 
	 * @return false if deleted
	 *
	 */
	public boolean deleteShirt() {

		if (!this.database.values().isEmpty() && !this.nameProperty.getValue().isBlank()) {

			Shirt shirtToDelete = this.formShirt();
			if (this.database.removeByKey(shirtToDelete.hashCode())) {
				this.update();
				this.clearTextFields();
				return true;
			}
		}
		return false;
	}

	/**
	 * Edits an existing Shirt in the database.
	 * 
	 * @return true if the Shirt was successfully edited, false otherwise.
	 */
	public boolean editShirt() {
		if (!this.database.values().isEmpty() && !this.nameProperty.getValue().isBlank()) {
			Shirt shirtToEdit = this.formShirt();
			int hash = shirtToEdit.hashCode();
			if (this.database.containsKey(shirtToEdit.hashCode())) {
				this.database.replaceByKey(hash, shirtToEdit);
				this.update();
				return true;
			}
		}
		return false;
	}

	/**
	 * Forms a new Shirt object based on the current state of the properties.
	 * 
	 * @return A new Shirt object.
	 */
	private Shirt formShirt() {
		return new TShirt(this.nameProperty.get(), this.pocketProperty.get(), this.shoulderProperty.get(),
				this.sizeProperty.get(), this.sleeveProperty.get(), this.colorProperty.get(),
				this.neckStyleProperty.get(), this.materialProperty.get(), this.backLengthProperty.get(),
				this.textProperty.get(), this.creatorProperty.get(), "", "");
	}
	
	/**
	 * Forms a new User object based on the current state of the properties.
	 * 
	 * @return A new User object.
	 */
	private User formUser() {
		System.out.println("Setting creator: " + this.creatorProperty.getValue());
		System.out.println("Setting password: " + this.passwordProperty.getValue());
		System.out.println("Setting role: " + this.roleProperty.getValue());
		return new User(this.creatorProperty.getValue(), this.passwordProperty.getValue(), this.roleProperty.getValue());
	}

	/**
	 * Clears all text fields by resetting their bound properties.
	 */
	private void clearTextFields() {
		this.pocketProperty.set(false);
		this.nameProperty.set("");
		this.shoulderProperty.set(null);
		this.sizeProperty.set(null);
		this.sleeveProperty.set(null);
		this.backLengthProperty.set(null);
		this.colorProperty.set(null);
		this.neckStyleProperty.set(null);
		this.materialProperty.set(null);
		this.textProperty().set("");
		this.creatorProperty.set("");
	}

	/**
	 * Updates the list property to reflect the current state of the database.
	 */
	private void update() {
		this.listProperty.set(FXCollections.observableArrayList(this.database.values()));
	}

	@Override
	public void setModel(model.ShirtCredentialsManager manager) {
		this.shirtManager = (ShirtCredentialsManager) manager;
	}

	@Override
	public void setPassword(String text) {
		
	}

	@Override
	public void setRole(String value) {
		
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}
}
