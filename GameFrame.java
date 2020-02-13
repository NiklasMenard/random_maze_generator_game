package random_maze_generator_game;

import javax.swing.JFrame;

public class GameFrame {

	public static final int gridscale = 50;

	private JFrame frame;
	private RenderPanel renderpanel;

	public GameFrame() {

		frame = new JFrame();
		renderpanel = new RenderPanel(gridscale * 10, gridscale * 10);

	}

	public void start() {

		frame.add(renderpanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("The Maze");
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
