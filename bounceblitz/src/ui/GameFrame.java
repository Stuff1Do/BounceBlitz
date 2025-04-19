package ui;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    private CardLayout cardLayout; 
    private JPanel mainPanel;
    private GamePanel gamePanel;
	private MainMenu.ModeSelectionPanel modepanel;
	private MainMenu.NameInputPanel nameInputPanel;
	
	
    public GameFrame() {
        setTitle("BounceBlitz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout(); // CARDLAYOUT For switching to diff panels
        mainPanel = new JPanel(cardLayout);

        MainMenu menu = new MainMenu(this);
        gamePanel = new GamePanel();
		modepanel = new MainMenu.ModeSelectionPanel(this);
		nameInputPanel = new MainMenu.NameInputPanel(this);
		
        mainPanel.add(menu, "Menu");
		mainPanel.add(gamePanel, "Game");
		mainPanel.add(modepanel, "ModeSelection");
		mainPanel.add(nameInputPanel, "NameInputPanel");

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

	public void modeSelection() {
		cardLayout.show(mainPanel, "ModeSelection");
	}

	public void NameInputPanel() {
		cardLayout.show(mainPanel, "NameInputPanel");
	}
}


