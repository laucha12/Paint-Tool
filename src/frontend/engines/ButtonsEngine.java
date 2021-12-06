package frontend.engines;

import backend.model.CanvasState;
import backend.model.FigureStyle;
import backend.model.components.Figure;
import backend.model.components.Point;
import backend.model.exceptions.BackEndException;
import frontend.StatusPanel;
import frontend.controllers.FigureButtons;
import frontend.controllers.FunctionButtons;
import frontend.controllers.MouseEvent;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ButtonsEngine {

    private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private FigureButtons actual;
    private MouseEvent mouseEventPressed; //ASUMO MOUSE RELEASED NO PUEDE SUCEDER SIN MOUSE PRESSED
    private boolean figureButtonSelected = false;

    //ColorControlEngine se encarga de manejar los colorPickers/Slider
    ColorControlsEngine colorControllersEngine = new ColorControlsEngine();

   public void setupButtons(VBox buttonsBox) {

       ToggleButton[] toolsArr = {selectionButton, FigureButtons.RECTANGLE.getButton(),
               FigureButtons.CIRCLE.getButton(), FigureButtons.SQUARE.getButton(),
               FigureButtons.ELLIPSE.getButton(), FigureButtons.LINE.getButton(),
               FunctionButtons.DELETE.getButton(), FunctionButtons.TOBACK.getButton(),
               FunctionButtons.TOFRONT.getButton()};

       ToggleGroup tools = new ToggleGroup();

       for (ToggleButton tool : toolsArr) {
           tool.setMinWidth(90);
           tool.setToggleGroup(tools);
           tool.setCursor(Cursor.HAND);
       }

       buttonsBox.getChildren().addAll(toolsArr);
       colorControllersEngine.setupButtons(buttonsBox);
   }


    public void startListener(CanvasState canvasState, Canvas canvas, StatusPanel statusPane) {

        colorControllersEngine.startListener(canvasState, canvas);
        for (FunctionButtons buttons : FunctionButtons.values())
            buttons.getButton().setOnMouseClicked( (e) -> {
                buttons.apply(canvasState, canvas);
                figureButtonSelected = false;
            });

        for (FigureButtons button : FigureButtons.values())
            button.getButton().setOnMouseClicked((e) -> {
                canvasState.unselectAll();
                actual = button;
                figureButtonSelected = true;
            });

        canvas.setOnMousePressed(event -> {
            mouseEventPressed = new MouseEvent(new Point(event.getX(), event.getY()));
        });


        canvas.setOnMouseReleased(event -> {
            mouseEventPressed.setEndPoint(new Point(event.getX(), event.getY()));

            // si hay algun boton seleccionado de los toggles se llama al enum que nos genera las figuras
            try {
                if (figureButtonSelected)
                    canvasState.addFigure(actual.getFigure(mouseEventPressed.getStartPoint(), mouseEventPressed.getEndPoint(), canvas.getGraphicsContext2D(),new FigureStyle(colorControllersEngine.getFigureColor(), colorControllersEngine.getStrokeColor(), colorControllersEngine.getStrokeWidth())));

            } catch(BackEndException e){
                //En caso de no poder crear la figura, avisamos al usuario
                raiseAlert(e);
            }
            CanvasEngine.redrawCanvas(canvasState, canvas);
        });

        canvas.setOnMouseMoved(event -> {
            // toma el punto en el que esta en el movimiento
            statusPane.mouseMoved(new Point(event.getX(), event.getY()));
        });


        canvas.setOnMouseClicked(event -> {
            if(selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());

                statusPane.selectionModeStart();
                canvasState.selectionModeStart();
                statusPane.updateStatus(canvasState.selectFigure(mouseEventPressed.getStartPoint(), eventPoint));
                statusPane.selectionModeEnded();
                canvasState.selectionModeEnded();
                CanvasEngine.redrawCanvas(canvasState, canvas);
            }
        });


        canvas.setOnMouseDragged(event -> {
            if (selectionButton.isSelected()) {
                double diffX = (event.getX() - mouseEventPressed.getX()) / 100;
                double diffY = (event.getY() - mouseEventPressed.getY()) / 100;

                // movemos la figura llamando a un metodo de la misma
                for (Figure figure : canvasState.getSelected())
                    figure.moveTo(diffX, diffY);

                // redibujamos todas las figuras pues las mismas tienen un orden de dibujo
                CanvasEngine.redrawCanvas(canvasState, canvas);
            }
        });

        selectionButton.setOnMousePressed(event -> {
            figureButtonSelected = false;
        });
    }

    public void raiseAlert(Exception e) {
        Alert errorAlert= new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText(e.getMessage());
        errorAlert.setContentText(e.getClass().getName());
        errorAlert.showAndWait();
    }

}
