package backend.model.components;

import backend.model.interfaces.Movable;

//La clase Point se utilisa tanto en el front como en el back, esta hecha de manera tal
//que el front le pueda agregar funcionalidad (pero que no modifique las coordenadas
// de los puntos) pero no quitar y/o modificar la existente.
public class Point implements Movable {

    @Override
    public void moveTo(double x, double y) {
        updateX(x);
        updateY(y);
    }

    public final double distanceXAxisTo(Point other){
        return Math.abs(x - other.getX());
    }

    public final double distanceYAxisTo(Point other) {
        return Math.abs(y - other.getY());
    }

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    private void updateX(double x) {
        this.x += x;
    }

    private void updateY(double y) {
        this.y += y;
    }

    @Override
    public final boolean equals(Object other){
        if (this == other)
            return true;
        if(! (other instanceof Point))
            return false;
        Point aux= (Point) other;
        return aux.x==this.x && aux.y == this.y;
    }
}
