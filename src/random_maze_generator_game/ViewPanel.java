package random_maze_generator_game;

import java.awt.*;

//Interface class for all JPanels inside ViewUpdate card layout.
//ALL viewpanel jpanels set ViewUpdate as their container class
//and set themselves inside the ViewUpdate cardlayout
public interface ViewPanel {

    void setParent(ViewUpdate parent);

    void setCardLayout(CardLayout cardLayout);

    void addToCard();
}
