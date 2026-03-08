package oceanGame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

public class OceanGame {
	
	JFrame game;

	public OceanGame(){
		
		
		game = new JFrame();
		Ocean ocean = new Ocean();
		game.add(ocean);
		game.setLocation(250,150);
		centreWindow(game);
		game.setResizable(false);
		game.pack();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
		
		
	}
	
	// Method to put the game window in the middle of the screen
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 800) / 2);
	    int y = (int) ((dimension.getHeight() - 600) / 2);
	    frame.setLocation(x, y);
	}
	
//	public JLabel createLoadScreen() {
//        JLabel loadScreen = new javax.swing.JLabel();
//        loadScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ghost_left.png")));
//        return loadScreen;
//    }
	
	public static void main(String[] args) {
		
		new OceanGame();
		
	}

}
