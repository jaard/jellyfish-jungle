package oceanGame;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class MovingObjects {
	
	protected int x, y, dy, dx, width, height;
	protected Image sprite;
	protected double xVel, yVel; // Negative velocities in Pixels per second
	protected double restX = 0, restY = 0;
	
	public void loadPicture(String name) {

		sprite = new ImageIcon(getClass().getResource(name)).getImage();

	}
	
	public Image getImage() {
		return sprite;
	}
	
	// Moves the object according to its velocity
	public void move(int fps){
				
		restX = restX + ((xVel/fps) % 1);
		x -= (int) (xVel/fps);
		x -= (int) restX;
		restX = restX % 1;
	
	}

	public void positionUpdate() {

		this.x += dx;
		this.y += dy;

	}

	public void setdx(int dx) {
		this.dx = dx;

	}

	public void setdy(int dy) {
		this.dy = dy;

	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Get method for X position
		public int getX(){
			return x;
		}
		
		// Get method for y position
		public int getY(){
			return y;
		}
		
		// Set method for X position
		public void setX(int x) {
			this.x = x;
		} 
		
		// Set method for Y position
		public void setY(int y) {
			this.y = y;
		} 
		
		// Get method for X position
			public int getHeight(){
				return height;
		}
			
		// Get method for y position
			public int getWidth(){
				return width;
		}

}
