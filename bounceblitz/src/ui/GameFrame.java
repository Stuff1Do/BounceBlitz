package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePanel gamePanel;
    private MainMenu.ModeSelectionPanel modepanel;
    private MainMenu.NameInputPanel nameInputPanel;
    private MainMenu.SettingsPanel settingsPanel;

    private Clip menuMusic;
    private Clip gameMusic;

    public GameFrame() {
        setTitle("BounceBlitz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainMenu menu = new MainMenu(this);
        modepanel = new MainMenu.ModeSelectionPanel(this);
        settingsPanel = new MainMenu.SettingsPanel(this);

        mainPanel.add(menu, "Menu");
        mainPanel.add(modepanel, "ModeSelection");
        mainPanel.add(settingsPanel, "SettingsPanel");

        add(mainPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        setScreen("Menu");
        pack();
    }

    public void setScreen(String screen) {
        if (menuMusic != null && menuMusic.isRunning()) menuMusic.stop();
        if (gameMusic != null && gameMusic.isRunning()) gameMusic.stop();

        switch (screen) {
            case "Menu" -> {
                playMenuMusic("bounceblitz/resources/menu.wav");
                cardLayout.show(mainPanel, "Menu");
            }
            case "ModeSelection" -> {
                playMenuMusic("bounceblitz/resources/menu.wav");
                cardLayout.show(mainPanel, "ModeSelection");
            }
            case "SettingsPanel" -> {
                playMenuMusic("bounceblitz/resources/menu.wav");
                cardLayout.show(mainPanel, "SettingsPanel");
            }
            case "Game" -> {
                playGameMusic("bounceblitz/resources/game.wav");
                cardLayout.show(mainPanel, "Game");
                SwingUtilities.invokeLater(() -> {
                    if (gamePanel != null) gamePanel.requestFocusInWindow();
                });
            }
            case "NameInputPanel" -> {
                playMenuMusic("bounceblitz/resources/menu.wav");
                cardLayout.show(mainPanel, "NameInputPanel");
            }
        }
        pack();
    }

    public void showGame(String name1, String name2, int format) {
        gamePanel = new GamePanel(name1, name2, format, this);
        mainPanel.add(gamePanel, "Game");
        setScreen("Game");
    }

    public void modeSelection() {
        setScreen("ModeSelection");
    }

    public void setNameInputPanel(int format) {
        nameInputPanel = new MainMenu.NameInputPanel(this, format);
        mainPanel.add(nameInputPanel, "NameInputPanel");
        setScreen("NameInputPanel");
    }

    public void showMainMenu() {
        setScreen("Menu");
    }

    public void showSettings() {
        setScreen("SettingsPanel");
    }

    private Clip playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void playMenuMusic(String filePath) {
        menuMusic = playMusic(filePath);
    }

    private void playGameMusic(String filePath) {
        gameMusic = playMusic(filePath);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }
}