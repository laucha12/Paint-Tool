package frontend;

import backend.model.*;
import backend.model.components.Figure;
import backend.model.components.Point;
import backend.model.interfaces.Colorable;
import frontend.controllers.FigureButtons;
import frontend.controllers.MouseEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PaintPanel extends BorderPane {

    // BackEnd
    CanvasState canvasState;

    // Canvas y relacionados
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    // Botones Barra Izquierda
    ToggleButton selectionButton = new ToggleButton("Seleccionar");
    ToggleButton clearButton = new ToggleButton("Limpiar");
    ToggleButton sendToBackButton = new ToggleButton("Al fondo");
    ToggleButton sendToFrontButton = new ToggleButton("Al frente");
    ToggleButton deleteButton = new ToggleButton("Borrar");

    FigureButtons actual;

    //Creamos los selectores de color y de ancho, con los valores por defecto
    ColorPicker figureColor = new ColorPicker(Color.web(Colorable.defaultColor()));
    ColorPicker figureStrokeColor = new ColorPicker(Color.web(Colorable.defaultStrokeColor()));
    Slider figureStrokeWidth = new Slider(Figure.getMinStroke(), Figure.getMaxStroke(), Figure.getDefaultStrokeWidth());

    // StatusBar
    StatusPanel statusPane;

    ArrayList<Figure> figureSelected = new ArrayList<>();

    MouseEvent mouseEventPressed; //ASUMO MOUSE RELEASED NO PUEDE SUCEDER SIN MOUSE PRESSED

    VBox buttonsBox = new VBox(10);

    private void setupButtons() {


        ToggleButton[] toolsArr = {selectionButton, clearButton, sendToBackButton, sendToFrontButton, FigureButtons.CIRCLE.getButton(), FigureButtons.RECTANGLE.getButton(), FigureButtons.LINE.getButton(),
                FigureButtons.ELLIPSE.getButton(), FigureButtons.SQUARE.getButton(), deleteButton};


        //FUNCIONALIDAD DE JAVA FX

        ToggleGroup tools = new ToggleGroup();

        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        buttonsBox.getChildren().addAll(toolsArr);
        //Agrego seleccionadores de colores
        buttonsBox.getChildren().add(figureColor);
        buttonsBox.getChildren().add(figureStrokeColor);
        buttonsBox.getChildren().add(figureStrokeWidth);
        buttonsBox.setPadding(new Insets(5));
        buttonsBox.setStyle("-fx-background-color: #999");
        buttonsBox.setPrefWidth(100);
        gc.setLineWidth(1);

    }

    public void deleteButtonListener() {
        deleteButton.setOnMouseClicked((e) -> {


        });

        sendToBackButton.setOnMouseClicked((event) -> {
            canvasState.sendMultipleFiguresToBack(figureSelected);
            unSelect();
        });

        sendToFrontButton.setOnMouseClicked((event) -> {
            canvasState.sendMultipleFiguresToFront(figureSelected);
            unSelect();
        });
    }


    public void clearButtonListener() {
        clearButton.setOnMouseClicked((e) -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());    //Se borra lo presente en la pantalla
            canvasState.clear();                                                //En el estado del back se borran las figuras
        });
    }


    public void colorButtonsListener() {
        figureColor.setOnMouseClicked(e -> figureSelected.forEach(figure -> figure.setColor("#" + Double.toHexString(figureColor.getValue().getRed()) + Double.toHexString(figureColor.getValue().getGreen()) + Double.toHexString(figureColor.getValue().getBlue()))));
        figureStrokeColor.setOnMouseClicked(e -> figureSelected.forEach(figure -> figure.setStrokeColor("#" + Double.toHexString(figureStrokeColor.getValue().getRed()) + Double.toHexString(figureStrokeColor.getValue().getGreen()) + Double.toHexString(figureStrokeColor.getValue().getBlue()))));
        figureStrokeWidth.setOnMouseClicked(e -> figureSelected.forEach(figure -> figure.setStrokeWidth(figureStrokeWidth.getValue())));
    }





    public void figureButtonsListener() {
        for (FigureButtons button : FigureButtons.values())
            button.getButton().setOnMouseClicked((e) -> {
                unSelect();
                actual = button;
            });
    }


    public PaintPanel(CanvasState canvasState, StatusPanel statusPane) {

        this.canvasState = canvasState;
        this.statusPane = statusPane;

        //We include buttons in the UI
        setupButtons();

        deleteButtonListener();
        clearButtonListener();
        colorButtonsListener();

        figureButtonsListener();


        canvas.setOnMousePressed(event -> {
            mouseEventPressed = new MouseEvent(new Point(event.getX(), event.getY()));
        });

		canvas.setOnMouseReleased(event -> {
			mouseEventPressed.setEndPoint(new Point(event.getX(), event.getY()));
			// si hay algun boton seleccionado de los toggles se llama al enum que nos genera las figuras
			if (actual != null)
				canvasState.addFigure(actual.getFigure(mouseEventPressed.getStartPoint(), mouseEventPressed.getEndPoint(), gc));
			actual=null;
			redrawCanvas();
		});
        canvas.setOnMouseReleased(event -> {

            mouseEventPressed.setEndPoint(new Point(event.getX(), event.getY()));

            if (actual != null)
                canvasState.addFigure(actual.getFigure(mouseEventPressed.getStartPoint(), mouseEventPressed.getEndPoint(), gc));
            actual = null;
            redrawCanvas();
        });

        canvas.setOnMouseMoved(event -> {
            // toma el punto en el que esta en el movimiento
            statusPane.mouseMoved(new Point(event.getX(), event.getY()));
        });


		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				statusPane.updateStatus ("Se seleccionó: ");
				//Bucos dentro de las figuras cuales tienen los puntos de seleccion dentro
				for (Figure figure : canvasState.figures()) {
					//llamo a las funciones para verificar el bellong tanto de un punto como de una figura
					if(figure.belongs(eventPoint) || figure.inside(mouseEventPressed.getStartPoint(), eventPoint)) {					//Si encontro la figuar
						figure.select();
						//Funcion creada en el statusPane para que concatene el texto que tiene en el label
						statusPane.appendText(figure.toString());
						figureSelected.add(figure);
					}
				}
				if (figureSelected.size() == 0 ) {
					unSelect();
					statusPane.updateStatus("Ninguna figura encontrada");  //Actualiza el estado si no encontro la figura
				}
				redrawCanvas();
			}
		});


        canvas.setOnMouseDragged(event -> {
            if (selectionButton.isSelected()) {
                double diffX = (event.getX() - mouseEventPressed.getX()) / 100;
                double diffY = (event.getY() - mouseEventPressed.getY()) / 100;

                // movemos la figura llamando a un metodo de la misma
                for (Figure aux : figureSelected)
                    aux.moveTo(diffX, diffY);

                // redibujamos todas las figuras pues las mismas tienen un orden de dibujo
                redrawCanvas();
            }
        });


        setLeft(buttonsBox);
        setRight(canvas);
    }

    public void unSelect() {
        //le cambio el borde a negro de las figuras que estaban seleccionadas
        for (Figure aux : figureSelected)
            aux.resetStrokeColor();

        //limpio las figuras seleccion
        figureSelected.clear();

        //dibjuamos de vuelta el canvas
        redrawCanvas();
    }

    void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Figure figure : canvasState.figures())                                //al canvasState le pido las figuras e itero sobre ellas
            figure.display();                                                        // dibujamos la figura en la pantalla
    }

}
