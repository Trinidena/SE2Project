package viewmodel;

import javafx.beans.property.DoubleProperty;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;

import javafx.beans.property.SimpleDoubleProperty;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import model.Material;
import model.NeckStyle;
import model.ShirtCollection;
import model.Size;
import model.TShirt;

public class ShirtCreatorViewModel {

	private final ListProperty<TShirt> listProperty;

	private final StringProperty nameProperty;

	private final ObjectProperty<Material> materialProperty;
	private final StringProperty colorProperty;

	private final StringProperty designProperty;

	private final ObjectProperty<NeckStyle> neckStyleProperty;
	private final StringProperty sleeveLengthProperty;

	private final DoubleProperty priceProperty;
	private final ObjectProperty<Integer> quantityProperty;
	private final ObjectProperty<Size> sizeProperty;

	private final StringProperty descriptionProperty;

	private ShirtCollection database;

	public ShirtCreatorViewModel() {
		this.nameProperty = new SimpleStringProperty();
		this.colorProperty = new SimpleStringProperty();
		this.designProperty = new SimpleStringProperty();
		this.sleeveLengthProperty = new SimpleStringProperty();
		this.priceProperty = new SimpleDoubleProperty();
		this.quantityProperty = new SimpleObjectProperty<Integer>();
		this.sizeProperty = new SimpleObjectProperty<Size>();
		this.neckStyleProperty = new SimpleObjectProperty<NeckStyle>();
		this.materialProperty = new SimpleObjectProperty<Material>();
		this.descriptionProperty = new SimpleStringProperty();

		this.database = new ShirtCollection();

		this.listProperty = new SimpleListProperty<TShirt>(FXCollections.observableArrayList(this.database.values()));

	}

	public ObjectProperty<Material> materialProperty() {
		return materialProperty;
	}

	public StringProperty colorProperty() {
		return colorProperty;
	}

	public StringProperty designProperty() {
		return designProperty;
	}

	public ObjectProperty<NeckStyle> neckStyleProperty() {
		return neckStyleProperty;
	}

	public StringProperty sleeveLengthProperty() {
		return sleeveLengthProperty;
	}

	public DoubleProperty priceProperty() {
		return priceProperty;
	}

	public ObjectProperty<Integer> quantityProperty() {
		return quantityProperty;
	}

	public StringProperty descriptionProperty() {
		return descriptionProperty;
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
}
