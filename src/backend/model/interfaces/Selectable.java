package backend.model.interfaces;

import backend.model.components.Point;

public interface Selectable {
    boolean belongs(Point point);
    boolean inside(Point point1, Point point2);

    //En la figura hacemos selected = true (internamente la figura cambia su color de stroke por el de selccionar)
    void select();
    //En la figura hacemos selected = false (internamente la figura cambia su color de stroke por el anterior antes de ser seleccionado)
    void unselect();
}
