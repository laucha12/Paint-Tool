package frontend.components;

import backend.model.components.Ellipse;
import backend.model.components.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EllipseFrontEnd extends Ellipse implements PrintOvals {

    private final GraphicsContext graphicsContext;

    public EllipseFrontEnd(Point middlePoint, double minAxis, double maxAxis, GraphicsContext graphicsContext) {
        super(middlePoint, minAxis, maxAxis);
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void display() {
        printOval(graphicsContext, getCenterPoint(), getWidth(), getHeight(), Color.web(getColor()), Color.web(getStrokeColor()), getStrokeWidth());

    }

}
