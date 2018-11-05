import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// Joseph
public class MovingPlain extends JPanel {
	private Ellipse2D cloud1, cloud2, cloud3;
	
	public static void main(String [] args) {
		JFrame screen = new JFrame("Drone Strike");
		MovingPlain m = new MovingPlain();
		screen.add(m);
		
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(DroneGame.GAME_WIDTH , DroneGame.GAME_HEIGHT);
		screen.setVisible(true);
	}
	
	public MovingPlain() {
		cloud1 = new Ellipse2D.Double(DroneGame.GAME_WIDTH - 100, 0, 55, 20);
		cloud2 = new Ellipse2D.Double(DroneGame.GAME_WIDTH - 100, 100, 70, 30);
		cloud3 = new Ellipse2D.Double(DroneGame.GAME_WIDTH - 100, 400, 100, 20);
//		Airplane f15 = new Airplane(500, 250, 150, 25);
//		JLabel plane = new JLabel(f15);
//		this.add(plane);
		
		int delay = 10;
		Timer t = new Timer(delay, event -> {
			move();
			repaint();
		});
		t.start();
	}
	
	public void move() {
		if (cloud1.getX() <= - cloud1.getWidth())
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
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Rectangle2D.Double r = new Rectangle2D.Double(0, 0, DroneGame.GAME_WIDTH, DroneGame.GAME_HEIGHT);
		g2.setColor(Color.CYAN);
		g2.fill(r);
		g2.setColor(Color.WHITE);
		g2.fill(cloud1);
		g2.fill(cloud2);
		g2.fill(cloud3);
		for (Component c : getComponents())
			c.paint(g2);
	}
}