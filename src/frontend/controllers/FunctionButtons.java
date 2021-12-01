package frontend.controllers;

import backend.model.CanvasState;

public enum FunctionButtons {
    DELETE("Borrar"){
        @Override
        public void apply(CanvasState canvasState) {
            canvasState.delete(canvasState.getSelected());
        }
    },
    TOBACK("Al fondo") {
        @Override
        public void apply(CanvasState canvasState) {
            canvasState.sendMultipleFiguresToBack(canvasState.getSelected());
            canvasState.unselectAll();
        }
    },
    TOFRONT("Al frente") {
        @Override
        public void apply(CanvasState canvasState) {
            canvasState.sendMultipleFiguresToFront(canvasState.getSelected());
            canvasState.unselectAll();
        }
    };;

    private final String name;

    abstract public void apply(CanvasState canvasState);

        FunctionButtons(String name){
            this.name = name;
    }
}
