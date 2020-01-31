package random_maze_generator_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel implements KeyListener, Runnable {

	private int width, height;

	private int sxCoor = 0, syCoor = 0;

	private Thread thread;
	private boolean running = false;

	private Player player;
	private MazeBuilder maze;

	private int ticks = 0;

	public RenderPanel(int width, int height) {

		this.width = width;
		this.height = height;

		setFocusable(true);
		addKeyListener(this);
		setPreferredSize(new Dimension(width, height));

		maze = new MazeBuilder(width, height);

		start();

	}

	public void tick() {

		player = new Player(sxCoor, syCoor);
		maze.drawMaze();

		if (player.getxCoor() == maze.getEnd().getxCoor() / 40 && player.getyCoor() == maze.getEnd().getyCoor() / 40) {
			stop();
		}

		ticks++;

		if (ticks > 200000) {

		}
	}

	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		player.draw(g);

		for (int row = 0; row < maze.getCell_array().length; row++) {
			for (int column = 0; column < maze.getCell_array()[row].length; column++) {
				maze.getCell_array()[row][column].draw(g);
			}
		}
	}

	public void run() {
		while (running) {
			tick();
			repaint();
		}
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
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
			if (maze.getCell_array()[x][y].isBottom_wall()) {
				return false;
			}
		}
		return true;

	}
}
