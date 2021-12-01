package frontend.controllers;

import backend.model.CanvasState;
import backend.model.components.Figure;
import frontend.PaintPanel.*;
import frontend.engines.CanvasEngine;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;


public enum FunctionButtons {
    DELETE("Borrar") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvasState.delete(canvasState.getSelected());
            CanvasEngine.redrawCanvas(canvasState, canvas);
        }
    },
    TOBACK("Al fondo") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvasState.sendMultipleFiguresToBack(canvasState.getSelected());
            canvasState.unselectAll();
            CanvasEngine.redrawCanvas(canvasState, canvas);
        }
    },
    TOFRONT("Al frente") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvasState.sendMultipleFiguresToFront(canvasState.getSelected());
            canvasState.unselectAll();
            CanvasEngine.redrawCanvas(canvasState, canvas);
        }
    },
    CLEAR("Limpiar") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      //Se borra lo presente en la pantalla
            canvasState.resetCanvas();
            CanvasEngine.redrawCanvas(canvasState, canvas);
        }
    };

    private final String name;
    private final ToggleButton button;

    abstract public void apply(CanvasState canvasState, Canvas canvas);

    FunctionButtons(String name) {
        this.name = name;
        this.button = new ToggleButton(name);
    }

    public String getName() {
        return name;
    }

    public ToggleButton getButton() {
        return button;
    }

}
