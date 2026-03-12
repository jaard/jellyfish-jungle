package jellyfishjungle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Ocean Class creates the ocean in the game with all objects
 * extends JPanel and implements the interface Runnable and Keylistener
 * @author Thore
 *
 */
public class Ocean extends JPanel implements Runnable, KeyListener {

	/**
	 * Integer for the key code
	 */
	private int k;
	/**
	 * height of the ocean
	 */
	private int height = 600;
	/**
	 * width of the ocean
	 */
	private int width = 800;
	/**
	 * Position of the Headline
	 */
	private int textposition = 130;
	/**
	 * Current choice in the menu
	 */
	private int currentChoice = 0;
	/**
	 * The options in the Menu
	 */
	private String[] options = { "Start", "Load", "Save", "Quit" };
	/**
	 * The game background
	 */
	Background bg;
	/**
	 * The second background (needed to loop the background) 
	 */
	Background bg2;
	/**
	 * Thread the game runs in
	 */
	Thread gameloop;
	/**
	 * Controls the state of the game
	 */
	GameState gs;
	/**
	 * Controls the enemies
	 */
	EnemyManager em;
	/**
	 * Arraylist which contains all enemies
	 */
	ArrayList<Enemy> enemies;
	/**
	 * The main Character
	 */
	Character hero;
	/**
	 * Bubble that can appear around the main Character
	 */
	Bubble bubble;
	/**
	 * Path were the save method writes
	 */
	String filenamesave = System.getProperty("user.home")
			+ File.separator + ".jellyfishjungle" + File.separator + "save.dat";

	/**
	 * Color of the title
	 */
	private Color titlecolor;
	/**
	 * Font of the title
	 */
	private Font titlefont;
	/**
	 * Normal font
	 */
	private Font font;
	/**
	 * Alternative font
	 */
	private Font font2;

	/**
	 * Target frames per second that the game should run at
	 */
	int fps = 60;

	/**
	 * Constructor of the Ocean class
	 * sets up the ocean for the game
	 */
	public Ocean() {

		super();
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		this.setDoubleBuffered(true);

		titlecolor = new Color(0, 0, 0);
		titlefont = new Font("Georgia", Font.ITALIC, 70);
		font = new Font("Arial", Font.PLAIN, 40);
		font2 = new Font("Arial", Font.PLAIN, 20);

		gs = new GameState();
		//gs.setBackgroundspeed();
		em = new EnemyManager(800, 600, gs.getBackgroundspeed());

		enemies = new ArrayList<Enemy>();
		hero = new Character();
		bubble = new Bubble(hero);
		bg = new Background(gs.getBackgroundspeed());
		bg2 = new Background(gs.getBackgroundspeed());

		//em.setTimeToEnemy(500);
		//enemies = em.addEnemy(enemies);
		gs.setRunning(false);

	}
	/**
	 * Loads the Picture for the background
	 * @param name
	 * @return img
	 */

	public Image loadPicture(String name) {

		Image img = new ImageIcon(getClass().getResource(name)).getImage();

		return img;
	}
	
	/**
	 * Creates the graphic with all its elements
	 */

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2d.drawImage(bg.getImage(), bg.getX(), bg.getY(), null);
		g2d.drawImage(bg2.getImage(), bg2.getX(), bg2.getY(), null);

		for (Enemy enemy : enemies) {
			g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
		}
		
		g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
		
		if(bubble.isActive()){
			g2d.drawImage(bubble.getImage(), bubble.getX(), bubble.getY(), null);
		}

		if (gs.isRunning() == false) {

			g2d.setColor(titlecolor);
			g2d.setFont(titlefont);
			g2d.drawString("JELLYFISH JUNGLE", 60, textposition);
			g2d.setFont(font2);
			g2d.setColor(Color.BLACK);
			g2d.drawString("press ESC for Menu", 280, 565);
			g2d.setFont(font);

			for (int i = 0; i < options.length; i++) {
				if (i == currentChoice) {
					g2d.setColor(Color.BLACK);

				} else {

					g2d.setColor(Color.WHITE);
				}
				g2d.drawString(options[i], 320, textposition + 120 + i * 50);

			}

		} else {
			g2d.setFont(font2);
			g2d.drawString("Level: " + gs.getLevel(), 700, 50);
			g2d.drawString("Score: " + gs.getScore(), 580, 50);
			g2d.drawString("Lifes: " + hero.getLife(), 460, 50);
		}

		if (hero.alive() == false & gs.isRunning() == true) {

			g2d.setColor(titlecolor);
			g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 70));
			g2d.drawString("GAME OVER", 180, 250);
			g2d.setFont(font);
			g2d.drawString("Your Score: " + gs.getScore(), 180, 350);

		}

	}

	/**
	 * Method that is used to start the game loop thread on startup
	 */
	public void addNotify() {
		super.addNotify();
		gameloop = new Thread(this);
		addKeyListener(this);
		gameloop.start();
	}

	/**
	 * Actions that run before the paint method in the game loop
	 */
	public void cycle() {

		if (gs.isRunning()) {

			enemies = em.update(enemies, fps);
			enemies = em.move(enemies, fps);
			enemies = em.animate(enemies, fps);
			hero.move(fps);
			hero.animationUpdate(fps);
			bubble.move(hero);
			bubble.update(fps);
			
			bg.move(fps);
			bg2.move(fps);
			bg.setSpeed(gs.getBackgroundspeed());
			bg2.setSpeed(gs.getBackgroundspeed());
			collisionDetection();
			backgroundloop();

			gs.updateTimeRunning(fps);
			enemies = gs.update(hero, enemies, em, fps);
			

		}

	}

	/**
	 * Method that is executed by the game loop thread
	 */
	public void run() {

		long beforeTime, deltaTime, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {

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

	/**
	 * Makes the background a continuous loop
	 */
	private void backgroundloop() {

		if (bg2.getX() <= 0) {
			bg.setPosition(bg2.getX() + bg.getImage().getWidth(null) - 1, 0);
		}
		if (bg.getX() <= 0) {
			bg2.setPosition(bg.getX() + bg2.getImage().getWidth(null) - 1, 0);
		}

	}
	
	/**
	 * Controls whether or not objects in the ocean are colliding and triggers events if they are
	 */

	public void collisionDetection() {

		ArrayList<Enemy> collidedEnemies = new ArrayList<Enemy>();
		ArrayList<Enemy> notCollidedEnemies = new ArrayList<Enemy>();
		for (Enemy enemy : enemies) {

			if (hero.intersects(enemy) == true) {
				collidedEnemies.add(enemy);
			}else{
				notCollidedEnemies.add(enemy);
			}
		}
		for (Enemy enemy : collidedEnemies) {
			if (hero.alive() == true) {
				if(bubble.isActive() == false){
					if (enemy.isEdible()) {
						enemies.remove(enemy);
						gs.incScore();
						if(hero.getxVel() <= 0){
							hero.eats();
						}
						
					} else if (enemy.isCollidable()) {
						hero.decLife();
						enemy.setCollidable(false);
						if(hero.getLife() == 0){
							hero.dies();
							bubble.setActive(false);
							gs.setBackgroundspeed(0);
							
						}
						if(hero.alive()){
							hero.blinks();
						}
					}	
				}
				
			}
		}
		for(Enemy enemy : notCollidedEnemies){
			enemy.setCollidable(true);
			
		}
		
	}
	/**
	 * Triggers events according to your current choice in the menu
	 */

	public void select() {
		if (currentChoice == 0) {
			
			gs = new GameState();
			em = new EnemyManager(800, 600, gs.getBackgroundspeed());

			enemies = new ArrayList<Enemy>();
			hero = new Character();
			bg = new Background(gs.getBackgroundspeed());
			bg2 = new Background(gs.getBackgroundspeed());
			gs.setRunning(true);
			
		}
		if (currentChoice == 1) {
			load();
		}
		if (currentChoice == 2) {
			save();

		}
		if (currentChoice == 3) {
			System.exit(0);
		}
	}
	/**
	 * Writes all objects which are important for your game in a file
	 */

	public void save() {

		try {
			new File(filenamesave).getParentFile().mkdirs();
			OutputStream os = new FileOutputStream(filenamesave);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(hero);
			oos.writeObject(bg);
			oos.writeObject(bg2);
			oos.writeObject(enemies);
			oos.writeObject(gs);
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		

	}
	
	/**
	 * Loads all objects from the file the save method created
	 * 
	 * @SuppressWarnings("unchecked") is needed to load the ArrayList of Enemies without an error
	 * It is ok because we control the file and know that it only contains the objects we want.
	 */

	@SuppressWarnings("unchecked")
	public void load() {
		try {
			InputStream is = new FileInputStream(filenamesave);
			ObjectInputStream ois = new ObjectInputStream(is);
			hero = (Character) ois.readObject();
			bg = (Background) ois.readObject();
			bg2 = (Background) ois.readObject();
			enemies = (ArrayList<Enemy>) ois.readObject();
			gs = (GameState) ois.readObject();
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		gs.setRunning(true);
		

	}
	/**
	 * Method needed to implement KeyListener, does nothing
	 */

	public void keyTyped(KeyEvent key) {

	}
	/**
	 * Method which triggers events according to the key's you are pressing
	 */

	public void keyPressed(KeyEvent key) {
		k = key.getKeyCode();

		if (gs.isRunning() == true) {
			if (hero.alive() == true) {

				if (k == KeyEvent.VK_UP) {
					hero.setyVel(hero.getSpeed());
				}
				if (k == KeyEvent.VK_DOWN) {
					hero.setyVel(-hero.getSpeed());
				}
				if (k == KeyEvent.VK_S) {
					hero.dies();
				}
				if (k == KeyEvent.VK_LEFT) {
					hero.setxVel(hero.getSpeed());
				}
				if (k == KeyEvent.VK_RIGHT) {
					hero.setxVel(-hero.getSpeed());
				}
				
				if (k == KeyEvent.VK_SPACE) {
					if(bubble.isActive()){
						bubble.setActive(false);
					}else{
						bubble.setActive(true);
					}
					
				}
			}
		}
		if (gs.isRunning() == false) {
			if (k == KeyEvent.VK_ENTER) {
				select();
			}
			if (k == KeyEvent.VK_UP) {
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = options.length - 1;
				}
			}
			if (k == KeyEvent.VK_DOWN) {
				currentChoice++;
				if (currentChoice == options.length) {
					currentChoice = 0;
				}
			}

		}
		if (k == KeyEvent.VK_ESCAPE) {

			gs.pause();

		}

	}
	/**
	 * Method which triggers events according to the key's you are releasing
	 */

	public void keyReleased(KeyEvent key) {
		if (hero.alive() == true) {
			k = key.getKeyCode();
			if (k == KeyEvent.VK_UP) {
				hero.setyVel(0);
			}
			if (k == KeyEvent.VK_DOWN) {
				hero.setyVel(0);
			}
			if (k == KeyEvent.VK_LEFT) {
				hero.setxVel(0);
			}
			if (k == KeyEvent.VK_RIGHT) {
				hero.setxVel(0);
			}
		}
	}

}
