package frontend;

import backend.model.Point;

public class MouseEvent {

    private final Point startPoint;
    private Point endPoint;

    public MouseEvent(Point beginPoint){
        this.startPoint = beginPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

}