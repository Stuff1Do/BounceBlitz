package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import physics.Puck;
import physics.Paddle;
import game.GameEngine;

public class GamePanel extends JPanel implements ActionListener {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int DELAY = 10; // milliseconds (100 FPS)

    private Timer timer;
    private GameEngine gameEngine;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        // Initialize the game engine (you'll pass in players, puck speed, etc.)
        gameEngine = new GameEngine(WIDTH, HEIGHT);

        addKeyListener(gameEngine.getInputHandler());

        // Start game loop
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Draw center line
        g.setColor(Color.GRAY);
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

        // Draw puck
        Puck puck = gameEngine.getPuck();
        g.setColor(Color.WHITE);
        g.fillOval(puck.getX(), puck.getY(), puck.getDiameter(), puck.getDiameter());

        // Draw paddles
        Paddle p1 = gameEngine.getPlayer1Paddle();
        Paddle p2 = gameEngine.getPlayer2Paddle();
        g.setColor(Color.RED);
        g.fillRect(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
        g.setColor(Color.BLUE);
        g.fillRect(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());

        // Draw HUD (optional: show score, timer, etc.)
        // gameEngine.getHUD().draw(g); // if you create a HUD class
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameEngine.update();
        repaint();
    }
}
