package random_maze_generator_game;

import javax.swing.JFrame;

public class GameFrame {
	
	private JFrame frame;
	private RenderPanel renderpanel;

	public GameFrame() {

		frame = new JFrame();
		renderpanel = new RenderPanel(400, 400);


	}
	
	public void start() {
		
		frame.add(renderpanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Maze");
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
