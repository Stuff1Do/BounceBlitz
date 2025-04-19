package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainMenu extends JPanel {

    private GameFrame frame;

    public MainMenu(GameFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Title
        JLabel title = new JLabel("BounceBlitz", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.CYAN);
        title.setBorder(BorderFactory.createEmptyBorder(80, 10, 30, 10));

        // Create styled buttons
        JButton playButton = createStyledButton("Play");
        JButton howToPlayButton = createStyledButton("How to Play");
        JButton exitButton = createStyledButton("Exit");

        // action listeners
        playButton.addActionListener(e -> frame.showGame());
        howToPlayButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, """
                                            How to play: 

                                            Match Formats: Players can choose between a single-round game (best of 1), 
                                            a short competitive match (best of 3), or a full-length battle (best of 5).

                                            Controls:
                                            -For single-round games, players can use the 'W' and 'S' keys to control their paddles.
                                            -For short competitive matches, players can use the 'UP' and 'DOWN' arrow keys to control their paddles.
                                            -For full-length battles, players can use the 'W' and 'S' keys for player 1 and the 'UP' and 'DOWN' arrow keys for player 2.
                                            
                                            Objective:
                                            -The objective is to score by getting the puck into the opponent's goal.
                                            -The game ends when a player scores 5 goals.
                                            
                                            Good luck and have fun!""");
        });
        exitButton.addActionListener(e -> System.exit(0));



        // Button container (vertical)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(exitButton);


        // Rectangle box around buttons
        JPanel boxPanel = new JPanel(new GridBagLayout());
        boxPanel.setBackground(Color.black);

        JPanel rectangle = new JPanel(new GridBagLayout());
        rectangle.setBackground(Color.black);
        rectangle.setBorder(new LineBorder(Color.CYAN, 10));
        rectangle.setPreferredSize(new Dimension(220, 190)); 
        rectangle.add(buttonPanel); 

        boxPanel.add(rectangle);

        // Add to main layout
        add(title, BorderLayout.NORTH);
        add(boxPanel, BorderLayout.CENTER);
        add(Box.createVerticalGlue(), BorderLayout.SOUTH); 
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBackground(Color.CYAN);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(140, 30));
        button.setMaximumSize(new Dimension(140, 30));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 200, 200));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.CYAN);
            }
        });

        return button;
    }
}

