package backend.model;

import java.util.Collection;

public abstract class Figure implements Movable, Drawable, Colorable{

     private String color, strokeColor;

     @Override
     public String toString(){
          return identifier() + "[" + getPoints() + "]";
     }

     abstract public Collection<Point> getPoints();

     abstract public double getWidth();

     abstract public double getHeight();

     abstract public String identifier();

     abstract public boolean belongs(Point point);

     public void isSelected() {
          setStrokeColor("#A52A2A");
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
