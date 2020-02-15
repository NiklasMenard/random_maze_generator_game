package random_maze_generator_game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	private int xCoor, yCoor, width, height;
	private BufferedImage[] player_model = new BufferedImage[2];

	public Player(int xCoor, int yCoor) {

		try {
			player_model[0] = ImageIO.read(getClass().getResource("/models/smile.png"));
			player_model[1] = ImageIO.read(getClass().getResource("/models/end.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = GameFrame.gridscale;
		height = GameFrame.gridscale;

	}

	public void draw(Graphics g, int state) {
		if(state == 0) {
			g.drawImage(player_model[0], xCoor * width, yCoor * height, null, null);
		} else if (state == 1) {
			g.drawImage(player_model[1], xCoor * width, yCoor * height, null, null);
		} 
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
