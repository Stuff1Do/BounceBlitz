package game;

import players.Player;
import java.awt.*;

public class Scoreboard {
	Player player1;
	Player player2;
	
	public Scoreboard(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Century Gothic", Font.BOLD, 20));
	    g.drawString(player1.getName() + ": " + player1.getScore(), 20, 30);
	    g.drawString(player2.getName() + ": " + player2.getScore(), 350, 30);
	  
	}
}
