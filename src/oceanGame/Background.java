package oceanGame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Background extends MovingObjects {

	// Constructor for the background
	public Background(double xVel) {
		
		this.xVel = xVel;
		this.loadPicture("/resources/betaHintergrund.png");
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
		
	}
	
	public void setSpeed(int speed){
		this.xVel = speed;
	}
}
