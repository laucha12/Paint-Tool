package backend.model;

import java.util.Collection;

public interface Figure extends Movable {
/*
    @Override
    default String toString(){
        return identifier() + "[" + getPoints() + "]";
    }*/

     Collection<Point> getPoints();

     double getWidth();

     double getHeight();

     String identifier();

     void display(); //This function is designed such that it's implemented in the front end
                             //
     boolean belongs(Point point);
}
