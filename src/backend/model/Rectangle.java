package backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Rectangle implements Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public Collection<Point> getPoints() {
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(topLeft);
        toReturn.add(bottomRight);
        return toReturn;
    }

    @Override
    public double getWidth() {
        return Math.abs( bottomRight.getX() - topLeft.getX());
    }

    @Override
    public double getHeight() {

        return Math.abs(topLeft.getY() - bottomRight.getY());
    }

    @Override
    public String identifier() {
        return "Rectangulo";
    }

    @Override
    public void moveTo(double x, double y) {
        bottomRight.moveTo(x, y);
        topLeft.moveTo(x, y);
    }
}
