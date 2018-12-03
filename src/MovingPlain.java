import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// Joseph
public class MovingPlain extends JPanel {
	private Cloud[] 		clouds;
	private Sky 			sky, skyFlipped;	
	private Random 			gen;
		
	public MovingPlain() throws IOException {
		gen = 			new Random();
		clouds = 		new Cloud[3];
		sky = 			new Sky(0, "pics\\SimpleSky2.png");
		skyFlipped = 	new Sky(DroneGame.GAME_WIDTH, "pics\\SimpleSky2_flipped.png");
		
		clouds[0] = new Cloud(DroneGame.GAME_WIDTH - 100, 0, 55, 20, "pics\\cloud.png");
		clouds[1] = new Cloud(DroneGame.GAME_WIDTH - 100, 100, 300, 100, "pics\\cloud.png");
		clouds[2] = new Cloud(DroneGame.GAME_WIDTH - 100, 200, 200, 50, "pics\\cloud.png");
		
		int delay = 10;
		Timer t = new Timer(delay, event -> {
			move();
			repaint();
		});
		t.start();
	}
	
	public void move() {
		clouds[0].translate(-1);
		clouds[1].translate(-2);
		clouds[2].translate(-3);
	
		sky.translate(-1);
		skyFlipped.translate(-1);
		
		for (Cloud c: clouds)
			if (c.getX() <= -c.getWidth()) { // if clouds get off frame, put them back on the right
				c.setX(DroneGame.GAME_WIDTH);
				c.setY(gen.nextInt(DroneGame.GAME_HEIGHT - 200));
			}
	
		if (sky.getX() <= -DroneGame.GAME_WIDTH)  // if the pictures move out of frame, put them back on the right
			sky.setX(DroneGame.GAME_WIDTH);
		if (skyFlipped.getX() <= -DroneGame.GAME_WIDTH)
			skyFlipped.setX(DroneGame.GAME_WIDTH);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		sky.paintSky(g2);
		skyFlipped.paintSky(g2);
		for (Cloud c: clouds)
			c.paint(g2);
		for (Component c : getComponents())
			if(c.getClass() != Time.class && c.getClass() != Scores.class && c.getClass() != Lives.class)
				c.paint(g2);
	}
}