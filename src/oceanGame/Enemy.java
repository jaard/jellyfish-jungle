package oceanGame;

import java.io.Serializable;


public class Enemy extends MovingObjects implements  Serializable{
	
	private boolean edible;
	
	// Constructor for the Enemy
	public Enemy(int type, int x, int y, double xVel) {
		
		this.xVel = xVel;
		
		if(type == 1){
			this.loadPicture("/resources/ghost_left.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = true;
		} else if(type == 2){
			this.loadPicture("/resources/ghost_orange.png");
			width = sprite.getImage().getWidth(null);
			height = sprite.getImage().getHeight(null);
			edible = false;
		}
		this.x = x;
		this.y = y;
	}
	
	public boolean isEdible(){
		return edible;
	} 
}
