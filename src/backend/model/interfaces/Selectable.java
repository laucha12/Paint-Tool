package backend.model.interfaces;

import backend.model.components.Point;


public interface Selectable {

    //Esta funcion se utilisa para verificar que un punto del canvas esta dentro
    //p no de una figura, se utilisa para implementar la funcionalidad de
    //seleccionar una figura al clickearla
    boolean belongs(Point point);

    //Devuelve verdadero o falso si la figura se encuentra dentro de un rectangulo
    //con punto bottomRight como punto2 y topLeft como punto1, se utilisa en
    //el backend para manejar la seleccion por el cuadrado imaginario.
    boolean inside(Point point1, Point point2);

    //En la figura hacemos selected = true (internamente la figura cambia su color de stroke por el de selccionar)
    void select();
    //En la figura hacemos selected = false (internamente la figura cambia su color de stroke por el anterior antes de ser seleccionado)
    void unselect();
}
