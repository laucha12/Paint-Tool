package frontend.engines;

import backend.model.CanvasState;
import backend.model.components.Figure;
import backend.model.interfaces.Colorable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorControlsEngine {

    private static final double MAX_STROKE = 50.0, MIN_STROKE = 1.0;

    //Creamos los selectores de color y de ancho, con los valores por defecto
    ColorPicker figureColor = new ColorPicker(Color.web(Colorable.defaultColor()));
    ColorPicker figureStrokeColor = new ColorPicker(Color.web(Colorable.defaultStrokeColor()));
    Slider figureStrokeWidth = new Slider(Figure.getDefaultStroke(), MAX_STROKE, MIN_STROKE);


    Label strokeLabel = new Label("Borde");
    Label fillLabel = new Label("Relleno");

    public void setupButtons(VBox buttonsBox) {
        buttonsBox.getChildren().add(strokeLabel);
        figureStrokeWidth.setShowTickMarks(true);
        figureStrokeWidth.setShowTickLabels(true);
        buttonsBox.getChildren().add(figureStrokeWidth);
        buttonsBox.getChildren().add(figureStrokeColor);
        buttonsBox.getChildren().add(fillLabel);
        buttonsBox.getChildren().add(figureColor);
    }

    public void startListener(CanvasState canvasState, Canvas canvas) {
        figureColor.setOnAction(event -> {
            canvasState.getSelected().forEach(figure ->
                    figure.setColor(HexStringEngine.ColorToHexString(figureColor.getValue())));
            CanvasEngine.redrawCanvas(canvasState, canvas);
        });

        figureStrokeColor.setOnAction(event -> {
            canvasState.getSelected().forEach(figure ->
                    figure.setStrokeColor(HexStringEngine.ColorToHexString(figureStrokeColor.getValue())));
            CanvasEngine.redrawCanvas(canvasState, canvas);
        });

        figureStrokeWidth.setOnMouseClicked(event -> {
            canvasState.getSelected().forEach(figure ->
                    figure.setStrokeWidth(figureStrokeWidth.getValue()));
            CanvasEngine.redrawCanvas(canvasState, canvas);
        });
        }

        public String getFigureColor(){
            return HexStringEngine.ColorToHexString(figureColor.getValue());
        }

        public String getStrokeColor(){
            return HexStringEngine.ColorToHexString(figureStrokeColor.getValue());
        }

        public double getStrokeWidth(){
            return figureStrokeWidth.getValue();
        }

}