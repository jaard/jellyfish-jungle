package oceanGame;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Background extends MovingObjects implements  Serializable {

	// Constructor for the background
	public Background(double xVel) {
		
		this.xVel = xVel;
		this.loadPicture("/resources/oceanBackground.png");
		//width = sprite.getImage().getWidth(null);
		width = sprite.getImage().getWidth(null);;
		height = sprite.getImage().getHeight(null);
		currentImage = sprite;
		
	}
	
	public void setSpeed(int speed){
		this.xVel = speed;
	}
}
