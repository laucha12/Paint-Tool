package backend.model;

import java.util.Collection;

public abstract class Figure implements Movable, Drawable, Colorable{

     private String color="#FFFF00", strokeColor="#000000";
     private boolean isSelected = false;

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
