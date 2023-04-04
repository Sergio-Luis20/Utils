package br.sergio.utils.math;

public class Line2DSegment extends Line2D {

    private Point p1, p2;
    private double maxX, minX, maxY, minY;
    
    public Line2DSegment(Point p1, Point p2) {
        super(p1, p2);
        this.p1 = new Point(p1.getX(), p1.getY());
        this.p2 = new Point(p2.getX(), p2.getY());
        maxX = MathUtils.largest(p1.getX(), p2.getX());
        minX = MathUtils.smallest(p1.getX(), p2.getX());
        maxY = MathUtils.largest(p1.getY(), p2.getY());
        minY = MathUtils.smallest(p1.getY(), p2.getY());
    }

    public Point getFirstPoint() {
        return p1;
    }

    public Point getSecondPoint() {
        return p2;
    }

    @Override
    public boolean contains(Point p) {
        return super.contains(p) && bound(p);
    }

    @Override
    public Point getPoint(double t) {
        Point point = super.getPoint(t);
        return !bound(point) ? null : point;
    }

    @Override
    public double getX(double t) {
        double x = super.getX(t);
        return !boundX(x) ? Double.NaN : x;
    }

    @Override
    public double getY(double t) {
        double y = super.getY(t);
        return !boundY(y) ? Double.NaN : y;
    }

    @Override
    public Point getIntersection(Line2D line) {
        Point intersection = super.getIntersection(line);
        return !bound(intersection) ? null : intersection;
    }

    public boolean bound(Point p) {
        return boundX(p.getX()) && boundY(p.getY());
    }

    public boolean boundX(double x) {
        return x >= minX && x <= maxX;
    }

    public boolean boundY(double y) {
        return y >= minY && y <= maxY;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(o == this) {
            return true;
        }
        if(o instanceof Line2DSegment segment) {
            return super.equals(segment) && p1.equals(segment.p1) && p2.equals(segment.p2);
        }
        return false;
    }

}
