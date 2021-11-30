package frontend;

import backend.model.Figure;
import backend.model.Point;
import frontend.Elements.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

public enum FigureButtons {
    RECTANGLE("Rectangulo") {
        public Figure  getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            return new RectangleFrontEnd(startPoint, endPoint, gc);
        }
    },
    SQUARE("Cuadrado") {
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc) {
            Point bottomRight = new Point(endPoint.getX(), startPoint.getY() + Math.abs(endPoint.getX() - startPoint.getX()));
            //SOLO SE DIBUJA HACIA DERECHA Y ABAJO??
            return new SquareFrontEnd(startPoint, bottomRight, gc);
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
            double axis1 = Math.abs(endPoint.getX() - startPoint.getX());
            double axis2 = Math.abs(endPoint.getY() - startPoint.getY());
            return new EllipseFrontEnd(startPoint, axis1, axis2, gc);
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

    FigureButtons(String name) {
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