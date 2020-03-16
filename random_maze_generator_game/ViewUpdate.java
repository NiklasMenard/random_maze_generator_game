package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class ViewUpdate extends JPanel implements Runnable {

    private GameState gameState;
    private Thread thread;
    private boolean running = false;

    private GamePanel gamepanel;
    private MenuPanel menupanel;

    private int width, height;

    public ViewUpdate(int width, int height) {

        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(140, 136, 136));

        gamepanel = new GamePanel(width, height);
        menupanel = new MenuPanel(width, height);

        setupGame();

        gameState = menupanel.getGameState();
        start();

    }

    @Override
    public void run() {

        while (running) {

            if (gamepanel.hasEnded()) {
                removeAll();
                setupGame();
                revalidate();
                gameState = GameState.MENU;
            } else {
                gameState = menupanel.getGameState();
            }

            switch (gameState) {
                case MENU:
                    menupanel.setVisible(true);
                    break;
                case GAME:
                    menupanel.setVisible(false);
                    startGame();
                    gamepanel.setVisible(true);
                    gamepanel.requestFocusInWindow();
                    break;

                case EXIT:
                    stop();
                    System.exit(0);
                    break;
                default:
                    throw new RuntimeException("Unknown state: " + gameState);

            }
        }
    }

    public void startGame() {

        try {
            gamepanel.tick();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gamepanel.repaint();

    }


    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void setupGame() {

        gamepanel = new GamePanel(width, height);
        menupanel = new MenuPanel(width, height);

        add(gamepanel);
        add(menupanel);

        gamepanel.setVisible(false);
        menupanel.setVisible(false);

    }
}