package br.sergio.utils.math;

import java.io.Serializable;

public class Line2D implements Serializable, Cloneable {
    
    private double a, b, c;

    private final Point point;
    private final Vector vector;

    public Line2D(Point p1, Point p2) {
        if(p1 == null || p2 == null) {
            throw new NullPointerException("p1 or p2 = null");
        }
        if(p1.equals(p2)) {
            throw new IllegalArgumentException("p1 = p2");
        }
        setABC(p1, p2);
        point = new Point(p1.getX(), p1.getY(), p1.getZ());
        vector = new Vector(a, b).unitVector();
    }

    public Line2D(Point point, Vector vector) {
        if(point == null || vector == null) {
            throw new NullPointerException("point or vector = null");
        }
        if(vector.getX() == 0 && vector.getY() == 0) {
            throw new IllegalArgumentException("NULL vector");
        }
        this.point = point.clone();
        this.vector = vector.clone().unitVector();
        setABC(point, getPoint(1));
    }

    private void setABC(Point p1, Point p2) {
        a = p2.getY() - p1.getY();
        b = p1.getX() - p2.getX();
        c = p2.getX() * p1.getY() - p1.getX() * p2.getY();
    }

    public boolean isHorizontal() {
        return vector.getY() == 0;
    }

    public boolean isVertical() {
        return vector.getX() == 0;
    }

    public boolean contains(Point p) {
        return a * p.getX() + b * p.getY() + c == 0;
    }

    public double distance(Point p) {
        return Math.abs(a * p.getX() + b * p.getY() + c) / Math.hypot(a, b);
    }

    public boolean isParallel(Line2D line) {
        return determinant(line) == 0;
    }

    public boolean isOrthogonal(Line2D line) {
        return vector.isOrthogonal(line.vector);
    }
    
    public double getSlopeX() {
    	if(isVertical()) {
    		return Double.POSITIVE_INFINITY;
    	} else if(isHorizontal()) {
    		return 0;
    	} else {
    		return -a / b;
    	}
    }
    
    public double getSlopeY() {
    	if(isHorizontal()) {
    		return Double.POSITIVE_INFINITY;
    	} else if(isVertical()) {
    		return 0;
    	} else {
    		return -b / a;
    	}
    }

    public double determinant(Line2D line) {
        return a * line.b - b * line.a;
    }

    public Point getIntersection(Line2D line) {
        double d = determinant(line);
        if(d == 0) {
            return null;
        }
        double detX = c * line.b - b * line.c;
        double detY = a * line.c - c * line.a;
        return new Point(detX / d, detY / d);
    }

    public double distance(Line2D line) {
        if(!isParallel(line)) {
            return 0;
        }
        return distance(line.getPoint());
    }

    public Point getPoint(double t) {
        return new Point(getX(t), getY(t));
    }
    
    public double y(double x) {
    	if(isVertical()) {
    		return Double.NaN;
    	} else if(isHorizontal()) {
    		return point.getY();
    	} else {
    		// Avoid negative zero
    		double r = a * x + c;
    		if(r == 0) {
    			return 0;
    		}
    		return -r / b;
    	}
    }
    
    public double x(double y) {
    	if(isHorizontal()) {
    		return Double.NaN;
    	} else if(isVertical()) {
    		return point.getX();
    	} else {
    		// Avoid negative zero
    		double r = b * y + c;
    		if(r == 0) {
    			return 0;
    		}
    		return -r / a;
    	}
    }

    public double getX(double t) {
        return point.getX() + t * vector.getX();
    }

    public double getY(double t) {
        return point.getY() + t * vector.getY();
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public Point getPoint() {
        return point;
    }

    public Vector getVector() {
        return vector.clone();
    }
    
    @Override
    public Line2D clone() {
    	return new Line2D(point, vector);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(o == this) {
            return true;
        }
        if(o instanceof Line2D line) {
            return isParallel(line) && contains(line.point);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(isHorizontal()) {
            return (int) point.getY();
        } else if(isVertical()) {
            return (int) point.getX();
        } else {
            return (int) (vector.getY() / vector.getX());
        }
    }

}
