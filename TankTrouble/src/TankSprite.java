import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class TankSprite {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean vis;
	private Image image;
	private String imagename;

	public TankSprite(int x, int y) {

		this.x = x;
		this.y = y;
		vis = true;
	}

	protected void loadImage(String imageName) {
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
		imagename = imageName;
	}

	protected void getImageDimensions() {

		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public int centerX() {
		return width / 2;

	}

	public int centerY() {
		return height / 2;

	}

	public String getimagename() {
		return imagename;
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void changeloc(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isVisible() {
		return vis;
	}

	public void setVisible(Boolean visible) {
		vis = visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
