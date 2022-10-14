package dev.azlabs.modernclient.core;

import dev.azlabs.modernclient.models.Person;

import java.util.stream.Stream;

public interface RepositoryInterface<TKey extends Integer, TValue> {
    public void delete(TValue value);

    public void delete(TKey id);

    public void create(TValue value);

    public boolean update(TKey id, TValue value);

    public TValue get(TKey id);

    public Stream<TValue> getAll();
}
