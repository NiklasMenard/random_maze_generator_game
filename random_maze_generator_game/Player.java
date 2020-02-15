package random_maze_generator_game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	private int xCoor, yCoor, width, height;
	private BufferedImage player_model;

	public Player(int xCoor, int yCoor) {

		try {
			player_model = ImageIO.read(getClass().getResource("/models/smile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = GameFrame.gridscale;
		height = GameFrame.gridscale;

	}

	public void draw(Graphics g) {

		g.drawImage(player_model, xCoor * width, yCoor * height, null, null);
	}

	public int getxCoor() {
		return xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

}
