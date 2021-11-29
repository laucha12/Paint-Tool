package backend.model;

public abstract class Square extends Rectangle{

    public Square(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public String toString() {
        return "Cuadrado";
    }

}
