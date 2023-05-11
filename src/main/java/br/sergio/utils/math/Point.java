package br.sergio.utils.math;

import java.io.Serializable;
import java.util.Objects;

public class Point implements Serializable, Cloneable {
	
	public static final Point ORIGIN;
	private double x, y, z;
	
	public Point(double x, double y) {
		this(x, y, 0);
	}
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(Point copy) {
		this(copy.x, copy.y, copy.z);
	}
	
	public Point add(Point p) {
		return new Point(x + p.x, y + p.y, z + p.z);
	}
	
	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y, z - p.z);
	}
	
	public Point multiply(Point p) {
		return new Point(x * p.x, y * p.y, z * p.z);
	}
	
	public Point multiply(double factor) {
		return new Point(x * factor, y * factor, z * factor);
	}
	
	public Vector toVector() {
		return new Vector(x, y, z);
	}
	
	public double distance(Point p) {
		return MathUtils.sqrt(MathUtils.pow(x - p.x, 2) + MathUtils.pow(y - p.y, 2) + MathUtils.pow(z - p.z, 2));
	}
	
	public static double[] getXArray(Point[] array) {
		double[] x = new double[array.length];
		for(int i = 0; i < array.length; i++) {
			x[i] = array[i].x;
		}
		return x;
	}
	
	public static double[] getYArray(Point[] array) {
		double[] y = new double[array.length];
		for(int i = 0; i < array.length; i++) {
			y[i] = array[i].y;
		}
		return y;
	}
	
	public static double[] getZArray(Point[] array) {
		double[] z = new double[array.length];
		for(int i = 0; i < array.length; i++) {
			z[i] = array[i].z;
		}
		return z;
	}
	
	@Override
	public Point clone() {
		return new Point(x, y, z);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(o instanceof Point p) {
			return x == p.x && y == p.y && z == p.z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		if(this == ORIGIN) {
			return;
		}
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		if(this == ORIGIN) {
			return;
		}
		this.y = y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		if(this == ORIGIN) {
			return;
		}
		this.z = z;
	}
	
	static {
		ORIGIN = new Point(0, 0, 0);
	}
	
}
