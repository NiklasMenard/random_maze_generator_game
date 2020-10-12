package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


//Class that paints the created maze and player movement. Uses a tick function to
//update the movement and check game logic.
public class GameRenderPanel extends JPanel implements KeyListener, Runnable {


    private Thread thread;
    private Player player;
    private MazeBuilder maze;
    private GamePanel parent;

    private int ticks;
    private final int end_coord_x;
    private final int end_coord_y;
    private final int width;
    private final int height;
    private int pXcoor = 0, pYcoor = 0;

    private Boolean paused = false;
    private boolean running = false;

    public GameRenderPanel(int width, int height) {

        ticks = 0;
        this.width = width;
        this.height = height;

        addKeyListener(this);
        setPreferredSize(new Dimension(width, height));

        maze = new MazeBuilder(width, height);
        player = new Player(0, 0);
        end_coord_x = maze.getEndX();
        end_coord_y = maze.getEndY();
        setBorder(BorderFactory.createLineBorder(Color.black));

    }

    //basic tick method to check game logic and update the view
    public void tick() throws IOException {

        //update player and maze
        player = new Player(pXcoor, pYcoor);
        maze.drawMaze();

        //check if player has made it to the end and if so reset the game
        //and return to menu and show win dialog
        if (pXcoor == end_coord_x && pYcoor == end_coord_y) {
            parent.returnToMenu();
            parent.showWinDialog();
            resetGame();
        }

        ticks++;

        if (ticks > 250000) {
            ticks = 0;
        }
    }

    //function reset coordinates, create new player and new maze
    public void resetGame() {
        pXcoor = 0;
        pYcoor = 0;
        player = new Player(pXcoor, pYcoor);
        maze = new MazeBuilder(width, height);
        stop();
    }

    //method to paint all elements from gamepanel (player, maze, colors etc.)
    @Override
    public void paint(Graphics g) {

        g.setColor(new Color(140, 136, 136));
        g.fillRect(0, 0, width, height);
        player.draw(g);

        for (int row = 0; row < maze.getCell_array().length; row++) {
            for (int column = 0; column < maze.getCell_array()[row].length; column++) {
                maze.getCell_array()[row][column].draw(g);
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                tick();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    //create new game thread
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //method that increases player X coordinate by one if moving left
    //or right. Y coordinate if moving down or up
    @Override
    public void keyPressed(KeyEvent event) {

        int key = event.getKeyCode();

        if (key == KeyEvent.VK_D) {
            if (checkIfCanMove("RIGHT")) {
                pXcoor++;
            }

        }
        if (key == KeyEvent.VK_A) {
            if (checkIfCanMove("LEFT")) {
                pXcoor--;
            }

        }
        if (key == KeyEvent.VK_W) {
            if (checkIfCanMove("UP")) {
                pYcoor--;
            }
        }
        if (key == KeyEvent.VK_S) {
            if (checkIfCanMove("DOWN")) {
                pYcoor++;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }
    //method that is called everytime a keypressed event happes. Checks
    //if there is a wall in the direction the player is moving
    public boolean checkIfCanMove(String direction) {

        int x = player.getxCoor();
        int y = player.getyCoor();

        if (!paused) {

            if (direction.equals("RIGHT")) {
                if (maze.getCell_array()[x][y].isRight_wall()) {
                    return false;
                }
            }

            if (direction.equals("LEFT")) {
                if (maze.getCell_array()[x][y].isLeft_wall()) {
                    return false;
                }
            }

            if (direction.equals("UP")) {
                if (maze.getCell_array()[x][y].isTop_wall()) {
                    return false;
                }
            }

            if (direction.equals("DOWN")) {
                return !maze.getCell_array()[x][y].isBottom_wall();
            }
        }
        return true;
    }

    public void setParent(GamePanel parent) {
        this.parent = parent;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public Boolean isPaused() {
        return paused;
    }

}