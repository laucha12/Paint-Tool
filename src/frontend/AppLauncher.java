package frontend;

import backend.CanvasState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Clase que extiende directamente a la clase de JavaFX que ejecuta el front end
public class AppLauncher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		CanvasState canvasState = new CanvasState();   // BackEnd

		MainFrame frame = new MainFrame(canvasState); // Defines a class that receives the canvas (the elements)
													  // we have to draw

		Scene scene = new Scene(frame);				  // We create a scene for JavaFX to render in the screen

		primaryStage.setResizable(true);             // We define it such that we cannot change the screen size

		primaryStage.setScene(scene);				 // We set the scene to be that we have defined based upon
													 // upon our canvas state and main frame

		primaryStage.show();						 // We make the stage to appear on screen

		primaryStage.setOnCloseRequest(event -> System.exit(0)); // We kill the app on a close request
	}

}
