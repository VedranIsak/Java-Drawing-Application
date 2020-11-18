package graphics.shapes;

import java.awt.*;

public class Square extends Shape{
    public Square(int xPos, int yPos, int width, int height, Color shapeColor) {
        super(xPos, yPos, width, height, shapeColor);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(shapeColor);
        g.fillRect(xPos, yPos, width, height);
    }
}
