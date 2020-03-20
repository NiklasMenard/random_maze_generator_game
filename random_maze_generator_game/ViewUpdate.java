package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;


public class ViewUpdate extends JPanel  {

    private MenuPanel menupanel;
    private LeaderboardsPanel leaderboardsPanel;
    private GamePanel gamepanel;
    private CardLayout cardLayout;


    public ViewUpdate(int width, int height) {

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(140, 136, 136));

        gamepanel = new GamePanel(width, height);
        menupanel = new MenuPanel(width, height);
        leaderboardsPanel = new LeaderboardsPanel(width, height);

        menupanel.setParent(this);
        menupanel.setCardLayout(cardLayout);
        menupanel.setGamepanel(gamepanel);

        gamepanel.setParent(this);
        gamepanel.setCardLayout(cardLayout);
        gamepanel.setMenuPanel(menupanel);

        add(menupanel, "MENUPANEL");
        add(gamepanel, "GAMEPANEL");
        add(leaderboardsPanel, "LEADERBOARDS");

    }
}