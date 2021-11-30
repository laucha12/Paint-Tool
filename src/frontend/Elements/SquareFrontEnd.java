package frontend.Elements;

import backend.model.Point;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;

public class SquareFrontEnd extends Square implements PrintPolygons {
    private GraphicsContext gc;
    public SquareFrontEnd(Point topLeft, Point bottomRight, GraphicsContext gc) {

        super(topLeft, bottomRight);
        this.gc = gc;
    }
    @Override
    public void display() {
        print(gc,this.getTopLeft(),this.getWidth(),this.getHeight());
    }
}
