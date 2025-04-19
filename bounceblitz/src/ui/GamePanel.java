package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import physics.*;

public class GamePanel extends JPanel implements ActionListener {
	Paddle player1, player2;
	Timer timer;
	Puck puck;

	public GamePanel() {
		setPreferredSize(new Dimension(500, 400));
		setBackground(Color.black);
		player1 = new Paddle(5, 0);
		player2 = new Paddle(490, 0);
		puck = new Puck(310, 230);
		setFocusable(true);
		requestFocusInWindow();
		EventListener listener = new EventListener();
		
		addKeyListener(listener);
		
		timer = new Timer(16, this);
	    timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
    	super.paintComponent(g); // 🔧 IMPORTANT
    	player1.draw(g);
    	player2.draw(g);
    	puck.draw(g);

    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.WHITE);
    	g2.setStroke(new BasicStroke(3));
    	g2.drawLine(250, 0, 250, 500);
    	g2.setStroke(new BasicStroke(6));
    	g2.drawOval(200, 140, 100, 100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player1.move();
        player2.move();
        puck.move();

        Collisions.handlePaddleCollision(puck, player1);
        Collisions.handlePaddleCollision(puck, player2);
        if (Collisions.checkGoal(puck)) {
                      
            puck.resetPosition(310, 230);
        }
        repaint();
	}

	public class EventListener extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				player1.setDirection(-8);
			}else if(e.getKeyCode() == KeyEvent.VK_S) {
				player1.setDirection(8);
			}else if(e.getKeyCode() == KeyEvent.VK_UP) {
				player2.setDirection(-8);
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				player2.setDirection(8);
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				player1.setDirection(0);
			}else if(e.getKeyCode() == KeyEvent.VK_S) {
				player1.setDirection(0);
			}else if(e.getKeyCode() == KeyEvent.VK_UP) {
				player2.setDirection(0);
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				player2.setDirection(0);
			}
		}
		 

	}
}
