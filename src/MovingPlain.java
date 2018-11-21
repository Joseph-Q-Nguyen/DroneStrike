import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// Joseph
public class MovingPlain extends JPanel {
	private Ellipse2D cloud1, cloud2, cloud3;
	private BufferedImage sky, skyFlipped;
	private int skyX, skyFX; 		// x coordinates of pictures
	
	public MovingPlain() throws IOException {
		cloud1 = new Ellipse2D.Double(DroneGame.GAME_WIDTH - 100, 0, 55, 20);
		cloud2 = new Ellipse2D.Double(DroneGame.GAME_WIDTH - 100, 100, 70, 30);
		cloud3 = new Ellipse2D.Double(DroneGame.GAME_WIDTH - 100, 400, 100, 20);
		sky = ImageIO.read(new File("SimpleSky2.png"));
		skyX = 0;
		skyFlipped = ImageIO.read(new File("SimpleSky2_flipped.png"));
		skyFX = 1000;
	
		int delay = 10;
		Timer t = new Timer(delay, event -> {
			move();
			repaint();
		});
		t.start();
	}
	
	public void move() {
		if (cloud1.getX() <= - cloud1.getWidth()) // if clouds get off frame, put them back on the right
			cloud1 = new Ellipse2D.Double(DroneGame.GAME_WIDTH, 0, 55, 20);
		else
			cloud1 = new Ellipse2D.Double(cloud1.getX() - 1, 0, 55, 20);
		
		if (cloud2.getX() <= - cloud2.getWidth())
			cloud2 = new Ellipse2D.Double(DroneGame.GAME_WIDTH, 100, 70, 30);
		else
			cloud2 = new Ellipse2D.Double(cloud2.getX() - 2, 100, 70, 30);
		
		if (cloud3.getX() <= - cloud3.getWidth())
			cloud3 = new Ellipse2D.Double(DroneGame.GAME_WIDTH, 400, 100, 20);
		else
			cloud3 = new Ellipse2D.Double(cloud3.getX() - 3, 400, 100, 20);
		if (skyX <= -1000)  // if the pictures move out of frame, put them back on the right
			skyX = 1000;
		else
			skyX--;
		if (skyFX <= -1000)
			skyFX = 1000;
		else
			skyFX--;	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(sky, null, skyX, 0);
		g2.drawImage(skyFlipped, null, skyFX, 0);
		g2.setColor(Color.WHITE);
		g2.fill(cloud1);
		g2.fill(cloud2);
		g2.fill(cloud3);
		for (Component c : getComponents())
		{
			if(c.getClass() != Time.class && c.getClass() != Scores.class)
				c.paint(g2);
		}
	}
}