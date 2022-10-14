package dev.azlabs.modernclient.core;

import javafx.collections.ObservableList;

public interface ObservableRepositoryInterface<TKey extends Integer, TValue> extends RepositoryInterface<TKey, TValue> {
    public ObservableList<TValue> createStateProperty();
}
