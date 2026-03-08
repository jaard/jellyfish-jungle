package oceanGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ocean extends JPanel implements Runnable,KeyListener {

	private int k;
	private int height = 600;
	private int width = 800;
	Background bg;
	Background bg2;
	Thread gameloop;
	GameState gs;
	EnemyManager em;
	ArrayList<Enemy> enemies;
	Character hero;

	// Target frames per second that the game should run at
	int fps = 60;
	// Initial speed of the background
	int backgroundSpeed = 30;

	public Ocean() {

		super();
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		//this.setBackground(Color.BLUE);
		this.setDoubleBuffered(true);

		gs = new GameState();
		em = new EnemyManager(800, 600);

		enemies = new ArrayList<Enemy>();
		hero = new Character();
		bg = new Background(backgroundSpeed);
		bg2= new Background(backgroundSpeed);

		em.setTimeToEnemy(500);
		enemies = em.addEnemy(enemies);

	}

	public Image loadPicture(String name) {

		Image img = new ImageIcon(getClass().getResource(name)).getImage();

		// BufferedImage img = null;
		// try{
		// img = ImageIO.read(getClass().getResource(name));
		// }catch(IOException e){
		// System.err.println(e.getMessage());
		// }

		return img;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(bg.getImage(), bg.getX(), bg.getY(), null);
		g2d.drawImage(bg2.getImage(), bg2.getX(), bg2.getY(), null);

		for (Enemy enemy : enemies) {
			g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
		}
		g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	
	// Method that is used to start the gameloop thread on startup
	public void addNotify() {
		super.addNotify();
		gameloop = new Thread(this);
		addKeyListener(this);
		gameloop.start();		
	}

	// Actions that run before the paint() method in the game loop
	public void cycle() {

		if (gs.isRunning()) {

			enemies = em.update(enemies, 1000 / fps);
			enemies = em.move(enemies, fps);
			hero.positionUpdate();
			
			bg.move(fps);
			bg2.move(fps);
			
		}
	}

	// Method that is executed by the gameloop thread
	public void run() {

		long beforeTime, deltaTime, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {
			
			backgroundloop();
			cycle();
			repaint();

			deltaTime = System.currentTimeMillis() - beforeTime;
			sleep = (1000 / fps) - deltaTime;

			if (sleep < 0)
				sleep = 2;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}

			beforeTime = System.currentTimeMillis();

		}
	}
	
	// Makes the background a continuous loop
	private void backgroundloop(){
		
		if(bg2.getX()== 0){
			bg.setPosition(bg2.getX() + 1100, 0);
		}
		if(bg.getX()== 0){
			bg2.setPosition(bg.getX() + 1100, 0);
		}
		
	}

	public void keyTyped(KeyEvent key) {

	}

	public void keyPressed(KeyEvent key) {
		
		if (k == KeyEvent.VK_ESCAPE) {
			gs.pause();
		}
		if (hero.alive() == true) {
			k = key.getKeyCode();
			if (k == KeyEvent.VK_UP) {
				hero.setdy(-4);
			}
			if (k == KeyEvent.VK_DOWN) {
				hero.setdy(4);
			}
			if (k == KeyEvent.VK_S) {
				hero.dies();
			}
		}

	}

	public void keyReleased(KeyEvent key) {
		if (hero.alive() == true) {
			k = key.getKeyCode();
			if (k == KeyEvent.VK_UP) {
				hero.setdy(0);
			}
			if (k == KeyEvent.VK_DOWN) {
				hero.setdy(0);
			}
		}
	}

}
