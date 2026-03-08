package oceanGame;

import java.io.Serializable;

import javax.swing.ImageIcon;


public class Enemy extends MovingObjects implements  Serializable{
	
	private boolean edible, animated;
	private Animation moveAnimation;
	
	// Constructor for the Enemy
	public Enemy(int type, int x, int y, double xVel) {
		
		this.xVel = xVel;
		
		if(type == 1){
			this.loadPicture("/resources/ghost_left.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = true;
			animated = false;
		} else if(type == 2){
			this.loadPicture("/resources/ghost_orange.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = false;
			animated = true;
		}
		this.x = x;
		this.y = y;
		currentImage = sprite;
		if(isAnimated()){
		loadMoveAnimation(xVel);
		}
		
		
	}
	
	// Loads the the animation for the enemys movement
	public void loadMoveAnimation(double xVel){
		
		//long duration1 = (long) Math.round(0.5*10000/xVel);
		//long duration2 = (long) Math.round(5*10000/xVel);
		moveAnimation = new Animation();
		moveAnimation.addScene(new ImageIcon(getClass()
				.getResource("/resources/ghost_orange.png")),140);
		moveAnimation.addScene(new ImageIcon(getClass()
				.getResource("/resources/ghost_left.png")),1300);
	}
	
	public boolean isEdible(){
		return edible;
	}
	
	public boolean isAnimated(){
		return animated;
	}
	
	public Animation getAnimation(){
		return moveAnimation;
	}
	
	public void updateCurrentImage(){
		currentImage = moveAnimation.getImage();
	}
	
	public double getXVel(){
		return xVel;
	}
	
	public void setXVel(double xVel){
		this.xVel = xVel;
	}
}
