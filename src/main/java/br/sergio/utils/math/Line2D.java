package br.sergio.utils.math;

import java.io.Serializable;

public class Line2D implements Serializable {
    
    private double a, b, c;

    private Point point;
    private Vector vector;

    public Line2D(Point p1, Point p2) {
        if(p1 == null || p2 == null) {
            throw new NullPointerException("p1 or p2 = null");
        }
        if(p1.equals(p2)) {
            throw new IllegalArgumentException("p1 = p2");
        }
        setABC(p1, p2);
        point = new Point(p1.getX(), p1.getY(), p1.getZ());
        vector = new Vector(a, b).versor();
    }

    public Line2D(Point point, Vector vector) {
        if(point == null || vector == null) {
            throw new NullPointerException("point or vector = null");
        }
        if(vector.getX() == 0 && vector.getY() == 0) {
            throw new IllegalArgumentException("NULL vector");
        }
        this.point = point;
        this.vector = vector.versor();
        setABC(point, getPoint(1));
    }

    private void setABC(Point p1, Point p2) {
        a = p1.getY() - p2.getY();
        b = p2.getX() - p1.getX();
        c = p1.getX() * p2.getY() - p1.getY() * p2.getX();
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

    public double distance(Line2D line) {
        if(a != line.a || b != line.b) {
            return 0;
        }
        if(c == line.c) {
            return 0;
        }
        return distance(line.getPoint());
    }

    public Point getPoint(double t) {
        return new Point(getX(t), getY(t));
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
        return vector;
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
            return contains(line.point) && vector.isMultipleOf(line.vector);
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
            return (int) (point.getY() / point.getX());
        }
    }

}
