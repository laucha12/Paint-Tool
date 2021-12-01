
package frontend.components;

import backend.model.components.Circle;
import backend.model.components.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleFrontEnd extends Circle implements PrintOvals {

    private final GraphicsContext graphicsContext;

    public CircleFrontEnd(Point centerPoint, double radius, GraphicsContext gc) {
        super(centerPoint, radius);
        graphicsContext = gc;
    }

    @Override
    public void display() {
        printOval(graphicsContext, getCenterPoint(), getRadius(), getRadius(), Color.web(getColor()), Color.web(getStrokeColor()), getStrokeWidth());
    }
}
