package backend.model.components;

public abstract class Circle extends Ellipse {

    private final static String NAME = "Cuadrado";

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, radius, radius);

    }

    public double getRadius(){
        return this.getHeight();
    }

    @Override public String identifier() { return NAME;}
}
