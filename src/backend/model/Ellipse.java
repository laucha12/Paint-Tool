package backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Ellipse extends Figure {


    protected final Point centerPoint;
    protected final double minAxis, maxAxis;

    public Ellipse(Point centerPoint, double minAxis, double maxAxis) {
        this.centerPoint = centerPoint;
        this.maxAxis = maxAxis;
        this.minAxis = minAxis;
    }

    @Override
    public String toString() {
        return String.format("Ellipse [Centro: %s, Radio: %.2f]", centerPoint, minAxis);
    }

    @Override
    public Collection<Point> getPoints() {
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(centerPoint);
        return toReturn;
    }

    @Override
    public double getWidth() {
        return minAxis;
    }

    @Override
    public double getHeight() {
        return maxAxis;
    }

    @Override
    public String identifier() {
        return null;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }


    @Override
    public void moveTo(double x, double y) {
        centerPoint.moveTo(x,y);
    }

    @Override
    public boolean belongs(Point point){
         return ((Math.pow(this.getCenterPoint().getX() - point.getX(), 2)/Math.pow(getWidth(),2) ) +
                 (Math.pow(this.getCenterPoint().getY() - point.getY(), 2)/Math.pow(getHeight(),2)) ) <= 1;

    }

    public boolean inside(Point point1, Point point2) {
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

}
