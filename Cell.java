package random_maze_generator_game;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {

	private int xCoor, yCoor, width, height;

	private boolean top_wall = true;
	private boolean right_wall = true;
	private boolean bottom_wall = true;
	private boolean left_wall = true;


	private boolean end = false;

	private int gridscale = 40;

	private boolean visited;

	public Cell(int xCoor, int yCoor) {

		this.xCoor = xCoor * gridscale;
		this.yCoor = yCoor * gridscale;
		width = gridscale;
		height = gridscale;
		visited = false;

	}

	public void draw(Graphics g) {

		if (end) {
			g.setColor(Color.BLUE);
			g.fillRect(xCoor, yCoor, width, height);
		}

		g.setColor(Color.BLACK);

		if (top_wall) {
			g.drawLine(xCoor, yCoor, xCoor + width, yCoor); // top line
		}

		if (right_wall) {
			g.drawLine(xCoor + width, yCoor, xCoor + width, yCoor + height); // right line
		}

		if (bottom_wall) {
			g.drawLine(xCoor + width, yCoor + height, xCoor, yCoor + height); // bottom line
		}

		if (left_wall) {
			g.drawLine(xCoor, yCoor, xCoor, yCoor + height); // left line
		}
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getxCoor() {
		return xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	// set walls
	public void setTop_wall(boolean top_wall) {
		this.top_wall = top_wall;
	}

	public void setRight_wall(boolean right_wall) {
		this.right_wall = right_wall;
	}

	public void setBottom_wall(boolean bottom_wall) {
		this.bottom_wall = bottom_wall;
	}

	public void setLeft_wall(boolean left_wall) {
		this.left_wall = left_wall;
	}

	// get walls
	public boolean isTop_wall() {
		return top_wall;
	}

	public boolean isRight_wall() {
		return right_wall;
	}

	public boolean isBottom_wall() {
		return bottom_wall;
	}

	public boolean isLeft_wall() {
		return left_wall;
	}

	// set as ending cell
	public void setEnd(boolean end) {
		this.end = end;
	}

}
