package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Objects;

public class IntVector implements Serializable, Cloneable {
    
    public static final IntVector NULL = new IntVector(0, 0, 0);

    protected double magnitude;
    protected int x, y, z;
    protected IntPoint origin, end;
    
    public IntVector() {
		this(IntPoint.ORIGIN, IntPoint.ORIGIN);
	}
	
	public IntVector(int x, int y) {
		this(x, y, 0);
	}
	
	public IntVector(int x, int y, int z) {
		this(IntPoint.ORIGIN, new IntPoint(x, y, z));
	}
	
	public IntVector(IntPoint p) {
		this(IntPoint.ORIGIN, p);
	}
	
	public IntVector(IntPoint origin, IntPoint end) {
		this.origin = origin.clone();
		this.end = end.clone();
		IntPoint difference = end.subtract(origin);
		x = difference.getX();
		y = difference.getY();
		z = difference.getZ();
		magnitude = Math.sqrt(x * x + y * y + z * z);
	}

    public IntVector add(IntVector v) {
		return new IntVector(x + v.x, y + v.y, z + v.z);
	}
	
	public IntVector subtract(IntVector v) {
		return new IntVector(x - v.x, y - v.y, z - v.z);
	}
	
	public IntVector multiplyByScalar(int scalar) {
		int x = this.x * scalar;
		int y = this.y * scalar;
		int z = this.z * scalar;
		return new IntVector(x, y, z);
	}
	
	public int dotProduct(IntVector v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	public IntVector crossProduct(IntVector v) {
		int i = y * v.z - v.y * z;
		int j = v.x * z - x * v.z;
		int k = x * v.y - v.x * y;
		return new IntVector(i, j, k);
	}
	
	public IntVector projection(IntVector v) {
		if(v.isNull()) {
			return NULL;
		} else {
            double doubleRatio = (double) dotProduct(v) / v.dotProduct(v);
			return v.multiplyByScalar((int) Math.round(doubleRatio));
		}
	}

	public boolean isOrthogonal(IntVector v) {
		return dotProduct(v) == 0;
	}
	
	public boolean isMultipleOf(IntVector v) {
		return crossProduct(v).equals(NULL);
	}
	
	public double angle(IntVector v) {
		if(equals(NULL) || v.equals(NULL)) {
			return Math.PI / 2;
		} else {
			return MathUtils.acos(dotProduct(v) / (magnitude * v.magnitude));
		}
	}
	
	public boolean isNull() {
		return equals(NULL);
	}
	
	public static double mixedProduct(IntVector u, IntVector v, IntVector w) {
		return u.dotProduct(v.crossProduct(w));
	}

    public IntPoint toIntPoint() {
        return new IntPoint(x, y, z);
    }

    public IntPoint getOrigin() {
		return origin;
	}
	
	public IntPoint getEnd() {
		return end;
	}
	
	public double getMagnitude() {
		return magnitude;
	}

    public double getX() {
		return x;
	}
	
	public IntVector getI() {
		return new IntVector(x, 0, 0);
	}
	
	public double getY() {
		return y;
	}
	
	public IntVector getJ() {
		return new IntVector(0, y, 0);
	}
	
	public double getZ() {
		return z;
	}
	
	public IntVector getK() {
		return new IntVector(0, 0, z);
	}

    @Override
    public IntVector clone() {
        return new IntVector(x, y, z);
    }

    @Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof IntVector v) {
			return x == v.x && y == v.y && z == v.z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	@Override
	public String toString() {
		String i = x + "i";
		String j = (y >= 0 ? "+" : "") + y + "j";
		String k = (z >= 0 ? "+" : "") + z + "k";
		return i + j + k;
	}

}
