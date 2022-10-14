package dev.azlabs.modernclient;

import dev.azlabs.modernclient.core.ControllerMixin;
import dev.azlabs.modernclient.core.ObservableRepositoryInterface;
import dev.azlabs.modernclient.core.RepositoryInterface;
import dev.azlabs.modernclient.models.Person;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable, ControllerMixin {

    private final RepositoryInterface<Integer, Person> repository;
    private final ObjectProperty<Person> selectedPersonProperty = new SimpleObjectProperty<Person>(this, "selectedPerson");

    // Observe a list of persons, an update if any of the observable$ properties changes
    private final ObservableList<Person> persons;

    // Track the state of the form component checking if the form ha change
    private final BooleanProperty requiresUpdateProperty = new SimpleBooleanProperty(false);

    //#region FXML Properties
    @FXML
    private Button removeButton;
    @FXML
    private Button createButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextInputControl firstnameTextView;
    @FXML
    private TextInputControl lastnameTextView;
    @FXML
    private ListView<Person> listView;
    @FXML
    private TextArea notesTextArea;
    //#endregion FXML properties

    ViewController(ObservableRepositoryInterface<Integer, Person> repository) {
        this.repository = repository;
        persons = repository.createStateProperty();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // The remove button is disabled when no item is selected
        var notSelectedValue = listView.getSelectionModel().selectedItemProperty().isNull().or(listView.itemsProperty().isNull());
        var hasInvalidForm = firstnameTextView.textProperty().isEmpty().or(lastnameTextView.textProperty().isEmpty());

        //#region Provides property bindings
        bind(removeButton.disableProperty(), notSelectedValue);
        bind(createButton.disableProperty(), listView.getSelectionModel().selectedItemProperty().isNotNull().or(hasInvalidForm));
        bind(updateButton.disableProperty(), notSelectedValue.or(requiresUpdateProperty.not()).or(hasInvalidForm));
        bind(selectedPersonProperty, listView.getSelectionModel().selectedItemProperty());
        //#endregion Provides property bindings

        //#region component properties initialization
        listView.setItems(persons);
        //#endregion component properties initialization
    }

    @FXML
    public void deletePerson(MouseEvent event) {
        repository.delete(selectedPersonProperty.get());
    }

    @FXML
    public void updatePerson(MouseEvent event) {
        Person p = selectedPersonProperty.get();
        // Remove listener on the listview selectedItemProperty
        // listView.getSelectionModel().selectedItemProperty()
        //        .removeListener(listChangeListener);
        p.setFirstname(firstnameTextView.getText());
        p.setLastname(lastnameTextView.getText());
        p.setNotes(notesTextArea.getText());
        // Add back the listener after changes
        // listView.getSelectionModel().selectedItemProperty()
        //        .removeListener(listChangeListener);
        requiresUpdateProperty.set(false);
    }

    @FXML
    public void createPerson(MouseEvent event) {
        var p = new Person(firstnameTextView.getText(), lastnameTextView.getText(), notesTextArea.getText());
        repository.create(p);
        listView.getSelectionModel().select(p);
    }

    // Listen for onKeyReleased event on TextFields, TextArea, etc...
    @FXML
    public void onChanges(KeyEvent event) {
        requiresUpdateProperty.set(true);
    }
}
