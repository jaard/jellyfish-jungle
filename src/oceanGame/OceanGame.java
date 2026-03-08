package oceanGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OceanGame {
	
	JFrame f;

	public OceanGame(){
		
		f = new JFrame();
		Ocean ocean = new Ocean();
		f.add(ocean);
		f.setLocation(250,150);
		f.setVisible(true);
		f.setResizable(false);
		f.setBackground(Color.BLUE);
		//f.setLayout(new GridLayout(1,1));
		f.setSize(800,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public static void main(String[] args) {
		
		new OceanGame();
		
	}

}
