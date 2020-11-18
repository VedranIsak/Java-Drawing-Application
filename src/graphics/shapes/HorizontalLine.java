package graphics.shapes;

import java.awt.*;

public class HorizontalLine extends Shape {

    public HorizontalLine(int xPos, int yPos, int width, int height, Color shapeColor) {
        super(xPos, yPos, width, height, shapeColor);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(shapeColor);
        g.drawLine(xPos, yPos, xPos + width, yPos);
    }
}
