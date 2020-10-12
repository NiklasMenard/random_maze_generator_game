package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


//Leaderboard panel that shows the current leading scores. Scoretable functionality
//hasnt been added yet. Uses gridbag layout.
public class LeaderBoardsPanel extends JPanel implements ActionListener, ViewPanel {

    private final InterfaceElements interfaceElements;
    private CardLayout cardLayout;
    private ViewUpdate parent;


    public LeaderBoardsPanel(int width, int height) {

        interfaceElements = new InterfaceElements();
        setPreferredSize(new Dimension(width, height));
        setBackground(interfaceElements.getMainPanelColor());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 3;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        add(setTitle(), c);

        c.gridx = 3;
        c.gridy = 3;
        c.weightx = 0;
        c.weighty = 1;
        c.ipady = 200;
        c.insets = new Insets(0, 0, 60, 40);
        c.anchor = GridBagConstraints.SOUTHEAST;
        add(setLeaderPane(), c);

        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 40, 60, 0);
        add(setLeftPane(), c);


    }

    private JPanel setLeaderPane() {

        JPanel pane = interfaceElements.getBoxJPanel("Y");

        JTextArea textArea = new JTextArea();
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel topten = new JLabel();
        topten.setFont(new Font("Apple Casual", Font.BOLD, 24));
        topten.setText("Top 10 Scores");
        topten.setAlignmentX(Component.CENTER_ALIGNMENT);

        pane.add(topten);
        pane.add(scrollableTextArea);

        return pane;
    }

    private JPanel setTitle() {

        JPanel pane = interfaceElements.getBoxJPanel("X");

        JLabel title = new JLabel();
        title.setFont(new Font("Apple Casual", Font.BOLD, 24));
        title.setText("Leaderboards");

        JPanel icons = interfaceElements.getIcons(this);

        pane.add(title);
        pane.add(Box.createRigidArea(new Dimension(25, 0)));
        pane.add(icons);

        return pane;
    }

    private JPanel setLeftPane() {

        JPanel pane = interfaceElements.getBoxJPanel("Y");

        BufferedImage player_model = interfaceElements.getPlayerModel();
        JLabel picLabel = new JLabel(new ImageIcon(player_model));
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton back = interfaceElements.getButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(this);

        pane.add(picLabel);
        pane.add(Box.createRigidArea(new Dimension(0, 180)));
        pane.add(back);

        return pane;
    }

    public void actionPerformed(ActionEvent e) {

        String pressed = e.getActionCommand();

        if (pressed.equals("Back")) {
            cardLayout.show(parent, "MenuPanel");
        }

        if (pressed.equals("Settings")) {
            cardLayout.show(parent, "SettingsPanel");
            parent.setSettingsPressed(getClass().getSimpleName());
        }
        if (pressed.equals("Help")) {
            parent.showHelpDialog();
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

