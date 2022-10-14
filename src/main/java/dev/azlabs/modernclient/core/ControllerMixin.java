package dev.azlabs.modernclient.core;

import javafx.beans.value.ObservableValue;
import javafx.beans.property.Property;

public interface ControllerMixin {
    default public  <T> void bind(Property<T> property, ObservableValue<T> observableValue) {
        property.bind(observableValue);
    }
}
