package oceanGame;

public class GameState {
	
	int level, score, timeToEnemy;
	boolean running;
	long timeRunning;
	
	public GameState(){
		
		running = true;
		level = 1;
		
	}
	public void levelUp() {
		level += 1;
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

}
