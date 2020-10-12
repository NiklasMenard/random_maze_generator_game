package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class MenuPanel extends JPanel implements ActionListener, ViewPanel {

    private final InterfaceElements interfaceElements;
    private CardLayout cardLayout;
    private ViewUpdate parent;

    public MenuPanel(int width, int height) {

        interfaceElements = new InterfaceElements();
        setPreferredSize(new Dimension(width, height));
        setBackground(interfaceElements.getMainPanelColor());
        setFocusable(true);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        add(interfaceElements.getIcons(this), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(createMenuPanel(), c);

    }


    private JPanel createMenuPanel() {

        List<JComponent> components = new ArrayList<>();

        JPanel buttons = interfaceElements.getBoxJPanel("Y");

        JLabel gametitle = new JLabel();
        gametitle.setFont(new Font("Apple Casual", Font.BOLD, 32));
        gametitle.setText("The Maze");

        JButton startgame = interfaceElements.getButton("Start Game");
        JButton leaderboard = interfaceElements.getButton("Leaderboard");
        JButton exit = interfaceElements.getButton("Exit");

        startgame.addActionListener(this);
        leaderboard.addActionListener(this);
        exit.addActionListener(this);

        components.add(gametitle);
        components.add(startgame);
        components.add(leaderboard);
        components.add(exit);

        Dimension d = leaderboard.getMaximumSize();
        for (JComponent component : components) {
            if (component == components.get(0)) {
                //setting title
                buttons.add(component);
                component.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttons.add(Box.createRigidArea(new Dimension(0, 60)));
            } else {
                //setting buttons
                buttons.add(component);
                component.setAlignmentX(Component.CENTER_ALIGNMENT);
                component.setMaximumSize(new Dimension(d));
                buttons.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }

        return buttons;
    }


    public void actionPerformed(ActionEvent e) {

        String pressed = e.getActionCommand();

        if (pressed.equals("Start Game")) {
            cardLayout.show(parent, "GamePanel");
            parent.startGame();
        }

        if (pressed.equals("Leaderboard")) {
            cardLayout.show(parent, "LeaderBoardsPanel");
        }

        if (pressed.equals("Settings")) {
            cardLayout.show(parent, "SettingsPanel");
            parent.setSettingsPressed(getClass().getSimpleName());
        }

        if (pressed.equals("Help")) {
            parent.showHelpDialog();
        }

        if (pressed.equals("Exit")) {
            System.exit(0);
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