package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, Runnable {

    private int width, height;
    private int sxCoor = 0, syCoor = 0;

    private Thread thread;
    private boolean running = false;

    private Player player;
    private MazeBuilder maze;
    private CardLayout cardLayout;
    private ViewUpdate parent;
    private MenuPanel menupanel;

    private int ticks;
    private int end_coord_x;
    private int end_coord_y;

    public GamePanel(int width, int height) {


        this.width = width;
        this.height = height;

        ticks = 0;

        addKeyListener(this);
        setPreferredSize(new Dimension(width, height));

        maze = new MazeBuilder(width, height);
        player = new Player(0, 0);
        end_coord_x = maze.getEnd().getxCoor() / GameFrame.gridscale;
        end_coord_y = maze.getEnd().getyCoor() / GameFrame.gridscale;

    }

    public void tick() throws IOException {

        player = new Player(sxCoor, syCoor);
        maze.drawMaze();

        if (sxCoor == end_coord_x && syCoor == end_coord_y) {
            sxCoor = 0;
            syCoor = 0;
            cardLayout.show(parent, "MENUPANEL");
            menupanel.requestFocusInWindow();
            resetGame();
        }


        ticks++;

        if (ticks > 250000) {
            ticks = 0;
        }

    }

    public void resetGame() {
        this.player = new Player(0, 0);
        this.maze = new MazeBuilder(width, height);
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

    public void setParent(ViewUpdate parent) {
        this.parent = parent;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public void setMenuPanel(MenuPanel MenuPanel) {
        this.menupanel = MenuPanel;
    }


}