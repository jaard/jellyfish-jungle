package jellyfishjungle;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

/**
 * Class that starts the Ocean Game including public static void main
 * 
 * @author Thore
 * 
 */
public class JellyfishJungle {
	/**
	 * Frame in which the game runs
	 */
	JFrame game;

	/**
	 * Constructor for JellyfishJungle 
	 * creates the Frame and the Ocean
	 */

	public JellyfishJungle() {

		game = new JFrame();
		Ocean ocean = new Ocean();
		game.add(ocean);
		game.setLocation(250, 150);
		centreWindow(game);
		game.setResizable(false);
		game.pack();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);

	}

	/**
	 * Method to put the game window in the middle of the screen
	 * 
	 * @param frame
	 */
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 800) / 2);
		int y = (int) ((dimension.getHeight() - 600) / 2);
		frame.setLocation(x, y);
	}

	/**
	 * Main creates a new JellyfishJungle
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		new JellyfishJungle();

	}

}
