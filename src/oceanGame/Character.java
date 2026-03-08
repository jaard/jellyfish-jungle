package oceanGame;

import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Character extends MovingObjects implements Serializable {

	private boolean exists = true;
	private boolean eats = false;
	private boolean blinks = false;
	private int speed = 6;
	private int life = 3;
	private Animation eating;
	private Animation blinking;

	public Character() {

		this.loadPicture("/resources/seal_swimming.png");
		width = sprite.getImage().getWidth(null);
		height = sprite.getImage().getHeight(null);
		currentImage = sprite;
		loadAnimations();

	}

	public void loadAnimations() {

		eating = new Animation();
		eating.addScene(
				new ImageIcon(getClass().getResource(
						"/resources/seal_eating.png")), 140);
		eating.addScene(sprite, 1000000000);

		ImageIcon sealRed = new ImageIcon(getClass().getResource(
				"/resources/seal_red.png"));

		blinking = new Animation();
		blinking.addScene(sealRed, 100);
		blinking.addScene(sprite, 100);
		blinking.addScene(sealRed, 100);
		blinking.addScene(sprite, 100);
		blinking.addScene(sealRed, 100);
		blinking.addScene(sprite, 1000000000);

	}

	public void animationUpdate(int fps) {

		if (alive()) {

			if (eats == true) {
				eating.update(1000 / fps);
				currentImage = eating.getImage();
				if (eating.getNumberOfScenes() == eating.getSceneIndex() + 1) {
					eats = false;
				}
			}
			if (blinks == true) {
				blinking.update(1000 / fps);
				currentImage = blinking.getImage();
				if (blinking.getNumberOfScenes() == blinking.getSceneIndex() + 1) {
					blinks = false;
					System.out.println(blinks);
				}
			}
		} else {

			currentImage = new ImageIcon(getClass().getResource(
					"/resources/seal_dead.png"));

		}

	}

	public void eats() {
		eats = true;
		eating.start();
	}

	public void blinks() {
		blinks = true;
		blinking.start();
	}

	public void dies() {
		exists = false;
		// try {
		// currentImage = new
		// ImageIcon(getClass().getResource("/resources/seal_dead.png"));
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		//
		// }
		this.dy = 3;
		this.dx = 0;

	}

	public boolean alive() {
		if (exists == true) {
			return true;
		} else {
			return false;
		}
	}

	public void positionUpdate() {

		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (y + (height + 20) > 600 & exists == true) {
			y = 600 - (height + 20);
		}

		else {
			this.x += dx;
			this.y += dy;
		}

	}

	public int getSpeed() {
		return speed;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int l) {
		life = l;
	}

	public void decLife() {
		life -= 1;
	}

	public void setExists(boolean e) {
		exists = e;
	}

}
