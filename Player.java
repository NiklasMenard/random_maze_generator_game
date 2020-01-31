package random_maze_generator_game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	private int xCoor, yCoor, width, height;
	private int gridscale = 40;

	public Player(int xCoor, int yCoor) {

		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = gridscale;
		height = gridscale;

	}

	public void draw(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}
	
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

}
