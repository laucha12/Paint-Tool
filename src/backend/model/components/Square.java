package backend.model.components;


import backend.model.FigureStyle;
import backend.model.exceptions.BackEndException;

public abstract class Square extends Rectangle {

    private static final String NAME = "Cuadrado";

    public Square(Point topLeft, Point bottomRight, FigureStyle color) {
        super(topLeft, bottomRight,color);

        if(topLeft.distanceXAxisTo(bottomRight) != topLeft.distanceYAxisTo(bottomRight))
            throw new BackEndException("Cannot instantiate Square with points that don't form a square");
    }

    @Override
    public final String identifier() { return NAME; }

}
