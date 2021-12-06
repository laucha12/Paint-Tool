package frontend;

import backend.model.*;
import frontend.engines.ColorControlsEngine;
import frontend.engines.ButtonsEngine;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class PaintPanel extends BorderPane {

    // BackEnd
    CanvasState canvasState;

    // Canvas y relacionados
    Canvas canvas = new Canvas(800, 600);
    StatusPanel statusPane;
    VBox buttonsBox = new VBox(10);


    ButtonsEngine buttonsEngine = new ButtonsEngine();

    private void setupButtons() {

        //Agrego motores de colores y butones
        buttonsEngine.setupButtons(buttonsBox);

        //JavaFX styling
        buttonsBox.setPadding(new Insets(5));
        buttonsBox.setStyle("-fx-background-color: #999");
        buttonsBox.setPrefWidth(100);
        canvas.getGraphicsContext2D().setLineWidth(1);
    }


    public PaintPanel(CanvasState canvasState, StatusPanel statusPane) {

        this.canvasState = canvasState;
        this.statusPane = statusPane;

        //We include buttons in the UI
        setupButtons();

        //We set up the listeners to the buttons
        buttonsEngine.startListener(canvasState, canvas, statusPane);

        setLeft(buttonsBox);
        setRight(canvas);
    }

}
