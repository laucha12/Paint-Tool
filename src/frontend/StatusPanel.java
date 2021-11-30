package frontend;

import backend.model.CanvasState;
import backend.model.Figure;
import backend.model.Point;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

///DONDE LLAMO A UPDATESTATUS? PODRIA TRABAJARLO DIRECTAMENTE DESDE ACA, SIMPLEMENTE
//ES LA POSICION DEL MOUSE O SI ESTA DENTRO DE UNA FIGURA (CUYO CASO TAMBIEN TENDRIA
//QUE ALMACENAR A CANVAS_STATE ACA)

public class StatusPanel extends BorderPane {

	private final Label statusLabel;
	private final CanvasState canvasState;
	private static final String DEFAULT_PANEL_STRING = "Paint 1.0";

	//Defino como se renderisa el panel de status, la posicion color y tamaniio de letra
	public StatusPanel(CanvasState canvasState) {
		this.canvasState = canvasState;
		setStyle("-fx-background-color: #4EBCF8");
		statusLabel = new Label(DEFAULT_PANEL_STRING);
		statusLabel.setAlignment(Pos.CENTER);
		statusLabel.setStyle("-fx-font-size: 16");
		setCenter(statusLabel);
	}

	//Este metodo es llamado cada vez que necesito actualisar el panel (que es constantemente
	// debido a que tiene el hover del mouse)
	public void updateStatus(String text) {
		statusLabel.setText(text);
	}

	//Este metodo es llamado cada vez que se mueve el mouse, actualisa el valor del panel de status
	//en base a donde se ubicaba el mouse al moverse (sobre una figura o no)
	public void mouseMoved(Point eventPoint){

		boolean found = false;
		StringBuilder label = new StringBuilder();
		for(Figure figure : canvasState.figures()) {
			// si esta arriba de una figura muestra que esta arriba de la misma
			if(figure.belongs(eventPoint)) {
				found = true;
				label.append(figure.toString());
			}
		}
		if(found) {
			//si la encuentra updatea el status del label
			updateStatus(label.toString());
		} else {
			updateStatus(eventPoint.toString());
		}
	}

}