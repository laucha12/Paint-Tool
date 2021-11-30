package frontend.Elements;

import backend.model.Circle;
import backend.model.Ellipse;
import backend.model.Figure;

import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface PrintOvals {

    default void print(GraphicsContext gc, Point centerPoint, double width, double length, Color fill, Color stroke, double lineWidth){
        gc.setFill(fill);
        gc.setStroke(stroke);
        gc.setLineWidth(lineWidth);
        gc.fillOval(centerPoint.getX()-(width), centerPoint.getY()-(length), width*2,length*2);
        gc.strokeOval(centerPoint.getX()-(width), centerPoint.getY()-(length),width*2,length*2);
    }
}
