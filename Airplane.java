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
		//bottom left corner
		Point2D.Double r1 = new Point2D.Double(x, y + width / 3);

		//top left corner
		Point2D.Double r2 = new Point2D.Double(x, y + width / 6);

		//point of the nose
		Point2D.Double r3 = new Point2D.Double(x - (width/5), y + width/6 + 10);

		//bottom part of the nose
		Line2D.Double bottomNose = new Line2D.Double(r1, r3);

		//top part of the nose
		Line2D.Double topNose = new Line2D.Double(r2, r3);

		nose.draw(topNose);
		nose.draw(bottomNose);
	}

	//draws the front wing of the airplane
	public void drawFrontWing(Graphics2D fWing)
	{
		//front wing right
		Point2D.Double r4 = new Point2D.Double(x + 80, y + width / 4);

		//front wing left
		Point2D.Double r5 = new Point2D.Double(x + 30, y + width / 4);

		//front wing point
		Point2D.Double r6 = new Point2D.Double(x + width - 15, y + width *3 / 5);

		//the line on the body
		Line2D.Double base = new Line2D.Double(r4, r5);

		//the outer part of the wing
		Line2D.Double outside = new Line2D.Double(r4, r6);

		//the inner part of the wing
		Line2D.Double inside = new Line2D.Double(r5, r6);

		fWing.draw(base);
		fWing.draw(inside);
		fWing.draw(outside);
	}

	//draws the back wing of the airplane
	//for the back, I put some small subtraction/addition integers to try to create the 
	//airplane with a more "3-D"-ish effect or give it some depth perception
	public void drawBackWing(Graphics2D bWing)
	{
		//back wing right
		Point2D.Double r7 = new Point2D.Double(x + 95, y + width / 5);

		//back wing left
		Point2D.Double r8 = new Point2D.Double(x + 45 , y + width / 5);

		//back wing point
		Point2D.Double r9 = new Point2D.Double(x - 12 + width , y - 10);

		//the line on the body
		Line2D.Double base = new Line2D.Double(r7, r8);

		//outside edge of the back wing
		Line2D.Double outside = new Line2D.Double(r7, r9);

		//inside edge of the back wing
		Line2D.Double inside = new Line2D.Double(r8, r9);

		bWing.draw(base);
		bWing.draw(outside);
		bWing.draw(inside);

	}

	//draws the airplane
	public void draw(Graphics2D g2)
	{
		//body of the plane
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, height);

		g2.draw(body);
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
