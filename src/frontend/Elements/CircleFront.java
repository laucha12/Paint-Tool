
package frontend.Elements;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class CircleFront extends Circle implements FrontCircle{

    private final GraphicsContext graphicsContext;

    public CircleFront(Point centerPoint, double radius, GraphicsContext gc) {
        super(centerPoint, radius);
        graphicsContext = gc;
    }

    @Override
    public void display() {
        print(graphicsContext, getCenterPoint(), getRadius(), getRadius());
    }
}
