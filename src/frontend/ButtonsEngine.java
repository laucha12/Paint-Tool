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

    public Buttons getClickedButton() {
        return clickedButton;
    }

    private void updateClickedButton(Buttons button){
        clickedButton = button;
    }
}
