package br.sergio.utils;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<A, B, C, D> {
    
    D apply(A a, B b, C c);

    default <E> TriFunction<A, B, C, E> andThen(Function<D, E> after) {
        Objects.requireNonNull(after);
        return (a, b, c) -> after.apply(apply(a, b, c));
    }

}
