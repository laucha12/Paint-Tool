package frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class ButtonsEngine {

    private Buttons clickedButton;

    ButtonsEngine(){

    }

    public Buttons getClickedButton() {
        return clickedButton;
    }

    private void updateClickedButton(Buttons button){
        clickedButton = button;
    }
}
