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
import model.Size;
import model.TShirt;
import model.ShirtCollection;

/**
 * The ShirtCreatorViewModel class is responsible for the application logic behind the Shirt Creator UI.
 * It binds UI components to the underlying data model, facilitating communication between the view and the model.
 * This class manages shirt attributes, validates user input, and updates the UI accordingly.
 */
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

    /**
     * Constructs a ShirtCreatorViewModel initializing all the property fields and the database.
     */
    public ShirtCreatorViewModel() {
        this.listProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.nameProperty = new SimpleStringProperty();
        this.materialProperty = new SimpleObjectProperty<>();
        this.colorProperty = new SimpleObjectProperty<>();
        this.pocketProperty = new SimpleBooleanProperty();
        this.neckStyleProperty = new SimpleObjectProperty<>();
        this.sleeveLengthProperty = new SimpleObjectProperty<>();
        this.quantityProperty = new SimpleObjectProperty<>();
        this.sizeProperty = new SimpleObjectProperty<>();
        this.textProperty = new SimpleStringProperty();
        
        this.database = new ShirtCollection();
        this.listProperty.set(FXCollections.observableArrayList(this.database.values()));
    }

    public ListProperty<TShirt> listProperty() {
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
        return sizeProperty;
    }

    public StringProperty textProperty() {
        return textProperty;
    }

    /**
     * Attempts to add a new TShirt to the database based on the current property values.
     * Validates the input before adding and updates the view accordingly.
     *
     * @return true if the shirt was added successfully, false otherwise.
     */
    public boolean addShirt() {
        TShirt newShirt = new TShirt(new ShirtAttributes(
                nameProperty.get(),
                sizeProperty.get(),
                materialProperty.get(),
                colorProperty.get(),
                textProperty.get(),
                neckStyleProperty.get(),
                quantityProperty.get(),
                pocketProperty.get(),
                sleeveLengthProperty.get()));
        
        if (this.database.put(newShirt)) {
            update();
            clearTextFields();
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

    /**
     * Clears all input fields by resetting their properties.
     */
    private void clearTextFields() {
        nameProperty.set("");
        sizeProperty.set(null);
        materialProperty.set(null);
        colorProperty.set(null);
        textProperty.set("");
        neckStyleProperty.set(null);
        quantityProperty.set(1);
        pocketProperty.set(false);
        sleeveLengthProperty.set(null);
    }
}
