package ui;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private CardLayout cardLayout; 
    private JPanel mainPanel;
    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("BounceBlitz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainMenu menu = new MainMenu(this);
        gamePanel = new GamePanel();

        mainPanel.add(menu, "Menu");
		mainPanel.add(gamePanel, "Game");

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void showGame() {
        cardLayout.show(mainPanel, "Game");
        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow()); 
    }
}


