package backend.model.components;

// It isn't reallu a rectangle, it's just a path between two points (in the backend)

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Line extends Figure {

    private final Point start;
    private final Point end;
    private static final double WIDTH = 10;

    public Line(Point start, Point end) {
       this.start = start;
       this.end = end;
       
       this.setStrokeColor(this.getColor()); //Necesario hacer esto sino aparece toda negra la lines
    }

    //Por respuesta del foro de la catedra no hace falta que se pueda seleccionar ni que se muestre en el status panel
    @Override
    public boolean belongs(Point point){
     return false;
    }

    @Override
    public Collection<Point> getPoints(){
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(start);
        toReturn.add(end);
        return toReturn;
    }

    public boolean inside(Point point1, Point point2){
        return point2.getX() > this.getEnd().getX() && point2.getY() > getEnd().getY() &&
                point1.getX() < getStart().getX() && point1.getY() < getStart().getY();
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

}
