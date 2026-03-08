package oceanGame;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Class that defines an enemy in the ocean game 
 * extends from Class MovingObjects and implements interface Serializable
 * 
 * @author Thore
 * 
 */

public class Enemy extends MovingObjects implements Serializable {

	/**
	 * booleans which define the Enemy: edible, animated and collidable
	 */
	private boolean edible, animated, collidable;
	/**
	 * Animation Class for the moving Animation of the enemy
	 */
	private Animation moveAnimation;

	/**
	 * Constructor for the Enemy
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param xVel
	 */
	public Enemy(int type, int x, int y, double xVel) {

		this.xVel = xVel;

		if (type == 1) {
			this.loadPicture("/resources/ghost_left.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = true;
			animated = false;
		} else if (type == 2) {
			this.loadPicture("/resources/ghost_orange.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = false;
			animated = false;
		}
		this.x = x;
		this.y = y;
		currentImage = sprite;
		if (isAnimated()) {
			loadMoveAnimation(xVel);
		}

	}

	/**
	 * Loads the the animation for the enemys movement
	 * 
	 * @param xVel
	 */
	public void loadMoveAnimation(double xVel) {

		// long duration1 = (long) Math.round(0.5*10000/xVel);
		// long duration2 = (long) Math.round(5*10000/xVel);
		moveAnimation = new Animation();
		moveAnimation.addScene(
				new ImageIcon(getClass().getResource(
						"/resources/ghost_orange.png")), 140);
		moveAnimation.addScene(
				new ImageIcon(getClass().getResource(
						"/resources/ghost_left.png")), 1300);
	}

	/**
	 * Method to find out if the enemy is edible
	 * 
	 * @return edible
	 */
	public boolean isEdible() {
		return edible;
	}

	/**
	 * Method to find out if the enemy is animated
	 * 
	 * @return animated
	 */

	public boolean isAnimated() {
		return animated;
	}
	
	/**
	 * Getter for the Animation
	 * @return moveAnimation
	 */

	public Animation getAnimation() {
		return moveAnimation;
	}
	/**
	 * Method to update the current image for an Animation
	 */

	public void updateCurrentImage() {
		currentImage = moveAnimation.getImage();
	}
	
	/**
	 * Getter for the Velocity in x-direction
	 * @return xVel
	 */

	public double getXVel() {
		return xVel;
	}
	/**
	 * Setter for the Velocity in x-direction
	 * @param xVel
	 */

	public void setXVel(double xVel) {
		this.xVel = xVel;
	}
	
	/**
	 * Getter for boolean collidable
	 * @return collidable
	 */

	public boolean isCollidable() {
		return collidable;
	}
	/**
	 * Setter for boolean collidable
	 * @param collidable
	 */

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}
}
