package backend.model.components;

import backend.model.FigureStyle;

public abstract class Circle extends Ellipse {

    private final static String NAME = "Cuadrado";

    public Circle(Point centerPoint, double radius, FigureStyle color) {
        super(centerPoint, radius, radius,color);

    }

    public final double getRadius(){
        return this.getHeight();
    }

    @Override public final String identifier() { return NAME;}
}
