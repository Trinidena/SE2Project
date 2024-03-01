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
import model.Color;
import model.Material;
import model.NeckStyle;
import model.ShirtAttributes;
import model.ShirtCollection;
import model.Size;
import model.TShirt;

public class ShirtCreatorViewModel {

	private final ListProperty<TShirt> listProperty;

	private final StringProperty nameProperty;

	private final ObjectProperty<Material> materialProperty;
	private final ObjectProperty<Color> colorProperty;

	private final BooleanProperty pocketProperty;

	private final ObjectProperty<NeckStyle> neckStyleProperty;
	private final ObjectProperty<Size> sleeveLengthProperty;

	private final ObjectProperty<Integer> quantityProperty;
	private final ObjectProperty<Size> sizeProperty;

	private final StringProperty textProperty;

	private ShirtCollection database;

	public ShirtCreatorViewModel() {
		this.nameProperty = new SimpleStringProperty();
		this.colorProperty = new SimpleObjectProperty<Color>();
		this.pocketProperty = new SimpleBooleanProperty();

		this.sleeveLengthProperty = new SimpleObjectProperty<Size>();

		this.quantityProperty = new SimpleObjectProperty<Integer>();
		this.sizeProperty = new SimpleObjectProperty<Size>();
		this.neckStyleProperty = new SimpleObjectProperty<NeckStyle>();
		this.materialProperty = new SimpleObjectProperty<Material>();
		this.textProperty = new SimpleStringProperty();

		this.database = new ShirtCollection();

		this.listProperty = new SimpleListProperty<TShirt>(FXCollections.observableArrayList(this.database.values()));

	}

	public ObjectProperty<Material> materialProperty() {
		return materialProperty;
	}

	public ObjectProperty<Color> colorProperty() {
		return colorProperty;
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

	public StringProperty textProperty() {
		return textProperty;
	}

	public ListProperty<TShirt> listProperty() {
		return listProperty;
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public ObjectProperty<Size> sizeProperty() {
		return sizeProperty;
	}

	public BooleanProperty pocketProperty() {
		return pocketProperty;
	}

	public boolean addShirt() {
		String name = this.nameProperty().get();
		Size newSize = this.sizeProperty.get();
		Material newMaterial = this.materialProperty.get();
		Color newColor = this.colorProperty().get();
		String newText = this.textProperty.get();
		NeckStyle newNeckStyle = this.neckStyleProperty.get();
		int newQuantity = this.quantityProperty.get();
		boolean newPocket = this.pocketProperty.get();
		Size newSleeveLength = this.sleeveLengthProperty.get();

		ShirtAttributes newAttributes = new ShirtAttributes(name, newSize, newMaterial, newColor, newText, newNeckStyle,
				newQuantity, newPocket, newSleeveLength);

		TShirt newShirt = new TShirt(newAttributes);
		
		

		if (this.database.put(newShirt)) {

			this.update();
			this.clearTextFields();
			return true;

		}

		return false;

	}

	private void update() {
		this.listProperty.set(FXCollections.observableArrayList(this.database.values()));
	}

	private void clearTextFields() {
		this.nameProperty.set("");
		this.sizeProperty.set(null);
		this.materialProperty.set(null);
		this.colorProperty.set(null);
		this.textProperty.set("");
		this.neckStyleProperty.set(null);
		this.quantityProperty.set(1);
		this.pocketProperty.set(false);
		this.sleeveLengthProperty.set(null);
	}

}
