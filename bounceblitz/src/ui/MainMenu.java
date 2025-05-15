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
        
        setPreferredSize(new Dimension(500, 400));
        
        JLabel title = new JLabel("BounceBlitz", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.CYAN);
        title.setBorder(BorderFactory.createEmptyBorder(80, 10, 30, 10));

        // Create styled buttons
        JButton playButton = createStyledButton("Play");
        JButton howToPlayButton = createStyledButton("How to Play");
        JButton SettingsButton = createStyledButton("Settings");
        JButton exitButton = createStyledButton("Exit");

        // action listeners
        playButton.addActionListener(e -> { frame.modeSelection();});    
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
                                            -The game ends when a player scores sufficient scores for chosen mode.
                                            
                                            Good luck and have fun!""");
        });
        
        exitButton.addActionListener(e -> System.exit(0));
        SettingsButton.addActionListener(e -> {frame.showSettings();});

        // Button container (vertical)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.black);
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(SettingsButton);
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
        button.setPreferredSize(new Dimension(180, 30));
        button.setMaximumSize(new Dimension(180, 30));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Hover effect
        button.setOpaque(true);
        button.setContentAreaFilled(true);

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
                frame.setNameInputPanel(1);
            });
            bestOf3.addActionListener(e -> {
                frame.setNameInputPanel(2);
            });
            bestOf5.addActionListener(e -> {
                frame.setNameInputPanel(3);
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
            button.setContentAreaFilled(true); // make sure background is drawn
            button.setOpaque(true); //
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
        public NameInputPanel(GameFrame frame, int matchFormat) {
        	
            setLayout(new BorderLayout());
            setBackground(Color.BLACK);

            JLabel title = new JLabel("Enter Player Names", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 36));
            title.setForeground(Color.CYAN);
            title.setBorder(BorderFactory.createEmptyBorder(60, 10, 30, 10));
            add(title, BorderLayout.NORTH);

            JTextField player1Field = new JTextField(15);
            JTextField player2Field = new JTextField(15);

            player1Field.setFont(new Font("Arial", Font.PLAIN, 18));
            player2Field.setFont(new Font("Arial", Font.PLAIN, 18));

            JLabel label1 = new JLabel("Player 1:");
            JLabel label2 = new JLabel("Player 2:");

            label1.setForeground(Color.CYAN);
            label2.setForeground(Color.CYAN);

            label1.setFont(new Font("Arial", Font.BOLD, 25));
            label2.setFont(new Font("Arial", Font.BOLD, 25));

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
                
                // case of empty names
                if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                    frame.showGame(player1Name, player2Name, matchFormat);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter names for both players.");
                }
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

    public static class SettingsPanel extends JPanel {

        public SettingsPanel(GameFrame frame) {
            setLayout(new BorderLayout());
            setBackground(Color.BLACK);

            JLabel title = new JLabel("Settings", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 36));
            title.setForeground(Color.CYAN);
            title.setBorder(BorderFactory.createEmptyBorder(50, 10, 30, 10));
            add(title, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setBackground(Color.BLACK);

            // Timer setting
            JLabel timerLabel = createLabel("Set Timer:");
            String[] timerOptions = { "None", "30 sec", "1 min", "5 min" };
            JComboBox<String> timerCombo = new JComboBox<>(timerOptions);
            styleComboBox(timerCombo);

            // Puck Speed
            int puckSpeed = 5;
            JLabel puckLabel = createLabel("Puck Speed:");
            JSlider puckSlider = createSlider(puckSpeed);
            
            // Paddle Speed
            int paddleSpeed = 5;
            JLabel paddleLabel = createLabel("Paddle Speed:");
            JSlider paddleSlider = createSlider(paddleSpeed);
            
            

            // Reset and Back buttons
            JButton resetButton = createStyledButton("Reset Values");
            resetButton.addActionListener(e -> {
                timerCombo.setSelectedIndex(0);
                puckSlider.setValue(puckSpeed);
                paddleSlider.setValue(paddleSpeed);
            });
            
            JButton backButton = createStyledButton("Back");
            backButton.addActionListener(e -> frame.showMainMenu());


            // Add components
            centerPanel.add(timerLabel);
            centerPanel.add(timerCombo);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            centerPanel.add(puckLabel);
            centerPanel.add(puckSlider);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            centerPanel.add(paddleLabel);
            centerPanel.add(paddleSlider);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            centerPanel.add(resetButton);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            centerPanel.add(backButton);

            // Rectangle box around settings
            JPanel wrapper = new JPanel(new GridBagLayout());
            wrapper.setBackground(Color.BLACK);
            JPanel rectangle = new JPanel();
            rectangle.setLayout(new BoxLayout(rectangle, BoxLayout.Y_AXIS));
            rectangle.setBackground(Color.BLACK);
            rectangle.setBorder(new LineBorder(Color.CYAN, 10));
            rectangle.setPreferredSize(new Dimension(300, 300));
            rectangle.add(centerPanel);

            wrapper.add(rectangle);
            add(wrapper, BorderLayout.CENTER);
        }

        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setForeground(Color.CYAN);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            return label;
        }

        private JSlider createSlider(int initialValue) {
            JSlider slider = new JSlider(1, 10, initialValue);
            slider.setMajorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.setForeground(Color.CYAN);
            slider.setBackground(Color.BLACK);
            slider.setAlignmentX(Component.CENTER_ALIGNMENT);
            return slider;
        }

        private void styleComboBox(JComboBox<String> comboBox) {
            comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
            comboBox.setMaximumSize(new Dimension(150, 25));
            comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
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

