package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Objects;

public class VectorialField implements Serializable, Cloneable {
    
    private DoubleTriFunction p, q, r;

    public VectorialField(DoubleTriFunction p, DoubleTriFunction q, DoubleTriFunction r) {
        setP(p);
        setQ(q);
        setR(r);
    }

    public Vector getVector(double x, double y, double z) {
        return new Vector(p.apply(x, y, z), q.apply(x, y, z), r.apply(x, y, z));
    }

    public Vector getVector(Point point) {
        return getVector(point.getX(), point.getY(), point.getZ());
    }
    
    public DoubleTriFunction getP() {
        return p;
    }
    
    public void setP(DoubleTriFunction p) {
        this.p = Objects.requireNonNull(p, "p");
    }

    public DoubleTriFunction getQ() {
        return q;
    }

    public void setQ(DoubleTriFunction q) {
        this.q = Objects.requireNonNull(q, "q");
    }

    public DoubleTriFunction getR() {
        return r;
    }

    public void setR(DoubleTriFunction r) {
        this.r = Objects.requireNonNull(r, "r");
    }

    @Override
    public VectorialField clone() {
        return new VectorialField(p, q, r);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(o instanceof VectorialField field) {
            return p.equals(field.p) && q.equals(field.q) && r.equals(field.r);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p, q, r);
    }

}
