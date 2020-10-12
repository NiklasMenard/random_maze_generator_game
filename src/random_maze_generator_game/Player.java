package random_maze_generator_game;

import java.awt.*;
import java.awt.image.BufferedImage;

//Player class that holds the coordinates of the player model and contains the
//player image.
public class Player {

    private int xCoor, yCoor;
    private final BufferedImage player_model;


    public Player(int xCoor, int yCoor) {

        InterfaceElements interfaceElements = new InterfaceElements();
        player_model = interfaceElements.getPlayerModel();

        this.xCoor = xCoor;
        this.yCoor = yCoor;

    }

    public void draw(Graphics g) {

        int gridscale = 40;
        g.drawImage(player_model, xCoor * gridscale, yCoor * gridscale, null, null);
    }

    public int getxCoor() {
        return xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

}
