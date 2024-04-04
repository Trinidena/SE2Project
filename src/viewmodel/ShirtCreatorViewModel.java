package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import model.ModelAwareController;
import model.server.ShirtCredentialsManager;
import model.shirt.Shirt;
import model.shirt.ShirtCollection;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;

/**
 * The ShirtCreatorViewModel class is responsible for the application logic
 * behind the Shirt Creator UI. It binds UI components to the underlying data
 * model, facilitating communication between the view and the model. This class
 * manages shirt attributes, validates user input, and updates the UI
 * accordingly.
 */
public class ShirtCreatorViewModel implements ModelAwareController{

	private final ListProperty<Shirt> listProperty;
	private final BooleanProperty pocketProperty;
	private final StringProperty nameProperty;
	private final ObjectProperty<Size> shoulderProperty;
	private final ObjectProperty<Size> sizeProperty;
	private final ObjectProperty<Size> sleeveProperty;
	private final ObjectProperty<Color> colorProperty;
	private final ObjectProperty<NeckStyle> neckStyleProperty;
	private final ObjectProperty<Material> materialProperty;

	private final ObjectProperty<Size> backLengthProperty;

	private final StringProperty textProperty;
	private final ObjectProperty<Image> imageProperty;
	private final ShirtCredentialsManager shirtManager;

	private ShirtCollection database;
	private ShirtCredentialsManager manager;

	/**
	 * Constructs a ShirtCreatorViewModel initializing all the property fields and
	 * the database.
	 */
	public ShirtCreatorViewModel() {
		this.listProperty = new SimpleListProperty<Shirt>(FXCollections.observableArrayList());
		this.nameProperty = new SimpleStringProperty();
		this.materialProperty = new SimpleObjectProperty<Material>();
		this.colorProperty = new SimpleObjectProperty<Color>();
		this.pocketProperty = new SimpleBooleanProperty();
		this.neckStyleProperty = new SimpleObjectProperty<NeckStyle>();
		this.backLengthProperty = new SimpleObjectProperty<Size>();
		this.shoulderProperty = new SimpleObjectProperty<Size>();
		this.sleeveProperty = new SimpleObjectProperty<Size>();

		this.sizeProperty = new SimpleObjectProperty<Size>();
		this.textProperty = new SimpleStringProperty();
		this.imageProperty = new SimpleObjectProperty<>();
		this.shirtManager = new ShirtCredentialsManager();

		this.database = new ShirtCollection();
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

	/**
	 * Adds a shirt to just the listview
	 *
	 * @return true if the shirt was added successfully, false otherwise.
	 */
	public boolean addShirtToListView() {
		Shirt newShirt = this.formShirt();
		if (this.database.put(newShirt)) {
			this.update();
			clearTextFields();
			return true;
		}

		return false;
	}

	public ListProperty<Shirt> listProperty() {
		return listProperty;
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public ObjectProperty<Material> materialProperty() {
		return materialProperty;
	}

	public ObjectProperty<Color> colorProperty() {
		return colorProperty;
	}

	public BooleanProperty pocketProperty() {
		return pocketProperty;
	}

	public ObjectProperty<NeckStyle> neckStyleProperty() {
		return neckStyleProperty;
	}

	public ObjectProperty<Size> sleeveLengthProperty() {
		return sleeveProperty;
	}

	public ObjectProperty<Size> sizeProperty() {
		return this.sizeProperty;
	}

	public ObjectProperty<Size> shoulderProperty() {
		return this.shoulderProperty;
	}

	public ObjectProperty<Size> backLengthProperty() {
		return this.backLengthProperty;
	}

	public void setSizeProperty(Size newSize) {
		this.sizeProperty.set(newSize);
	}

	public StringProperty textProperty() {
		return textProperty;
	}

	public StringProperty imageProperty() {
		return this.imageProperty();
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
	 * Edits an existing T shirt
	 * 
	 * @return false if deleted
	 *
	 */
	public boolean editShirt() {

		if (!this.database.values().isEmpty() && !this.nameProperty.getValue().isBlank()) {

			Shirt shirtToEdit = this.formShirt();
			int hash = shirtToEdit.hashCode();

			if (this.database.containsKey(shirtToEdit.hashCode())) {

				this.database.replaceByKey(hash, shirtToEdit);

				this.update();

				this.clearTextFields();
				return true;
			}
		}
		return false;

	}

	/**
	 * Updates the list property to reflect any changes in the database.
	 */
	private void update() {

		this.listProperty.set(FXCollections.observableArrayList(this.database.values()));

	}

	private Shirt formShirt() {

		Shirt shirt = new TShirt(nameProperty.get(), pocketProperty.get(), shoulderProperty.get(), sizeProperty.get(),
				sleeveProperty.get(), colorProperty.get(), neckStyleProperty.get(), materialProperty.get(),
				backLengthProperty.get(), textProperty.get());

		return shirt;
	}

	/**
	 * Clears all input fields by resetting their properties.
	 */
	private void clearTextFields() {
		pocketProperty.set(false);
		nameProperty.set("");
		shoulderProperty.set(null);
		sizeProperty.set(null);
		this.sleeveProperty.set(null);
		backLengthProperty.set(null);
		colorProperty.set(null);
		neckStyleProperty.set(null);
		materialProperty.set(null);
		this.textProperty().set("");
	}
	
	@Override
	public void setModel(model.ShirtCredentialsManager manager) {
		this.manager = (ShirtCredentialsManager) manager;
		
	}
}
