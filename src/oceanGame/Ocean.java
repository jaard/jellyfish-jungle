package oceanGame;

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

public class Ocean extends JPanel implements Runnable, KeyListener {

	private int k;
	private int mult = 1;
	private int height = 600;
	private int width = 800;
	private int textposition = 150;
	private int currentChoice = 0;
	private String[] options = { "Start", "Load", "Save", "Quit" };
	Background bg;
	Background bg2;
	Thread gameloop;
	GameState gs;
	EnemyManager em;
	ArrayList<Enemy> enemies;
	Character hero;
	String filenamestart = "src/resources/start.txt";

	String filenamesave = "/Users/Thore/javasave/save.txt";

	// Writing on Screen
	private Color titlecolor;
	private Font titlefont;
	private Font font;
	private Font font2;

	// Target frames per second that the game should run at
	int fps = 60;
	// Initial speed of the background
	int speed = 30;
	int levelSpeedIncrement = 30;

	public Ocean() {

		super();
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		// this.setBackground(Color.BLUE);
		this.setDoubleBuffered(true);

		titlecolor = new Color(0, 0, 0);
		titlefont = new Font("Georgia", Font.ITALIC, 70);
		font = new Font("Arial", Font.PLAIN, 40);
		font2 = new Font("Arial", Font.PLAIN, 20);

		gs = new GameState();
		em = new EnemyManager(800, 600, speed);

		enemies = new ArrayList<Enemy>();
		hero = new Character();
		hero.setPosition(10, 200);
		bg = new Background(speed);
		bg2 = new Background(speed);

		em.setTimeToEnemy(500);
		enemies = em.addEnemy(enemies);
		gs.setRunning(false);

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

		if (gs.isRunning() == false) {

			g2d.setColor(titlecolor);
			g2d.setFont(titlefont);
			g2d.drawString("FISH ATTACK", 150, textposition);
			g2d.setFont(font2);
			g2d.setColor(Color.BLACK);
			g2d.drawString("press ESC for Menu", 280, 565);
			g2d.setFont(font);

			for (int i = 0; i < options.length; i++) {
				if (i == currentChoice) {
					g2d.setColor(Color.BLACK);

				} else {

					g2d.setColor(Color.RED);
				}
				g2d.drawString(options[i], 320, textposition + 70 + i * 50);

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

			enemies = em.update(enemies, fps);
			enemies = em.move(enemies, fps);
			enemies = em.animate(enemies, fps);
			hero.positionUpdate();

			bg.move(fps);
			bg2.move(fps);
			bg.setSpeed(speed);
			bg2.setSpeed(speed);
			collisionDetection();
			backgroundloop();

			gs.updateTimeRunning(fps);

			if (gs.getTimeRunning() > 6000 * mult) {
				gs.levelUp();
				mult += 1;
				speed += levelSpeedIncrement;
				em.setSpeed(speed);
				enemies = em.increaseEnemySpeed(enemies, levelSpeedIncrement);

			}
			

		}

	}

	// Method that is executed by the gameloop thread
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

	// Makes the background a continuous loop
	private void backgroundloop() {

		if (bg2.getX() <= 0) {
			bg.setPosition(bg2.getX() + 1100, 0);
		}
		if (bg.getX() <= 0) {
			bg2.setPosition(bg.getX() + 1100, 0);
		}

	}

	public void collisionDetection() {

		ArrayList<Enemy> collidedEnemies = new ArrayList<Enemy>();
		for (Enemy enemy : enemies) {

			if (hero.intersects(enemy) == true) {
				collidedEnemies.add(enemy);
			}
		}
		for (Enemy enemy : collidedEnemies) {
			if (hero.alive() == true) {
				if (enemy.isEdible()) {
					enemies.remove(enemy);
					gs.incScore();
				} else {
					hero.decLife();
					enemies.remove(enemy);
					if(hero.getLife() == 0){
						hero.dies();
						
					}
				}
			}
		}
	}

	public void select() {
		if (currentChoice == 0) {
			// start
			
			gs.setRunning(true);
			gs.setScore(0);
			gs.setTimeRunning(0);
			gs.setLevel(1);
			hero = new Character();
			hero.setPosition(10, 200);
			enemies.removeAll(enemies);
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

	public void save() {

		try {
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
		// System.out.println(hero.getX());

	}

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
		// System.out.println(hero.getX());
		gs.setRunning(true);

	}

	public void keyTyped(KeyEvent key) {

	}

	public void keyPressed(KeyEvent key) {
		k = key.getKeyCode();

		if (gs.isRunning() == true) {
			if (hero.alive() == true) {

				if (k == KeyEvent.VK_UP) {
					hero.setdy(-hero.getSpeed());
				}
				if (k == KeyEvent.VK_DOWN) {
					hero.setdy(hero.getSpeed());
				}
				if (k == KeyEvent.VK_S) {
					hero.dies();
				}
				if (k == KeyEvent.VK_LEFT) {
					hero.setdx(-hero.getSpeed());
				}
				if (k == KeyEvent.VK_RIGHT) {
					hero.setdx(hero.getSpeed());
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

	public void keyReleased(KeyEvent key) {
		if (hero.alive() == true) {
			k = key.getKeyCode();
			if (k == KeyEvent.VK_UP) {
				hero.setdy(0);
			}
			if (k == KeyEvent.VK_DOWN) {
				hero.setdy(0);
			}
			if (k == KeyEvent.VK_LEFT) {
				hero.setdx(0);
			}
			if (k == KeyEvent.VK_RIGHT) {
				hero.setdx(0);
			}
		}
	}

}
