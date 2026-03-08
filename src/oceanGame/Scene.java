package oceanGame;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Scene implements Serializable{
	
	private ImageIcon image;
	private long endTime;
	
	public Scene(ImageIcon i, long endTime){
		
		this.image = i;
		this.endTime = endTime;
	}
	
	public ImageIcon getImage(){
		return image;
	}
	
	public long getEndTime(){
		return endTime;
	}
}
