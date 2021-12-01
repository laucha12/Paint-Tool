package backend.model;

import backend.model.components.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CanvasState {

    //Guarda una lista con las figuras que estan presentes en el canvas
    private final LinkedList<Figure> list = new LinkedList<>();
    //TIENE QUE SER UNA LINKED LIST PARA INSERTAR ADELANTE DE TODO, PUNTO 4

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    //Me retorna un objeto iterable para que pueda recorrer sobre las distintas figuras
    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

    public void sendFigureToBack(Figure figure){
        list.remove(figure);
        list.addFirst(figure);
    }

    public void sendFigureToFront(Figure figure){
        list.remove(figure);
        list.addLast(figure);
    }

    public void sendMultipleFiguresToBack(Collection<Figure> figures){
        figures.forEach(this::sendFigureToBack);
    }

    public void sendMultipleFiguresToFront(Collection<Figure> figures){
        figures.forEach(this::sendFigureToFront);
    }

    public void delete(Collection<Figure> figures){
        list.removeAll(figures);
    }

    public void clear() {
        list.clear();
    }

}
