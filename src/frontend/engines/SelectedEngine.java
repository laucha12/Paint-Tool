package frontend.engines;

import backend.model.components.Figure;

import java.util.ArrayList;
import java.util.Collection;

public class SelectedEngine {
    private ArrayList<Figure> elements= new ArrayList<>();

    public void addFigure(Figure other){
        other.select();
        elements.add(other);
    }
    public Collection<Figure> getSelected(){
        return elements;
    }
    public void unselectAll(){
        for (Figure aux : elements)
            aux.resetStrokeColor();
        //limpio las figuras seleccion
        elements.clear();
    }
}
