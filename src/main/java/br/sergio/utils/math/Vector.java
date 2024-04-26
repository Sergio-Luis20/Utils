package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

public class Vector implements Serializable, Cloneable {
	
	protected double x, y, z;
	
	// Null vector constructor
	public Vector() {
		this(0, 0, 0);
	}
	
	public Vector(double x, double y) {
		this(x, y, 0);
	}
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(Point p) {
		this(p.getX(), p.getY(), p.getZ());
	}
	
	public Vector(Point origin, Point end) {
		Point difference = end.subtract(origin);
		x = difference.getX();
		y = difference.getY();
		z = difference.getZ();
	}
	
	public static Vector trigonometric(double magnitude, double angle) {
		double magAbs = Math.abs(magnitude);
		return new Vector(magAbs * Math.cos(angle), magAbs * Math.sin(angle));
	}
	
	public static Vector fromLineMatrix(Matrix matrix) {
		if(!matrix.isLine() || matrix.getColumnAmount() != 3) {
			throw new MathException("invalid matrix");
		}
		return new Vector(matrix.getValue(0, 0), matrix.getValue(0, 1), matrix.getValue(0, 2));
	}
	
	public static Vector fromColumnMatrix(Matrix matrix) {
		if(!matrix.isColumn() || matrix.getLineAmount() != 3) {
			throw new MathException("invalid matrix");
		}
		return new Vector(matrix.getValue(0, 0), matrix.getValue(1, 0), matrix.getValue(2, 0));
	}
	
	public Matrix toLineMatrix() {
		return Matrix.lineMatrix(new double[] {x, y, z});
	}
	
	public Matrix toColumnMatrix() {
		return Matrix.columnMatrix(new double[] {x, y, z});
	}
	
	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y, z + v.z);
	}

	public Vector addSet(Vector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public Vector addX(double x) {
		return new Vector(this.x + x, y, z);
	}

	public Vector addXSet(double x) {
		this.x += x;
		return this;
	}

	public Vector addY(double y) {
		return new Vector(x, this.y + y, z);
	}

	public Vector addYSet(double y) {
		this.y += y;
		return this;
	}

	public Vector addZ(double z) {
		return new Vector(x, y, this.z + z);
	}

	public Vector addZSet(double z) {
		this.z += z;
		return this;
	}
	
	public Vector subtract(Vector v) {
		return new Vector(x - v.x, y - v.y, z - v.z);
	}

	public Vector subtractSet(Vector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	public Vector subtractX(double x) {
		return new Vector(this.x - x, y, z);
	}

	public Vector subtractXSet(double x) {
		this.x -= x;
		return this;
	}

	public Vector subtractY(double y) {
		return new Vector(x, this.y - y, z);
	}

	public Vector subtractYSet(double y) {
		this.y -= y;
		return this;
	}

	public Vector subtractZ(double z) {
		return new Vector(x, y, this.z - z);
	}

	public Vector subtractZSet(double z) {
		this.z -= z;
		return this;
	}
	
	public Vector multiplyByScalar(double scalar) {
		double x = this.x * scalar;
		double y = this.y * scalar;
		double z = this.z * scalar;
		return new Vector(x, y, z);
	}

	public Vector multiplyByScalarSet(double scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	public Vector reverseDirection() {
		return multiplyByScalar(-1);
	}
	
	public double dotProduct(Vector v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	public Vector crossProduct(Vector v) {
		double i = y * v.z - v.y * z;
		double j = v.x * z - x * v.z;
		double k = x * v.y - v.x * y;
		return new Vector(i, j, k);
	}
	
	public Vector projection(Vector v) {
		if(v.isNull()) {
			return newNull();
		} else {
			return v.multiplyByScalar(dotProduct(v) / v.magnitudeSquared());
		}
	}

	public boolean isOrthogonal(Vector v) {
		return dotProduct(v) == 0;
	}
	
	public boolean isMultipleOf(Vector v) {
		return crossProduct(v).isNull();
	}
	
	public Vector unitVector() {
		if(isNull()) {
			throw new MathException("the null vector doesn't have a unit vector");
		}
		return multiplyByScalar(1 / magnitude());
	}

	// uses fast inverse sqrt algorithm, which is faster but has less precision
	public Vector fastUnitVector() {
		if(isNull()) {
			throw new MathException("the null vector doesn't have a unit vector");
		}
		return multiplyByScalar(MathUtils.inverseSqrt(magnitudeSquared()));
	}

	public Vector newMagnitude(double scalar) {
		if(isNull()) {
			throw new MathException("the null vector cannot generate a new magnitude");
		}
		return unitVector().multiplyByScalarSet(scalar);
	}

	public Vector fastNewMagnitude(double scalar) {
		if(isNull()) {
			throw new MathException("the null vector cannot generate a new magnitude");
		}
		return fastUnitVector().multiplyByScalarSet(scalar);
	}
	
	public double angle(Vector v) {
		if(isNull() || v.isNull()) {
			return Math.PI / 2;
		} else {
			return Math.acos(dotProduct(v) / (magnitude() * v.magnitude()));
		}
	}
	
	public boolean isNull() {
		return x == 0 && y == 0 && z == 0;
	}
	
	public static double mixedProduct(Vector u, Vector v, Vector w) {
		return u.dotProduct(v.crossProduct(w));
	}

	public static Vector newNull() {
		return new Vector();
	}

	public static Vector sum(Vector... vectors) {
		Vector result = newNull();
		for(Vector v : vectors) {
			result.addSet(v);
		}
		return result;
	}

	public static Vector sum(Iterable<? extends Vector> iterable) {
		Vector result = newNull();
		for(Vector v : iterable) {
			result.addSet(v);
		}
		return result;
	}

	public static double[] getXArray(Vector... vectors) {
		return getCoordinateArray(Vector::getX, vectors);
	}

	public static double[] getYArray(Vector... vectors) {
		return getCoordinateArray(Vector::getY, vectors);
	}

	public static double[] getZArray(Vector... vectors) {
		return getCoordinateArray(Vector::getZ, vectors);
	}
	
	private static double[] getCoordinateArray(Function<Vector, Double> toCoord, Vector... vectors) {
		double[] array = new double[vectors.length];
		for(int i = 0; i < array.length; i++) {
			array[i] = toCoord.apply(vectors[i]);
		}
		return array;
	}

	public static Vector newXVersor() {
		return new Vector(1, 0, 0);
	}

	public static Vector newYVersor() {
		return new Vector(0, 1, 0);
	}

	public static Vector newZVersor() {
		return new Vector(0, 0, 1);
	}
	
	public Point toPoint() {
		return new Point(x, y, z);
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double magnitudeSquared() {
		return dotProduct(this);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public Vector getXComponent() {
		return new Vector(x, 0, 0);
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Vector getYComponent() {
		return new Vector(0, y, 0);
	}
	
	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public Vector getZComponent() {
		return new Vector(0, 0, z);
	}

	@Override
	public Vector clone() {
		return new Vector(x, y, z);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof Vector v) {
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
