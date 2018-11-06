import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JLabel;

public class Airplane implements Icon
{
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	/**
    Constructs a car item.
    @param x the left of the bounding rectangle
    @param y the top of the bounding rectangle
    @param width the width of the bounding rectangle
	 */
	public Airplane(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	//draws the nose of the airplane
	public void drawNose(Graphics2D nose)
	{
		nose.fillPolygon(new int[] {x, x, x - (width/5)}, new int[] {y + width / 3, y + width / 6, y + width/6 +10}, 3);
	}

	//draws the front wing of the airplane
	public void drawFrontWing(Graphics2D fWing)
	{	
		fWing.fillPolygon(new int[] {x + 80, x + 30, x + width - 15}, new int[] {y + width / 4, y + width / 4, y + width * 3 / 5},  3);
	}

	//draws the back wing of the airplane
	//for the back, I put some small subtraction/addition integers to try to create the 
	//airplane with a more "3-D"-ish effect or give it some depth perception
	public void drawBackWing(Graphics2D bWing)
	{
		bWing.fillPolygon(new int[] {x + 95, x + 45, x - 12 + width}, new int[] {y + width / 5, y + width / 5, y - 10}, 3);
	}

	//draws the airplane
	public void draw(Graphics2D g2)
	{
		//body of the plane
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, height);
		
		g2.draw(body);
		g2.fill(body);
		drawNose(g2);
		drawFrontWing(g2);
		drawBackWing(g2);

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
		draw(g2);
		
	}
}
