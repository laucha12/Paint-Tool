package backend.model.components;

public abstract class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, radius, radius);

    }
    public double getRadius(){
        return this.getHeight();
    }

}
