package graphics;

import logic.GameState;

import javax.swing.*;
import java.awt.*;

public class Frame {
    private JFrame frame;
    private ContainerPanel containerPanel;

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public Frame() {
        containerPanel = new ContainerPanel(WIDTH, HEIGHT);

        frame = new JFrame("Drawing Panel");
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.add(containerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
