package frontend.Elements;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class EllipseFrontEnd extends Ellipse implements PrintOvals {

    private final GraphicsContext graphicsContext;

    EllipseFrontEnd(GraphicsContext graphicsContext, Point middlePoint, double minAxis, double maxAxis) {
        super(middlePoint, minAxis, maxAxis);
        this.graphicsContext = graphicsContext;
    }
    @Override
    public void display() {
        print(graphicsContext, getCenterPoint(), getWidth(), getHeight());
    }
}
