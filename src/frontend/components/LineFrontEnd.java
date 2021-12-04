package frontend.components;

import backend.model.ColorStyle;
import backend.model.components.Line;
import backend.model.components.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//TODO: POINT FRONT-END????, IMPLEMENTS PRINT_RECTANGLE ??

final public class LineFrontEnd extends Line {

    private final GraphicsContext graphicsContext;

    public LineFrontEnd(Point start, Point end, GraphicsContext gc, ColorStyle color){
        super(start, end,color);
        this.graphicsContext = gc;
    }

    @Override
    public void display() {
        graphicsContext.setStroke(Color.web(this.getStrokeColor()));
        graphicsContext.setLineWidth(getStrokeWidth());
        graphicsContext.strokeLine(getStart().getX(), getStart().getY(),getEnd().getX(),getEnd().getY());
    }

}
