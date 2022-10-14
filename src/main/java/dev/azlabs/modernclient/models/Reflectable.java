package dev.azlabs.modernclient.models;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public interface Reflectable {

    default Field[] getDeclaredFields() {
        return getClass()
                .getDeclaredFields();
    }

    default boolean deepEquals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var properties = getDeclaredFields();
        boolean equals = true;
        for (var property : properties) {
            try {
                equals = equals && Objects.equals(property.get(this), property.get(obj));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return equals;
    }

    default int computeHashCode() {
        return Objects.hash(Objects.hash(
                Arrays.stream(getDeclaredFields())
                        .filter(field -> field.canAccess(this))
                        .map(field -> {
                            try {
                                return field.get(this);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }))
        );
    }
}
