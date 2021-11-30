package frontend.Elements;

import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

//TODO: POINT FRONT-END????, IMPLEMENTS PRINT_RECTANGLE ??

public class LineFrontEnd extends Line {

    private final GraphicsContext graphicsContext;
    private static final double WIDTH = 20;

    public LineFrontEnd(Point start, Point end, GraphicsContext gc){
        super(start, end);
        this.graphicsContext = gc;
    }


    @Override
    public void display() {
        graphicsContext.fillPolygon(getX(), getY(), 8);
    }

    private double[] getX(){
        return new double[]{getStart().getX() - WIDTH, getStart().getX() + WIDTH,
                            getEnd().getX() - WIDTH, getEnd().getX() + WIDTH};
    }

    private double[] getY() {
        return new double[]{getStart().getY() - WIDTH, getStart().getY() + WIDTH,
                            getEnd().getY() - WIDTH, getEnd().getX() + WIDTH};
    }
}
