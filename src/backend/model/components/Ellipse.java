package backend.model.components;

import backend.model.FigureStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Ellipse extends Figure {


    protected final Point centerPoint;
    protected final double minAxis, maxAxis;
    private final static String NAME = "Elipse";

    public Ellipse(Point centerPoint, double minAxis, double maxAxis, FigureStyle color) {
        super(color);
        this.centerPoint = centerPoint;
        this.maxAxis = maxAxis;
        this.minAxis = minAxis;
    }

    //SELECTABLE METHODS

    @Override
    public final boolean belongs(Point point){
         return ((Math.pow(this.getCenterPoint().getX() - point.getX(), 2)/Math.pow(getWidth(),2) ) +
                 (Math.pow(this.getCenterPoint().getY() - point.getY(), 2)/Math.pow(getHeight(),2)) ) <= 1;

    }

    @Override
    public final boolean inside(Point point1, Point point2) {
        if (centerPoint.getX() + getWidth()  > point2.getX()) {
            //it is outside of the rectangle on the right side
            return false;
        } else if (centerPoint.getX() - getWidth()  < point1.getX()) {
            //it is outside on the left side
            return false;
        }
        if (centerPoint.getY() + getHeight()  > point2.getY()) {
            //it is outside of the rectangle on the bottom side
            return false;

        } else if (centerPoint.getY() -  getHeight() < point1.getY()) {
            //it is outside on the top side
            return false;
        }
        return true;
    }

    //GETTERS

    @Override
    public final Collection<Point> getPoints() {
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(centerPoint);
        return toReturn;
    }

    @Override
    public final double getWidth() {
        return minAxis;
    }

    @Override
    public final double getHeight() {
        return maxAxis;
    }

    @Override
    public String identifier() {
        return NAME;
    }

    public final Point getCenterPoint() {
        return centerPoint;
    }

}
