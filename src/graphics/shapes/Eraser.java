package graphics.shapes;

import java.awt.*;

public class Eraser extends Shape {

    public Eraser(int xPos, int yPos, int width, int height, Color shapeColor) {
        super(xPos, yPos, width, height, shapeColor);
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(xPos, yPos, width, height);
    }
}
