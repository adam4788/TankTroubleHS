import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank1 extends TankSprite {
	public int dx; // change in horizontal position
	public int dy; // change in vertical position
	public int d = 0; // what is this? a thing so it collides successfully
	public int c = 0; // what is this?
	boolean moveable;
	boolean moveFront;
	double angle;
	public int prevX;
	public int prevY;
	public ArrayList<Bullet> adam = new ArrayList<Bullet>();

	public Tank1(int x, int y) {
		super(x, y);
		initTank1();
	}// closes the constructor

	private void initTank1() {
		loadImage("blueT.png");
		getImageDimensions();
	}// closes the initAlien method

	public void changedir(int x, int y) {
		dx = x;
		dy = y;
	}

	public void moveBack() {
		x = prevX;
		y = prevY;
	}

	public void reverse() {
		dx = -dx;
		dy = -dy;
		x += dx * 2;
		y += dy * 2;
	}

	public void move() {
		prevX = x;
		prevY = y;
		if (moveable) {
			if (x + dx > 1 && x + 13 + dx < 975)
				x += dx;
			if (y + dy > 1 && y + 13 + dy < 975)
				y += dy;
			// 1* Math.cos(Math.abs(angle)*180/Math.PI);
		}

		// System.out.println("x: "+x+" y:"+y);
	}// closes the method

	public void keyPressed(KeyEvent e) {
		// dx=(int) Math.round(Math.ceil(1*Math.cos(angle)));dX =
		// Math.sin(Math.toRadians(angle)) * speed;
		// dY = -Math.cos(Math.toRadians(angle)) * speed;
		// dy=(int) Math.round(Math.ceil(1*Math.sin(angle)));
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			if (moveFront) {
				dx = ((int) Math.round(2 * Math.cos(Math.toRadians(angle))));
				dy = ((int) Math.round(2 * Math.sin(Math.toRadians(angle))));
			} else {
				dx = (int) -Math.round(2 * Math.cos(Math.toRadians(angle)));
				dy = (int) -Math.round(2 * Math.sin(Math.toRadians(angle)));
			}
			angle = angle - 5;

		}
		if (key == KeyEvent.VK_D) {

			if (moveFront) {
				dx = ((int) Math.round(2 * Math.cos(Math.toRadians(angle))));
				dy = ((int) Math.round(2 * Math.sin(Math.toRadians(angle))));
			} else {
				dx = (int) -Math.round(2 * Math.cos(Math.toRadians(angle)));
				dy = (int) -Math.round(2 * Math.sin(Math.toRadians(angle)));
			}
			angle = angle + 5;

		}

		if (key == KeyEvent.VK_W) // move forwards
		{
			dx = ((int) Math.round(2 * Math.cos(Math.toRadians(angle))));
			dy = ((int) Math.round(2 * Math.sin(Math.toRadians(angle))));
			moveFront = true;
			moveable = true;
		}

		if (key == KeyEvent.VK_S) // move backwards (subtract instead of add)
		{
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

		if (key == KeyEvent.VK_A) {

		}

		if (key == KeyEvent.VK_D) {

		}

		if (key == KeyEvent.VK_W) {
			dx = 0;
			dy = 0;
			moveable = false;
		}

		if (key == KeyEvent.VK_S) {
			dx = 0;
			dy = 0;
			moveable = false;
		}

	}
}// closes the class
