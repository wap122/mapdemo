package vn.neolab.mapdemo;

/**
 * Code by Minh Sacred
 */
public class Line {
    private double a;
    private double b;
    private double c;
    private Vector cp;
    private Vector pt;

    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        pt = new Vector(a, b);
        cp = pt.getOpposite();
    }

    public Line(Point p1, Point p2) {
        cp = new Vector(p2.getX() - p1.getX(), p2.getY() - p1.getY());
        pt = cp.getOpposite();
        a = pt.getX();
        b = pt.getY();
        c = -(a * p1.getX() + b * p1.getY());
    }

    public Line(Point p1, Vector pt) {
        this.pt = pt;
        cp = pt.getOpposite();
        a = pt.getX();
        b = pt.getY();
        c = -(a * p1.getX() + b * p1.getY());
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double generateY(double x) {
        return (-c - a * x) / b;
    }

    public double getDistance(Point point) {
        return Math.abs(a * point.getX() + b * point.getY() + c) / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public Vector getCp() {
        return cp;
    }

    public void setCp(Vector cp) {
        this.cp = cp;
    }

    public Vector getPt() {
        return pt;
    }

    public void setPt(Vector pt) {
        this.pt = pt;
    }

    public Point getPointWithDistance(Point ori, double dis) {
        return  null;
    }
}
