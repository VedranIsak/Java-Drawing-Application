package logic;

import graphics.shapes.*;
import graphics.shapes.Rectangle;
import graphics.shapes.Shape;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class DrawStats implements Serializable {
    private Shape shape = new Circle(0, 0, 0, 0, null);
    private ArrayList<graphics.shapes.Shape> shapes;

    private String gameName;
    private int width = 50;
    private int height = 50;
    private int rValue = 0;
    private int gValue = 0;
    private int bValue = 0;
    private Color shapeColor = new Color(rValue, gValue, bValue);

    public DrawStats() {
        shapes = new ArrayList<Shape>();
    }

    public ArrayList<Shape> getShapes() { return shapes; }
    public void setShapes(ArrayList<Shape> shapes) { this.shapes = shapes; }

    public void setShape(Shape newShape) { shape = newShape; }
    public void resetShapes() { shapes.clear(); }
    public void removeShape() {
        if(shapes.size() == 0) return;
        shapes.remove(shapes.size()-1);
    }

    public void addShape(int xPos, int yPos) {
        if(shape instanceof Circle) shapes.add(new Circle(xPos, yPos, width, height, getColor()));
        else if(shape instanceof Square) shapes.add(new Square(xPos, yPos, width, height, getColor()));
        else if(shape instanceof Triangle) shapes.add(new Triangle(xPos, yPos, width, height, getColor()));
        else if (shape instanceof Rectangle) shapes.add(new Rectangle(xPos, yPos, width + height/2, height, getColor()));
        else if(shape instanceof LeftLine) shapes.add(new LeftLine(xPos, yPos, width, height, getColor()));
        else if(shape instanceof RightLine) shapes.add(new RightLine(xPos, yPos, width, height, getColor()));
        else if(shape instanceof VerticalLine) shapes.add(new VerticalLine(xPos, yPos, width, height, getColor()));
        else if (shape instanceof HorizontalLine) shapes.add(new HorizontalLine(xPos, yPos, width, height, getColor()));
        else if (shape instanceof Eraser) shapes.add(new Eraser(xPos, yPos, width, height, getColor()));
    }

    public int getRValue() { return rValue; }
    public int getGValue() { return gValue; }
    public int getBValue() { return bValue; }

    public void setSize(int size) { width = size; height = size; }
    public int getSize() { return width; }
    public void setColor(Color newColor) { this.shapeColor = newColor; }
    public Color getColor() { return this.shapeColor; }

    public void setGameName(String newGameName) { gameName = newGameName; }
    public String getGameName() { return gameName; }
}
