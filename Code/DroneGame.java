
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


// Joseph's note: anything added to be displayed on screen must be added to the Moving Plain
public class DroneGame 
{
	public final static int GAME_WIDTH = 1000;
	public final static int GAME_HEIGHT = 525;
	private Random r;
	private int x;
	private static Drone drone;
	private static Airplane f15;
	public int colCount = 3;
	
	public DroneGame() throws IOException, InterruptedException
	{
		JFrame screen = new JFrame("Drone Strike");
		MovingPlain plainBackground = new MovingPlain();
		r = new Random();
		Lives lives = new Lives();
		
		f15 = new Airplane(850, r.nextInt(450), 150, 25);
		JLabel airplane = new JLabel(f15);
		airplane.setBounds(850, r.nextInt(450), 150, 25);
		//Airplane tomcat = new Airplane(800, r.nextInt(450), 150, 25);
		//JLabel airplane2 = new JLabel(tomcat);
		//airplane2.setBounds(850, r.nextInt(450), 150, 25);
		Time time = new Time();
		Scores score = new Scores();
		
		drone = new Drone(50, 150);
		
		screen.add(plainBackground);
		
		
		time.setBounds(500, 390, 75, 75);
		lives.setBounds(470, 390, 150, 150);
		plainBackground.add(lives);
		plainBackground.add(time);
		plainBackground.add(score);
		plainBackground.add(drone);
		plainBackground.add(airplane);
		//plainBackground.setLayout(null);
		//plainBackground.add(airplane2);
		
		

		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(GAME_WIDTH , GAME_HEIGHT);
		screen.setVisible(true);
		
		while(screen.isVisible()) 
		{
			System.out.println("");
			if(isOverlapping())
			{
				lives.lost();
				drone.reset();
				f15.reset();
			}
			if(lives.getLives() == 0)
			{
				lives.getALife();
				score.gameOver();
			}
			
		}
	}
	
	public boolean isOverlapping() {
		//bottom of plane
	    if (f15.getY()+20 < drone.getY()-drone.getHeight()
	      || f15.getY()-f15.getIconHeight()-50 > drone.getY()) {
	    	//top of plane
	        return false;
	    }
	    //back of plane
	    if (f15.getX()+f15.getIconWidth()-50 < drone.getX()-drone.getHeight() 
	      || f15.getX()-f15.getIconHeight() > drone.getX()+drone.getWidth()) {
	        return false;
	    }
	    return true;
	}

	
	
	public static void detection()
	{
		System.out.print("X:" + drone.getX() + " ");
		System.out.println("Y: " + drone.getY());
	}
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		DroneGame MegaDimensionNeptuniaVII = new DroneGame();
		//System.out.println(drone.getWidth());
	}

}
