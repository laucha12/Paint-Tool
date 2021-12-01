package backend.model.interfaces;

import backend.model.components.Point;

public interface Selectable {
    boolean belongs(Point point);
    boolean inside(Point point1, Point point2);
}
