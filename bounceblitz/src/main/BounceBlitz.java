// File: main/BounceBlitz.java
package main;

import javax.swing.*;
import ui.GamePanel;

public class BounceBlitz {
    public static void main(String[] args) {
        // Always start Swing apps on the EDT
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bounce Blitz");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Create your game canvas and add it
            GamePanel gamePanel = new GamePanel();
            frame.getContentPane().add(gamePanel);
            
            frame.pack();                    // Sizes to preferred 640×480
            frame.setResizable(false);       // Fix window size
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);          // Show it!
        });
    }
}
