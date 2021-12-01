package frontend.controllers;

import backend.model.CanvasState;
import backend.model.components.Figure;
import frontend.PaintPanel.*;

import java.util.ArrayList;

public enum FunctionButtons {
    DELETE("Borrar"){
        @Override
        public void apply(ArrayList<Figure> figuresSelected, CanvasState canvasState) {
            canvasState.delete(figuresSelected);
        }
    };

    private final String name;

    abstract public void apply(ArrayList<Figure> figuresSelected, CanvasState canvasState);

    FunctionButtons(String name){
        this.name = name;
    }
}
