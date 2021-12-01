package backend.model.interfaces;

public interface Colorable {

    String getColor();

    void setColor(String other);

    String getStrokeColor();

    void setStrokeColor(String other);

    static String selectedStrokeColor() { return "#A52A2A";}

    static String defaultColor() {
        return "#FFFF00";
    }

    static String defaultStrokeColor() {
        return "#000000";
    }

    default void resetStrokeColor() { setStrokeColor(Colorable.defaultStrokeColor()); }

}
