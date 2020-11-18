package graphics;

import logic.DrawStats;
import logic.GameState;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {
    private int width;
    private int height;
    private DrawStats drawStats;

    private MainMenuPanel mainMenuPanel;
    private DrawingPanel drawingPanel;
    private LoadGamePanel loadGamePanel;
    private CardLayout cardLayout;

    public ContainerPanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.drawStats = new DrawStats();

        mainMenuPanel = new MainMenuPanel(this, width, height);
        drawingPanel = new DrawingPanel(this, drawStats, width, height);
        loadGamePanel = new LoadGamePanel(this, drawStats, width, height);
        cardLayout = new CardLayout();

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(cardLayout);

        this.add(mainMenuPanel, mainMenuPanel.toString());
        this.add(drawingPanel, drawingPanel.toString());
        this.add(loadGamePanel, loadGamePanel.toString());

        cardLayout.show(this, mainMenuPanel.toString());
    }

    public void changeGameState(GameState newGameState) {
        switch (newGameState) {
            case PLAYSTATE:
                cardLayout.show(this, drawingPanel.toString());
                break;
            case MAINMENUSTATE:
                cardLayout.show(this, mainMenuPanel.toString());
                break;
            case LOADGAMESSTATE:
                loadGamePanel.loadGames();
                cardLayout.show(this, loadGamePanel.toString());
                break;
        }
     }
}
