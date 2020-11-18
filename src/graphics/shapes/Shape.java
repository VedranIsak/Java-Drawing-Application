package graphics.shapes;

import java.awt.*;
import java.io.Serializable;

abstract public class Shape implements Serializable {
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;
    protected Color shapeColor;


    public Shape(int xPos, int yPos, int width, int height, Color shapeColor) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.shapeColor = shapeColor;
    }

    abstract public void drawShape(Graphics g);
}
