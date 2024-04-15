package br.sergio.utils;

@FunctionalInterface
public interface PairSupplier<T, U> {
    
    Pair<T, U> get();

}
