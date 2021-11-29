package frontend;

import javafx.scene.layout.VBox;

// Define el VBox principal que maneja AppMenuBar (el menu superior con la opcion de salir o about) y
// el StatusPane (panel que aparece abajo de la pantalla con informacion sobre el current elemento
// del UI o la posicion del cursor del mouse )
public class MainFrame extends VBox {

    public MainFrame(CanvasState canvasState) {

        getChildren().add(new AppMenuBar());                        //Agrego el AppMenuBar al VBox para que se renderise

        StatusPane statusPane = new StatusPane();                   //Creo un nuevo StatusPanel para mantener informacion en
                                                                    // el VBox sobre la current figure o cursor del mouse

        getChildren().add(new PaintPane(canvasState, statusPane));  //Agrego el panel donde dibujo las figuras para que se renderise

        getChildren().add(statusPane);                              //Agrego el panel de status para que se renderise
    }

}
