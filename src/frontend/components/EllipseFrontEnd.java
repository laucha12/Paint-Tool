package frontend.components;

import backend.model.FigureStyle;
import backend.model.components.Ellipse;
import backend.model.components.Point;
import frontend.engines.OvalsEngine;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

final public class EllipseFrontEnd extends Ellipse {

    private final GraphicsContext graphicsContext;

    public EllipseFrontEnd(Point middlePoint, double minAxis, double maxAxis, GraphicsContext graphicsContext, FigureStyle color) {
        super(middlePoint, minAxis, maxAxis,color);
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void display() {
        OvalsEngine.print(graphicsContext, getCenterPoint(), getWidth(), getHeight(), Color.web(getColor()), Color.web(getStrokeColor()), getStrokeWidth());
    }

}
