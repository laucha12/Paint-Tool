package backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, radius,radius);

    }
    public double getRadius(){
        return this.getHeight();
    }

}
