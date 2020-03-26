package random_maze_generator_game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private static int gridscale = 40;
    private int xCoor, yCoor;
    private BufferedImage player_model;
    private InterfaceElements interfaceElements;


    public Player(int xCoor, int yCoor) {

        gridscale = 40;

        interfaceElements = new InterfaceElements();
        player_model = interfaceElements.getPlayerModel();

        this.xCoor = xCoor;
        this.yCoor = yCoor;

    }

    public void draw(Graphics g) {

        g.drawImage(player_model, xCoor * gridscale, yCoor * gridscale, null, null);
    }

    public int getxCoor() {
        return xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

}
