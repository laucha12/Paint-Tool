
package frontend.components;

import backend.model.ColorStyle;
import backend.model.components.Circle;
import backend.model.components.Point;
import frontend.engines.OvalsEngine;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

final public class CircleFrontEnd extends Circle {

    private final GraphicsContext graphicsContext;

    public CircleFrontEnd(Point centerPoint, double radius, GraphicsContext gc, ColorStyle color) {
        super(centerPoint, radius,color);
        graphicsContext = gc;
    }

    @Override
    public void display() {
        OvalsEngine.print(graphicsContext, getCenterPoint(), getRadius(), getRadius(), Color.web(getColor()), Color.web(getStrokeColor()), getStrokeWidth());
    }
}
