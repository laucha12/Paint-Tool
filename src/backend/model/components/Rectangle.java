package backend.model.components;

import backend.model.exceptions.BackEndException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        if(topLeft.getX()>bottomRight.getX()||topLeft.getY()>bottomRight.getY()){
            throw new BackEndException("No se puede armar la figura");
        }
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    //SELECTABLE METHODS
    @Override
    public final boolean belongs(Point point){
        return  point.getX() > this.getTopLeft().getX() && point.getX() < this.getBottomRight().getX() &&
                point.getY() > this.getTopLeft().getY() && point.getY() < this.getBottomRight().getY();
    }

    @Override
    public final boolean inside(Point point1, Point point2){
        return point2.getX() > getBottomRight().getX() && point2.getY() > getBottomRight().getY() &&
          point1.getX() < getTopLeft().getX() && point1.getY() < getTopLeft().getY();
    }

    //GETTERS
    @Override
    public final Collection<Point> getPoints() {
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(topLeft);
        toReturn.add(bottomRight);
        return toReturn;
    }

    public final Point getTopLeft() {
        return topLeft;
    }

    public final Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public final double getWidth() {
        return Math.abs( bottomRight.getX() - topLeft.getX());
    }

    @Override
    public final double getHeight() { return Math.abs(topLeft.getY() - bottomRight.getY()); }

    @Override
    public String identifier() {
        return "Rectangulo";
    }
}
