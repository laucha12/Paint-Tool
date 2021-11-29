package frontend;

import backend.model.Point;

public class MouseEvent {

    private Point beginPoint = null, endPoint = null;

    public MouseEvent(){

    }


    public MouseEvent(Point beginPoint){
        this.beginPoint = beginPoint;
    }


    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public void setEndPoint(Point endPoint){
        this.endPoint = endPoint;
    }


}
