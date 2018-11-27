import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Drone extends JLabel implements KeyListener 
{
	private BufferedImage image;
	private int droneX, droneY;
	private int width, height;
	public Rectangle bounding;
	private Timer tUp, tDown, tRight, tLeft;

	public Drone(int droneX, int droneY) throws IOException 
	{
		this.droneX = droneX;
		this.droneY = droneY;

		this.width = 100;
		this.height = 80;

		image = ImageIO.read(new File("pics\\drone2.png"));

		addKeyListener(this);

		int delay = 10;
		tUp = new Timer(delay, event -> {
			if (!isTouchingTop())
				changeY(-10);
			repaint();
		});
		tDown = new Timer(delay, event -> {
			if (!isTouchingBottom())
				changeY(10);
			repaint();
		});
		tRight = new Timer(delay, event -> {
			if (!isTouchingRight())
				changeX(10);
			repaint();
		});
		tLeft = new Timer(delay, event -> {
			if (!isTouchingLeft())
				changeX(-10);
			repaint();
		});
	}


	public void addNotify() 
	{
		super.addNotify();
		requestFocus();
	}

	public boolean isTouchingTop() 
	{
		if (droneY <= 0)
			return true;
		return false;
	}

	public boolean isTouchingBottom() 
	{
		if (droneY + height >= DroneGame.GAME_HEIGHT - 40) // -40 offset
			return true;
		return false;
	}

	public boolean isTouchingRight() 
	{
		if (droneX + width >= DroneGame.GAME_WIDTH - 20) // -20 offset
			return true;
		return false;
	}

	public boolean isTouchingLeft() 
	{
		if (droneX <= 0)
			return true;
		return false;
	}

	public void paint(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(image, null, droneX, droneY);
		bounding = new Rectangle(droneX, droneY, width, height);
		g2.draw(bounding);
	}

	public void keyPressed(KeyEvent e) 
	{ 
		if (e.getKeyCode() == KeyEvent.VK_UP)
			tUp.start();
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			tDown.start();
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			tRight.start();
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			tLeft.start();
	}

	public void keyReleased(KeyEvent e) 
	{ 
		if (e.getKeyCode() == KeyEvent.VK_UP)
			tUp.stop();
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			tDown.stop();
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			tRight.stop();
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			tLeft.stop();
	}

	public void keyTyped(KeyEvent e) {}

	public int getDroneY() 
	{
		return droneY;
	}

	public int getDroneX()
	{
		return droneX;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void changeX(int lateral)
	{
		droneX += lateral;
	}

	public void changeY(int vertical)
	{
		droneY += vertical;
	}

}
