package ui;
//,
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
	

	 private CardLayout cardLayout; 
	    private JPanel mainPanel;
	    private GamePanel gamePanel;
		private MainMenu.ModeSelectionPanel modepanel;
		private MainMenu.NameInputPanel nameInputPanel;
		
	
	    public GameFrame() {
	        setTitle("BounceBlitz");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	        cardLayout = new CardLayout();
	        mainPanel = new JPanel(cardLayout);
	
	        MainMenu menu = new MainMenu(this);
	        
			modepanel = new MainMenu.ModeSelectionPanel(this);
			
			
	        mainPanel.add(menu, "Menu");
			
			mainPanel.add(modepanel, "ModeSelection");
			
	
	        add(mainPanel);
	      
	        setLocationRelativeTo(null);
	        setResizable(false);
	        setVisible(true);
	        
	        cardLayout.show(mainPanel, "Menu");
	        pack();
	    }
	
	    public void showGame(String name1, String name2, int format) {
	        gamePanel = new GamePanel(name1, name2, format);
	        mainPanel.add(gamePanel, "Game");
	        cardLayout.show(mainPanel, "Game");
	        pack();
	        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
	    }

	
		public void modeSelection() {
			cardLayout.show(mainPanel, "ModeSelection");
		}
	
		
		public void setNameInputPanel(int format) {
		    nameInputPanel = new MainMenu.NameInputPanel(this, format);
		    mainPanel.add(nameInputPanel, "NameInputPanel");
		    cardLayout.show(mainPanel, "NameInputPanel");
		}

}

