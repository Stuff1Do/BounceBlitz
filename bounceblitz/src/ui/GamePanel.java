package ui;

import physics.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private final Paddle player1;
    private final Paddle player2;
    private final Puck puck;
    private final HUD hud;
    private final Timer timer;

    public GamePanel() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();

        player1 = new Paddle(30, 200);
        player2 = new Paddle(600, 200);
        puck = new Puck(310, 230);
        hud = new HUD();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> player1.setDirection(-8);
                    case KeyEvent.VK_S -> player1.setDirection(8);
                    case KeyEvent.VK_UP -> player2.setDirection(-8);
                    case KeyEvent.VK_DOWN -> player2.setDirection(8);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_S -> player1.setDirection(0);
                    case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> player2.setDirection(0);
                }
            }
        });

        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player1.move();
        player2.move();
        puck.move();

        Collisions.handlePaddleCollision(puck, player1);
        Collisions.handlePaddleCollision(puck, player2);

        if (Collisions.checkGoal(puck)) {
            if (puck.x <= 0) hud.addScore(2); // Player 2 scores
            else hud.addScore(1);             // Player 1 scores
            puck.resetPosition(310, 230);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player1.draw(g);
        player2.draw(g);
        puck.draw(g);
        hud.draw(g);
    }
}
