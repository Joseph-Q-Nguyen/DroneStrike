import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Drone extends JLabel implements KeyListener 
{
	private Image image;
	private int droneX, droneY;
	private Timer tUp, tDown, tRight, tLeft;

	public Drone(int droneX, int droneY) 
	{
		this.droneX = droneX;
		this.droneY = droneY;

		ImageIcon i = new ImageIcon("pics\\drone2.png");
		image = i.getImage();
		this.setPreferredSize(new Dimension(1, 1));
//		this.setBounds(0, 0, 0, 0);

		addKeyListener(this);

		int delay = 10;
		tUp = new Timer(delay, event -> {
			changeY(-10);
			repaint();
		});
		tDown = new Timer(delay, event -> {
			changeY(10);
			repaint();
		});
		tRight = new Timer(delay, event -> {
			changeX(10);
			repaint();
		});
		tLeft = new Timer(delay, event -> {
			changeX(-10);
			repaint();
		});
	}
	

	public void addNotify() 
	{
		super.addNotify();
		requestFocus();
	}

	public void paintComponent(Graphics g) 
	{
	//	Graphics2D g2 = (Graphics2D)g;
		((Graphics2D)g).drawImage(image, droneX, droneY, 100, 80, this);
	}

	public void keyPressed(KeyEvent e) 
	{ 
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			tUp.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			tDown.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			tRight.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			tLeft.start();
		}

	}

	public void keyReleased(KeyEvent e) 
	{ 
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			tUp.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			tDown.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			tRight.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			tLeft.stop();
		}
	}

	public void keyTyped(KeyEvent e) 
	{

	}

	public int getDroneY()
	{
		return droneY;
	}

	public int getDroneX()
	{
		return droneX;
	}

	public void moveRight()
	{
		int delay = 10;
		Timer t = new Timer(delay, event -> {
			changeX(1);
			repaint();
		});
		t.start();
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
