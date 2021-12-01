package frontend.controllers;

import backend.model.CanvasState;
import backend.model.components.Figure;
import frontend.PaintPanel.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;


public enum FunctionButtons {
    DELETE("Borrar") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvasState.delete(canvasState.getSelected());
        }
    },
    TOBACK("Al fondo") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvasState.sendMultipleFiguresToBack(canvasState.getSelected());
            canvasState.unselectAll();
        }
    },
    TOFRONT("Al frente") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvasState.sendMultipleFiguresToFront(canvasState.getSelected());
            canvasState.unselectAll();
        }
    },
    CLEAR("Limpiar") {
        @Override
        public void apply(CanvasState canvasState, Canvas canvas) {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      //Se borra lo presente en la pantalla
            canvasState.resetCanvas();
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
