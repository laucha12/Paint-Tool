package frontend.engines;

import backend.model.CanvasState;
import backend.model.components.Figure;
import javafx.scene.canvas.Canvas;

public class CanvasEngine {

    //Hecha de clase asi la pueden llamar los engines
    public static void redrawCanvas(CanvasState canvasState, Canvas canvas) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Figure figure : canvasState.figures())                                //al canvasState le pido las figuras e itero sobre ellas
            figure.display();                                                        // dibujamos la figura en la pantalla
    }
}
