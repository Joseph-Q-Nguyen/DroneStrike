import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cloud {
	private BufferedImage cloud;
	private int x, y, width, height;

	public Cloud(int x, int y, int width, int height, String filePath) throws IOException {
		this.x = x; this.y = y;
		this.width = width; this.height = height;
		cloud = ImageIO.read(new File(filePath));
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void translate(int x) {
		this.x += x;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(cloud, x, y, width, height, null);
	}

}
