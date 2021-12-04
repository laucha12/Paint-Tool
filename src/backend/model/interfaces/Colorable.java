package backend.model.interfaces;

//Esta interfaz maneja la funcionalidad de coloreable, defini
//que es ser una clase coloreable (no solamente Figures podrian
//ser coloreables, sino que podriamos tener un TextBox que tenga color)
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

}
