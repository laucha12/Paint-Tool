package backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Circle extends Ellipse {

    public Circle(MovablePoint centerPoint, double radius) {
        super(centerPoint, radius);
    }
}
