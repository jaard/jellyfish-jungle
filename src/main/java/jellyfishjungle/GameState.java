package jellyfishjungle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that controls the state of the game implements Serializable
 * 
 * @author Thore
 * 
 */

public class GameState implements Serializable {

	/**
	 * Integers for GameState 
	 * the level 
	 * the Score the 
	 * time it takes till the next enemy is created
	 */
	private int level, score, backgroundspeed;
	
	/**
	 * The speed by which the backgroundspeed is increased by a level up 
	 */
	private int levelSpeedIncrement;
	/**
	 * Multiplicator for level up
	 */
	private int multiplier = 1;

	/**
	 * boolean that controls if the game is running
	 */
	private boolean running;
	/**
	 * time the game is running
	 */
	private long timeRunning;

	/**
	 * Constructor of the GameState Class
	 * sets running to true 
	 * sets the level to one
	 */
	public GameState() {

		running = true;
		level = 1;
		multiplier = 1;
		backgroundspeed = 30;
		levelSpeedIncrement = 15;

	}
	
	public ArrayList<Enemy> update(Character hero, ArrayList<Enemy> enemies, EnemyManager em, int fps){
		
		if (this.getTimeRunning() > 6000 * multiplier & hero.alive()) {
			this.levelUp();
			multiplier += 1;
			this.setBackgroundspeed(this.getBackgroundspeed() + levelSpeedIncrement);
			em.setSpeed(this.getBackgroundspeed());
			enemies = em.increaseEnemySpeed(enemies, levelSpeedIncrement);
		}
		return enemies;
	}

	/**
	 * Method to increase the level by one
	 */
	public void levelUp() {
		level += 1;
	}

	/**
	 * Getter for level
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Setter for level
	 * 
	 * @param l
	 */
	public void setLevel(int l) {
		level = l;
	}

	/**
	 * Setter for boolean running
	 * 
	 * @param r
	 */

	public void setRunning(boolean r) {
		running = r;

	}

	/**
	 * Getter for boolean running
	 * 
	 * @return running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Method to pause/continue the game
	 */

	public void pause() {
		if (running == true) {
			running = false;
		} else if (running == false) {
			running = true;
		}
	}

	/**
	 * Method to update the Time the game is running
	 * 
	 * @param fps
	 */

	public void updateTimeRunning(int fps) {
		timeRunning += 1000 / fps;
	}

	/**
	 * Getter for the time the game is running
	 * 
	 * @return timeRunning
	 */

	public long getTimeRunning() {
		return timeRunning;
	}

	/**
	 * Setter for the time the game is running
	 * 
	 * @param t
	 */

	public void setTimeRunning(long t) {
		timeRunning = t;
	}

	/**
	 * Getter for score
	 * 
	 * @return score
	 */

	public int getScore() {
		return score;
	}

	/**
	 * Setter for Score
	 * 
	 * @param s
	 */

	public void setScore(int s) {
		score = s;
	}

	/**
	 * Method to increase the score by one
	 */

	public void incScore() {
		score += 1;
	}
	/**
	 * Getter for backgroundspeed
	 * @return backgroundspeed
	 */

	public int getBackgroundspeed() {
		return backgroundspeed;
	}
	/**
	 * Setter for backgroundspeeed
	 * @param backgroundspeed
	 */

	public void setBackgroundspeed(int backgroundspeed) {
		this.backgroundspeed = backgroundspeed;
	}

}
