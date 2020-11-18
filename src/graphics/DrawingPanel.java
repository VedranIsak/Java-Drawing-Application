package graphics;

import graphics.drawPanels.RightPanel;
import graphics.drawPanels.LeftPanel;
import logic.DrawStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

//Optimera addningprocessen (ta bort instanceof)
//Fixat att kan ladda sparade spel

public class DrawingPanel extends JPanel {
    private ContainerPanel containerPanel;
    private int width;
    private int height;
    private DrawStats drawStats;
    private LinkedList<Shape> shapes;

    private RightPanel rightPanel;
    private LeftPanel leftPanel;

    public DrawingPanel(ContainerPanel containerPanel, DrawStats drawStats, int width, int height) {
        this.containerPanel = containerPanel;
        this.width = width;
        this.height = height;
        shapes = new LinkedList<Shape>();
        this.drawStats = drawStats;

        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.addMouseListener(new ClickHandler());
        this.addMouseMotionListener(new ClickHandler());

        rightPanel = new RightPanel(drawStats, width/12, height - 45);
        leftPanel = new LeftPanel(drawStats, containerPanel,width/9, height - 45);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(graphics.shapes.Shape shape : drawStats.getShapes()) { shape.drawShape(g); }
        repaint();
    }

    private class ClickHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            int xPos = mouseEvent.getX();
            int yPos = mouseEvent.getY();

            if(xPos > 0 && xPos < width - width/12 && yPos > 0 && yPos < height)
                drawStats.addShape(xPos - 20, yPos - 20);
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {}
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}
        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
        @Override
        public void mouseMoved(MouseEvent mouseEvent) {}

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();

            if(x > 0 && x < (width - width/12) && y > 0 && y < height) drawStats.addShape(x, y);
        }

    }

    @Override
    public String toString() { return "DrawingPanel"; }

}
