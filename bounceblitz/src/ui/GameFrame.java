package ui;

import javax.swing.*;

public class GameFrame extends JFrame{
	public GameFrame() {
		add(new GamePanel());
		pack();
		
        setVisible(true);   
	}
}
