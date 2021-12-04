package frontend.engines;

import backend.model.components.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

final public class PolygonsEngine {

    private PolygonsEngine() {} //de esta manera no se puede instanciar la clase

    public static void print(GraphicsContext gc, Point centerPoint, double width, double length, Color fill, Color stroke, double lineWidth){
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.setLineWidth(lineWidth);
        gc.fillRect(centerPoint.getX(), centerPoint.getY(), width,length);
        gc.strokeRect(centerPoint.getX(), centerPoint.getY(),width,length);
    }
}
