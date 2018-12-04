import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Airplane extends JLabel implements Icon
{
	private int x, xd;
	private int y, yd;
	private int width;
	private int height;
	private Random r;
	private boolean boss;
	
	
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
		
		xd = this.x + 25;
		yd = this.y + 50;
		
		r = new Random();
		
		this.setBounds(new Rectangle(x, y, width, height));
		
		int delay = 10;
		Timer t = new Timer(delay, event -> {
			move();
			if(this.getX() <= -250)
			{
				reset();
			}
			
		});
		t.start();
		
		
		
	}

	public void move()
	{
		x-=5;
		xd-=5;
	}

	public void seeker(int onYou)
	{
		x--;
		xd--;
		y = onYou-80;
		yd = onYou-80;
	}
	
	//resets the position of the airplane back to the left hand side
	public void reset()
	{
		x = 1000;
		y = r.nextInt(450);
		yd = y + 40;
	}

	public void setX(int back)
	{
		x = back;
	}
	
	//gets the x position of the airplane
	public int getX()
	{
		return x-35;
	}

	public int getY()
	{
		return y-10;
	}
	
	public int getXD()
	{
		return xd+25;
	}
	
	public int getYD()
	{
		return yd+50;
	}
	
	public void setYD(int yo)
	{
		yd = yo;
	}
	
	//draws the nose of the airplane
	public void drawNose(Graphics2D nose)
	{
		
		nose.setColor(Color.RED); // first drawing the polygon and then filling it gives an outline affect
		nose.drawPolygon(new int[] {x, x, x - (width/5)-1}, new int[] {y + width / 3, y + width / 6 -1, y + width/6 +10}, 3);
		nose.setColor(Color.DARK_GRAY);
		nose.fillPolygon(new int[] {x, x, x - (width/5)}, new int[] {y + width / 3, y + width / 6, y + width/6 +10}, 3);
	}

	//draws the front wing of the airplane
	public void drawFrontWing(Graphics2D fWing)
	{	
		fWing.setColor(Color.RED);
		fWing.drawPolygon(new int[] {x + 82, x + 28, x + width - 15}, new int[] {y + width / 4, y + width / 4, y + width * 3 / 5},  3);
		fWing.setColor(Color.DARK_GRAY);
		fWing.fillPolygon(new int[] {x + 80, x + 30, x + width - 15}, new int[] {y + width / 4, y + width / 4, y + width * 3 / 5},  3);
	}

	//draws the back wing of the airplane
	//for the back, I put some small subtraction/addition integers to try to create the 
	//airplane with a more "3-D"-ish effect or give it some depth perception
	public void drawBackWing(Graphics2D bWing)
	{
		bWing.setColor(Color.RED);
		bWing.drawPolygon(new int[] {x + 95, x + 43, x - 12 + width}, new int[] {y + width / 5, y + width / 5, y - 10}, 3);
		bWing.setColor(Color.DARK_GRAY);
		bWing.fillPolygon(new int[] {x + 95, x + 45, x - 12 + width}, new int[] {y + width / 5, y + width / 5, y - 10}, 3);
	}

	//draws the airplane
	public void draw(Graphics2D g2)
	{
		//body of the plane
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width-1, height);
		
		drawBackWing(g2); // draw the back wing first
		g2.setColor(Color.RED);
		g2.drawRect(x, y + width / 6 - 1, width - 1, height + 1); // outline rectangle
		g2.setColor(Color.DARK_GRAY);
		g2.fill(body);
		drawNose(g2);
		drawFrontWing(g2);
		
		//Rectangle2D.Double bounding = new Rectangle2D.Double(x-35, y-10, width+50, height+80);
		//g2.draw(bounding);
	}
	
	@Override
	public int getIconHeight() 
	{
		
		return height;
	}

	@Override
	public int getIconWidth() 
	{
		
		return width;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int i, int j) 
	{
		Graphics2D g2 = (Graphics2D) g;
		draw(g2);
		
	}
	
	public boolean crashF(Drone drone) 
	{
		//bottom of plane
		if (this.getY()+20 < drone.getYs()-drone.getHeight()
				|| this.getY()-this.getIconHeight()-50 > drone.getYs()) {
			//top of plane
			return false;
		}
		//back of plane
		if (this.getX()+this.getIconWidth()-50 < drone.getXs()-drone.getHeight() 
				|| this.getX()-this.getIconHeight() > drone.getXs()+drone.getWidth()) 
		{
			return false;
		}
		return true;
	}
	
}
