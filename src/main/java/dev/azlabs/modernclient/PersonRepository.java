package dev.azlabs.modernclient;

import dev.azlabs.modernclient.core.ListMixin;
import dev.azlabs.modernclient.core.ObservableRepositoryInterface;
import dev.azlabs.modernclient.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Stream;

public class PersonRepository implements ObservableRepositoryInterface<Integer, Person>, ListMixin {

    // State/collection of persons in the application
    private final ObservableList<Person> state$ = FXCollections.observableArrayList(Person.observables$);

    @Override
    public void delete(Person person) {
        state$.remove(person);
    }

    @Override
    public void delete(Integer id) {
        // state$.remove(id - 1); // We perform a - 1, because each newly added person has an id incremented by one on each add
        // The second implementation is less error prone as we find the index of the element matching
        // the id parameter before deleting it
        state$.removeIf(p -> p.getId() == id);
    }

    @Override
    public void create(Person person) {
        // We generate a new identifier to the application user
        // if the id property is missing
        if (person.getId() == 0) {
            person.setId(state$.size() + 1);
        }
        state$.add(person);
    }

    @Override
    public boolean update(Integer id, Person person) {
        return false;
    }

    @Override
    public Person get(Integer id) {
        return state$.get(indexOf(state$, p -> p.getId() == id));
    }

    @Override
    public Stream<Person> getAll() {
        return state$.stream();
    }

    @Override
    public ObservableList<Person> createStateProperty() {
        return state$;
    }
}
