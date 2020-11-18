package graphics.shapes;

import java.awt.*;

public class Triangle extends Shape {

    public Triangle(int xPos, int yPos, int width, int height, Color shapeColor) { super(xPos, yPos, width, height, shapeColor);}
    @Override
    public void drawShape(Graphics g) {
        g.setColor(shapeColor);
        g.fillPolygon(new int[] {xPos - width/2, xPos, xPos + width/2}, new int[] {yPos + height, yPos, yPos + height}, 3);
        //g.fillPolygon(new int[] {xPos - 5, xPos + 20, xPos + 45}, new int[] {yPos + 40, yPos - 10, yPos + 40}, 3);
    }
}
