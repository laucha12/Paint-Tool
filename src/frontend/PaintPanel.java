package frontend;

import backend.model.*;
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

	FigureButtons actual;

	ColorPicker colorPicker = new ColorPicker();
	ColorPicker strokePicker = new ColorPicker();
	Slider slider = new Slider();

	// Seleccionar una figura
	Figure selectedFigure;

	// StatusBar
	StatusPanel statusPane;

	MouseEvent mouseEventPressed; //ASUMO MOUSE RELEASED NO PUEDE SUCEDER SIN MOUSE PRESSED

	VBox buttonsBox = new VBox(10);

	private void setupButtons(){


		// Itero por todos los botons
		for(FigureButtons button : FigureButtons.values())
			button.getButton().setOnMouseClicked((e) -> actual = button);


		ToggleButton[] toolsArr = {selectionButton, clearButton, sendToBackButton, sendToFrontButton, FigureButtons.CIRCLE.getButton(), FigureButtons.RECTANGLE.getButton(), FigureButtons.LINE.getButton(),
				FigureButtons.ELLIPSE.getButton(), FigureButtons.SQUARE.getButton()};


		//FUNCIONALIDAD DE JAVA FX

		ToggleGroup tools = new ToggleGroup();

		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}

		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().add(slider);
		buttonsBox.getChildren().add(strokePicker);
		buttonsBox.getChildren().add(colorPicker);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

	}

	public PaintPanel(CanvasState canvasState, StatusPanel statusPane) {

		this.canvasState = canvasState;
		this.statusPane = statusPane;

		setupButtons();

		clearButton.setOnMouseClicked((e) -> {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			canvasState.clear();

		});

		sendToBackButton.setOnMouseClicked((e) -> {

		});


		canvas.setOnMousePressed(event -> {
			mouseEventPressed = new MouseEvent(new Point(event.getX(), event.getY()));
		});

		canvas.setOnMouseReleased(event -> {

			if(selectionButton.isSelected())
				return;

			mouseEventPressed.setEndPoint(new Point(event.getX(), event.getY()));

			canvasState.addFigure(actual.getFigure(mouseEventPressed.getStartPoint(), mouseEventPressed.getEndPoint(), gc));

			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			// toma el punto en el que esta en el movimiento
			statusPane.mouseMoved(new Point(event.getX(), event.getY()));
		});

		sendToBackButton.setOnMouseClicked( (event) -> {
			for (Figure figure : canvasState.figures())
				if(figure.isSelected()) {
					canvasState.sendFigureToBack(figure);
				}
		});


		//TODO fijarse si hay que cambiarlo
		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");

				//OBS: RECORRER DE ATRAS PARA ADELANTE MAS EFICIENTE
				for (Figure figure : canvasState.figures()) {   			//Itera para buscar dentro de las figuras de la canvas
					if(figure.belongs(eventPoint)) {					//Si encontro la figuar
						found = true;
						selectedFigure = figure;
						figure.select();
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());				//Actualiza el estado si encontro la figura
				} else {
					if (selectedFigure != null)
						selectedFigure.setStrokeColor("#000000");          
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");  //Actualiza el estado si no encontro la figura
				}
				redrawCanvas();
			}
		});


		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				double diffX = (event.getX() - mouseEventPressed.getX()) / 100;
				double diffY = (event.getY() - mouseEventPressed.getY()) / 100;

				// movemos la figura llamando a un metodo de la misma
				selectedFigure.moveTo(diffX, diffY);

				// redibujamos todas las figuras pues las mismas tienen un orden de dibujo
				redrawCanvas();
			}
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	//Ippo: Quedo hermoso
	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures())								//al canvasState le pido las figuras e itero sobre ellas
			figure.display();         												// dibujamos la figura en la pantalla
	}

}
