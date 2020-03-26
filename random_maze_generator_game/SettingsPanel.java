package random_maze_generator_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SettingsPanel extends JPanel implements ActionListener, ViewPanel {

    private InterfaceElements interfaceElements;
    private CardLayout cardLayout;
    private ViewUpdate parent;

    public SettingsPanel(int width, int height) {

        interfaceElements = new InterfaceElements();
        setPreferredSize(new Dimension(width, height));
        setBackground(interfaceElements.getMainPanelColor());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(setTopPanel(), c);

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 100;
        c.anchor = GridBagConstraints.NORTH;
        add(setBottomPanel(), c);

        JButton back = interfaceElements.getButton("Back");
        back.addActionListener(this);
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(back, c);

    }

    private JPanel setTopPanel() {

        JPanel pane = interfaceElements.getBoxJPanel("Y");

        JLabel nametitle = new JLabel();
        nametitle.setFont(new Font("Apple Casual", Font.BOLD, 24));
        nametitle.setText("Change name here: ");

        TextField textField = new TextField();
        textField.setText("Max 10 characters");

        JLabel playertitle = new JLabel();
        playertitle.setFont(new Font("Apple Casual", Font.BOLD, 24));
        playertitle.setText("Change player model");

        pane.add(nametitle);
        pane.add(Box.createRigidArea(new Dimension(0, 20)));
        pane.add(textField);
        pane.add(Box.createRigidArea(new Dimension(0, 40)));
        pane.add(playertitle);

        return pane;
    }

    private JPanel setBottomPanel() {

        JPanel pane = interfaceElements.getBoxJPanel("X");
        BufferedImage player_model = interfaceElements.getPlayerModel();
        JLabel picLabel = new JLabel(new ImageIcon(player_model));
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] playerModels = {"Duck", "Cat", "Dog", "Rabbit", "Pig"};
        JComboBox<String> playerDropDown = new JComboBox<>(playerModels);

        pane.add(picLabel);
        pane.add(Box.createRigidArea(new Dimension(40, 0)));
        pane.add(playerDropDown);

        return pane;

    }

    public void actionPerformed(ActionEvent e) {

        String pressed = e.getActionCommand();

        if (pressed.equals("Back")) {
            cardLayout.show(parent, parent.whereWasSettingsPressed());
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
