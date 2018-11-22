import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Drone extends JLabel implements KeyListener 
{
	private Image image;
	private int x, y, width, height;
	private Timer tUp, tDown, tRight, tLeft;

	public Drone(int x, int y) 
	{
		this.x = y;
		this.x = y;

		ImageIcon i = new ImageIcon("Files\\drone2.png");
		image = i.getImage();
		this.setPreferredSize(new Dimension(1, 1));
//		this.setBounds(0, 0, 0, 0);

		this.width = image.getWidth(this);
		this.height = image.getHeight(this);
		
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
		((Graphics2D)g).drawImage(image, x, y, width, height, this);
		
		//g.drawImage(image, x, y, width, height, this);
	}

	public void keyPressed(KeyEvent e) 
	{ 
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			
			if(y <= 0)
        	{
        		y = 0;
        		
        	}
			else
			{
				tUp.start();
			}
        	
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			
			if(y >= 375)
        	{
        		y = 375;
        	}
			else
			{
				tDown.start();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			
			if(x >= 875)
        	{
        		x = 875;
        	}
			else
			{
				tRight.start();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			
			if(x <= 0)
        	{
        		x = 0;
        	}
			else
			{
				tLeft.start();
			}
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

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void reset()
	{
		x = 50;
		y = 250;
	}
	
	public void changeX(int lateral)
	{
		x += lateral;
		if(x == 0)
		{
			x = 0;
		}
	}

	public void changeY(int vertical)
	{
		y += vertical;
	}

	public boolean checkBounds()
	{
		return true;
	}
	
}
