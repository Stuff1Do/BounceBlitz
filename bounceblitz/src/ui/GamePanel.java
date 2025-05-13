package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import physics.Puck;
import physics.Paddle;
import game.Game;
import game.Scoreboard;
import players.Player;
import players.PlayerManager;
import utils.Randomizer;

public class GamePanel extends JPanel implements ActionListener {
    Paddle p1, p2;
    Puck puck;
    Player player1, player2;
    PlayerManager playerManager;
    Game game;
    Scoreboard scoreboard;
    Timer timer;
    int format = 3;
    int p1RoundsWon = 0;
    int p2RoundsWon = 0;
    boolean matchOver = false;

    public GamePanel(String name1, String name2, int format) {
        this.format = format;
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.BLACK);
       
        p1 = new Paddle(5, 150);
        p2 = new Paddle(480, 150);
        puck = new Puck(250, 200);

        player1 = new Player(name1, p1);
        player2 = new Player(name2, p2);
        playerManager = new PlayerManager(List.of(player1, player2));
        playerManager.setInitialServer(player1);
        game = new Game(puck, playerManager, 500);
        scoreboard = new Scoreboard(player1, player2);

        game.freezePuck();
        Randomizer.randomizeDirectionForServer(puck, playerManager.getServer().getPaddle());

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new EventListener());

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        p1.draw(g);
        p2.draw(g);
        puck.draw(g);
        scoreboard.draw(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(250, 0, 250, 400);
        g2.setStroke(new BasicStroke(6));
        g2.drawOval(200, 150, 100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (matchOver) return;
        p1.move();
        p2.move();
        game.engine();
        checkRoundWin();
        repaint();
    }

    private void checkRoundWin() {
        int goalsToWinRound = 5; // 5 goals per round
        if (player1.getScore() >= goalsToWinRound) {
            p1RoundsWon++;
            showRoundWinner(player1.getName());
            resetRound();
        } else if (player2.getScore() >= goalsToWinRound) {
            p2RoundsWon++;
            showRoundWinner(player2.getName());
            resetRound();
        }
        int roundsToWin = (format / 2) + 1;
        if (p1RoundsWon >= roundsToWin) {
            matchOver = true;
            showMatchWinner(player1.getName());
        } else if (p2RoundsWon >= roundsToWin) {
            matchOver = true;
            showMatchWinner(player2.getName());
        }
    }

    private void resetRound() {
        player1.resetScore();
        player2.resetScore();
        game.freezePuck();
        Randomizer.randomizeDirectionForServer(puck, playerManager.getServer().getPaddle());
        repaint();
    }

    private void showRoundWinner(String winnerName) {
        JOptionPane.showMessageDialog(this, winnerName + " wins the round!");
    }

    private void showMatchWinner(String winnerName) {
        JOptionPane.showMessageDialog(this, winnerName + " wins the match!");
        // Return to main menu
        SwingUtilities.getWindowAncestor(this).dispose();
        new GameFrame();
    }

    private class EventListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (game.isPuckFrozen()) {
                int code = e.getKeyCode();
                Player server = playerManager.getServer();
                boolean validServe = (server == playerManager.getPlayers().get(0)
                        && (code == KeyEvent.VK_W || code == KeyEvent.VK_S))
                        || (server == playerManager.getPlayers().get(1)
                        && (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN));
                if (validServe) {
                    game.unfreezePuckOnServe();
                }
            }

            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> p1.setDirection(-8);
                case KeyEvent.VK_S -> p1.setDirection(8);
                case KeyEvent.VK_UP -> p2.setDirection(-8);
                case KeyEvent.VK_DOWN -> p2.setDirection(8);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W, KeyEvent.VK_S -> p1.setDirection(0);
                case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> p2.setDirection(0);
            }
        }
    }
}
