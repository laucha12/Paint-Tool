package backend.model;

// It isn't reallu a rectangle, it's just a path between two points (in the backend)

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Line extends Figure{

    private Point start, end;
    private static final double WIDTH = 10;

    public Line(Point start, Point end) {
       this.start = start;
       this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double getWidth() {
        return WIDTH;
    }

    public double getHeight() {
        return WIDTH;
    }

    public String identifier() {
        return "Linea";
    }

    public boolean belongs(Point point){

        double m = ( this.getStart().getY() - getEnd().getY() ) / ( this.getStart().getX() - getEnd().getX() );
        double ordenada = this.getStart().getY() - this.getStart().getX() * m;
        return Math.abs( point.getY() - ( m * point.getX()+ordenada)) < 2 &&  point.getX() > this.getStart().getX() && point.getX() < this.getEnd().getX() &&
                point.getY() > this.getStart().getY() && point.getY() < this.getEnd().getY() ;
    }

    public Collection<Point> getPoints(){
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(start);
        toReturn.add(end);
        return toReturn;
    }

    @Override
    public void moveTo(double x, double y) {
        start.moveTo(x, y);
        end.moveTo(x, y);
    }


    public boolean inside(Point point1, Point point2){
        return true;//point2.getX() > getBottomRight().getX() && point2.getY() > getBottomRight().getY() &&
                //point1.getX() < getTopLeft().getX() && point1.getY() < getTopLeft().getY();
    }


}
