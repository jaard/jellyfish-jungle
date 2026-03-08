package oceanGame;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Animation implements Serializable{
	
	private ArrayList<Scene> scenes;
	private int sceneIndex;
	private int numberOfScenes;
	private long movieTime;
	private long totalTime;
	
	// Constructor for an empty Animation
	public Animation(){
		
		scenes = new ArrayList<Scene>();
		totalTime = 0;
		numberOfScenes = 0;
		start();
		
	}
	
	public synchronized void addScene(ImageIcon i, long duration){
		
		totalTime += duration;
		scenes.add(new Scene(i, totalTime));
		numberOfScenes++;
		
	}
	
	// Start animation from beginning
	public synchronized void start(){
		
		movieTime = 0;
		sceneIndex = 0;
		
	}
	
	public synchronized void update(long timePassed){
		
		if(scenes.size() > 1){
			movieTime += timePassed;
			if(movieTime >= totalTime){
				movieTime = 0;
				sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).getEndTime()){
				sceneIndex++;
			}
		}
		
	}
	
	public synchronized ImageIcon getImage(){
	
		if(scenes.size() == 0){
			return null;
		}else{
			return (ImageIcon) getScene(sceneIndex).getImage();
		}
	}
	
	public Scene getScene(int sceneIndex){
		
		return (Scene) scenes.get(sceneIndex);
	}

	public int getNumberOfScenes() {
		
		return numberOfScenes;
	}

	public int getSceneIndex() {
		return sceneIndex;
	}
	
}
