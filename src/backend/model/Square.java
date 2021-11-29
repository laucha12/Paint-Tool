package backend.model;

public class Square extends Rectangle{

    public Square(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public String toString() {
        return "Cuadrado";
    }

}
