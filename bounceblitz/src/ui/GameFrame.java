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

    private Clip menuMusic;
    private Clip gameMusic;

    public GameFrame() {
        setTitle("BounceBlitz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainMenu menu = new MainMenu(this);
        modepanel = new MainMenu.ModeSelectionPanel(this);
        mainPanel.add(menu, "Menu");
        mainPanel.add(modepanel, "ModeSelection");
        cardLayout.show(mainPanel, "Menu");

        add(mainPanel);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Start menu music
        playMenuMusic("bounceblitz/resources/menu.wav");
        pack();
    }

    public void showGame(String name1, String name2, int format) {
        // Stop menu music and start game music
        stopMenuMusic();
        playGameMusic("bounceblitz/resources/game.wav");

        gamePanel = new GamePanel(name1, name2, format);
        mainPanel.add(gamePanel, "Game");
        cardLayout.show(mainPanel, "Game");
        pack();
        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
    }

    public void showMainMenu() {
        cardLayout.show(mainPanel, "Menu");
    }

    public void playMenuMusicAgain() {
        playMenuMusic("bounceblitz/resources/menu.wav");
    }

    public void modeSelection() {
        cardLayout.show(mainPanel, "ModeSelection");
    }

    public void setNameInputPanel(int format) {
        nameInputPanel = new MainMenu.NameInputPanel(this, format);
        mainPanel.add(nameInputPanel, "NameInputPanel");
        cardLayout.show(mainPanel, "NameInputPanel");
    }

    private void playMenuMusic(String filePath) {
        menuMusic = playMusic(filePath);
    }

    private void stopMenuMusic() {
        if (menuMusic != null && menuMusic.isRunning()) {
            menuMusic.stop();
        }
    }

    private void playGameMusic(String filePath) {
        gameMusic = playMusic(filePath);
    }

    public void stopGameMusic() {
        if (gameMusic != null && gameMusic.isRunning()) {
            gameMusic.stop();
        }
    }

    private Clip playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
}