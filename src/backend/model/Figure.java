package backend.model;

import java.util.Collection;

public interface Figure extends Movable, Drawable{
/*
    @Override
    default String toString(){
        return identifier() + "[" + getPoints() + "]";
    }*/

     Collection<Point> getPoints();

     double getWidth();

     double getHeight();

     String identifier();

     boolean belongs(Point point);
}
