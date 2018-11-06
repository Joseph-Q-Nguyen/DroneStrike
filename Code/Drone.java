import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;

public class Drone implements Icon
{
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage image;
	
	
	/**
    Constructs a car item.
    @param x the left of the bounding rectangle
    @param y the top of the bounding rectangle
    @param width the width of the bounding rectangle
	 */
	public Drone(int x, int y, int width, int height) throws IOException
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		image = ImageIO.read(new File("Files/drone2.png"));
		
	}

	public void move()
	{
		x--;
	}

	//resets the position of the airplane back to the left hand side
	public void reset()
	{
		x = 425;
		y = 0;
	}

	//gets the x position of the airplane
	public int getX()
	{
		return x;
	}

	@Override
	public int getIconHeight() 
	{
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getIconWidth() 
	{
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int i, int j) 
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, null, 100, 50);
	
	}
	
	
}