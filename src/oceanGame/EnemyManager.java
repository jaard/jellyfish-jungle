package oceanGame;

import java.util.ArrayList;

/**
 * Class that manages the movement, appearance and disappearance
 * of the enemies in the game
 * 
 * @author Thore, Jaard
 *
 */
public class EnemyManager {
	
	/**
	 * The width and height of the frame
	 */
	int width, height;
	/**
	 * The lower boundary of the area in which enemies can be drawn
	 */
	int lowerBoundary;
	/**
	 * Number of different types of enemies
	 */
	int enemyTypes = 2;
	/**
	 * Time since the last enemy appeared and time between enemies
	 */
	private int timeSinceEnemy = 0;
	private int timeToEnemy = 500;
	/**
	 * Probability of an enemy showing up after the specified time
	 */
	double enemyProbability = 0.4;
	/**
	 * Probabilities of the different types of enemies
	 * The probabilities should add up to 1
	 */
	double probabilityType1 = 0.6;
	double probabilityType2 = 0.4;
	/**
	 * Speed of the background, speeds of the enemy types depending on 
	 * the speed of the background and the variance in speed between
	 * individual enemies
	 * All speeds are in pixels per second
	 */
	int speed;
	double speedType1 = speed + 150;
	double speedType2 = speed + 70;
	double speedVariance = 100;
	
	/**
	 * Constructor for the EnemyManager
	 * @param width
	 * @param height
	 * @param speed
	 */
	public EnemyManager(int width, int height, int speed) {
		
		this.speed = speed;
		this.width = width;
		this.height = height;
		lowerBoundary = 500;
		
	}
	
	/** 
	 * Method to add new enemies and delete those that moved 
	 * off the screen
	 * Is called for every cycle of the game
	 * 
	 * @param enemies
	 * @param fps
	 * @return
	 */
	public ArrayList<Enemy> update(ArrayList<Enemy> enemies, int fps) {
		
		timeSinceEnemy += 1000 / fps;
		if(timeSinceEnemy > timeToEnemy){
			
			if(Math.random() < enemyProbability){
				addEnemy(enemies);
			}
			timeSinceEnemy = 0;
		}
		enemies = this.deleteOffScreen(enemies);
		return enemies;
	}
	
	/** 
	 * Method to move the enemies according to their velocities
	 * 
	 * @param enemies
	 * @param fps
	 * @return
	 */
	public ArrayList<Enemy> move(ArrayList<Enemy> enemies, int fps){
		
		for(Enemy enemy : enemies){
			
			enemy.move(fps);
		}
		return enemies;
	}
	
	/** 
	 * Method to set the time between enemies showing up
	 * 
	 * @param timeToEnemy
	 */
	public void setTimeToEnemy(int timeToEnemy) {
		
		this.timeToEnemy = timeToEnemy;
	}
	
	/** 
	 * Method to create a single random enemy
	 * The type is chosen based in the probabilities specified above
	 * 
	 * @param enemies
	 * @return
	 */
	public ArrayList<Enemy> addEnemy(ArrayList<Enemy> enemies){
		
			int spawnPoint = (int) (Math.random() * (lowerBoundary));
			double randomNumber = Math.random();
			if(randomNumber < probabilityType1){
				enemies.add(new Enemy(1,width,spawnPoint,speedType1 - speedVariance*0.5 + Math.random()*speedVariance));
			}else if(randomNumber >= probabilityType1 && randomNumber < probabilityType1 + probabilityType2){
				enemies.add(new Enemy(2,width,spawnPoint,speedType2 - speedVariance*0.5 + Math.random()*speedVariance));
			}
			return enemies;
		
	}
	
	/** Method to delete enemies that left the screen
	 * 
	 * @param enemies
	 * @return
	 */
	public ArrayList<Enemy> deleteOffScreen(ArrayList<Enemy> enemies){
		
		ArrayList<Enemy> removeList = new ArrayList<Enemy>();
		
		for(Enemy enemy : enemies){
			if(enemy.getX() < 0 - enemy.getWidth()){
				removeList.add(enemy);
			}
			if(enemy.getY() >= lowerBoundary){
				removeList.add(enemy);
			}
		}
		for(Enemy enemy : removeList){
			enemies.remove(enemy);
		}
		return enemies;
	}
	
	/**
	 * Setter for the game speed and the speeds of the enemy types
	 * @param speed
	 */
	public void setSpeed(int speed){
		
		this.speed = speed;
		this.speedType1 = speed + 150;
		this.speedType2 = speed + 70;
	}
	
	/**
	 * Method to animate the enemies
	 * @param enemies
	 * @param fps
	 * @return
	 */
	public ArrayList<Enemy> animate(ArrayList<Enemy> enemies, int fps){
		
		for(Enemy enemy : enemies){
			
			if(enemy.isAnimated()){
			enemy.getAnimation().update(1000/fps);
			enemy.updateCurrentImage();
			}
		}
		return enemies;
	}
	
	/**
	 * Method to increase the speed of all existing enemies when
	 * the game speeds up
	 * 
	 * @param enemies
	 * @param increment
	 * @return
	 */
	public ArrayList<Enemy> increaseEnemySpeed(ArrayList<Enemy> enemies, int increment){
		
		for(Enemy enemy : enemies){
			double oldSpeed = enemy.getXVel();
			enemy.setXVel(oldSpeed + increment);
		}
		return enemies;
	}

}
