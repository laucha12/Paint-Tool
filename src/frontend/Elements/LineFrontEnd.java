package frontend.Elements;

import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//TODO: POINT FRONT-END????, IMPLEMENTS PRINT_RECTANGLE ??

public class LineFrontEnd extends Line {

    private final GraphicsContext graphicsContext;
    private static final double WIDTH = 20;

    public LineFrontEnd(Point start, Point end, GraphicsContext gc){
        super(start, end);
        this.graphicsContext = gc;
        this.setStrokeColor(this.getColor());
    }


    @Override
    public void display() {
        graphicsContext.setStroke(Color.web(this.getStrokeColor()));
        graphicsContext.setLineWidth(getStrokeWidth());
        graphicsContext.strokeLine(getStart().getX(), getStart().getY(),getEnd().getX(),getEnd().getY());
    }



}
