package frontend;

import backend.model.*;
import frontend.Elements.CircleFront;
import frontend.Elements.RectangleFront;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");

	Buttons actual;

	// Seleccionar una figura
	Figure selectedFigure;

	// StatusBar
	StatusPane statusPane;

	Point startPoint;

	 MouseEvent mouseEvent; //ASUMO MOUSE RELEASED NO PUEDE SUCEDER SIN MOUSE PRESSED

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {

		// Itero por todos los botons
		for(Buttons button : Buttons.values())
			button.getButton().setOnMouseClicked((e) -> actual = button);

		this.canvasState = canvasState;
		this.statusPane = statusPane;

		ToggleButton[] toolsArr = {Buttons.SELECCIONAR.getButton(), Buttons.CIRCLE.getButton(), Buttons.RECTANGLE.getButton()};
		ToggleGroup tools = new ToggleGroup();

		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}

		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);



		canvas.setOnMousePressed(event -> {
			mouseEvent = new MouseEvent(new Point(event.getX(), event.getY()));
		});

		canvas.setOnMouseReleased(event -> {

			//Si no es valido el end point termino, todo crear excepcion corresponde
			try {
				mouseEvent.setEndPoint(new Point(event.getX(), event.getY()));
			} catch (Exception e) {
				return;
			}

			//
			canvasState.addFigure(actual.getFigure(mouseEvent.getStartPoint(), mouseEvent.getEndPoint(), gc));

			redrawCanvas();
		});
		canvas.setOnMouseMoved(event -> {
			// toma el punto en el que esta en el movimiento
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				// si esta arriba de una figura muestra que esta arriba de la misma
				if(figureBelongs(figure, eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				//si la encuentra updatea el status del label
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});


		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.figures()) {   			//Itera para buscar dentro de las figuras de la canvas
					if(figureBelongs(figure, eventPoint)) {					//Si encontro la figuar
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());				//Actualiza el estado si encontro la figura
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");  //Actualiza el estado si no encontro la figura
				}
				redrawCanvas();
			}
		});
		canvas.setOnMouseDragged(event -> {
			//lo que hace es determinar el evento que se va a probocar en el momento
			// en el que el mouse se se mueva con una figura adentro

			//TODO Esta mal como lo hace debemos ponerlo directamente en la clase figure
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				selectedFigure.moveTo(diffX,diffY);
				redrawCanvas();
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}


	//TODO ya averiguamos como se puede borrar una figura, entonces podemos mejorar la eficiencia evitantdo tener que redibujar entero
	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {								//al canvasState le pido las figuras e itero sobre ellas
			if(figure == selectedFigure) {											//Si la figura es la seleccionada, el stroke se lo hago rojo
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(lineColor);											//Si no el lineColor (que por ahora es negro xq es el por defecto)
			}
			gc.setFill(fillColor);
			figure.display();
		}
	}


	//TODO Hay que moverlo al back, haciedno que figure tenga el metodo y que cada tipo de figura lo sobreescriba
	boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.belongs(eventPoint);
	}

}
