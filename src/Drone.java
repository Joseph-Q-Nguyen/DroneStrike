import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Drone extends JLabel implements KeyListener 
{
    
    private Image image;
    public int x, y, height, width, xd, yd;
    private Airplane emeny;
    private boolean collision;
    public Rectangle bounding;
    private Timer tUp, tDown, tRight, tLeft;
    
    public Drone(int x, int y) 
    {
    	this.x = x;
    	this.y = y;
    	//emeny = new Airplane(100, 100, 150, 25);
    	collision = false;
    	
    	ImageIcon i = new ImageIcon("pics\\drone2.png");
    	image = i.getImage();
    	height = 80;//image.getHeight(null);
    	width = 100;//image.getWidth(null);
    	
    	xd = x + width;
    	yd = y + height;
    	
    	
    	//this.setBounds(new Rectangle(x, y, width, height));
        this.setPreferredSize(new Dimension(0, 0));
        addKeyListener(this);
        
		int delay = 10;
		
	    Timer t = new Timer(delay * 10, event -> {
	    	if (!isTouchingRight()) 
	    		changeX(1);
	    	repaint();
	    });
	    t.start();
		
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
		if (y <= 0)
			return true;
		return false;
	}

	public boolean isTouchingBottom() 
	{
		if (y + height >= DroneGame.GAME_HEIGHT - 40) // -40 offset
			return true;
		return false;
	}

	public boolean isTouchingRight() 
	{
		if (x + width >= DroneGame.GAME_WIDTH - 20) // -20 offset
			return true;
		return false;
	}

	public boolean isTouchingLeft() 
	{
		if (x <= 0)
			return true;
		return false;
	}

    public void paintComponent(Graphics g) 
    {
    		g.drawImage(image, x, y, width, height, null);
    		
    		bounding = new Rectangle(x, y, width, height);
    		Graphics2D g2 = (Graphics2D) g;
    		g2.draw(bounding);
    		//repaint();
    }

    public void reset()
    {
    	x = 50;
    	y = 150;
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

    public int getYs()
    {
    	return y;
    }
    
    public int getXs()
    {
    	return x;
    }
    
    public int getYD()
    {
    	return yd;
    }
    
    public int getXD()
    {
    	return xd;
    }
    
    public int getHeight()
    {
    	return height;
    }
    
    public int getWidth()
    {
    	return width;
    }
    
    public void changeX(int lateral)
    {
    	x += lateral;
    }
    
    public void changeY(int vertical)
    {
    	y += vertical;
    }
    
    public void changeYD(int crossY)
    {
    	yd += crossY;
    }
    
    public void changeXD(int crossX)
    {
    	xd += crossX;
    }
    
    public boolean getCollision()
    {
    	return collision;
    }
    
    public void setCollision(boolean c)
    {
    	collision = c;
    }
    
    
    
}