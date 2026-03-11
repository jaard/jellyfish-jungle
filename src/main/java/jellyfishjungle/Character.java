package jellyfishjungle;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Class which creates the main character
 * extends class MovingObjects and implements interface Serializable
 * @author Jaard, Thore
 *
 */
public class Character extends MovingObjects implements Serializable {

	/**
	 * Booleans which controls if the Character is existing, eating, blinking
	 */
	private boolean exists = true;
	private boolean eats = false;
	private boolean blinks = false;
	
	/**
	 * The movement speed and life
	 * speed of the Character is in pixels per second
	 */
	private int speed = 360;
	private int life = 3;
	
	/**
	 * The animations for eating and blinking
	 */
	private Animation eating;
	private Animation blinking;

	/**
	 * Constructor of the Character Class
	 * loads the image
	 * sets height and width
	 */
	
	public Character() {
		
		loadPicture("/seal_swimming.png");
		setPosition(60, 200);
		width = sprite.getImage().getWidth(null);
		height = sprite.getImage().getHeight(null);
		currentImage = sprite;
		loadAnimations();
		
	}
	
	/**
	 * Loads the animations
	 */

	public void loadAnimations() {

		eating = new Animation();
		eating.addScene(
				new ImageIcon(getClass().getResource(
						"/seal_eating.png")), 140);
		eating.addScene(sprite, 1000000000);

		ImageIcon sealRed = new ImageIcon(getClass().getResource(
				"/seal_red.png"));

		blinking = new Animation();
		blinking.addScene(sealRed, 100);
		blinking.addScene(sprite, 100);
		blinking.addScene(sealRed, 100);
		blinking.addScene(sprite, 100);
		blinking.addScene(sealRed, 100);
		blinking.addScene(sprite, 1000000000);

	}
	
	/**
	 * Updates the animation according to fps
	 * @param fps
	 */

	public void animationUpdate(int fps) {

		if (alive()) {

			if (eats == true) {
				eating.update(1000 / fps);
				currentImage = eating.getImage();
				if (eating.getNumberOfScenes() == eating.getSceneIndex() + 1) {
					eats = false;
				}
			}
			if (blinks == true) {
				blinking.update(1000 / fps);
				currentImage = blinking.getImage();
				if (blinking.getNumberOfScenes() == blinking.getSceneIndex() + 1) {
					blinks = false;
					
				}
			}
		} else {

			currentImage = new ImageIcon(getClass().getResource(
					"/seal_dead.png"));

		}

	}
	
	/**
	 * Method that checks if the Character MovingObject is intersecting with another
	 * Overwrites the method in MovingObjects and uses a Polygon instead of a Rectangle
	 * needed for collision detection
	 * @param other
	 * @return boolean if the objects are intersecting
	 */
	
	public boolean intersects(MovingObjects other) {
		Polygon p1 = getPolygon();
		Rectangle r2 = other.getRectangle();
		return p1.intersects(r2);
	}
	
	/**
	 * Method to create a Polygon for the Character that approximates its outline
	 * @return Polygon
	 */
	public Polygon getPolygon(){
		
		int x = this.x;
		int y = this.y;
		int[] xPoints = {x + 90,x + 164,x + 90,x + 0};
		int[] yPoints = {y + 0,y + 60,y + 120,y + 90};
		
		return new Polygon(xPoints,yPoints,4);
	}
	
	
	/**
	 * Method for the Character to eat enemies
	 * starts an animation
	 */

	public void eats() {
		eats = true;
		eating.start();
	}
	/**
	 * Method for the Character to blink
	 * starts an animation
	 */

	public void blinks() {
		blinks = true;
		blinking.start();
	}
	
	/**
	 * Method for the Character to die
	 */

	public void dies() {
		exists = false;
		
		yVel = -180;
		xVel = 0;

	}
	/**
	 * Method to check if the Character is alive
	 * @return true for alive false for dead
	 */

	public boolean alive() {
		if (exists == true) {
			return true;
		} else {
			return false;
		}
	}
//	/**
//	 * Method to update position of the Character
//	 */
//
//	public void positionUpdate() {
//
//		if (x < 0) {
//			x = 0;
//		}
//		if (y < 0) {
//			y = 0;
//		}
//		if (y + (height) > 600 & exists == true) {
//			y = 600 - (height);
//		}
//
//		else {
//			this.x += dx;
//			this.y += dy;
//		}
//
//	}
//	
	/**
	 * Method to move the Character according to its velocity but keep it
	 * inside the boundaries of the screen
	 * @param fps
	 */
	public void move(int fps) {
		
		restX = restX + ((xVel / fps) % 1);
		x -= (int) (xVel / fps);
		x -= (int) restX;
		restX = restX % 1;
		
		restY = restY + ((yVel / fps) % 1);
		y -= (int) (yVel / fps);
		y -= (int) restY;
		restY = restY % 1;
		
		if(x < 0){
			x = 0;
		}else if(x + getWidth() > 800){
			x = 800 - getWidth();
		}else if(y < 0){
			y = 0;
		}else if(y + getHeight() > 600){
			y = 600 - getHeight();
		}

	}
	
	/**
	 * Getter for speed
	 * @return speed
	 */

	public int getSpeed() {
		return speed;
	}
	/**
	 * Getter for life
	 * @return life
	 */

	public int getLife() {
		return life;
	}
	
	/**
	 * Setter for life
	 * @param l
	 */

	public void setLife(int l) {
		life = l;
	}
	
	/**
	 * Method to decrease life by one
	 */

	public void decLife() {
		life -= 1;
	}
	
	/**
	 * Setter for exists
	 * @param e
	 */

	public void setExists(boolean e) {
		exists = e;
	}

}
