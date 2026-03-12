package jellyfishjungle;

import javax.swing.ImageIcon;

/**
 * Class that defines an enemy in the ocean game 
 * extends from Class MovingObjects and implements interface Serializable
 * 
 * @author Thore
 * 
 */

public class Enemy extends MovingObjects {

	/**
	 * booleans which define the Enemy: edible and collidable
	 */
	private boolean edible, collidable;
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
			this.loadPicture("/jelly_blue_small.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = true;
		} else if (type == 2) {
			this.loadPicture("/jelly_orange_small.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = false;
		}
		this.x = x;
		this.y = y;
		currentImage = sprite;
		loadMoveAnimation(xVel, type);

	}

	/**
	 * Loads the the animation for the enemys movement according to its type
	 * 
	 * @param xVel
	 */
	public void loadMoveAnimation(double xVel, int type) {
		
		if(type == 1){
			long duration0 = (long) (Math.random()*500);
			long duration1 = (long) Math.round(1.2*100000/xVel);
			moveAnimation = new Animation();
			moveAnimation.addScene(
					new ImageIcon(getClass().getResource(
							"/jelly_blue_small.png")), duration0);
			moveAnimation.addScene(
					new ImageIcon(getClass().getResource(
							"/jelly_blue_large.png")), duration1);
			moveAnimation.addScene(
					new ImageIcon(getClass().getResource(
							"/jelly_blue_small.png")), duration1);
		}else if(type == 2){
			long duration0 = (long) (Math.random()*500);
			long duration1 = (long) Math.round(1.2*100000/xVel);
			moveAnimation = new Animation();
			moveAnimation.addScene(
					new ImageIcon(getClass().getResource(
							"/jelly_orange_small.png")), duration0);
			moveAnimation.addScene(
					new ImageIcon(getClass().getResource(
							"/jelly_orange_large.png")), duration1);
			moveAnimation.addScene(
					new ImageIcon(getClass().getResource(
							"/jelly_orange_small.png")), duration1);
		}
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
	 * @return always true, all enemies are animated
	 */

	public boolean isAnimated() {
		return true;
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
