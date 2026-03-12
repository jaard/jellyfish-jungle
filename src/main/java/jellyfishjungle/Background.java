package jellyfishjungle;

/**
 * Class that creates the ocean background
 * extends Class MovingObjects implements Interface Serializable
 * @author Thore
 *
 */

public class Background extends MovingObjects {

	/**
	 * Constructor for the background
	 * sets the movespeed
	 * loads the background image
	 * @param xVel
	 */
	public Background(double xVel) {
		
		this.xVel = xVel;
		this.loadPicture("/oceanBackground.png");
		//width = sprite.getImage().getWidth(null);
		width = sprite.getImage().getWidth(null);;
		height = sprite.getImage().getHeight(null);
		currentImage = sprite;
		
	}
	
	/**
	 * Setter for the velocity in x direction
	 * @param speed
	 */
	
	public void setSpeed(int speed){
		this.xVel = speed;
	}
}
