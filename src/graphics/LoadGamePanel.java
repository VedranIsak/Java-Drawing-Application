package graphics;

import logic.DrawStats;
import logic.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class LoadGamePanel extends JPanel {
    private int width;
    private int height;
    private ContainerPanel containerPanel;
    private DrawStats drawStats;
    private Image backgroundImage;

    private ArrayList<DrawStats> savedGames;
    private ObjectInputStream ois;
    private File saveFile;

    public LoadGamePanel(ContainerPanel containerPanel, DrawStats drawStats, int width, int height) {
        this.containerPanel = containerPanel;
        this.drawStats = drawStats;
        this.width = width;
        this.height = height;

        savedGames = new ArrayList<DrawStats>();
        saveFile = new File("src\\files\\SaveFile");
        try { backgroundImage = ImageIO.read(new File("src\\files\\Background1.jpg")); }
        catch (IOException e) { e.printStackTrace(); }

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width, height));
        this.add(new LeftPanel(), BorderLayout.WEST);
    }

    public void loadGames() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            if(br.readLine() == null) { return; }

            ois = new ObjectInputStream(new FileInputStream(saveFile));
            savedGames = (ArrayList<DrawStats>) ois.readObject();

            this.add(new RightPanel(), BorderLayout.EAST);
            ois.close();
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);

        repaint();
    }

    @Override
    public String toString() { return "LoadGamePanel"; }

    private class ClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            containerPanel.changeGameState(GameState.MAINMENUSTATE);
        }
    }

    private class LeftPanel extends JPanel {
        private JButton returnButton;

        public LeftPanel() {
            returnButton = new JButton("Return");
            returnButton.setPreferredSize(new Dimension(width/6 - 10, height/12));
            returnButton.setContentAreaFilled(false);
            returnButton.setFocusPainted(false);
            returnButton.setForeground(new Color(255, 255, 255));
            returnButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
            returnButton.addActionListener(new ClickListener());

            this.setPreferredSize(new Dimension(width/6, height));
            this.setLayout(new FlowLayout());
            this.setOpaque(false);
            this.add(returnButton);
        }
    }

    private class RightPanel extends JPanel {

        public RightPanel() {
            this.setLayout(new FlowLayout());
            this.setPreferredSize(new Dimension(width/6, height));
            this.setOpaque(false);

            for(DrawStats game : savedGames) {
                JButton button = new JButton(game.getGameName());
                button.setPreferredSize(new Dimension(width/6 - 10, height/12));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setForeground(new Color(255, 255, 255));
                button.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
                button.addActionListener(new ClickListener());
                this.add(button);
            }
        }

        private class ClickListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton button = (JButton) actionEvent.getSource();

                for(DrawStats game : savedGames) {
                    if(game.getGameName().equals(button.getText())) {
                        drawStats.setShapes(game.getShapes());
                    }
                }
            }
        }
    }

}
