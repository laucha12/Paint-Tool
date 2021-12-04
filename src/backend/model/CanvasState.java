package backend.model;

import backend.model.components.Figure;
import backend.model.components.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

//Esta clase maneja el estado del canvas, es decir las figuras que se dibujaron,
// y la funcionalidad de seleccionarlas

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

    public String selectFigure( Point actualPoint, Point eventPoint){
        StringBuilder aux = new StringBuilder("Figuras seleccionadas: ");
        unselectAll();
        for (int i = list.size()-1; i>=0;i--) {

            Figure figure = list.get(i);
            //llamo a las funciones para verificar el bellong tanto de un punto como de una figura
            // Pido que el punto este dentro de donde se hizo click y pido que el punto donde inicio la accion sea la misma de donde termino la accion
            if(figure.inside(actualPoint, eventPoint)) {
                figure.select();
                selected.add(figure);
                aux.append(figure.toString());
            }else if ((figure.belongs(eventPoint) && actualPoint.equals(eventPoint) )){
                figure.select();
                selected.add(figure);
                  aux.append(figure.toString());
                  return  aux.toString();
            }
        }
        return aux.toString();
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


    public void delete(Collection<Figure> figures){
        list.removeAll(figures);
    }


    public void selectionModeStart() {
        lastSelectedElementsAmount = getSelected().size();
    }

    public void selectionModeEnded() {
        if(lastSelectedElementsAmount == getSelected().size())
            unselectAll();
    }

}
