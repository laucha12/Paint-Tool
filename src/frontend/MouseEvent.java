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

    public void setEndPoint(Point endPoint) throws Exception{
        this.endPoint = endPoint;
        if(startPoint == null || endPoint.getX() <= startPoint.getX() || endPoint.getY() <= startPoint.getY())  // Le agregamos que no deben ser iguales (antes era < no <=), TODO asegurarnos de esto
            throw new Exception("Invalid end point relative to start point");
    }

}