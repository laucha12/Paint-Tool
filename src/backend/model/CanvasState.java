package backend.model;

import backend.model.components.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CanvasState {

    //Guarda una lista con las figuras que estan presentes en el canvas
    private final LinkedList<Figure> list = new LinkedList<>();
    //Guarda una lista con las figuras selccionadas
    private final List<Figure> selected = new ArrayList<>();

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

    public void selectFigure(Figure figure){
        //Marco que en la figura que esta seleccionada
        figure.select();
        //La agrego a mi lista de seleccionadas
        selected.add(figure);
    }

    public Collection<Figure> getSelected(){
        return new ArrayList<>(selected);
    }

    public void unselectAll(){
        //A cada figura la deselecciono
        selected.forEach(Figure::unselect);
        //limpio las figuras seleccion
        selected.clear();
    }

    public boolean isEmpty(){
        return selected.isEmpty();
    }

    public void delete(Collection<Figure> figures){
        list.removeAll(figures);
    }

    public void resetCanvas() {
        list.clear();
    }

}
