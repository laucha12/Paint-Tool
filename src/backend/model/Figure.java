package backend.model;

import java.util.Collection;

public abstract class Figure implements Movable, Drawable, Colorable{

     private static final double DEFAULT_STROKE = 1.0, MAX_STROKE = 20.0;
     private String color="#FFFF00", strokeColor="#000000";
     private boolean isSelected = false;
     private double strokeWidth = DEFAULT_STROKE;

     @Override
     public String toString(){
          return identifier() + "[" + getPoints() + "]";
     }

     abstract public Collection<Point> getPoints();

     abstract public double getWidth();

     abstract public double getHeight();

     abstract public String identifier();

     abstract public boolean belongs(Point point);

     public void select() {
          this.isSelected = true;
          setStrokeColor("#A52A2A");
     }

     public boolean isSelected() { return isSelected; }

     public void setStrokeWidth(double strokeWidth) {
          this.strokeWidth = strokeWidth;
     }

     public double getStrokeWidth(){
          return strokeWidth;
     }

     public static double getDefaultStrokeWidth(){
          return DEFAULT_STROKE;
     }

     public static double getMaxStroke(){
          return MAX_STROKE;
     }

     @Override
     public String getColor() {
          return color;
     }

     @Override
     public void setColor(String other) {
          color = other;
     }

     @Override
     public String getStrokeColor() {
          return strokeColor;
     }

     @Override
     public void setStrokeColor(String other) {
          strokeColor = other;
     }

}
