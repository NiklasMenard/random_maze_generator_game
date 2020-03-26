package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ViewUpdate extends JPanel {

    private MenuPanel menupanel;
    private LeaderBoardsPanel leaderboardsPanel;
    private GamePanel gamepanel;
    private SettingsPanel settingsPanel;
    private CardLayout cardLayout;
    private List<ViewPanel> panels;

    private int width, height;
    private String settingspressed;


    public ViewUpdate(int width, int height) {

        this.width = width;
        this.height = height;

        panels = new ArrayList<>();
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(140, 136, 136));
        setupPanels();

    }

    private void setupPanels() {

        gamepanel = new GamePanel(width, height);
        menupanel = new MenuPanel(width, height);
        leaderboardsPanel = new LeaderBoardsPanel(width, height);
        settingsPanel = new SettingsPanel(width, height);

        panels.add(menupanel);
        panels.add(gamepanel);
        panels.add(leaderboardsPanel);
        panels.add(settingsPanel);

        for (ViewPanel panel : panels) {
            panel.setParent(this);
            panel.setCardLayout(cardLayout);
            panel.addToCard();
        }
    }

    public void startGame() {
        gamepanel.start();
    }

    //    returns the panel name where settings button was pressed
    //    so it returns to the panel where it was pressed
    public String whereWasSettingsPressed() {
        return settingspressed;
    }

    //    sets the panel name where the settings was pressed
    public void setSettingsPressed(String settingspressed) {
        this.settingspressed = settingspressed;
    }

    public void showHelpDialog(){

        JOptionPane.showMessageDialog(null, "Solve the maze\n\nUse W,A,S,D to move\n");

    }
}