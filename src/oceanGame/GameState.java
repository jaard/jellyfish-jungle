package oceanGame;

import java.io.Serializable;

public class GameState implements Serializable {
	
	private int level, score, timeToEnemy;
	private boolean running;
	private long timeRunning;
	
	public GameState(){
		
		running = true;
		level = 1;
		
	}
	public void levelUp() {
		level += 1;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int l){
		level = l;
	}
	
	public void setRunning(boolean r){
		running = r;
		
	}
	public boolean isRunning(){
		return running;
	}
	
	public void pause(){
		if(running == true){
			running = false;
		}else if(running == false){
			running = true;
		}
	}
	
	public void updateTimeRunning(int fps){
		timeRunning += 1000/fps;
	}
	public long getTimeRunning(){
		return timeRunning;
	}
	public void setTimeRunning(long t){
		timeRunning = t;
	}
	public int getScore(){
		return score;
	}
	public void setScore(int s){
		score = s;
	}
	public void incScore(){
		score +=1;
	}

}
