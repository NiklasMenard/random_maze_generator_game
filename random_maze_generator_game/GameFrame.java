package random_maze_generator_game;

import javax.swing.*;

public class GameFrame extends JFrame{

    public static final int gridscale = 40;

    private ViewUpdate viewUpdate;


    public GameFrame() {

        viewUpdate = new ViewUpdate(gridscale * 10, gridscale * 10);

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