package juc.collection;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface Iterable<T> {

    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);

//        for (T t : this) {
//            action.accept(t);
//        }
    }

    default Spliterator<T> spliterator() {
        return null;
//        return Spliterators.spliteratorUnknownSize(new java.lang.Iterator(), 0);
    }
}
