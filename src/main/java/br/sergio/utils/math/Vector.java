package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Objects;

public class Vector implements Serializable, Cloneable {
	
	public static final Vector NULL = new Vector();

	protected double magnitude;
	protected double x, y, z;
	protected Point origin, end;
	
	public Vector() {
		this(Point.ORIGIN, Point.ORIGIN);
	}
	
	public Vector(double x, double y) {
		this(x, y, 0);
	}
	
	public Vector(double x, double y, double z) {
		this(Point.ORIGIN, new Point(x, y, z));
	}
	
	public Vector(Point p) {
		this(Point.ORIGIN, p);
	}
	
	public Vector(Point origin, Point end) {
		this.origin = origin.clone();
		this.end = end.clone();
		Point difference = end.subtract(origin);
		x = difference.getX();
		y = difference.getY();
		z = difference.getZ();
		magnitude = Math.sqrt(x * x + y * y + z * z);
	}
	
	public static Vector trigonometric(double magnitude, double angle) {
		double magAbs = MathUtils.abs(magnitude);
		return new Vector(magAbs * MathUtils.cos(angle), magAbs * MathUtils.sin(angle));
	}
	
	public static Vector fromLineMatrix(Matrix matrix) {
		if(!matrix.isLine() || matrix.getColumnAmount() != 3) {
			throw new MathException("invalid matrix");
		}
		return new Vector(matrix.getValue(0, 0), matrix.getValue(0, 1), matrix.getValue(0, 2));
	}
	
	public static Vector fromColumnMatrix(Matrix matrix) {
		if(!matrix.isColumn() && matrix.getLineAmount() != 3) {
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
	
	public Vector subtract(Vector v) {
		return new Vector(x - v.x, y - v.y, z - v.z);
	}
	
	public Vector multiplyByScalar(double scalar) {
		double x = this.x * scalar;
		double y = this.y * scalar;
		double z = this.z * scalar;
		return new Vector(x, y, z);
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
			return NULL;
		} else {
			return v.multiplyByScalar(dotProduct(v) / v.dotProduct(v));
		}
	}

	public boolean isOrthogonal(Vector v) {
		return dotProduct(v) == 0;
	}
	
	public boolean isMultipleOf(Vector v) {
		return crossProduct(v).equals(NULL);
	}
	
	public Vector versor() {
		if(equals(NULL)) {
			throw new MathException("the null vector doesn't have a versor");
		}
		return multiplyByScalar(1 / magnitude);
	}
	
	public Vector toNewMagnitude(double scalar) {
		if(equals(NULL)) {
			throw new MathException("the null vector cannot generate a new magnitude");
		}
		return multiplyByScalar(MathUtils.abs(scalar) / magnitude);
	}
	
	public double angle(Vector v) {
		if(equals(NULL) || v.equals(NULL)) {
			return Math.PI / 2;
		} else {
			return MathUtils.acos(dotProduct(v) / (magnitude * v.magnitude));
		}
	}
	
	public boolean isNull() {
		return equals(NULL);
	}
	
	public static double mixedProduct(Vector u, Vector v, Vector w) {
		return u.dotProduct(v.crossProduct(w));
	}
	
	public Point toPoint() {
		return new Point(x, y, z);
	}
	
	public Point getOrigin() {
		return origin;
	}
	
	public Point getEnd() {
		return end;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public double getX() {
		return x;
	}
	
	public Vector getI() {
		return new Vector(x, 0, 0);
	}
	
	public double getY() {
		return y;
	}
	
	public Vector getJ() {
		return new Vector(0, y, 0);
	}
	
	public double getZ() {
		return z;
	}
	
	public Vector getK() {
		return new Vector(0, 0, z);
	}
	
	@Override
	public Vector clone() {
		return new Vector(origin, end);
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
	
}
