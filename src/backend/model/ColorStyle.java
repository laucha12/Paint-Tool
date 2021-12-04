package backend.model;

public class ColorStyle {
    private String colorFigure;
    private String colorStroke;
    private double widthStroke;
    public ColorStyle(String colorFigure,String colorStroke,double widthStroke){
        this.colorFigure=colorFigure;
        this.colorStroke=colorStroke;
        this.widthStroke=widthStroke;
    }

    public String getColorFigure() {
        return colorFigure;
    }

    public String getColorStroke() {
        return colorStroke;
    }

    public void setColorFigure(String colorFigure) {
        this.colorFigure = colorFigure;
    }

    public void setColorStroke(String colorStroke) {
        this.colorStroke = colorStroke;
    }

    public void setWidthStroke(double widthStroke) {
        this.widthStroke = widthStroke;
    }

    public double getWidthStroke() {
        return widthStroke;
    }
}
