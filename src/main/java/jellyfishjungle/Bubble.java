package jellyfishjungle;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;

public class Bubble extends MovingObjects implements Serializable {
	
	
	boolean active;
	private long timeActive = 0;
	private long maxTime = 5000;
	
	public Bubble(Character hero){
		
		loadPicture("/resources/bubble_small.png");
		currentImage = sprite;
		width = sprite.getImage().getWidth(null);
		height = sprite.getImage().getHeight(null);
		int xPos = hero.getX()+(hero.getImage().getWidth(null)/2)
					-(this.getImage().getWidth(null)/2);
		int yPos = hero.getY()+(hero.getImage().getHeight(null)/2)
				-(this.getImage().getHeight(null)/2)+5;
		this.x = xPos;
		this.y = yPos;
		
		
	}
	
	public void move(Character hero){
		
		int xPos = hero.getX()+(hero.getImage().getWidth(null)/2)
				-(this.getImage().getWidth(null)/2);
		int yPos = hero.getY()+(hero.getImage().getHeight(null)/2)
				-(this.getImage().getHeight(null)/2)+5;
		this.x = xPos;
		this.y = yPos;
	
	}
	
	public void update(int fps){
		if(isActive()){
			if(timeActive > maxTime){
				setActive(false);
			}else{
				timeActive += 1000/fps;
			}
		}else if(timeActive > 0){
			timeActive -= (1000/fps)/2;
			if(timeActive < 0){
				timeActive = 0;
			}
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
		
		int x = this.x + (width/2);
		int y = this.y + (height/2);
		int r = width/2;
		double pi = Math.PI;
		int x1 = x + (int) Math.round((r * Math.cos(pi/8*0)));
		int x2 = x + (int) Math.round((r * Math.cos(pi/8*1)));
		int x3 = x + (int) Math.round((r * Math.cos(pi/8*2)));
		int x4 = x + (int) Math.round((r * Math.cos(pi/8*3)));
		int x5 = x + (int) Math.round((r * Math.cos(pi/8*4)));
		int x6 = x + (int) Math.round((r * Math.cos(pi/8*5)));
		int x7 = x + (int) Math.round((r * Math.cos(pi/8*6)));
		int x8 = x + (int) Math.round((r * Math.cos(pi/8*7)));
		int y1 = y + (int) Math.round((r * Math.sin(pi/8*0)));
		int y2 = y + (int) Math.round((r * Math.sin(pi/8*1)));
		int y3 = y + (int) Math.round((r * Math.sin(pi/8*2)));
		int y4 = y + (int) Math.round((r * Math.sin(pi/8*3)));
		int y5 = y + (int) Math.round((r * Math.sin(pi/8*4)));
		int y6 = y + (int) Math.round((r * Math.sin(pi/8*5)));
		int y7 = y + (int) Math.round((r * Math.sin(pi/8*6)));
		int y8 = y + (int) Math.round((r * Math.sin(pi/8*7)));
		
		
		int[] xPoints = {x1,x2,x3,x4,x5,x6,x7,x8};
		int[] yPoints = {y1,y2,y3,y4,y5,y6,y7,y8};
		
		return new Polygon(xPoints,yPoints,8);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getTimeActive() {
		return timeActive;
	}

	public void setTimeActive(long timeActive) {
		this.timeActive = timeActive;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

}
