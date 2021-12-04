package backend.model.components;

// It isn't reallu a rectangle, it's just a path between two points (in the backend)

import backend.model.ColorStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Line extends Figure {

    private final Point start;
    private final Point end;
    private static final double WIDTH = 10;

    public Line(Point start, Point end, ColorStyle color) {
        super(color);

       this.start = start;
       this.end = end;
       
       this.setStrokeColor(this.getColor()); //Necesario hacer esto sino aparece toda negra la lines
    }

    //Por respuesta del foro de la catedra no hace falta que se pueda seleccionar ni que se muestre en el status panel
    @Override
    public final boolean belongs(Point point){
     return false;
    }

    @Override
    public final Collection<Point> getPoints(){
        List<Point> toReturn = new ArrayList<>();
        toReturn.add(start);
        toReturn.add(end);
        return toReturn;
    }

    public final boolean inside(Point point1, Point point2){
        return point2.getX() > this.getEnd().getX() && point2.getY() > getEnd().getY() &&
                point1.getX() < getStart().getX() && point1.getY() < getStart().getY();
    }

    public final Point getStart() {
        return start;
    }

    public final Point getEnd() {
        return end;
    }

    public final double getWidth() {
        return WIDTH;
    }

    public final double getHeight() {
        return WIDTH;
    }

    public final String identifier() {
        return "Linea";
    }

}
