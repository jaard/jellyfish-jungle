package jellyfishjungle;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Class that stores an image together with the time
 * in the Animation at which it stops playing
 * 
 * Thore, Jaard
 *
 */
public class Scene implements Serializable{
	
	/**
	 * Image of the scene
	 */
	private ImageIcon image;
	/**
	 * Time at which the Scene ends
	 */
	private long endTime;
	
	/**
	 * Constructor for a new Scene
	 * @param i
	 * @param endTime
	 */
	public Scene(ImageIcon i, long endTime){
		
		this.image = i;
		this.endTime = endTime;
	}
	
	/**
	 * Getter for the Scenes image
	 * @return image
	 */
	public ImageIcon getImage(){
		return image;
	}
	
	/**
	 * Getter for the Scenes time at which it ends
	 * @return endTime
	 */
	public long getEndTime(){
		return endTime;
	}
}
