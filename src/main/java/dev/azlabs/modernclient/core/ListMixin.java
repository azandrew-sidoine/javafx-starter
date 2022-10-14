package dev.azlabs.modernclient.core;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public interface ListMixin {

    default public <T> int indexOf(List<T> list, Predicate<T> predicate) {
        return IntStream.range(0, list.size())
                .filter(i -> predicate.test(list.get(i)))
                .findFirst()
                .orElse(-1);
    }

    default  public <T> void remove(List<T> list, Predicate<T> predicate) {
        var item = list.stream().filter(predicate).findFirst();
        item.ifPresent(list::remove);
    }
}
