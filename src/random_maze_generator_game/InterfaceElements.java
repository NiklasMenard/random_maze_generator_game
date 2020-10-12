package random_maze_generator_game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Class that has several GUI elements that different classes can
//use to get similar elements
public class InterfaceElements {

    public JButton getButton(String buttonName) {

        JButton button = new JButton(buttonName);
        button.setBackground(new Color(25, 209, 203));

        return button;

    }

    public Color getMainPanelColor() {

        return new Color(140, 136, 136);

    }

    public JPanel getBoxJPanel(String axis) {

        JPanel pane = new JPanel();
        pane.setBackground(new Color(140, 136, 136));

        if (axis.equals("X")) {
            pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        } else if (axis.equals("Y")) {
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        }

        return pane;
    }

    public JPanel getIcons(ActionListener al) {

        JPanel iconpanel = getBoxJPanel("X");
        List<JButton> icons = new ArrayList<>();

        JButton help = new JButton();
        JButton settings = new JButton();
        help.setActionCommand("Help");
        settings.setActionCommand("Settings");

        help.addActionListener(al);
        settings.addActionListener(al);

        icons.add(help);
        icons.add(settings);

        for (JButton component : icons) {
            component.setBackground(getMainPanelColor());
            component.setBorderPainted(false);
            component.setPreferredSize(new Dimension(40, 40));
            iconpanel.add(component);
        }

        try {
            Image qimg = ImageIO.read(getClass().getResource("/models/question.png"));
            Image cogimg = ImageIO.read(getClass().getResource("/models/settings.png"));
            settings.setIcon(new ImageIcon(cogimg));
            help.setIcon(new ImageIcon(qimg));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return iconpanel;
    }

    public BufferedImage getPlayerModel() {

        BufferedImage player_model = null;

        try {
            player_model = ImageIO.read(getClass().getResource("/models/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return player_model;
    }
}