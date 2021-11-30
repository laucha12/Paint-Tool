package backend.model;

// It isn't reallu a rectangle, it's just a path between two points (in the backend)

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Line implements Figure{

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
        return false;
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
}
