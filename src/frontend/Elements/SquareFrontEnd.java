package frontend.Elements;

import backend.model.Point;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareFrontEnd extends Square implements PrintPolygons {
    private GraphicsContext gc;
    public SquareFrontEnd(Point topLeft, Point bottomRight, GraphicsContext gc) {

        super(topLeft, bottomRight);
        this.gc = gc;
    }
    @Override
    public void display() {
        print(gc,this.getTopLeft(),this.getWidth(),this.getHeight(), Color.web(getColor()),Color.web(getStrokeColor()));
    }
}
