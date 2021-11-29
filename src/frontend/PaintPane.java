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
	ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	ToggleButton circleButton = new ToggleButton("Círculo");

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	Figure selectedFigure;

	// StatusBar
	StatusPane statusPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton};
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
			// tomo el primer punto en el que se hace click en la pantalla para guardarlo
			startPoint = new Point(event.getX(), event.getY());
		});
		canvas.setOnMouseReleased(event -> {
			//toma el segundo punto donde se suelta el mouse para saber donde poder dibujar la figura si la misma
			//fue seleccionada
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return ;
			}
			Figure newFigure = null;
			//TODO tener una variable axuliar con el boton seleccionado o algo similar pues sino seria una serie infinita de if else
			if(rectangleButton.isSelected()) {
				// si selecciono el boton de rectangulo anteriormente lo dibuja
				newFigure = new RectangleFront(startPoint, endPoint,gc);
			}
			else if(circleButton.isSelected()) {
				// si selecciono el boton de circulo lo dibujo
				double circleRadius =Math.sqrt( Math.pow(endPoint.getX() - startPoint.getX(),2)+Math.pow(endPoint.getY() - startPoint.getY(),2));
				newFigure = new CircleFront(startPoint, circleRadius,gc);
			} else {
				return ;
			}
			// dibuja la figura que generaste anteriormente
			canvasState.addFigure(newFigure);
			startPoint = null;
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


	boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.belongs(eventPoint);
	}

}
