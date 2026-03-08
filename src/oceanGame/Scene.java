package oceanGame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Scene {
	
	private Image sceneImage;
	private int sceneDuration;
	
	public Scene(String filename, int sceneDuration){
		
		loadImage(filename);
		this.sceneDuration = sceneDuration;
		
	}
	
	public void loadImage(String filename) {

		sceneImage = new ImageIcon(getClass().getResource(filename)).getImage();

	}
	
	public Image getImage(){
		return sceneImage;
	}
	
	public int getDuration(){
		return sceneDuration;
	}
}
