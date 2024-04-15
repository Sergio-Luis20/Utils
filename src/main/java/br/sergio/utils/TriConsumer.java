package br.sergio.utils;

import java.util.Objects;

@FunctionalInterface
public interface TriConsumer<A, B, C> {
    
    void accept(A a, B b, C c);

    default TriConsumer<A, B, C> andThen(TriConsumer<? super A, ? super B, ? super C> other) {
        Objects.requireNonNull(other);
        return (a, b, c) -> {
            accept(a, b, c);
            other.accept(a, b, c);
        };
    }

}
