package backend.model;

public interface Colorable {

    String getColor();

    void setColor(String other);

    String getStrokeColor();

    void setStrokeColor(String other);

    static String defaultColor() {
        return "#FFFF00";
    }

    static String defaultStrokeColor() {
        return "#000000";
    }

}
