package backend.model.interfaces;

//Esta interfaz maneja la funcionalidad de coloreable, defini
//que es ser una clase coloreable (no solamente Figures podrian
//ser coloreables, sino que podriamos tener un TextBox que tenga color)
public interface Colorable {

    //Retorno el color del objeto
    String getColor();

    //Seteo el color del objeto
    void setColor(String other);

    //Retorno el color del contorno del objeto
    String getStrokeColor();

    //Seteo el color del contorno del objeto
    void setStrokeColor(String other);

    //En caso de que sea seleccionable, se le puede setear un color que represente el color seleccionable
    static String selectedStrokeColor() { return "#A52A2A";}

    //Se le setea un color por default
    static String defaultColor() {
        return "#FFFF00";
    }

    //Se le setea un color por default
    static String defaultStrokeColor() {
        return "#000000";
    }

}
