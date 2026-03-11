package oceanGame;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Class that can store a series of images and return them 
 * depending on the time that has passed
 * implements the interface Serializable
 * 
 * @author Thore, Jaard
 *
 */

public class Animation implements Serializable{
	
	/**
	 * ArrayList object to store the scenes
	 */
	private ArrayList<Scene> scenes;
	/**
	 * Index of the image that will be returned
	 */
	private int sceneIndex;
	/**
	 * Total number of scenes that the Animation contains
	 */
	private int numberOfScenes;
	/**
	 * Time that the current loop of the Animation has 
	 * been playing in milliseconds
	 */
	private long movieTime;
	/**
	 * Time at which the Animation will be restarted from
	 * the beginning
	 */
	private long totalTime;
	
	/**
	 * Contructor that creates an empty Animation without
	 * any scenes
	 */
	public Animation(){
		
		scenes = new ArrayList<Scene>();
		totalTime = 0;
		numberOfScenes = 0;
		start();
		
	}
	
	/**
	 * Method to add a new Scene to the Animation
	 * 
	 * @param i
	 * @param duration
	 */
	public synchronized void addScene(ImageIcon i, long duration){
		
		totalTime += duration;
		scenes.add(new Scene(i, totalTime));
		numberOfScenes++;
		
	}
	
	/**
	 * Method to start the animation from the beginning
	 */
	public synchronized void start(){
		
		movieTime = 0;
		sceneIndex = 0;
		
	}
	
	/**
	 * Method to update the Animation according to the time that has passed
	 * 
	 * @param timePassed
	 */
	public void update(long timePassed){
		
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
	
	/**
	 * Getter for the image of the Scene that is playing
	 * @return Image
	 */
	public ImageIcon getImage(){
	
		if(scenes.size() == 0){
			return null;
		}else{
			return (ImageIcon) getScene(sceneIndex).getImage();
		}
	}
	
	/**
	 * Getter for the Scene that is playing
	 * @param sceneIndex
	 * @return Scene
	 */
	public Scene getScene(int sceneIndex){
		
		return (Scene) scenes.get(sceneIndex);
	}
	
	/**
	 * Getter for the total number of Scenes
	 * @return numberOfScenes
	 */
	public int getNumberOfScenes() {
		
		return numberOfScenes;
	}
	
	/**
	 * Getter for the index of the scene that is playing
	 * @return sceneIndex
	 */
	public int getSceneIndex() {
		return sceneIndex;
	}
	
}
