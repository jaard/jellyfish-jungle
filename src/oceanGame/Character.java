package oceanGame;

import java.io.Serializable;

import javax.imageio.ImageIO;
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
	 */
	private int speed = 6;
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

		this.loadPicture("/resources/seal_swimming.png");
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
						"/resources/seal_eating.png")), 140);
		eating.addScene(sprite, 1000000000);

		ImageIcon sealRed = new ImageIcon(getClass().getResource(
				"/resources/seal_red.png"));

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
					"/resources/seal_dead.png"));

		}

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
		
		this.dy = 3;
		this.dx = 0;

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
	/**
	 * Method to update position of the Character
	 */

	public void positionUpdate() {

		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (y + (height) > 600 & exists == true) {
			y = 600 - (height);
		}

		else {
			this.x += dx;
			this.y += dy;
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
