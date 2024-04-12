package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

public class IntVector implements Serializable, Cloneable {
    
    protected int x, y, z;
    
    public IntVector() {
		this(0, 0, 0);
	}
	
	public IntVector(int x, int y) {
		this(x, y, 0);
	}
	
	public IntVector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public IntVector(IntPoint p) {
		this(p.getX(), p.getY(), p.getZ());
	}
	
	public IntVector(IntPoint origin, IntPoint end) {
		IntPoint difference = end.subtract(origin);
		x = difference.getX();
		y = difference.getY();
		z = difference.getZ();
	}

	public Matrix toLineMatrix() {
		return Matrix.lineMatrix(new double[] {x, y, z});
	}
	
	public Matrix toColumnMatrix() {
		return Matrix.columnMatrix(new double[] {x, y, z});
	}

    public IntVector add(IntVector v) {
		return new IntVector(x + v.x, y + v.y, z + v.z);
	}

	public IntVector addSet(IntVector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public IntVector addX(int x) {
		return new IntVector(this.x + x, y, z);
	}

	public IntVector addXSet(int x) {
		this.x += x;
		return this;
	}

	public IntVector addY(int y) {
		return new IntVector(x, this.y + y, z);
	}

	public IntVector addYSet(int y) {
		this.y += y;
		return this;
	}

	public IntVector addZ(int z) {
		return new IntVector(x, y, this.z + z);
	}

	public IntVector addZSet(int z) {
		this.z += z;
		return this;
	}
	
	public IntVector subtract(IntVector v) {
		return new IntVector(x - v.x, y - v.y, z - v.z);
	}

	public IntVector subtractSet(IntVector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	public IntVector subtractX(int x) {
		return new IntVector(this.x - x, y, z);
	}

	public IntVector subtractXSet(int x) {
		this.x -= x;
		return this;
	}

	public IntVector subtractY(int y) {
		return new IntVector(x, this.y - y, z);
	}

	public IntVector subtractYSet(int y) {
		this.y -= y;
		return this;
	}

	public IntVector subtractZ(int z) {
		return new IntVector(x, y, this.z - z);
	}

	public IntVector subtractZSet(int z) {
		this.z -= z;
		return this;
	}
	
	public IntVector multiplyByScalar(int scalar) {
		int x = this.x * scalar;
		int y = this.y * scalar;
		int z = this.z * scalar;
		return new IntVector(x, y, z);
	}

	public IntVector multiplyByScalarSet(int scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	public IntVector reverseDirection() {
		return multiplyByScalar(-1);
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
			return newNull();
		} else {
            double doubleRatio = (double) dotProduct(v) / v.magnitudeSquared();
			return v.multiplyByScalar((int) Math.round(doubleRatio));
		}
	}

	public boolean isOrthogonal(IntVector v) {
		return dotProduct(v) == 0;
	}
	
	public boolean isMultipleOf(IntVector v) {
		return crossProduct(v).isNull();
	}
	
	public double angle(IntVector v) {
		if(isNull() || v.isNull()) {
			return Math.PI / 2;
		} else {
			return MathUtils.acos(dotProduct(v) / (magnitude() * v.magnitude()));
		}
	}
	
	public boolean isNull() {
		return x == 0 && y == 0 && z == 0;
	}
	
	public static double mixedProduct(IntVector u, IntVector v, IntVector w) {
		return u.dotProduct(v.crossProduct(w));
	}

	public static IntVector newNull() {
		return new IntVector();
	}

	public static IntVector sum(IntVector... vectors) {
		IntVector result = newNull();
		for(IntVector v : vectors) {
			result.addSet(v);
		}
		return result;
	}

	public static IntVector sum(Iterable<? extends IntVector> iterable) {
		IntVector result = newNull();
		for(IntVector v : iterable) {
			result.addSet(v);
		}
		return result;
	}

	public static int[] getXArray(IntVector... vectors) {
		return getCoordinateArray(IntVector::getX, vectors);
	}

	public static int[] getYArray(IntVector... vectors) {
		return getCoordinateArray(IntVector::getY, vectors);
	}

	public static int[] getZArray(IntVector... vectors) {
		return getCoordinateArray(IntVector::getZ, vectors);
	}
	
	private static int[] getCoordinateArray(Function<IntVector, Integer> toCoord, IntVector... vectors) {
		int[] array = new int[vectors.length];
		for(int i = 0; i < array.length; i++) {
			array[i] = toCoord.apply(vectors[i]);
		}
		return array;
	}

	public static IntVector newXVersor() {
		return new IntVector(1, 0, 0);
	}

	public static IntVector newYVersor() {
		return new IntVector(0, 1, 0);
	}

	public static IntVector newZVersor() {
		return new IntVector(0, 0, 1);
	}

    public IntPoint toIntPoint() {
        return new IntPoint(x, y, z);
    }
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double magnitudeSquared() {
		return dotProduct(this);
	}

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public IntVector getXComponent() {
		return new IntVector(x, 0, 0);
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public IntVector getYComponent() {
		return new IntVector(0, y, 0);
	}
	
	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public IntVector getZComponent() {
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

	public String toCoordinateString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

}
