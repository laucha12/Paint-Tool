package frontend;

import backend.model.components.Point;

public class MouseEvent {

    private final Point startPoint;
    private Point endPoint;
    private final double x, y;

    public MouseEvent(Point beginPoint){
        this.startPoint = beginPoint;
        this.x = beginPoint.getX() ;
        this.y = beginPoint.getY();
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

    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }
}