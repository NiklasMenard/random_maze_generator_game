package random_maze_generator_game;

import java.awt.*;

public interface ViewPanel {

    void setParent(ViewUpdate parent);

    void setCardLayout(CardLayout cardLayout);

    void addToCard();
}
