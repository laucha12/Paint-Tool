package backend.model;

import backend.model.components.Figure;
import backend.model.components.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CanvasState {

    //Guarda una lista con las figuras que estan presentes en el canvas
    private final LinkedList<Figure> list = new LinkedList<>();

    //Guarda una lista con las figuras selccionadas
    private final List<Figure> selected = new ArrayList<>();

    //Guarda el numero de figuras seleccionadas antes de entrar de nuevo a un selection mode
    private int lastSelectedElementsAmount;

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

    public boolean selectFigure(Figure figure, Point actualPoint, Point eventPoint){
        //decido si una figura esta seleccionada o no viendo si los puntos estan dentro de la misma o no
        if((figure.belongs(eventPoint) && actualPoint.equals(eventPoint) )|| figure.inside(actualPoint, eventPoint)) {
            figure.select();
            //La agrego a mi lista de seleccionadas
            selected.add(figure);
            return true;
        }
        return false;
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

    public void selectionModeStart() {
        lastSelectedElementsAmount = getSelected().size();
    }

    public void selectionModeEnded() {
        if(lastSelectedElementsAmount == getSelected().size())
            unselectAll();
    }

}
