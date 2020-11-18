package graphics;

import graphics.ContainerPanel;
import graphics.Frame;
import logic.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainMenuPanel extends JPanel {
    private int width;
    private int height;
    private ContainerPanel containerPanel;

    private Image backGroundImage;
    private Font mainMenuFont;

    private JButton playButton;
    private JButton loadGamesButton;
    private JButton quitButton;
    private JPanel leftPanel;

    public MainMenuPanel(ContainerPanel containerPanel, int width, int height) {
        this.containerPanel = containerPanel;
        this.height = height;
        this.width = width;

        try { backGroundImage = ImageIO.read(new File("src\\files\\Background1.jpg")); }
        catch (Exception e) { e.printStackTrace(); }

        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(250, height));

        playButton = new JButton("Play Game");
        playButton.setPreferredSize(new Dimension(width/6, height/12));
        playButton.setFocusPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        playButton.addActionListener(new ClickListener());
        playButton.setForeground(new Color(255, 255, 255));

        loadGamesButton = new JButton("Load Games");
        loadGamesButton.setPreferredSize(new Dimension(width/6, height/12));
        loadGamesButton.setFocusPainted(false);
        loadGamesButton.setContentAreaFilled(false);
        loadGamesButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        loadGamesButton.addActionListener(new ClickListener());
        loadGamesButton.setForeground(new Color(255, 255, 255));

        quitButton = new JButton("Quit Game");
        quitButton.setPreferredSize(new Dimension(width/6, height/12));
        quitButton.setFocusPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        quitButton.addActionListener(new ClickListener());
        quitButton.setForeground(new Color(255, 255, 255));

        mainMenuFont = new Font("SAN SERIF", Font.BOLD, 48);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width, height));
        this.add(leftPanel, BorderLayout.WEST);

        leftPanel.add(playButton);
        leftPanel.add(loadGamesButton);
        leftPanel.add(quitButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGroundImage, 0,0, null);

        g.setFont(mainMenuFont);
        g.setColor(new Color(255, 255, 0));
        g.drawString("Main Menu", width/3 + width/16, height/4 - height/6);
        repaint();
    }

    private class ClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == quitButton) {
                System.exit(0);
            }
            else if(actionEvent.getSource() == loadGamesButton) {
                containerPanel.changeGameState(GameState.LOADGAMESSTATE);
            }
            else if(actionEvent.getSource() == playButton) {
                containerPanel.changeGameState(GameState.PLAYSTATE);
            }
        }
    }

    @Override
    public String toString() { return "MainMenuPanel"; }
}
