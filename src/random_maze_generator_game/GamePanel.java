package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Gamepanel that contains the button panel on top and gamerender panel on the
//bottom. ViewUpdate is parent and button functionality controls how the game
//is managed (restart, pause, back to menu, help). Uses gridbaglayout
public class GamePanel extends JPanel implements ViewPanel, ActionListener {

    private final GameRenderPanel gameRenderPanel;
    private CardLayout cardLayout;
    private ViewUpdate parent;
    private final InterfaceElements interfaceElements;

    public GamePanel(int width, int height) {

        interfaceElements = new InterfaceElements();

        setBackground(interfaceElements.getMainPanelColor());
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(width, height));
        GridBagConstraints c = new GridBagConstraints();

        gameRenderPanel = new GameRenderPanel(400, 400);
        gameRenderPanel.setParent(this);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        add(setUpTopPanel(), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.SOUTH;
        add(gameRenderPanel, c);
    }

    private JPanel setUpTopPanel() {

        JPanel pane = interfaceElements.getBoxJPanel("X");

        //timer has not yet been implemented
        JLabel timer = new JLabel();
        timer.setText("00:00    ");

        JButton pause = interfaceElements.getButton("Pause");
        pause.addActionListener(this);

        JButton restart = interfaceElements.getButton("Restart");
        restart.addActionListener(this);

        JButton menu = interfaceElements.getButton("Menu");
        menu.addActionListener(this);

        JButton help = interfaceElements.getButton("?");
        help.addActionListener(this);

        pane.add(timer);
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

    public void showWinDialog(){
        String text = "You made it!";
        JOptionPane.showMessageDialog(null, text);
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
