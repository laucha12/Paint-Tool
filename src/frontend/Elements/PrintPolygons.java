package frontend.Elements;

import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface PrintPolygons {
    default void print(GraphicsContext gc, Point centerPoint, double width, double length, Color fill, Color stroke){
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.fillRect(centerPoint.getX(), centerPoint.getY(), width,length);
        gc.strokeRect(centerPoint.getX(), centerPoint.getY(),width,length);
    }
}
