package ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
	public GameFrame() {
		setTitle("Bounce Blitz");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);       
		setLocationRelativeTo(null); 
		
		
		GamePanel panel = new GamePanel();
		add(panel);
		pack();
		
        setVisible(true);   
	}
}
