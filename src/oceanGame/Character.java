package oceanGame;

import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Character extends MovingObjects implements  Serializable{

	private boolean exists = true;
	private int speed = 6;
	
	
	
     public Character() {
		
 			this.loadPicture("/resources/ghost_right.png");
 			width = sprite.getImage().getWidth(null);
 			height = sprite.getImage().getHeight(null);

	}
     
     public void dies(){
    	 exists = false;
    	 try {
 			sprite = new ImageIcon(getClass().getResource("/resources/ghost_orange.png"));

 		} catch (Exception e) {
 			e.printStackTrace();

 		}
    	this.dy= 3; 
    	this.dx= 0;
    	 
     }
     
     public boolean alive(){
    	 if (exists == true){
    		 return true;
    	 }
    	 else{
    		 return false;
    	 }
     }
     public void positionUpdate() {

 		if (x < 0) {
 			x = 0;
 		}
 		if(y < 0){
 			y = 0;
 		}
 		if(y + (height+ 20) > 600 & exists == true){
 			y= 600 - (height + 20) ;
 		}
 		
 		else {
 			this.x += dx;
 			this.y += dy;
 		}

 	}
    public int getSpeed(){
    	return speed;
    }


}
