import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank2 extends TankSprite {
	public int dx;
	public int dy;
	public int d = 0;
	public int c = 0;
	double angle;
	boolean moveable;
	boolean moveFront;
	public int prevX;
	public int prevY;
	public ArrayList<Bullet> josh = new ArrayList<Bullet>();

	public Tank2(int x, int y) {
		super(x, y);
		initTank2();
	}// closes the constructor

	private void initTank2() {
		loadImage("redT.png");
		getImageDimensions();
	}// closes the initAlien method

	public void changedir(int x, int y) {
		dx = x;
		dy = y;
	}

	public void reverse() {
		dx = -dx;
		dy = -dy;
		x += dx * 2;
		y += dy * 2;
	}

	public void moveBack() {
		x = prevX;
		y = prevY;
	}

	public void move() {
		prevX = x;
		prevY = y;
		if (moveable) {
			if (x + dx > 1 && x + 13 + dx < 1000)
				x += dx;
			if (y + dy > 1 && y + 13 + dy < 1000)
				y += dy;
			// 1* Math.cos(Math.abs(angle)*180/Math.PI);
		}

		// System.out.println("x: "+x+" y:"+y);
	}// closes the method

	// closes the class
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		// Left and Right "angle" values were switched to fix rotation
		if (key == KeyEvent.VK_LEFT) {
			if (moveFront) {
				dx = ((int) Math.round(2 * Math.cos(Math.toRadians(angle))));
				dy = ((int) Math.round(2 * Math.sin(Math.toRadians(angle))));
			} else {
				dx = (int) -Math.round(2 * Math.cos(Math.toRadians(angle)));
				dy = (int) -Math.round(2 * Math.sin(Math.toRadians(angle)));
			}
			angle = angle - 5;
		}

		if (key == KeyEvent.VK_RIGHT) {
			if (moveFront) {
				dx = ((int) Math.round(2 * Math.cos(Math.toRadians(angle))));
				dy = ((int) Math.round(2 * Math.sin(Math.toRadians(angle))));
			} else {
				dx = (int) -Math.round(2 * Math.cos(Math.toRadians(angle)));
				dy = (int) -Math.round(2 * Math.sin(Math.toRadians(angle)));
			}
			angle = angle + 5;

		}

		if (key == KeyEvent.VK_UP) {
			dx = ((int) Math.round(2 * Math.cos(Math.toRadians(angle))));
			dy = ((int) Math.round(2 * Math.sin(Math.toRadians(angle))));
			moveFront = true;
			moveable = true;
		}

		if (key == KeyEvent.VK_DOWN) {
			dx = (int) -Math.round(2 * Math.cos(Math.toRadians(angle)));
			dy = (int) -Math.round(2 * Math.sin(Math.toRadians(angle)));
			c = -1;
			d = 0;
			moveable = true;
			moveFront = false;
		}

	}// closes the class

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {

		}

		if (key == KeyEvent.VK_RIGHT) {

		}

		if (key == KeyEvent.VK_UP) {
			dx = 0;
			dy = 0;
			moveable = false;
		}

		if (key == KeyEvent.VK_DOWN) {
			dx = 0;
			dy = 0;
			moveable = false;
		}
	}// closes the class

}// closes the class
