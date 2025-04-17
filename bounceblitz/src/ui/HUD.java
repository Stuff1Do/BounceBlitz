package ui;

import java.awt.*;

public class HUD {
    private int player1Score = 0;
    private int player2Score = 0;

    public void addScore(int player) {
        if (player == 1) player1Score++;
        else if (player == 2) player2Score++;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("P1: " + player1Score, 20, 30);
        g.drawString("P2: " + player2Score, 500, 30);
    }

    public void reset() {
        player1Score = 0;
        player2Score = 0;
    }

    public int getPlayer1Score() { return player1Score; }
    public int getPlayer2Score() { return player2Score; }
}
