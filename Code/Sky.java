import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sky {
	private BufferedImage sky;
	private int x;
	
	public Sky(int x, String filePath) throws IOException {
		this.x = x;
		sky = ImageIO.read(new File(filePath));
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void translate(int x) {
		this.x += x;
	}
	
	public void paintSky(Graphics g) {
		((Graphics2D)g).drawImage(sky, null, x, 0);
	}
}

