package frontend.Elements;

import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

    public interface PrintPolygons {
    default void print(GraphicsContext gc, Point centerPoint, double width, double length){
        gc.fillRect(centerPoint.getX(), centerPoint.getY(), width,length);
        gc.strokeRect(centerPoint.getX(), centerPoint.getY(),width,length);
    }
}
