package oceanGame;


public class Enemy extends MovingObjects{
	
	// Constructor for the Enemy
	public Enemy(int type, int x, int y, double xVel) {
		
		this.xVel = xVel;
		
		if(type == 1){
			this.loadPicture("/resources/ghost_left.png");
			width = sprite.getWidth(null);
			height = sprite.getHeight(null);
		} else if(type == 2){
			this.loadPicture("/resources/ghost_orange.png");
			width = sprite.getWidth(null);
			height = sprite.getHeight(null);
		}
		this.x = x;
		this.y = y;
	}
}
