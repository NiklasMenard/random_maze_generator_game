package random_maze_generator_game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            GameFrame game = new GameFrame();
            game.start();
        });
    }
}
