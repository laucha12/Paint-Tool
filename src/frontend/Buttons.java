package frontend;

import backend.model.Figure;
import backend.model.Point;
import frontend.Elements.CircleFront;
import frontend.Elements.RectangleFront;
import frontend.Elements.SquareFront;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

public enum Buttons {
    RECTANGLE("Rectangulo") {
        public Figure  getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            return new RectangleFront(startPoint, endPoint, gc);
        }
    },
    SQUARE("Cuadrado") {
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            return new SquareFront(startPoint, endPoint, gc);
        }
    },
    CIRCLE("Circulo") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            double radius = Math.abs(endPoint.getX() - startPoint.getX());
            return new CircleFront(startPoint, radius, gc);
        }
    },
    SELECCIONAR( "Seleccionar") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            throw new RuntimeException();
        }
    };

    private final String name;
    private final ToggleButton button;

    Buttons(String name) {
        this.name = name;
        this.button = new ToggleButton(name);
    }

    public String getName() {
        return name;
    }

    public ToggleButton getButton() {
        return button;
    }

    abstract public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc);

    public void activate() {
        //this.getButton().setOnMouseClicked((e) -> actual = button);
    }
}