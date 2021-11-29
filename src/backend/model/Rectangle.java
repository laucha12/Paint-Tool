package backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rectangle extends Figure {

    private final MovablePoint topLeft, bottomRight;

    public Rectangle(MovablePoint topLeft, MovablePoint bottomRight) {
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
    Collection<Point> getPoints() {
        List<Point> toReturn =  new ArrayList<Point>();
        toReturn.add(topLeft);
        toReturn.add(bottomRight);
        return toReturn;
    }

    @Override
    double getWidth() {
        return bottomRight.getX() - topLeft.getX();
    }

    @Override
    double getHeight() {
        return topLeft.getY() - bottomRight.getY();
    }

    @Override
    String identifier() {
        return "Rectangulo";
    }

    @Override
    public void moveTo(double x, double y) {
        bottomRight.moveTo(x, y);
        topLeft.moveTo(x, y);
    }
}
