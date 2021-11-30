package backend.model;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CanvasState {

    //Guarda una lista con las figuras que estan presentes en el canvas
    private final List<Figure> list = new ArrayList<>();
    //TIENE QUE SER UNA LINKED LIST PARA INSERTAR ADELANTE DE TODO, PUNTO 4. Es necesario una linkedList?

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    //Me retorna un objeto iterable para que pueda recorrer sobre las distintas figuras
    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

    //Nuevos metodos creados:
    public void sendFigureToBack(Figure figure){
        list.remove(figure);
        list.add(0,figure);
    }

    public void sendFigureToFront(Figure figure){
        list.remove(figure);
        list.add(list.size(), figure);
    }

    public void sendMultipleFiguresToBack(Collection<Figure> figures){
        figures.forEach(this::sendFigureToBack);
    }

    public void sendMultipleFiguresToFront(Collection<Figure> figures){
        figures.forEach(this::sendFigureToFront);
    }


}

//PUNTO 3, BORRAR DEL CANVAS Y VOLVER A REDRAWEAR()



/*
* Modificaciones para el punto 4:
*   Se cambia el ArrayList por un LinkedList. De esta forma podemos hacer que un elemento vaya al comienzo o al final de la lista.
*
* */