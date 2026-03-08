package oceanGame;

import java.util.ArrayList;

public class EnemyManager {
	
	int width, height, lowerBoundary;
	int maxEnemyHeight = 100;
	int enemyTypes = 2;
	private int timeSinceEnemy = 0;
	private int timeToEnemy = 700;
	double enemyProbability = 0.4;
	double probabilityType1 = 0.4;	// The sum of the probabilities 
	double probabilityType2 = 0.6;	//   of the enemies should be 1
	int speed;
	double speedType1 = speed + 150;
	double speedType2 = speed + 70;
	double speedVariance = 100;
	
	
	public EnemyManager(int width, int height, int speed) {
		
		this.speed = speed;
		this.width = width;
		this.height = height;
		lowerBoundary = 500;
		//lowerBoundary = height - maxEnemyHeight;
		
	}
	
	// Add new enemies and delete those off screen
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
	
	// Method to move the enemies according to their velocities
	public ArrayList<Enemy> move(ArrayList<Enemy> enemies, int fps){
		
		for(Enemy enemy : enemies){
			
			enemy.move(fps);
		}
		return enemies;
	}
	
	// Method to set the time between enemies showing up
	public void setTimeToEnemy(int timeToEnemy) {
		
		this.timeToEnemy = timeToEnemy;
	}
	
	// Method to create a single random enemy
	public ArrayList<Enemy> addEnemy(ArrayList<Enemy> enemies){
		
			int spawnPoint = (int) (Math.random() * (lowerBoundary));
			//int spawnPoint = (int) (Math.random() * (lowerBoundary + 1));
			//int type = 1 + (int) (Math.random() * ((enemyTypes - 1) + 1));
			
			// In this block an enemy type is chosen randomly based on the probabilities
			double randomNumber = Math.random();
			if(randomNumber < probabilityType1){
				enemies.add(new Enemy(1,width,spawnPoint,speedType1 - speedVariance*0.5 + Math.random()*speedVariance));
			}else if(randomNumber >= probabilityType1 && randomNumber < probabilityType1 + probabilityType2){
				enemies.add(new Enemy(2,width,spawnPoint,speedType2 - speedVariance*0.5 + Math.random()*speedVariance));
			}
			return enemies;
		
	}
	
	// Method to delete enemies that left the screen
	public ArrayList<Enemy> deleteOffScreen(ArrayList<Enemy> enemies){
		
		// ArrayList that stores enemies to be removed afterwards
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
	
	public void setSpeed(int speed){
		this.speed = speed;
	}

}
