package backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Ellipse extends Figure {


    protected final Point centerPoint;
    protected final double radius;

    public Ellipse(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    @Override
    Collection<Point> getPoints() {
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(centerPoint);
        return toReturn;
    }

    @Override
    double getWidth() {
        return getRadius();
    }

    @Override
    double getHeight() {
        return getRadius();
    }

    @Override
    public String identifier() {
        return null;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void moveTo(double x, double y) {
        centerPoint.moveTo(x,y);
    }
}
