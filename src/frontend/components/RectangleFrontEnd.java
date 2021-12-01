package frontend.components;

import backend.model.components.Point;
import backend.model.components.Rectangle;
import frontend.engines.PolygonsEngine;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

final public class RectangleFrontEnd extends Rectangle {
    private final GraphicsContext gc;

    public RectangleFrontEnd(Point topLeft, Point bottomRight, GraphicsContext gc) {
        super(topLeft,bottomRight);
        this.gc = gc;
    }

    @Override
    public void display() {
        PolygonsEngine.print(gc,this.getTopLeft(),this.getWidth(),this.getHeight(), Color.web(getColor()),Color.web(getStrokeColor()), getStrokeWidth());
    }
}
