package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameRenderPanel extends JPanel implements KeyListener, Runnable {


    private Thread thread;
    private Player player;
    private MazeBuilder maze;
    private GamePanel parent;

    private int ticks;
    private int end_coord_x;
    private int end_coord_y;
    private int width, height;
    private int sxCoor = 0, syCoor = 0;

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


    }

    public void tick() throws IOException {

        player = new Player(sxCoor, syCoor);
        maze.drawMaze();

        if (sxCoor == end_coord_x && syCoor == end_coord_y) {
            parent.returnToMenu();
            resetGame();
        }

        ticks++;

        if (ticks > 250000) {
            ticks = 0;
        }
    }

    public void resetGame() {
        sxCoor = 0;
        syCoor = 0;
        player = new Player(sxCoor, syCoor);
        maze = new MazeBuilder(width, height);
        stop();
    }

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

    @Override
    public void keyPressed(KeyEvent event) {

        int key = event.getKeyCode();

        if (key == KeyEvent.VK_D) {
            if (checkIfCanMove("RIGHT")) {
                sxCoor++;
            }

        }
        if (key == KeyEvent.VK_A) {
            if (checkIfCanMove("LEFT")) {
                sxCoor--;
            }

        }
        if (key == KeyEvent.VK_W) {
            if (checkIfCanMove("UP")) {
                syCoor--;
            }
        }
        if (key == KeyEvent.VK_S) {
            if (checkIfCanMove("DOWN")) {
                syCoor++;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

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