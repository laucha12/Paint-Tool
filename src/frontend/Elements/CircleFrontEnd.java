
package frontend.Elements;

import backend.model.Circle;
import backend.model.Point;
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
        print(graphicsContext, getCenterPoint(), getRadius(), getRadius(), Color.web(getColor()), Color.web(getStrokeColor()));
    }
}
