import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TankTroubleBoard extends JPanel implements ActionListener {
	private Timer timer;
	private final int B_WIDTH = 1000;
	private final int B_HEIGHT = 1000;
	private boolean ingame;
	private final int DELAY = 15;
	private int score1 = 0;
	private int score2 = 0;
	private int score3 = 0;
	private Tank1 t1;
	private Tank2 t2;
	private Tank3 t3;
	ArrayList<Bullet> adam;
	ArrayList<Bullet> josh;
	ArrayList<Bullet> ben;
	int map;
	private Rectangle[] rects;
	private int clockTick;
	private double clockTime;

	public TankTroubleBoard() {
		initBoard();
		addKeyListener(new TAdapter());
		addMouseListener(new TAdapter());
		addMouseMotionListener(new TAdapter());
	}

	private void initBoard() {
		clockTick = 0;
		clockTime = 0;
		timer = new Timer(DELAY, this);
		adam = new ArrayList<Bullet>();
		josh = new ArrayList<Bullet>();
		ben = new ArrayList<Bullet>();
		setFocusable(true);
		timer.start();
		// setBackground(Color.BLACK);
		ingame = true;
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		Random generator = new Random();
		map = (generator.nextInt(5) + 1);
		createWalls(map);
		int[] Rpos = { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 6; i += 2) {
			if (map == 1) {
				Rectangle x = rects[(int) (Math.random() * 20 + 1)];
				Rpos[i] = (int) x.getX() + 10;
				Rpos[i + 1] = (int) x.getY() + 10;
			} else if (map == 2) {
				Rectangle x = rects[(int) (Math.random() * 30 + 1)];
				Rpos[i] = (int) x.getX() + 10;
				Rpos[i + 1] = (int) x.getY() + 10;
			} else if (map == 3) {
				Rectangle x = rects[(int) (Math.random() * 10 + 1)];
				Rpos[i] = (int) x.getX() + 10;
				Rpos[i + 1] = (int) x.getY() + 10;
			} else if (map == 4) {
				Rectangle x = rects[(int) (Math.random() * 20 + 1)];
				Rpos[i] = (int) x.getX() + 10;
				Rpos[i + 1] = (int) x.getY() + 10;
			} else if (map == 5) {
				Rectangle x = rects[(int) (Math.random() * 24 + 1)];
				Rpos[i] = (int) x.getX() + 10;
				Rpos[i + 1] = (int) x.getY() + 10;
			}
		}
		t1 = new Tank1(Rpos[0], Rpos[1]);
		t2 = new Tank2(Rpos[2], Rpos[3]);
		t3 = new Tank3(Rpos[4], Rpos[5]);
	}// closes the method initBoard()

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (ingame) {
			g.drawString("Score BLUE: " + score1, 25, 1000 - 25);
			g.drawString("Score RED: " + score2, 355, 1000 - 25);
			g.drawString("Score GREEN: " + score3, 695, 1000 - 25);
			g2d.setColor(Color.BLUE);

			drawMap(g);
			drawObjects(g2d);
		} else {
			drawGameOver(g);
		}
		Toolkit.getDefaultToolkit().sync();
		// MAKE THE WIDTHS 7 or 8 (give or take 1 000 000 000 000 000 000)

	}// closes the method

	private void drawObjects(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		if (adam.size() > 0)
			for (int i = 0; i < adam.size(); i++) {
				g2d.drawImage(adam.get(i).getImage(), adam.get(i).getX(), adam.get(i).getY(), this); // draw t1
			}

		if (josh.size() > 0)
			for (int i = 0; i < josh.size(); i++) {
				g2d.drawImage(josh.get(i).getImage(), josh.get(i).getX(), josh.get(i).getY(), this); // draw t1
			}

		if (ben.size() > 0)
			for (int i = 0; i < ben.size(); i++) {
				g2d.drawImage(ben.get(i).getImage(), ben.get(i).getX(), ben.get(i).getY(), this); // draw t1
			}

		if (t1.isVisible()) {
			g2d.rotate(Math.toRadians(t1.angle), t1.getX() + t1.centerX(), t1.getY() + t1.centerY());
			g2d.drawImage(t1.getImage(), t1.getX(), t1.getY(), this); // draw t1
			g2d.setTransform(transform);
			g2d.draw(new Rectangle(t1.getBounds())); // draw t1
		}
		if (t2.isVisible()) {
			g2d.rotate(Math.toRadians(t2.angle), t2.getX() + t2.centerX(), t2.getY() + t2.centerY());
			g2d.drawImage(t2.getImage(), t2.getX(), t2.getY(), this);// draw t2
			g2d.setTransform(transform);
		}
		if (t3.isVisible()) {
			g2d.rotate(t3.angle, t3.getX() + t3.centerX(), t3.getY() + t3.centerY());
			g2d.drawImage(t3.getImage(), t3.getX(), t3.getY(), this); // draw t3
			g2d.setTransform(transform);
		}
		// System.out.println("t3 angle: "+t3.angle+" t1dx:"+t1.dx+" t1dy:"+t1.dy+" "+
		// t1.angle);
		// System.out.println(t1.angle);
	}// closes the method

	private void drawGameOver(Graphics g) {
		String msg = "You ain't the best";
		Font small = new Font("Helvetica", Font.BOLD, 25);
		FontMetrics fm = getFontMetrics(small);
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}

	public void createWalls(int m) {
		if (map == 1) {
			rects = new Rectangle[] { new Rectangle(347, 406, 63, 10), // 1
					new Rectangle(347, 416, 10, 48), // 2
					new Rectangle(347, 464, 73, 10), // 3
					new Rectangle(410, 358, 10, 58), // 4
					new Rectangle(420, 406, 126, 10), // 5
					new Rectangle(599, 406, 73, 10), // 6
					new Rectangle(662, 358, 10, 48), // 7
					new Rectangle(473, 464, 199, 10), // 8
					new Rectangle(662, 474, 10, 63), // 9
					new Rectangle(536, 527, 73, 10), // 10
					new Rectangle(347, 527, 73, 10), // 11
					new Rectangle(347, 537, 10, 116), // 12
					new Rectangle(294, 643, 53, 10), // 13
					new Rectangle(410, 537, 10, 116), // 14
					new Rectangle(473, 527, 10, 116), // 15
					new Rectangle(473, 643, 63, 10), // 16
					new Rectangle(536, 600, 10, 53), // 17
					new Rectangle(536, 590, 73, 10), // 18
					new Rectangle(599, 600, 10, 53), // 19
					new Rectangle(662, 590, 10, 53), // 20
					new Rectangle(662, 643, 73, 10), // 21
					new Rectangle(284, 348, 451, 10), // top
					new Rectangle(284, 707, 451, 10), // bottom
					new Rectangle(284, 348, 10, 369), // left
					new Rectangle(725, 348, 10, 369),// right

			};
			System.out.println("map: " + map);

		} else if (map == 2) {
			rects = new Rectangle[] { new Rectangle(320, 260, 10, 70), // 1
					new Rectangle(390, 260, 10, 70), // 2
					new Rectangle(530, 260, 10, 70), // 4
					new Rectangle(670, 260, 10, 70), // 6
					new Rectangle(540, 320, 70, 10), // 12
					new Rectangle(750, 320, 70, 10), // 15
					new Rectangle(530, 330, 10, 70), // 19
					new Rectangle(670, 330, 10, 70), // 21
					new Rectangle(260, 390, 70, 10), // 23
					new Rectangle(470, 390, 70, 10), // 26
					new Rectangle(610, 390, 70, 10), // 28
					new Rectangle(390, 400, 10, 70), // 32
					new Rectangle(740, 400, 10, 70), // 37
					new Rectangle(400, 460, 70, 10), // 40
					new Rectangle(540, 460, 70, 10), // 42
					new Rectangle(320, 470, 10, 70), // 46
					new Rectangle(600, 470, 10, 70), // 50
					new Rectangle(670, 470, 10, 70), // 51
					new Rectangle(740, 470, 10, 70), // 52
					new Rectangle(400, 530, 70, 10), // 55
					new Rectangle(470, 530, 70, 10), // 56
					new Rectangle(320, 540, 10, 70), // 61
					new Rectangle(530, 540, 10, 70), // 64
					new Rectangle(670, 540, 10, 70), // 66
					new Rectangle(330, 600, 70, 10), // 69
					new Rectangle(540, 600, 70, 10), // 72
					new Rectangle(680, 600, 70, 10), // 74
					new Rectangle(460, 610, 10, 70), // 78
					new Rectangle(600, 610, 10, 70), // 80
					new Rectangle(670, 610, 10, 70), // 81
					new Rectangle(250, 250, 570, 10), // top
					new Rectangle(250, 670, 570, 10), // bottom
					new Rectangle(250, 250, 10, 430), // left
					new Rectangle(810, 250, 10, 430),// right

			};
			System.out.println("map: " + map);

		} else if (map == 3) {
			rects = new Rectangle[] { new Rectangle(370, 260, 10, 100), // 1
					new Rectangle(370, 360, 110, 10), // 2
					new Rectangle(370, 460, 110, 10), // 3
					new Rectangle(370, 460, 10, 100), // 4
					new Rectangle(260, 560, 120, 10), // 5
					new Rectangle(590, 360, 10, 110), // 6
					new Rectangle(590, 460, 110, 10), // 7
					new Rectangle(690, 260, 10, 110), // 8
					new Rectangle(590, 560, 110, 10), // 9
					new Rectangle(490, 560, 110, 10), // 10
					new Rectangle(490, 560, 10, 110), // 11
					new Rectangle(250, 250, 570, 10), // top
					new Rectangle(250, 670, 570, 10), // bottom
					new Rectangle(250, 250, 10, 430), // left
					new Rectangle(810, 250, 10, 430),// right

			};
			System.out.println("map: " + map);

		} else if (map == 4) {
			rects = new Rectangle[] { new Rectangle(335, 260, 10, 85), // 1
					new Rectangle(505, 260, 10, 85), // 3
					new Rectangle(345, 335, 85, 10), // 6
					new Rectangle(515, 335, 85, 10), // 8
					new Rectangle(505, 345, 10, 85), // 12
					new Rectangle(260, 420, 85, 10), // 14
					new Rectangle(345, 420, 85, 10), // 15
					new Rectangle(600, 420, 75, 10), // 18
					new Rectangle(505, 430, 10, 85), // 21
					new Rectangle(345, 505, 85, 10), // 24
					new Rectangle(430, 505, 85, 10), // 25
					new Rectangle(515, 505, 85, 10), // 26
					new Rectangle(505, 515, 10, 85), // 30
					new Rectangle(345, 590, 85, 10), // 33
					new Rectangle(600, 590, 75, 10), // 36
					new Rectangle(335, 590, 10, 95), // 37
					new Rectangle(260, 675, 85, 10), // 41
					new Rectangle(430, 675, 85, 10), // 43
					new Rectangle(515, 675, 85, 10), // 44
					new Rectangle(505, 685, 10, 85), // 48
					new Rectangle(250, 250, 435, 10), // top
					new Rectangle(250, 760, 435, 10), // bottom
					new Rectangle(250, 250, 10, 520), // left
					new Rectangle(675, 250, 10, 520),// right
			};
			System.out.println("map: " + map);

		} else {
			rects = new Rectangle[] { new Rectangle(550, 260, 10, 100), // 3
					new Rectangle(350, 350, 110, 10), // 6
					new Rectangle(460, 350, 100, 10), // 7
					new Rectangle(560, 350, 100, 10), // 8
					new Rectangle(660, 350, 100, 10), // 9
					new Rectangle(350, 360, 10, 100), // 10
					new Rectangle(550, 360, 10, 100), // 12
					new Rectangle(750, 360, 10, 100), // 13.5
					new Rectangle(450, 460, 10, 100), // 20
					new Rectangle(650, 460, 10, 100), // 22
					new Rectangle(260, 550, 100, 10), // 23
					new Rectangle(360, 550, 100, 10), // 24
					new Rectangle(660, 550, 100, 10), // 27
					new Rectangle(760, 550, 100, 10), // 27.5
					new Rectangle(350, 560, 10, 100), // 28
					new Rectangle(750, 560, 10, 100), // 31.5
					new Rectangle(460, 650, 100, 10), // 34
					new Rectangle(560, 650, 100, 10), // 35
					new Rectangle(350, 650, 10, 100), // 37
					new Rectangle(550, 660, 10, 100), // 39
					new Rectangle(750, 660, 10, 100), // 40.5
					new Rectangle(350, 750, 110, 10), // 42
					new Rectangle(660, 750, 100, 10), // 45
					new Rectangle(550, 760, 10, 100), // 48
					new Rectangle(250, 250, 610, 10), // top
					new Rectangle(250, 850, 610, 10), // bottom
					new Rectangle(250, 250, 10, 610), // left
					new Rectangle(850, 250, 10, 610),// right
			};
			System.out.println("map: " + map);
		}
	}

	private void drawMap(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GRAY);
		g2.fill(rects[0]);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(rects[1]);
		// g2.fill(rects[2]);
		g2.setColor(Color.GRAY);
		for (int i = 1; i < rects.length; i++) {
			g2.fill(rects[i]);
		}
	}

	public void actionPerformed(ActionEvent e) {
		inGame();
		updateCraft();
		checkCollisions();
		updateGame();
		repaint();
	}

	private void inGame() {
		if (!ingame) {
			timer.stop();
		}
	}

	private void updateCraft() {
		if (t1.isVisible())
			t1.move();
		if (t2.isVisible())
			t2.move();
		if (t3.isVisible())
			t3.move();
		if (adam.size() > 0)
			for (int i = 0; i < adam.size(); i++) {
				if (adam.get(i).isVisible())
					adam.get(i).move();

				if (adam.get(i).isVisible() == false)
					adam.remove(i);
			}

		if (josh.size() > 0)
			for (int i = 0; i < josh.size(); i++) {
				if (josh.get(i).isVisible())
					josh.get(i).move();

				if (josh.get(i).isVisible() == false)
					josh.remove(i);
			}

		if (ben.size() > 0)
			for (int i = 0; i < ben.size(); i++) {
				if (ben.get(i).isVisible())
					ben.get(i).move();

				if (ben.get(i).isVisible() == false)
					ben.remove(i);
			}

	}

	public void checkCollisions() {
		Rectangle r3 = t1.getBounds();
		Rectangle r4 = t2.getBounds();
		Rectangle r5 = t3.getBounds();

		for (int i = 0; i < rects.length; i++) {
			if (r3.intersects(rects[i])) {
				t1.moveBack();
			}
			if (r4.intersects(rects[i])) {
				t2.moveBack();
			}
			if (r5.intersects(rects[i])) {// tank 3 cant work...
				t3.moveBack();
			}
			for (int j = 0; j < adam.size(); j++) {
				Rectangle b = adam.get(j).getBounds();
				if (b.intersects(rects[i])) {
					if (rects[i].height > 10)
						adam.get(j).changedir(adam.get(j).dx * -1, adam.get(j).dy);
					else
						adam.get(j).changedir(adam.get(j).dx, adam.get(j).dy * -1);
				}
				if (b.intersects(r3)) {
					// adam.remove(i); //Why are the adam immediately removed
					t1.setVisible(false); // Why are the tanks not dying
				} // closes the if statement
				if (b.intersects(r4)) {
					// adam.remove(i);
					t2.setVisible(false);
				} // closes the second if statemnent
				if (b.intersects(r5)) {
					// adam.remove(i);
					t3.setVisible(false);
					// t3.changeloc(1000, 1000); //Code for Tank3 "dying"
				}
			}

			for (int j = 0; j < josh.size(); j++) {
				Rectangle b = josh.get(j).getBounds();
				if (b.intersects(rects[i])) {
					if (rects[i].height > 10)
						josh.get(j).changedir(josh.get(j).dx * -1, josh.get(j).dy);
					else
						josh.get(j).changedir(josh.get(j).dx, josh.get(j).dy * -1);
				}
				if (b.intersects(r3)) {
					// josh.remove(i); //Why are the josh immediately removed
					t1.setVisible(false); // Why are the tanks not dying
				} // closes the if statement
				if (b.intersects(r4)) {
					// josh.remove(i);
					t2.setVisible(false);
				} // closes the second if statemnent
				if (b.intersects(r5)) {
					// josh.remove(i);
					t3.setVisible(false);
					// t3.changeloc(1000, 1000); //Code for Tank3 "dyinqqqg"
				}
			}

			for (int j = 0; j < ben.size(); j++) {
				Rectangle b = ben.get(j).getBounds();
				if (b.intersects(rects[i])) {
					if (rects[i].height > 10)
						ben.get(j).changedir(ben.get(j).dx * -1, ben.get(j).dy);
					else
						ben.get(j).changedir(ben.get(j).dx, ben.get(j).dy * -1);
				}
				if (b.intersects(r3)) {
					// ben.remove(i); //Why are the ben immediately removed
					t1.setVisible(false); // Why are the tanks not dying
				} // closes the if statement
				if (b.intersects(r4)) {
					// ben.remove(i);
					t2.setVisible(false);
				} // closes the second if statemnent
				if (b.intersects(r5)) {
					// ben.remove(i);
					t3.setVisible(false);
					// t3.changeloc(1000, 1000); //Code for Tank3 "dying"
				}
			}

		}
	}

	private void updateGame() {

		// If statement to check if tank3 is the only tank left alive
		if (t1.isVisible() == false && t2.isVisible() == false && t3.isVisible()) {
			clockTick++;
			clockTime = ((double) clockTick) / 100.0;
		}

		// If statement to check if tank2 is the only tank left alive
		if (t1.isVisible() == false && t3.isVisible() == false && t2.isVisible()) {
			clockTick++;
			clockTime = ((double) clockTick) / 100.0;
		}

		// If statement to check if tank1 is the only tank left alive
		if (t2.isVisible() == false && t3.isVisible() == false && t1.isVisible()) {
			clockTick++;
			clockTime = ((double) clockTick) / 100.0;
		}

		// If statement to check if tank3 wins the round
		if (clockTime >= 5 && t3.isVisible()) {
			score3 = score3 + 1;
			timer.stop();
			for (int i = 0; i < adam.size(); i++) {
				adam.get(i).clockTe = 10;
			}
			for (int i = 0; i < josh.size(); i++) {
				josh.get(i).clockTe = 10;
			}
			for (int i = 0; i < ben.size(); i++) {
				ben.get(i).clockTe = 10;
			}
			initBoard();
		}

		// If statement to check if tank2 wins the round
		if (clockTime >= 5 && t2.isVisible()) {
			score2 = score2 + 1;
			timer.stop();
			for (int i = 0; i < adam.size(); i++) {
				adam.get(i).clockTe = 10;
			}
			for (int i = 0; i < josh.size(); i++) {
				josh.get(i).clockTe = 10;
			}
			for (int i = 0; i < ben.size(); i++) {
				ben.get(i).clockTe = 10;
			}
			initBoard();
		}

		// If statement to check if tank1 wins the round
		if (clockTime >= 5 && t1.isVisible()) {
			score1 = score1 + 1;
			timer.stop();
			for (int i = 0; i < adam.size(); i++) {
				adam.get(i).clockTe = 10;
			}
			for (int i = 0; i < josh.size(); i++) {
				josh.get(i).clockTe = 10;
			}
			for (int i = 0; i < ben.size(); i++) {
				ben.get(i).clockTe = 10;
			}
			initBoard();
		}
		// If statement to check if all three tanks have died
		if (t1.isVisible() == false && t2.isVisible() == false && t3.isVisible() == false) {
			timer.stop();
			for (int i = 0; i < adam.size(); i++) {
				adam.get(i).clockTe = 10;
			}
			for (int i = 0; i < josh.size(); i++) {
				josh.get(i).clockTe = 10;
			}
			for (int i = 0; i < ben.size(); i++) {
				ben.get(i).clockTe = 10;
			}
			initBoard();
		}

		// clockTime = ((double) clockTick)/100.0;
	}// closes the method updateGame()

	private class TAdapter extends KeyAdapter implements MouseListener, MouseMotionListener {

		public void keyReleased(KeyEvent e) {

			t1.keyReleased(e);
			t2.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_Q) {
				if (t1.isVisible() && adam.size() < 5) {
					int bulletX = (int) (t1.x + t1.centerX() + Math.round(2 * Math.cos(Math.toRadians(t1.angle))) * 20);
					int bulletY = (int) (t1.y + t1.centerY() + Math.round(2 * Math.sin(Math.toRadians(t1.angle))) * 20);
					// bullets.add(new Bullet(t1.x, t1.y, (int)
					// Math.round(2*Math.cos(Math.toRadians(t1.angle)))*3, (int)
					// Math.round(2*Math.sin(Math.toRadians(t1.angle)))*3,t1.angle));
					adam.add(new Bullet(bulletX, bulletY, (int) Math.round(2 * Math.cos(Math.toRadians(t1.angle))) * 2,
							(int) Math.round(2 * Math.sin(Math.toRadians(t1.angle))) * 2, Math.toRadians(t1.angle)));
				}
			}
			if (key == KeyEvent.VK_M) {
				if (t2.isVisible() && josh.size() < 5) {
					int bulletX = (int) (t2.x + t2.centerX() + Math.round(2 * Math.cos(Math.toRadians(t2.angle))) * 20);
					int bulletY = (int) (t2.y + t2.centerY() + Math.round(2 * Math.sin(Math.toRadians(t2.angle))) * 20);
					josh.add(new Bullet(bulletX, bulletY, (int) Math.round(2 * Math.cos(Math.toRadians(t2.angle))) * 2,
							(int) Math.round(2 * Math.sin(Math.toRadians(t2.angle))) * 2, Math.toRadians(t2.angle)));

				}
			}
			t1.keyPressed(e);
			t2.keyPressed(e);
		}

		public void mouseMoved(MouseEvent e) {
			t3.mouseMoved(e);
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (t3.isVisible() && ben.size() < 5) {
				int bulletX = (int) (t3.x + t3.centerX() + Math.round(2 * Math.cos(t3.angle)) * 20);
				int bulletY = (int) (t3.y + t3.centerY() + Math.round(2 * Math.sin(t3.angle)) * 20);
				ben.add(new Bullet(bulletX, bulletY, ((int) Math.round(2 * Math.cos(t3.angle))) * 2,
						((int) Math.round(2 * Math.sin(t3.angle))) * 2, t3.angle));
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}// closes the class
