package random_maze_generator_game;

import javax.swing.*;

public class GameFrame extends JFrame {

    private ViewUpdate viewUpdate;

    public GameFrame() {
        viewUpdate = new ViewUpdate(400, 425);

    }
    public void start() {

        add(viewUpdate);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Maze");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}