package frontend;

import javafx.scene.control.*;

import java.util.Optional;

public class AppMenuBar extends MenuBar {

    public AppMenuBar() {
        //crea el menu de arriba del la app
        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> {
            //si tocaste para salir te tira una confimation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir de la aplicación");
            alert.setContentText("¿Está seguro que desea salir de la aplicación?");
            //espera confirmacion y se queda en ese punto
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    System.exit(0);
                }
            }
        });
        //opcion de acerde de para que aparece en le menu arriba en la aplicacion
        // cuando tocamos dicho boton nos tira esta ventana con la alerta
        file.getItems().add(exitMenuItem);
        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Paint");
            alert.setContentText("TPE Final POO Diciembre 2021");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        getMenus().addAll(file, help);
    }

}
