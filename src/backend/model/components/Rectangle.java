package backend.model.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    //SELECTABLE METHODS
    @Override
    public boolean belongs(Point point){
        return  point.getX() > this.getTopLeft().getX() && point.getX() < this.getBottomRight().getX() &&
                point.getY() > this.getTopLeft().getY() && point.getY() < this.getBottomRight().getY();
    }

    @Override
    public boolean inside(Point point1, Point point2){
        return point2.getX() > getBottomRight().getX() && point2.getY() > getBottomRight().getY() &&
          point1.getX() < getTopLeft().getX() && point1.getY() < getTopLeft().getY();
    }

    //GETTERS
    @Override
    public Collection<Point> getPoints() {
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(topLeft);
        toReturn.add(bottomRight);
        return toReturn;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public double getWidth() {
        return Math.abs( bottomRight.getX() - topLeft.getX());
    }

    @Override
    public double getHeight() { return Math.abs(topLeft.getY() - bottomRight.getY()); }

    @Override
    public String identifier() {
        return "Rectangulo";
    }
}
