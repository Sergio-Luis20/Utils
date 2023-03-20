package br.sergio.utils;

import java.io.Serializable;
import java.util.Objects;

public class Pair<M, F> implements Serializable {
    
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

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(o == this) {
            return true;
        }
        if(o instanceof Pair<?, ?> pair) {
            boolean maleEquals = male == null ? pair.male == null : male.equals(pair.male);
            boolean femaleEquals = female == null ? pair.female == null : female.equals(pair.female);
            return maleEquals && femaleEquals;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(male, female);
    }

}
