package jellyfishjungle;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Abstract Class for every moving object in the game
 * implements the Interface Serializable
 * @author Thore
 *
 */
public abstract class MovingObjects implements Serializable{

	/**
	 * Integers of the MovingObject class
	 * the x & y - position, the delta - x & y, width and height of the object
	 */
	
	protected int x, y, dy, dx, width, height;
	
	/**
	 * Image of the object
	 * sprite is the default image
	 * currentImage is the current image
	 */
	
	protected ImageIcon sprite, currentImage;
	
	/**
	 * Velocity in x & y - direction in pixels per second
	 */
	
	protected double xVel, yVel;
	
	/**
	 * Part of the velocity after the decimal point which can't be displayed in pixels
	 */
	
	protected double restX = 0, restY = 0;
	
	/**
	 * Method to load a picture for the object
	 * @param name
	 */

	public void loadPicture(String name) {

		sprite = new ImageIcon(getClass().getResource(name));

	}
	
	/**
	 * Getter for the Image of the object
	 * @return image of the object
	 */

	public Image getImage() {
		
		return currentImage.getImage();
	}

	/**
	 * Method to move the object according to its velocity
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

	}

//	/**
//	 * Setter for delta - x
//	 * @param dx
//	 */
//
//	public void setdx(int dx) {
//		this.dx = dx;
//
//	}
//	
//	/**
//	 * Setter for delta - y
//	 * @param dy
//	 */
//
//	public void setdy(int dy) {
//		this.dy = dy;
//
//	}
	
	/**
	 * Method to set the position of the object
	 * @param x
	 * @param y
	 */

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter for the x - position
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter for y - position
	 * @return y
	 */
	
	public int getY() {
		return y;
	}

	/**
	 * Setter for x - position
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setter for y - position
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter for height
	 * @return height
	 */
	
	public int getHeight() {
		return height;
	}

	/**
	 * Getter for width
	 * @return width
	 */
	
	public int getWidth() {
		return width;
	}
	
//	/**
//	 * Getter for delta - y
//	 * @return dy
//	 */
//	
//	public int getDy() {
//		return dy;
//	}
//	
//	/**
//	 * Setter for delta - y
//	 * @param dy
//	 */
//
//	public void setDy(int dy) {
//		this.dy = dy;
//	}
//	
//	/**
//	 * Getter for delta - x
//	 * @return dx
//	 */
//
//	public int getDx() {
//		return dx;
//	}
//	
//	/**
//	 * Setter for delta - x
//	 * @param dx
//	 */
//
//	public void setDx(int dx) {
//		this.dx = dx;
//	}

	/**
	 * Method that checks if one MovingObject is intersecting with another
	 * needed for collision detection
	 * @param other
	 * @return boolean if the objects are intersecting
	 */

	public boolean intersects(MovingObjects other) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = other.getRectangle();
		return r1.intersects(r2);

	}
	
	/**
	 * Method to create a rectangle for an MovingObject, according to its height, width and position
	 * @return Rectangle
	 */

	public Rectangle getRectangle() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	/**
	 * Getter/Setter methods for the velocities
	 * @return xVel, yVel current velocities in x/y - direction
	 */
	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

}
