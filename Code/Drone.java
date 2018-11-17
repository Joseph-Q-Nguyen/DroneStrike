import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Drone extends JLabel implements KeyListener 
{
    
    private Image image;
    private int x, y;
    
    public Drone(int x, int y) 
    {
    	this.x = x;
    	this.y = y;
    	
    	ImageIcon i = new ImageIcon("Files/drone2.png");
    	image = i.getImage();
        this.setPreferredSize(new Dimension(100, 80));
       
        addKeyListener(this);
    }

    public void addNotify() 
    {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) 
    {
    	g.drawImage(image, x, y, null);
    }

    public void keyPressed(KeyEvent e) 
    { 
    	if(e.getKeyCode() == KeyEvent.VK_UP)
        {
        	changeY(-5);
        	repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
        	changeY(5);
        	repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
        	changeX(5);
        	repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
        	changeX(-5);
        	repaint();
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
    
    public void changeX(int lateral)
    {
    	x += lateral;
    }
    
    public void changeY(int vertical)
    {
    	y += vertical;
    }
    
}