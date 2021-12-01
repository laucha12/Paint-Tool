package frontend.controllers;

import backend.model.CanvasState;
import backend.model.components.Figure;
import frontend.PaintPanel.*;
import frontend.engines.SelectedEngine;

import java.util.ArrayList;

public enum FunctionButtons {
    DELETE("Borrar"){
        @Override
        public void apply(SelectedEngine selectedEngine, CanvasState canvasState) {
            canvasState.delete(selectedEngine.getSelected());
        }
    },
    TOBACK("Al fondo") {
        @Override
        public void apply(SelectedEngine selectedEngine, CanvasState canvasState) {
            canvasState.sendMultipleFiguresToBack(selectedEngine.getSelected());
            selectedEngine.unselectAll();
        }
    },
    TOFRONT("Al frente") {
        @Override
        public void apply(SelectedEngine selectedEngine, CanvasState canvasState) {
            canvasState.sendMultipleFiguresToFront(selectedEngine.getSelected());
            selectedEngine.unselectAll();
        }
    };;

    private final String name;

    abstract public void apply(SelectedEngine selectedEngine, CanvasState canvasState);

        FunctionButtons(String name){
            this.name = name;
    }
}
