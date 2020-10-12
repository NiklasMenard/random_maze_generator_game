package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Class that controls which panel is currently being shown. Uses cardlayout.
public class ViewUpdate extends JPanel {

    private GamePanel gamepanel;
    private final CardLayout cardLayout;
    private final List<ViewPanel> panels;

    private final int width;
    private final int height;
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
        MenuPanel menupanel = new MenuPanel(width, height);
        LeaderBoardsPanel leaderboardsPanel = new LeaderBoardsPanel(width, height);
        SettingsPanel settingsPanel = new SettingsPanel(width, height);

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

    //function that is called from menupanel to start the game
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

    //shows help dialog when help icon is pressed in any panel
    public void showHelpDialog(){
        String text = "Solve the maze as fast as possible\n\nUse W,A,S,D to move\n";
        JOptionPane.showMessageDialog(null, text);
    }

}