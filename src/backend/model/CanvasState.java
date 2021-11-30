package backend.model;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    //Guarda una lista con las figuras que estan presentes en el canvas
    private final List<Figure> list = new ArrayList<>();
    //TIENE QUE SER UNA LINKED LIST PARA INSERTAR ADELANTE DE TODO, PUNTO 4

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    //Me retorna un objeto iterable para que pueda recorrer sobre las distintas figuras
    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}

//PUNTO 3, BORRAR DEL CANVAS Y VOLVER A REDRAWEAR()