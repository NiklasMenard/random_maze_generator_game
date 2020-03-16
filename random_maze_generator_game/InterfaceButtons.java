package random_maze_generator_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InterfaceButtons {

	private JButton startgame;

	public JButton getMenuButton(String buttonName) {

		JButton button = new JButton(buttonName);
		button.setBackground(new Color(25, 209, 203));

		return button;

	}

	public JPanel createIcons() {

		List<JButton> components = new ArrayList<>();

		JButton help = new JButton();
		JButton cog = new JButton();

		components.add(help);
		components.add(cog);

		for (JButton component : components) {
			component.setBackground(new Color(140, 136, 136));
			component.setBorderPainted(false);
			component.setPreferredSize(new Dimension(40, 40));
		}

		try {
			Image qimg = ImageIO.read(getClass().getResource("/models/question.png"));
			Image cogimg = ImageIO.read(getClass().getResource("/models/settings.png"));
			cog.setIcon(new ImageIcon(cogimg));
			help.setIcon(new ImageIcon(qimg));

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		JPanel icons = new JPanel();
		icons.setLayout(new BoxLayout(icons, BoxLayout.X_AXIS));
		icons.add(help);
		icons.add(cog);

		return icons;
	}
}
