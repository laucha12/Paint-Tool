package frontend;

import backend.model.Point;

public class MouseEvent {

    private Point startPoint = null, endPoint = null;

    public MouseEvent(){}  //TODO ver si es necesario este contructor

    public MouseEvent(Point beginPoint){
        this.startPoint = beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.startPoint = beginPoint;
    }

    public void setEndPoint(Point endPoint) throws IllegalStateException{
        this.endPoint = endPoint;
        if(startPoint == null || endPoint.getX() <= startPoint.getX() || endPoint.getY() <= startPoint.getY())  // Le agregamos que no deben ser iguales (antes era < no <=), TODO asegurarnos de esto
            throw new IllegalStateException("Invalid end point relative to start point");
    }

}