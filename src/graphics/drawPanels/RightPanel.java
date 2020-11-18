package graphics.drawPanels;

import graphics.shapes.*;
import graphics.shapes.Rectangle;
import logic.DrawStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RightPanel extends JPanel {
    private int width;
    private int height;
    private DrawStats drawStats;
    private JLabel label;
    private Font labelFont;
    private Font drawStringFont;

    public RightPanel(DrawStats drawStats, int width, int height) {
        this.drawStats = drawStats;
        this.width = width;
        this.height = height;
        labelFont = new Font("San Serif", Font.BOLD, 12);
        drawStringFont = new Font("San Serif", Font.BOLD, 15);

        label = new JLabel("SHAPES");
        label.setForeground(Color.BLACK);
        label.setFont(labelFont);

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.add(label);
        this.addMouseListener(new MouseHandler());

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(0, 25, width, 25);

        g.drawRect(30, 30, 50, 50);
        g.drawLine(0, 85, width, 85);

        g.drawOval(30, 90, 50, 50);
        g.drawLine(0, 145, width, 145);

        g.drawPolygon(new int[] {30, 55, 80}, new int[] {200, 150, 200}, 3);
        g.drawLine(0, 205, width, 205);

        g.drawRect(15, 210, 80, 50);
        g.drawLine(0, 265, width, 265);

        g.drawLine(25, 270, 75, 320);
        g.drawLine(0, 325, width, 325);

        g.drawLine(25, 380, 75, 330);
        g.drawLine(0, 385, width, 385);

        g.drawLine(50, 390, 50, 440);
        g.drawLine(0, 445, width, 445);

        g.drawLine(25, 475, 75, 475);
        g.drawLine(0, 505, width, 505);

        g.setFont(drawStringFont);
        g.drawString("ERASER", 20, 540);
        g.drawLine(0, 565, width, 565);

        repaint();
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            int xPos = mouseEvent.getX();
            int yPos = mouseEvent.getY();

            if(xPos >= 0 && xPos <= width) {
                if(yPos > 25 && yPos < 85) {
                    drawStats.setShape(new Square(0, 0, 0, 0, null));
                }
                else if(yPos > 85 && yPos < 145) {
                    drawStats.setShape(new Circle(0, 0, 0, 0, null));
                }
                else if (yPos > 145 && yPos < 205) {
                    drawStats.setShape(new Triangle(0, 0, 0, 0,  null));
                }
                else if (yPos > 205 && yPos < 265) {
                    drawStats.setShape(new Rectangle(0, 0, 0, 0, null));
                }
                else if(yPos > 265 && yPos < 325) {
                    drawStats.setShape(new LeftLine(0, 0, 0, 0, null));
                }
                else if (yPos > 325 && yPos < 385) {
                    drawStats.setShape(new RightLine(0, 0, 0, 0, null));
                }
                else if(yPos > 385 && yPos < 445) {
                    drawStats.setShape(new VerticalLine(0, 0, 0, 0, null));
                }
                else if(yPos > 445 && yPos < 505) {
                    drawStats.setShape(new HorizontalLine(0, 0, 0, 0, null));
                }
                else if(yPos > 505 && yPos < 565) {
                    drawStats.setShape(new Eraser(0, 0, 0, 0, null));
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {}
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}
        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }
}
