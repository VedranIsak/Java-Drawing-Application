package graphics.shapes;

import java.awt.*;

public class Circle extends Shape {
    public Circle(int xPos, int yPos, int width, int height, Color shapeColor) {
        super(xPos, yPos, width, height, shapeColor);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(shapeColor);
        g.fillOval(xPos, yPos, width, height);
    }
}
