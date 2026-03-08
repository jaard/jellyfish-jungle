package oceanGame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Character extends MovingObjects{
	private boolean exists = true;
	private int[][] bounders;
	
	
     public Character() {
		
 			this.loadPicture("/resources/ghost_right.png");
 			width = sprite.getWidth(null);
 			height = sprite.getHeight(null);

	}
     
     public void dies(){
    	 exists = false;
    	 try {
 			sprite = new ImageIcon(getClass().getResource("/resources/ghost_orange.png")).getImage();

 		} catch (Exception e) {
 			e.printStackTrace();

 		}
    	this.dy= 3; 
    	 
     }
     
     public boolean alive(){
    	 if (exists == true){
    		 return true;
    	 }
    	 else{
    		 return false;
    	 }
     }


}
