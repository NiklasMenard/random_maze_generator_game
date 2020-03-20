package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;

public class LeaderboardsPanel extends JPanel {

    private InterfaceButtons interfacebuttons;

    public LeaderboardsPanel(int width, int height){

        interfacebuttons = new InterfaceButtons();
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(140, 136, 136));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        add(interfacebuttons.createIcons(), c);

    }


}
