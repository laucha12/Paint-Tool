package frontend.engines;

import backend.model.components.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

final public class OvalsEngine {

    private OvalsEngine() {} //De esta manera no se puede instanciar la clase

    public static void print(GraphicsContext gc, Point centerPoint, double width, double length, Color fill, Color stroke, double lineWidth){
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.setLineWidth(lineWidth);
        gc.fillOval(centerPoint.getX()-(width), centerPoint.getY()-(length), width*2,length*2);
        gc.strokeOval(centerPoint.getX()-(width), centerPoint.getY()-(length),width*2,length*2);
    }
}
