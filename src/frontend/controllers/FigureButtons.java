package frontend.controllers;

import backend.model.ColorStyle;
import backend.model.components.Figure;
import backend.model.components.Point;
import frontend.components.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

public enum FigureButtons {
    RECTANGLE("Rectangulo") {
        public Figure  getFigure(Point startPoint, Point endPoint, GraphicsContext gc, ColorStyle color) {
            return new RectangleFrontEnd(startPoint, endPoint, gc,color);
        }
    },
    SQUARE("Cuadrado") {
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc, ColorStyle color) {
            Point bottomRight = new Point(endPoint.getX(), startPoint.getY() + Math.abs(endPoint.getX() - startPoint.getX()));
            return new SquareFrontEnd(startPoint, bottomRight, gc,color);
        }
    },
    CIRCLE("Circulo") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc, ColorStyle color) {
            double radius = Math.abs(endPoint.getX() - startPoint.getX());
            return new CircleFrontEnd(startPoint, radius, gc,color);
        }
    },
    ELLIPSE("Ellipse") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc, ColorStyle color) {
            double axis1 = Math.abs(endPoint.getX() - startPoint.getX());
            double axis2 = Math.abs(endPoint.getY() - startPoint.getY());
            return new EllipseFrontEnd(startPoint, axis1, axis2, gc,color);
        }
    },
    LINE("Linea") {
        @Override
        public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc, ColorStyle color) {
            return new LineFrontEnd(startPoint, endPoint, gc,color);
        }
    }
    ;

    private final String name;
    private final ToggleButton button;

    FigureButtons(String name) {
        this.name = name;
        this.button = new ToggleButton(name);
    }

    public ToggleButton getButton() {
        return button;
    }

    abstract public Figure getFigure(Point startPoint, Point endPoint, GraphicsContext gc, ColorStyle color);

}