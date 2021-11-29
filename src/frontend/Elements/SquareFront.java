package frontend.Elements;

import backend.model.Point;
import backend.model.Rectangle;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;

public class SquareFront extends Square implements FrontRectangle {
    private GraphicsContext gc;
    public SquareFront(Point topLeft, Point bottomRight, GraphicsContext gc) {

        super(topLeft, bottomRight);
        this.gc = gc;
    }
    @Override
    public void display() {
        print(gc,this.getTopLeft(),this.getWidth(),this.getHeight());
    }
}
