package frontend;

public class ButtonsEngine {

    private FigureButtons clickedButton;

    public FigureButtons getClickedButton() {
        return clickedButton;
    }

    private void updateClickedButton(FigureButtons button){
        clickedButton = button;
    }
}
