package dev.azlabs.modernclient.models;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

public class Person implements Reflectable {

    // Returns a list of property to listen changes in order to update a list item
    // when they change
    public static Callback<Person, Observable[]> observables$ = p -> new Observable[] {
            p.firstnameProperty(), p.lastnameProperty()
    };

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty firstname = new SimpleStringProperty(this, "firstname", "");
    private final StringProperty lastname = new SimpleStringProperty(this, "lastname", "");
    private final StringProperty notes = new SimpleStringProperty(this, "notes", "sample notes");

    public Person() {
    }

    public Person(String firstname, String lastname, String notes) {
        setFirstname(firstname);
        setLastname(lastname);
        setNotes(notes);
    }

    public Person(String firstname, String lastname) {
        setFirstname(firstname);
        setLastname(lastname);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String value) {
        firstname.set(value);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String value) {
        lastname.set(value);
    }

    public String getNotes() {
        return notes.get();
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String value) {
        notes.set(value);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int value) {
        id.set(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        return deepEquals(obj);
    }

    @Override
    public int hashCode() {
        return computeHashCode();
    }
}
