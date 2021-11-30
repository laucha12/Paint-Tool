package frontend;

import backend.model.Point;

public class MouseEvent {

    private final Point startPoint;
    private Point endPoint;
    private final static int REDRAW_FACTOR = 100;
    private final double xDragged;
    private double yDragged;

    public MouseEvent(Point beginPoint){
        this.startPoint = beginPoint;
        this.xDragged = beginPoint.getX() ;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        this.yDragged = endPoint.getY();
    }

    public double getXDragged(){
        return xDragged;
    }

    public double getYDragged() {
        return yDragged;
    }
}