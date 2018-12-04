import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Drone extends JLabel implements KeyListener 
{
    
    private Image image;
    public int x, y, height, width;
    private Laser laser;
    private boolean firstShot;
    
   // public Rectangle bounding;
    private Timer tUp, tDown, tRight, tLeft;
    
    public Drone(int x, int y) 
    {
    	this.x = x;
    	this.y = y;
    	
    	ImageIcon i = new ImageIcon("pics\\drone2.png");
    	image = i.getImage();
    	height = 80;
    	width = 100;
        laser = new Laser(x + (width / 2), y + (height / 2));
        firstShot = false;
        

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
			Graphics2D g2 = (Graphics2D) g;
    		laser.paint(g2);
    		g2.drawImage(image, x, y, width, height, null);
    }

    public void reset()
    {
    	x = 50;
    	y = 150;
    	laser.setX(x + (width / 2));
    	laser.setY(y + (height / 2));
    	if (!laser.isShooting())
    		resetLaser();
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!laser.isShooting())
				firstShot = true;
			laser.startMove();	
			firstShot = false;
		}
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
    	laser.setX(x + (width / 2));
    	if (!laser.isShooting())
    		laser.setXMove(laser.getXMove() + lateral);
    }
    
    public void changeY(int vertical)
    {
    	y += vertical;
    	laser.setY(y + (height / 2));
    	if (!laser.isShooting())
    		laser.setYMove(laser.getYMove() + vertical);
    }   
    
    public void resetLaser() {
    	laser.setX(x + (width / 2));
    	laser.setXMove(x + (width / 2));
    	laser.setY(y + (height / 2));
    	laser.setYMove(y + (height / 2));
    	laser.stopMove();
    }
    
    public boolean laserHit(Airplane plane) {
    	int xLeft, xRight, yTop, yBottom;
    	xLeft = plane.getX();
    	xRight = xLeft + 150;
    	yTop = plane.getY();
    	yBottom = yTop + 100;
    	if (laser.getXMove() > xLeft && laser.getXMove() < xRight &&laser.getYMove() < yBottom && laser.getYMove() > yTop) {
    		resetLaser();
    		return true;
    	}
    	return false;
    } 
    
    public boolean firstShot() {
    	return firstShot;
    }
}