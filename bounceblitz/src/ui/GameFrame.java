package ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
	public GameFrame() {
		add(new GamePanel());
		pack();
		setResizable(false); // optional: avoid user resizing
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
