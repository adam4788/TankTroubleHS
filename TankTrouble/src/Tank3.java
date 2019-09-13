import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Tank3 extends TankSprite {
	public int dx;
	public int dy;
	public int d = 0;
	public int c = 0;
	int dir = 0;
	int centerX = width / 2;
	int centerY = height / 2;
	double mouseY = 0;
	double mouseX = 0;
	double angle;
	public int prevX;
	public int prevY;
	public ArrayList<Bullet> ben = new ArrayList<Bullet>();

	public Tank3(int x, int y) {
		super(x, y);
		initTank3();
	}// closes the constructor

	private void initTank3() {
		loadImage("greenT.png");
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
		// Check to see if PacMan can move (eitherwise he stops at a wall)
		prevX = x;
		prevY = y;
		if (x + dx > 1 && x + 13 + dx < 1000)
			x += dx;
		if (y + dy > 1 && y + 13 + dy < 1000)
			y += dy;

		// System.out.println("x: "+x+" y:"+y);
	}// closes the method

	// closes the class

	public void mouseMoved(MouseEvent e) {
		double dxi = e.getX() - super.x;
		double dyi = e.getY() - super.y;
		angle = Math.atan2(dyi, dxi);
		// dx=((int) Math.round(2*Math.cos(Math.toRadians(angle))));
		// dy=((int) Math.round(2*Math.sin(Math.toRadians(angle))));
		System.out.println(angle + "dx:" + dx + " dy:" + dy);

		if (x < e.getX()) {
			dx = 1;
		}
		if (x > e.getX()) {
			dx = -1;
		}
		if (y < e.getY()) {
			dy = 1;
		}
		if (y > e.getY()) {
			dy = -1;
		}

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (x < e.getX()) {
			dx = 1;
		}
		if (x > e.getX()) {
			dx = -1;
		}
		if (y < e.getY()) {
			dy = 1;
		}
		if (y > e.getY()) {
			dy = -1;
		}
	}
}// closes the class
