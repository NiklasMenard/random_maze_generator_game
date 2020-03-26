package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ViewPanel, ActionListener {

    private GameRenderPanel gameRenderPanel;
    private CardLayout cardLayout;
    private ViewUpdate parent;
    private InterfaceElements interfaceElements;

    public GamePanel(int width, int height) {

        interfaceElements = new InterfaceElements();

        setBackground(interfaceElements.getMainPanelColor());
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(width, height));
        GridBagConstraints c = new GridBagConstraints();

        gameRenderPanel = new GameRenderPanel(400, 400);
        gameRenderPanel.setParent(this);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.VERTICAL;
        add(setUpTopPanel(), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(gameRenderPanel, c);


    }

    private JPanel setUpTopPanel() {

        JPanel pane = interfaceElements.getBoxJPanel("X");

        JButton pause = interfaceElements.getButton("Pause");
        pause.addActionListener(this);

        JButton restart = interfaceElements.getButton("Restart");
        restart.addActionListener(this);

        JButton menu = interfaceElements.getButton("Menu");
        menu.addActionListener(this);

        JButton help = interfaceElements.getButton("?");
        help.addActionListener(this);

        pane.add(pause);
        pane.add(restart);
        pane.add(menu);
        pane.add(help);

        return pane;
    }

    public void start() {
        gameRenderPanel.start();
        gameRenderPanel.requestFocusInWindow();
    }

    public void returnToMenu() {
        cardLayout.show(parent, "MenuPanel");
    }

    public void actionPerformed(ActionEvent e) {

        String pressed = e.getActionCommand();

        if (pressed.equals("Menu")) {
            returnToMenu();
            gameRenderPanel.resetGame();
        }

        if (pressed.equals("Pause")) {
            if(!gameRenderPanel.isPaused()){
                gameRenderPanel.setPaused(true);
            } else {
                gameRenderPanel.setPaused(false);
                gameRenderPanel.requestFocusInWindow();
            }
        }

        if (pressed.equals("Restart")) {
            gameRenderPanel.resetGame();
            gameRenderPanel.start();
            gameRenderPanel.requestFocusInWindow();
        }

        if (pressed.equals("?")) {
            parent.showHelpDialog();
            gameRenderPanel.requestFocusInWindow();
        }
    }

    public void setParent(ViewUpdate parent) {
        this.parent = parent;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public void addToCard() {
        parent.add(this, this.getClass().getSimpleName());
    }
}
