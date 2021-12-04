package frontend.components;

import backend.model.FigureStyle;
import backend.model.components.Line;
import backend.model.components.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


final public class LineFrontEnd extends Line {

    private final GraphicsContext graphicsContext;

    public LineFrontEnd(Point start, Point end, GraphicsContext gc, FigureStyle color){
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
