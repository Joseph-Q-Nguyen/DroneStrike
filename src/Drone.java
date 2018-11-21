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
    
    public Drone(int x, int y) 
    {
    	this.x = x;
    	this.y = y;
    	//emeny = new Airplane(100, 100, 150, 25);
    	collision = false;
    	
    	ImageIcon i = new ImageIcon("drone2.png");
    	image = i.getImage();
    	height = 80;//image.getHeight(null);
    	width = 100;//image.getWidth(null);
    	
    	xd = x + width;
    	yd = y + height;
    	
    	
    	//this.setBounds(new Rectangle(x, y, width, height));
        this.setPreferredSize(new Dimension(0, 0));
        addKeyListener(this);
    }

    public void addNotify() 
    {
        super.addNotify();
        requestFocus();
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
    	if(e.getKeyCode() == KeyEvent.VK_UP)
        {
        	changeY(-10);
        	changeYD(-10);
        	if(y <= 0)
        	{
        		y = 0;
        	}
        	if(yd <= 80)
        	{
        		yd = 80;
        	}
        	repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
        	changeY(10);
        	changeYD(10);
        	if(y >= 375)
        	{
        		y = 375;
        	}
        	if(yd >= 435)
        	{
        		yd = 435;
        	}
        	repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
        	changeX(10);
        	changeXD(10);
        	if(x >= 875)
        	{
        		x = 875;
        	}
        	if(xd >= 975)
        	{
        		xd = 975;
        	}
        	repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
        	changeX(-10);
        	changeXD(-10);
        	if(x <= 0)
        	{
        		x = 0;
        	}
        	if(xd <= 100)
        	{
        		xd = 100;
        	}
        	repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_R)
        {
        	reset();
        }
        
    }
    
    public void keyReleased(KeyEvent e) 
    { 
    	
    }
    
    public void keyTyped(KeyEvent e) 
    {
    	
    }

    public int getY()
    {
    	return y;
    }
    
    public int getX()
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