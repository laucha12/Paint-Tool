package frontend;

import backend.model.Figure;
import backend.model.Point;
import frontend.Elements.CircleFrontEnd;
import frontend.Elements.LineFrontEnd;
import frontend.Elements.RectangleFrontEnd;
import frontend.Elements.SquareFrontEnd;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

public enum Buttons {
    RECTANGLE("Rectangulo") {
        public Figure  getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            return new RectangleFrontEnd(startPoint, endPoint, gc);
        }
    },
    SQUARE("Cuadrado") {
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            return new SquareFrontEnd(startPoint, endPoint, gc);
        }
    },
    CIRCLE("Circulo") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            double radius = Math.abs(endPoint.getX() - startPoint.getX());
            return new CircleFrontEnd(startPoint, radius, gc);
        }
    },
    ELLIPSE("Ellipse") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            double radius = Math.abs(endPoint.getX() - startPoint.getX());
            return new CircleFrontEnd(startPoint, radius, gc);
        }
    },
    LINE("Linea") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            return new LineFrontEnd(startPoint, endPoint, gc);
        }
    }
    ;

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