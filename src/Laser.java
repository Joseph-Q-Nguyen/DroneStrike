import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Laser extends JComponent {
	private int x, y;
	private int xMove , yMove;
	private Timer tMove;
	private boolean isShooting;
	
	public Laser(int x, int y) {
		isShooting = false;
		this.x = x;
		this.y = y;
		xMove = x;
		yMove = y;
		
		int delay = 10;
		tMove = new Timer(delay, event -> {
			move();
			//System.out.println(xMove);
			if (xMove > DroneGame.GAME_WIDTH)
				reset();
		});
	}
	
	public void move() {
		xMove+=5;
		repaint();
	}
	
	public void startMove() {
		isShooting = true;
		tMove.start();
	}
	
	public void stopMove() {
		isShooting = false;
		tMove.stop();
	}
	
	public void reset() {
		isShooting = false;
		xMove = x;
		yMove = y;
		tMove.stop();
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setXMove(int x) {
		xMove = x;
	}
	
	public void setYMove(int x) {
		yMove = y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getXMove() {
		return xMove;
	}
	
	public int getYMove() {
		return yMove;
	}
	
	public boolean isShooting() {
		return isShooting;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		g2.fill(new Ellipse2D.Double(xMove, yMove, 10, 10));
	}
}
