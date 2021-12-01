package frontend.components;

import backend.model.components.Ellipse;
import backend.model.components.Point;
import frontend.engines.OvalsEngine;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

final public class EllipseFrontEnd extends Ellipse {

    private final GraphicsContext graphicsContext;

    public EllipseFrontEnd(Point middlePoint, double minAxis, double maxAxis, GraphicsContext graphicsContext) {
        super(middlePoint, minAxis, maxAxis);
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void display() {
        OvalsEngine.print(graphicsContext, getCenterPoint(), getWidth(), getHeight(), Color.web(getColor()), Color.web(getStrokeColor()), getStrokeWidth());
    }

}
