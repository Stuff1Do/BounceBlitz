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
        playButton.addActionListener(e -> {
            frame.modeSelection();
        });
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

    public static class ModeSelectionPanel extends JPanel {

        public ModeSelectionPanel(GameFrame frame) {
            setLayout(new BorderLayout());
            setBackground(Color.BLACK);

            // Title
            JLabel title = new JLabel("2 Player Mode", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC,40));
            title.setForeground(Color.CYAN);
            title.setBorder(BorderFactory.createEmptyBorder(60, 10, 30, 10));
            add(title, BorderLayout.NORTH);

            // Buttons
            JButton bestOf1 = createModeButton("Best of 1");
            JButton bestOf3 = createModeButton("Best of 3");
            JButton bestOf5 = createModeButton("Best of 5");

            bestOf1.addActionListener(e -> {
                frame.NameInputPanel();
            });
            bestOf3.addActionListener(e -> {
                frame.NameInputPanel();
            });
            bestOf5.addActionListener(e -> {
                frame.NameInputPanel();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.BLACK);
            buttonPanel.add(bestOf1);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            buttonPanel.add(bestOf3);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            buttonPanel.add(bestOf5);

            JPanel boxPanel = new JPanel(new GridBagLayout());
            boxPanel.setBackground(Color.BLACK);

            JPanel rectangle = new JPanel(new GridBagLayout());
            rectangle.setBackground(Color.BLACK);
            rectangle.setBorder(new LineBorder(Color.CYAN, 10));
            rectangle.setPreferredSize(new Dimension(220, 190));
            rectangle.add(buttonPanel);

            boxPanel.add(rectangle);
            add(boxPanel, BorderLayout.CENTER);
        }

        // Styling for mode buttons
        private static JButton createModeButton(String text) {
            JButton button = new JButton(text);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.setBackground(Color.CYAN);
            button.setForeground(Color.BLACK);
            button.setPreferredSize(new Dimension(140, 30));
            button.setMaximumSize(new Dimension(140, 30));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

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

    // Name Input Panel
    public static class NameInputPanel extends JPanel {
        public NameInputPanel(GameFrame frame) {
            setLayout(new BorderLayout());
            setBackground(Color.BLACK);

            JLabel title = new JLabel("Enter Player Names", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 36));
            title.setForeground(Color.CYAN);
            title.setBorder(BorderFactory.createEmptyBorder(60, 10, 30, 10));
            add(title, BorderLayout.NORTH);

            JTextField player1Field = new JTextField(15);
            JTextField player2Field = new JTextField(15);

            JLabel label1 = new JLabel("Player 1:");
            JLabel label2 = new JLabel("Player 2:");

            label1.setForeground(Color.CYAN);
            label2.setForeground(Color.CYAN);

            JButton startButton = createModeButton("Start Game");

            JPanel form = new JPanel();
            form.setLayout(new GridLayout(3, 2, 10, 10));
            form.setBackground(Color.BLACK);
            form.add(label1);
            form.add(player1Field);
            form.add(label2);
            form.add(player2Field);
            form.add(new JLabel()); // spacer
            form.add(startButton);

            JPanel formWrapper = new JPanel(new GridBagLayout());
            formWrapper.setBackground(Color.BLACK);
            JPanel rectangle = new JPanel(new GridBagLayout());
            rectangle.setBackground(Color.BLACK);
            rectangle.setBorder(new LineBorder(Color.CYAN, 10));
            rectangle.setPreferredSize(new Dimension(320, 150));
            rectangle.add(form);
            formWrapper.add(rectangle);

            add(formWrapper, BorderLayout.CENTER);

            startButton.addActionListener(e -> {
                String player1Name = player1Field.getText().trim();
                String player2Name = player2Field.getText().trim();
                frame.showGame();
            });
        }

        private static JButton createModeButton(String text) {
            JButton button = new JButton(text);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.setBackground(Color.CYAN);
            button.setForeground(Color.BLACK);
            button.setPreferredSize(new Dimension(140, 30));
            button.setMaximumSize(new Dimension(140, 30));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

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
}

