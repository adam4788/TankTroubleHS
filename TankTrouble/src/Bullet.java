import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Bullet extends TankSprite implements ActionListener {
	public int dx;
	public int dy;
	public int d = 0;
	public int c = 0;
	int dir = 0;
	private final int DELAY = 15;
	public Timer t = new Timer(DELAY, this);
	private int clockTk; // time in ticks
	public double clockTe; // time in seconds
	static int turretLength = 45;
	static double angle;

	public Bullet(int x, int y, int dx, int dy, double angle) {
		super(x, y);
		initBullet();
		this.dx = dx;
		this.dy = dy;
		this.angle = angle;
		turretLength = (int) Math.round(turretLength * 0.75);
	}

	public void actionPerformed(ActionEvent e) {
	}// closes the actionPerformed method

	private void initBullet() {
		loadImage("bullet.png");
		getImageDimensions();
		t.start();
	}

	public void changedir(int x, int y) {
		dx = x;
		dy = y;
	}

	public void move() {
		// Check to see if PacMan can move (eitherwise he stops at a wall)
		if (x + dx > 1 && x + 13 + dx < 975)
			x += dx;
		else
			dx = -dx;
		if (y + dy > 1 && y + 13 + dy < 975)
			y += dy;
		else
			dy = -dy;
		updateStatus();
	}

	public void updateStatus() {
		clockTk++;
		clockTe = ((double) clockTk) / 100.0;
		if (clockTe == 3) {
			setVisible(false);
			dx = 0;
			dy = 0;
			x = 1000000;
			y = 1000000;
		} // closes the if statement

	}// closes the method updateStatus
}
