package br.sergio.utils.math;

public class MutableVector extends Vector {
	
	public MutableVector() {
		super();
	}
	
	public MutableVector(double x, double y) {
		super(x, y);
	}
	
	public MutableVector(double x, double y, double z) {
		super(x, y, z);
	}
	
	public MutableVector(Point p) {
		super(p);
	}
	
	public MutableVector(Point origin, Point end) {
		super(origin, end);
	}
	
	@Override
	public MutableVector add(Vector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}
	
	@Override
	public MutableVector subtract(Vector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}
	
	@Override
	public MutableVector multiplyByScalar(double scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}
	
	@Override
	public MutableVector crossProduct(Vector v) {
		double i = y * v.z - v.y * z;
		double j = v.x * z - x * v.z;
		double k = x * v.y - v.x * y;
		x = i;
		y = j;
		z = k;
		return this;
	}
	
}
