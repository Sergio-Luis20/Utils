package br.sergio.utils;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class Pair<M, F> implements Serializable, Cloneable {
    
    protected M male;
    protected F female;

    public Pair() {
        this(null, null);
    }

    public Pair(M male, F female) {
        this.male = male;
        this.female = female;
    }

    public M getMale() {
        return male;
    }

    public void setMale(M male) {
        this.male = male;
    }

    public F getFemale() {
        return female;
    }

    public void setFemale(F female) {
        this.female = female;
    }

    public Optional<M> getMaleAsOptional() {
        return Optional.ofNullable(male);
    }

    public Optional<F> getFemaleAsOptional() {
        return Optional.ofNullable(female);
    }

    public void accept(BiConsumer<M, F> biConsumer) {
        biConsumer.accept(male, female);
    }

    public boolean test(BiPredicate<M, F> biPredicate) {
        return biPredicate.test(male, female);
    }

    public <C> C apply(BiFunction<M, F, C> biFunction) {
        return biFunction.apply(male, female);
    }

    public PairSupplier<M, F> asSupplier() {
        return () -> this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<M, F> clone() {
        try {
            return (Pair<M, F>) super.clone();
        } catch(CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(o == this) {
            return true;
        }
        if(o instanceof Pair<?, ?> pair) {
            return Objects.deepEquals(male, pair.male) && Objects.deepEquals(female, pair.female);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(male, female);
    }

}
