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

    public void resetCanvas() {
        list.clear();
    }

}

/*
*   List<Figures> selected = new ArrayList<>();
*   Metodos para agregar
*
*   public void addFigure(Figure other){
        other.select();
        selected.add(other);
    }
    public Collection<Figure> getSelected(){
        return selected;
    }

    public void unselectAll(){
        for (Figure aux : selected)
            aux.unselect();
        //limpio las figuras seleccion
        selected.clear();
    }

    public boolean isEmpty(){
        return selected.isEmpty();
    }
*
* */
