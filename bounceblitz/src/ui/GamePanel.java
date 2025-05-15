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
import ui.MainMenu;

public class GamePanel extends JPanel implements ActionListener {
    Paddle p1, p2;
    Puck puck;
    Player player1, player2;
    PlayerManager playerManager;
    Game game;
    Scoreboard scoreboard;
    Timer timer;
    int format = 3;
    boolean matchOver = false;
    java.util.List<String> matchHistory = new java.util.ArrayList<>();
    int p1Wins = 0;
    int p2Wins = 0;
    int paddleSpeed = 8;
    public GamePanel(String name1, String name2, int format) {
        this.format = format;
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.BLACK);
       
        p1 = new Paddle(5, 150);
        p2 = new Paddle(480, 150);
        puck = new Puck(242, 193);

        player1 = new Player(name1, p1);
        player2 = new Player(name2, p2);
        playerManager = new PlayerManager(List.of(player1, player2));
        playerManager.setInitialServer(player1);
        game = new Game(puck, playerManager, 500);
        scoreboard = new Scoreboard(player1, player2);

        game.freezePuck();
        puck.resetPosition();
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

        // Draw current match score at the bottom
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Score: " + p1Wins + ":" + p2Wins, 20, 380);
    }

    private void checkMatchWin() {
        if (player1.getScore() >= format) {
            matchOver = true;
            showMatchWinner(player1.getName());
        } else if (player2.getScore() >= format) {
            matchOver = true;
            showMatchWinner(player2.getName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (matchOver) return;
        p1.move();
        p2.move();
        game.engine();
        checkMatchWin();
        repaint();
    }

    private void showMatchWinner(String winnerName) {
        if (winnerName.equals(player1.getName())) {
            p1Wins++;
        } else {
            p2Wins++;
        }
        matchHistory.add(p1Wins + ":" + p2Wins);
        int option = JOptionPane.showOptionDialog(this,
                winnerName + " wins the match!\n\nDo you want to play again?",
                "Match Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Continue", "Exit"},
                "Continue");
        GameFrame parentFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.stopGameMusic();
        }
        if (option == JOptionPane.YES_OPTION) {
            // Continue: reset scores, keep match history, start new match
            player1.resetScore();
            player2.resetScore();
            matchOver = false;
            game.freezePuck();
            Randomizer.randomizeDirectionForServer(puck, playerManager.getServer().getPaddle());
            if (parentFrame != null) {
                parentFrame.stopGameMusic();
                parentFrame.playGameMusicAgain();
            }
            repaint();
        } else if (option == JOptionPane.NO_OPTION) {
            // Exit: return to main menu instead of closing window
            if (parentFrame != null) {
                parentFrame.showMainMenu();
                parentFrame.stopGameMusic();
                parentFrame.playMenuMusicAgain();
            }
        }
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
                case KeyEvent.VK_W -> p1.setDirection(-paddleSpeed);
                case KeyEvent.VK_S -> p1.setDirection(paddleSpeed);
                case KeyEvent.VK_UP -> p2.setDirection(-paddleSpeed);
                case KeyEvent.VK_DOWN -> p2.setDirection(paddleSpeed);
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
    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed + 6;
        
    }   
}
