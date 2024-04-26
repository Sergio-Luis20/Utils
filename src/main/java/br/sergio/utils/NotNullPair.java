package br.sergio.utils;

import java.util.Objects;

public final class NotNullPair<M, F> extends Pair<M, F> {

    public NotNullPair(M male, F female) {
        super(Objects.requireNonNull(male, "male"), Objects.requireNonNull(female, "female"));
    }

    public void setMale(M male) {
        this.male = Objects.requireNonNull(male, "male");
    }

    public void setFemale(F female) {
        this.female = Objects.requireNonNull(female, "female");
    }

}
