package backend.model;

public interface Colorable {

    String getColor();

    void setColor(String other);

    String getStrokeColor();

    void setStrokeColor(String other);

    default String defaultColor() {
        return "#FFFF00";
    }

    default String defaultStrokeColor() {
        return "#000000";
    }
}
