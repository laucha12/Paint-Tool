package frontend.engines;

import javafx.scene.paint.Color;

public class HexStringEngine {

    public static String ColorToHexString(Color color){
        return "#" + color.toString().subSequence(2,8);
    }

}
