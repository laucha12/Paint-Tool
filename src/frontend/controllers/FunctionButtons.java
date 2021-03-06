package frontend.controllers;

import backend.model.CanvasState;

import frontend.engines.CanvasEngine;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;

//Enum que almacena la funcionalidad de botones funcionales, definidos como los
//botones que trabajan a partir del boton seleccionar.
public enum FunctionButtons {
    DELETE("Borrar") {
        @Override
        public void applyWithoutRedrawing(CanvasState canvasState, Canvas canvas) {
            canvasState.deleteSelected();
        }
    },
    TOBACK("Al fondo") {
        @Override
        public void applyWithoutRedrawing(CanvasState canvasState, Canvas canvas) {
            canvasState.sendMultipleFiguresToBack(canvasState.getSelected());
            canvasState.unselectAll();
        }
    },
    TOFRONT("Al frente") {
        @Override
        public void applyWithoutRedrawing(CanvasState canvasState, Canvas canvas) {
            canvasState.sendMultipleFiguresToFront(canvasState.getSelected());
            canvasState.unselectAll();
        }
    };

    private final String name;
    private final ToggleButton button;

    public void apply(CanvasState canvasState, Canvas canvas) {
        applyWithoutRedrawing(canvasState, canvas);
        CanvasEngine.redrawCanvas(canvasState, canvas);
    }

    abstract public void applyWithoutRedrawing(CanvasState canvasState, Canvas canvas);

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
