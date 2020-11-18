package graphics.drawPanels;

import graphics.ContainerPanel;
import logic.DrawStats;
import logic.GameState;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class LeftPanel extends JPanel {
    private DrawStats drawStats;
    private ContainerPanel containerPanel;
    private int width;
    private int height;
    private int r = 0;
    private int g = 0;
    private int b = 0;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ArrayList<DrawStats> savedGames;

    private JLabel headliner;
    private JLabel currentColorLabel;
    private JLabel sizeLabel;
    private JLabel rLabel;
    private JLabel gLabel;
    private JLabel bLabel;
    private JTextField gameNameField;

    private Font labelFont;
    private Font buttonFont;
    private JButton returnButton;
    private JButton saveGameButton;
    private JButton resetButton;
    private JButton resetLastButton;

    private JSlider sizeSlider;
    private JSlider rSlider;
    private JSlider gSlider;
    private JSlider bSlider;

    public LeftPanel(DrawStats drawStats, ContainerPanel containerPanel, int width, int height) {
        this.drawStats = drawStats;
        this.containerPanel = containerPanel;
        this.width = width;
        this.height = height;

        savedGames = new ArrayList<DrawStats>();

        labelFont = new Font("San Serif", Font.BOLD, 12);
        buttonFont = new Font("San Serif", Font.BOLD, 9);
        headliner = new JLabel("TOOLBAR");
        headliner.setFont(labelFont);
        currentColorLabel = new JLabel("CURRENT COLOR");
        currentColorLabel.setFont(labelFont);
        sizeLabel = new JLabel("SIZE: " + Integer.toString(drawStats.getSize()));
        sizeLabel.setFont(labelFont);

        gameNameField = new JTextField("Name here...");
        gameNameField.setPreferredSize(new Dimension(width - 20, height/32));

        rLabel = new JLabel("R-VALUE: " + Integer.toString(drawStats.getRValue()));
        rLabel.setFont(labelFont);
        gLabel = new JLabel("G-VALUE: " + Integer.toString(drawStats.getGValue()));
        gLabel.setFont(labelFont);
        bLabel = new JLabel("B-VALUE: " + Integer.toString(drawStats.getBValue()));
        bLabel.setFont(labelFont);

        returnButton = new JButton("RETURN TO MENU");
        returnButton.setBackground(new Color(0, 0, 0));
        returnButton.setForeground(new Color(255, 255, 255));
        returnButton.setFont(buttonFont);
        returnButton.addActionListener(new ClickListener());
        returnButton.setPreferredSize(new Dimension(width - 20, height/24));

        saveGameButton = new JButton("SAVE GAME");
        saveGameButton.setBackground(new Color(0, 0, 0));
        saveGameButton.setForeground(new Color(255, 255, 255));
        saveGameButton.setFont(buttonFont);
        saveGameButton.addActionListener(new ClickListener());
        saveGameButton.setPreferredSize(new Dimension(width - 20, height/24));

        resetButton = new JButton("RESET");
        resetButton.setBackground(new Color(0, 0, 0));
        resetButton.setForeground(new Color(255, 255, 255));
        resetButton.addActionListener(new ClickListener());
        resetButton.setPreferredSize(new Dimension(width - 20, height/12));

        resetLastButton = new JButton("REGRET");
        resetLastButton.setBackground(new Color(0, 0, 0));
        resetLastButton.setForeground(new Color(255, 255, 255));
        resetLastButton.setPreferredSize(new Dimension(width - 20, height/12));
        resetLastButton.addActionListener(new ClickListener());

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
        sizeSlider.setPreferredSize(new Dimension(width - 5, 40));
        sizeSlider.setMajorTickSpacing(5);
        sizeSlider.setPaintTicks(true);
        sizeSlider.addChangeListener(new SliderListener());

        rSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        rSlider.setPreferredSize(new Dimension(width - 5, 40));
        rSlider.setMajorTickSpacing(5);
        rSlider.setPaintTicks(true);
        rSlider.addChangeListener(new SliderListener());

        gSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        gSlider.setPreferredSize(new Dimension(width - 5, 40));
        gSlider.setMajorTickSpacing(5);
        gSlider.setPaintTicks(true);
        gSlider.addChangeListener(new SliderListener());

        bSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        bSlider.setPreferredSize(new Dimension(width - 5, 40));
        bSlider.setMajorTickSpacing(5);
        bSlider.setPaintTicks(true);
        bSlider.addChangeListener(new SliderListener());

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.add(headliner);
        this.add(gameNameField);
        this.add(returnButton);
        this.add(saveGameButton);
        this.add(resetButton);
        this.add(resetLastButton);
        this.add(rLabel);
        this.add(rSlider);
        this.add(gLabel);
        this.add(gSlider);
        this.add(bLabel);
        this.add(bSlider);
        this.add(sizeLabel);
        this.add(sizeSlider);
        this.add(currentColorLabel);
    }
    public void setRGBValues(int r, int g, int b) {
        rLabel.setText("R VALUE: " + Integer.toString(r));
        gLabel.setText("G VALUE: " + Integer.toString(g));
        bLabel.setText("B VALUE: " + Integer.toString(b));
    }
    private void setCurrentColor(Color newColor) { drawStats.setColor(newColor); }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(drawStats.getColor());
        g.fillRect(width-110, height - height/5, width - 60, width-60);
        repaint();
    }

    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == resetButton) {
                drawStats.resetShapes();
            }
            else if (actionEvent.getSource() == resetLastButton) {
                drawStats.removeShape();
            }
            else if (actionEvent.getSource() == returnButton) {
                containerPanel.changeGameState(GameState.MAINMENUSTATE);
            }
            else if(actionEvent.getSource() == saveGameButton) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(new File("src\\files\\SaveFile")));
                        if(br.readLine() != null) {
                            objectInputStream = new ObjectInputStream(new FileInputStream(new File("src\\files\\SaveFile")));
                            savedGames = (ArrayList<DrawStats>) objectInputStream.readObject();
                            objectInputStream.close();
                        }
                        drawStats.setGameName(gameNameField.getText());
                        savedGames.add(drawStats);
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("src\\files\\SaveFile")));
                        objectOutputStream.writeObject(savedGames);
                        objectOutputStream.close();
                        br.close();
                    }
                    catch (Exception e) { e.printStackTrace(); }
            }
        }
    }

    private class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(changeEvent.getSource() == sizeSlider) {
                drawStats.setSize(sizeSlider.getValue());
                sizeLabel.setText("SIZE: " + Integer.toString(drawStats.getSize()));
                return;
            }
            setCurrentColor(new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue()));
            rLabel.setText("R VALUE: " + Integer.toString(rSlider.getValue()));
            gLabel.setText("G VALUE: " + Integer.toString(gSlider.getValue()));
            bLabel.setText("B VALUE: " + Integer.toString(bSlider.getValue()));
        }
    }
}
