package backend.model;

import java.util.Collection;

public abstract class Figure implements Movable {

    @Override
    public String toString(){
        return identifier() + "[" + getPoints() + "]";
    }

    abstract Collection<Point> getPoints();

    abstract double getWidth();

    abstract double getHeight();

    abstract String identifier();

}
