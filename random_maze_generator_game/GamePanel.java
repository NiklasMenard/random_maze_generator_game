package random_maze_generator_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements KeyListener {


    private Player player;
    private MazeBuilder maze;

    private int ticks;
    private int end_coord_x;
    private int end_coord_y;
    private int width, height;
    private int sxCoor = 0, syCoor = 0;

    private boolean hasEnded = false;

    public GamePanel(int width, int height) {

        this.width = width;
        this.height = height;

        ticks = 0;

        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(width, height));
        maze = new MazeBuilder(width, height);
        end_coord_x = maze.getEnd().getxCoor() / GameFrame.gridscale;
        end_coord_y = maze.getEnd().getyCoor() / GameFrame.gridscale;


    }


    public void tick() throws IOException {


        player = new Player(sxCoor, syCoor);

        if (player.getxCoor() == end_coord_x && player.getyCoor() == end_coord_y) {
            maze = new MazeBuilder(width, height);
            hasEnded = true;
        }

        maze.drawMaze();

        ticks++;

        if (ticks > 250000) {
            ticks = 0;
        }
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        player.draw(g);

        for (int row = 0; row < maze.getCell_array().length; row++) {
            for (int column = 0; column < maze.getCell_array()[row].length; column++) {
                maze.getCell_array()[row][column].draw(g);
            }
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

    public boolean checkIfCanMove(String direction) {

        int x = player.getxCoor();
        int y = player.getyCoor();


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
        return true;

    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    public boolean hasEnded() {
        return hasEnded;
    }
}