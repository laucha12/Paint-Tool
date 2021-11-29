package backend.model;

public class MovablePoint extends Point implements Movable {

    public MovablePoint(double x, double y){
        super(x,y);
    }

    //Estamos trabajando con metodos setter protegidos, es decir point y movablePoint son clases mutables, para mejorar la eficiiencia al momento de mover las figuras
    @Override
    public void moveTo(double x, double y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
