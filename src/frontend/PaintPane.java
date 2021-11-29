package frontend;

import backend.CanvasState;
import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
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
				newFigure = new Rectangle(startPoint, endPoint);
			}
			else if(circleButton.isSelected()) {
				// si selecciono el boton de circulo lo dibujo
				double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
				newFigure = new Circle(startPoint, circleRadius);
			} else {
				return ;
			}
			// dibuja la figura que generaste anteriormente
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figureBelongs(figure, eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
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
				if(selectedFigure instanceof Rectangle) {
					Rectangle rectangle = (Rectangle) selectedFigure;
					rectangle.getTopLeft().x += diffX;
					rectangle.getBottomRight().x += diffX;
					rectangle.getTopLeft().y += diffY;
					rectangle.getBottomRight().y += diffY;
				} else if(selectedFigure instanceof Circle) {
					Circle circle = (Circle) selectedFigure;
					circle.getCenterPoint().x += diffX;
					circle.getCenterPoint().y += diffY;
				}
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
			gc.setFill(fillColor);													//Relleno la figura con el color por defecto
			if(figure instanceof Rectangle) {										//Si la figura es un rectangulo procede a dibujarla
				Rectangle rectangle = (Rectangle) figure;
				gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
				gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
			} else if(figure instanceof Circle) {									//Si la figura es un circulo procede a dibujarla
				Circle circle = (Circle) figure;
				double diameter = circle.getRadius() * 2;
				gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
				gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
			}
		}
	}


	//TODO Hay que moverlo al back, haciedno que figure tenga el metodo y que cada tipo de figura lo sobreescriba
	boolean figureBelongs(Figure figure, Point eventPoint) {
		boolean found = false;										//Comienza como que no lo encontre
		if(figure instanceof Rectangle) {							//Si es un rectangulo se fija que el punto este dentro de la figura
			Rectangle rectangle = (Rectangle) figure;
			found = eventPoint.getX() > rectangle.getTopLeft().getX() && eventPoint.getX() < rectangle.getBottomRight().getX() &&
					eventPoint.getY() > rectangle.getTopLeft().getY() && eventPoint.getY() < rectangle.getBottomRight().getY();
		} else if(figure instanceof Circle) {						//Si es un circulo se fija que el punto este dentro de la figura
			Circle circle = (Circle) figure;
			found = Math.sqrt(Math.pow(circle.getCenterPoint().getX() - eventPoint.getX(), 2) +
					Math.pow(circle.getCenterPoint().getY() - eventPoint.getY(), 2)) < circle.getRadius();
		}
		return found;
	}

}
