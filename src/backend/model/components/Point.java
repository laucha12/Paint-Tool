package backend.model.components;

import backend.model.interfaces.Movable;

public class Point implements Movable {

    @Override
    public void moveTo(double x, double y) {
        updateX(x);
        updateY(y);
    }

    public double distanceXAxisTo(Point other){
        return Math.abs(x - other.getX());
    }

    public double distanceYAxisTo(Point other) {
        return Math.abs(y - other.getY());
    }

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    protected void updateX(double x) {
        this.x += x;
    }

    protected void updateY(double y) {
        this.y += y;
    }
}
