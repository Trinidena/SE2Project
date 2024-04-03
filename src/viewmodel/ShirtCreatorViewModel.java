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
public class ShirtCreatorViewModel {

	private final ListProperty<Shirt> listProperty;
	private final StringProperty nameProperty;
	private final ObjectProperty<Material> materialProperty;
	private final ObjectProperty<Color> colorProperty;
	private final BooleanProperty pocketProperty;
	private final ObjectProperty<NeckStyle> neckStyleProperty;
	private final ObjectProperty<Size> backLengthProperty;
	private final ObjectProperty<Size> shoulderProperty;
	private final ObjectProperty<Integer> quantityProperty;
	private final ObjectProperty<Size> sizeProperty;
	private final StringProperty textProperty;
	private final ObjectProperty<Image> imageProperty;
	private final ShirtCredentialsManager shirtManager;

	/**
	 * Constructs a ShirtCreatorViewModel initializing all the property fields and
	 * the database.
	 */
	public ShirtCreatorViewModel() {
		this.listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.nameProperty = new SimpleStringProperty();
		this.materialProperty = new SimpleObjectProperty<>();
		this.colorProperty = new SimpleObjectProperty<>();
		this.pocketProperty = new SimpleBooleanProperty();
		this.neckStyleProperty = new SimpleObjectProperty<>();
		this.backLengthProperty = new SimpleObjectProperty<>();
		this.shoulderProperty = new SimpleObjectProperty<>();
		this.quantityProperty = new SimpleObjectProperty<>();
		this.sizeProperty = new SimpleObjectProperty<>();
		this.textProperty = new SimpleStringProperty();
		this.imageProperty = new SimpleObjectProperty<>();
		this.shirtManager = new ShirtCredentialsManager();
	}

	/**
	 * Attempts to add a new TShirt to the database based on the current property
	 * values. Validates the input before adding and updates the view accordingly.
	 *
	 * @return true if the shirt was added successfully, false otherwise.
	 */
	public boolean addShirt() {
		Shirt newShirt = new TShirt(pocketProperty.get(), nameProperty.get(), shoulderProperty.get(),
				sizeProperty.get(), backLengthProperty.get(), colorProperty.get(), neckStyleProperty.get(),
				materialProperty.get(), imageProperty.get());
		shirtManager.addShirt(newShirt);
		clearTextFields();
		return true;
	}

	/**
	 * Clears all input fields by resetting their properties.
	 */
	private void clearTextFields() {
		pocketProperty.set(false);
		nameProperty.set("");
		shoulderProperty.set(null);
		sizeProperty.set(null);
		backLengthProperty.set(null);
		colorProperty.set(null);
		neckStyleProperty.set(null);
		materialProperty.set(null);
	}

	private ShirtCollection database;

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
		return sleeveLengthProperty;
	}

	public ObjectProperty<Integer> quantityProperty() {
		return quantityProperty;
	}

	public ObjectProperty<Size> sizeProperty() {
		return this.sizeProperty;
	}

	public void setSizeProperty(Size newSize) {
		this.sizeProperty.set(newSize);
	}

	public StringProperty textProperty() {
		return textProperty;
	}

	/**
	 * Deletes a tshirt from the database
	 * 
	 * @return false if deleted
	 *
	 */
	public boolean deleteShirt() {

		TShirt shirtToDelete = this.formTShirt();

		if (this.database.removeByKey(shirtToDelete.hashCode())) {
			this.update();
			this.clearTextFields();
			return true;
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

		TShirt shirtToEdit = this.formTShirt();

		if (this.database.containsKey(shirtToEdit.hashCode())) {

			this.database.findByKey(shirtToEdit.hashCode()).setHasPocket(shirtToEdit.hasPocket());

			this.database.findByKey(shirtToEdit.hashCode()).setQuantity(shirtToEdit.getQuantity());
			this.database.findByKey(shirtToEdit.hashCode()).setSize(shirtToEdit.getSize());

			this.database.findByKey(shirtToEdit.hashCode()).setSleeveLength(shirtToEdit.getSleeveLength());
			this.database.findByKey(shirtToEdit.hashCode()).setColor(shirtToEdit.getColor());
			this.database.findByKey(shirtToEdit.hashCode()).setNeckStyle(shirtToEdit.getNeckStyle());
			this.database.findByKey(shirtToEdit.hashCode()).setMaterial(shirtToEdit.getMaterial());
			this.database.findByKey(shirtToEdit.hashCode()).setShirtText(shirtToEdit.getShirtText());

			this.update();
			this.clearTextFields();
			return true;
		}

		return false;
	}

	/**
	 * Updates the list property to reflect any changes in the database.
	 */
	private void update() {
		this.listProperty.set(FXCollections.observableArrayList(this.database.values()));
	}

	private TShirt formTShirt() {

		TShirt newShirt = new TShirt(this.nameProperty.getValue(), this.sizeProperty().getValue(),
				this.materialProperty.get(), this.colorProperty.get(), this.textProperty.getValue(),
				this.neckStyleProperty.get(), this.quantityProperty.get(), this.pocketProperty.get(),
				this.sleeveLengthProperty.get());

		return newShirt;
	}
}
