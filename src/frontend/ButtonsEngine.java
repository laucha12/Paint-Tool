package frontend;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ButtonsEngine {

    private Buttons clickedButton;

    public void setupButtons(GraphicsContext gc) {

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
    }

    public Buttons getClickedButton() {
        return clickedButton;
    }

    private void updateClickedButton(Buttons button){
        clickedButton = button;
    }
}
