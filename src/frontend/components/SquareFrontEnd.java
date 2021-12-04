package frontend.components;

import backend.model.ColorStyle;
import backend.model.components.Point;
import backend.model.components.Square;
import frontend.engines.PolygonsEngine;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

final public class SquareFrontEnd extends Square {
    private final GraphicsContext gc;

    public SquareFrontEnd(Point topLeft, Point bottomRight, GraphicsContext gc, ColorStyle color) {
        super(topLeft, bottomRight,color);
        this.gc = gc;
    }

    @Override
    public void display() {
        PolygonsEngine.print(gc,this.getTopLeft(),this.getWidth(),this.getHeight(), Color.web(getColor()),Color.web(getStrokeColor()), getStrokeWidth());
    }
}
