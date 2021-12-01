package backend.model.components;

import backend.model.interfaces.Colorable;
import backend.model.interfaces.Drawable;
import backend.model.interfaces.Movable;
import backend.model.interfaces.Selectable;

import java.util.Collection;

public abstract class Figure implements Movable, Drawable, Colorable, Selectable {

     private static final double DEFAULT_STROKE = 1.0, MAX_STROKE = 50.0, MIN_STROKE = 1.0;
     private String color = Colorable.defaultColor(), strokeColor = Colorable.defaultStrokeColor();
     private double strokeWidth = DEFAULT_STROKE;
     private boolean selected = false;

     @Override
     public String toString(){
          return identifier() + "[" + getPoints() + "]";
     }

     abstract public Collection<Point> getPoints();

     abstract public double getWidth();

     abstract public double getHeight();

     abstract public String identifier();

     //SELECTABLE METHODS
     public void select() {selected = true;}

     public void unselect(){selected = false;}

     abstract public boolean belongs(Point point);

     //MOVABLE METHOD
     @Override
     public void moveTo(double x, double y) {
          for (Point point : getPoints())
               point.moveTo(x, y);
     }

     //COLORABLE METHODS

     public void setStrokeWidth(double strokeWidth) {
          this.strokeWidth = strokeWidth;
     }

     public double getStrokeWidth(){
          return strokeWidth;
     }

     public static double getDefaultStrokeWidth(){
          return DEFAULT_STROKE;
     }

     public static double getMinStroke() {
          return MIN_STROKE;
     }

     public static double getMaxStroke(){
          return MAX_STROKE;
     }

     @Override public String getColor() {
          return color;
     }

     @Override public void setColor(String other) {
          color = other;
     }

     @Override public String getStrokeColor() {
          //Si esta seleccionada, retorna el color de seleccion, si no retorna el color propio.
          return (selected) ? Colorable.selectedStrokeColor() : strokeColor;
     }

     @Override public void setStrokeColor(String other) {
          strokeColor = other;
     }

}
