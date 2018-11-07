import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Airplane implements Icon
{
	private int x;
	private int y;
	private int width;
	private int height;
	private Random r;
	private boolean hit;
	
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
		this.hit = false;
		r = new Random();
		
		int delay = 10;
		Timer t = new Timer(delay, event -> {
			move();
			if(this.getX() <= -250)
			{
				reset();
			}
			
		});
		t.start();
		
		if(hit)
		{
			reset();
		}
		
	}

	public void move()
	{
		x--;
	}

	public void setHit()
	{
		hit = true;
	}
	
	public boolean getHit()
	{
		return hit;
	}
	
	//resets the position of the airplane back to the left hand side
	public void reset()
	{
		x = 1000;
		y = r.nextInt(400);
	}

	//gets the x position of the airplane
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	
	//draws the nose of the airplane
	public void drawNose(Graphics2D nose)
	{
		
		nose.setColor(Color.BLACK); // first drawing the polygon and then filling it gives an outline affect
		nose.drawPolygon(new int[] {x, x, x - (width/5)}, new int[] {y + width / 3, y + width / 6, y + width/6 +10}, 3);
		nose.setColor(Color.WHITE);
		nose.fillPolygon(new int[] {x, x, x - (width/5)}, new int[] {y + width / 3, y + width / 6, y + width/6 +10}, 3);
	}

	//draws the front wing of the airplane
	public void drawFrontWing(Graphics2D fWing)
	{	
		fWing.setColor(Color.BLACK);
		fWing.drawPolygon(new int[] {x + 82, x + 28, x + width - 15}, new int[] {y + width / 4, y + width / 4, y + width * 3 / 5},  3);
		fWing.setColor(Color.WHITE);
		fWing.fillPolygon(new int[] {x + 80, x + 30, x + width - 15}, new int[] {y + width / 4, y + width / 4, y + width * 3 / 5},  3);
	}

	//draws the back wing of the airplane
	//for the back, I put some small subtraction/addition integers to try to create the 
	//airplane with a more "3-D"-ish effect or give it some depth perception
	public void drawBackWing(Graphics2D bWing)
	{
		bWing.setColor(Color.BLACK);
		bWing.drawPolygon(new int[] {x + 95, x + 43, x - 12 + width}, new int[] {y + width / 5, y + width / 5, y - 10}, 3);
		bWing.setColor(Color.WHITE);
		bWing.fillPolygon(new int[] {x + 95, x + 45, x - 12 + width}, new int[] {y + width / 5, y + width / 5, y - 10}, 3);
	}

	//draws the airplane
	public void draw(Graphics2D g2)
	{
		//body of the plane
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, height);
		
		drawBackWing(g2); // draw the back wing first
		g2.setColor(Color.BLACK);
		g2.drawRect(x, y + width / 6 - 1, width - 1, height + 1); // outline rectangle
		g2.setColor(Color.WHITE);
		g2.fill(body);
		drawNose(g2);
		drawFrontWing(g2);
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