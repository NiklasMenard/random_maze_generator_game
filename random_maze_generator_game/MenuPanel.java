package random_maze_generator_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuPanel extends JPanel implements ActionListener {

    private InterfaceButtons interfacebuttons;
    private JLabel gametitle;
    private JButton startgame;
    private JButton settings;
    private JButton exit;
    private CardLayout cardLayout;
    private ViewUpdate parent;
    private GamePanel gamepanel;

    public MenuPanel(int width, int height) {

        interfacebuttons = new InterfaceButtons();
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(140, 136, 136));
        setFocusable(true);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        add(interfacebuttons.createIcons(), c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(createMenuPanel(), c);

    }

    private JPanel createMenuPanel() {

        List<JComponent> components = new ArrayList<>();

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBackground(new Color(140, 136, 136));

        gametitle = new JLabel();
        gametitle.setFont(new Font("Apple Casual", Font.BOLD, 32));
        gametitle.setText("The Maze");


        startgame = interfacebuttons.getMenuButton("Startgame");
        settings = interfacebuttons.getMenuButton("Settings");
        exit = interfacebuttons.getMenuButton("Exit");

        startgame.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);

        components.add(gametitle);
        components.add(startgame);
        components.add(settings);
        components.add(exit);

        Dimension d = startgame.getMaximumSize();
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

        if (e.getSource() == startgame) {
            cardLayout.show(parent, "GAMEPANEL");
            gamepanel.requestFocusInWindow();
            gamepanel.start();
            System.out.print("Start game pressed");

        } else if (e.getSource() == settings) {

        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    public void setParent(ViewUpdate parent){
        this.parent = parent;
    }

    public void setCardLayout (CardLayout cardLayout){
        this.cardLayout = cardLayout;
    }

    public void setGamepanel (GamePanel GamePanel){
        this.gamepanel = GamePanel;
    }

}