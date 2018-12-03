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
	
	private static Drone drone;
	private static Airplane f15, tomcat;
	public int colCount = 3;

	public DroneGame() throws IOException, InterruptedException
	{
		JFrame screen = new JFrame("Drone Strike");
		MovingPlain plainBackground = new MovingPlain();
		r = new Random();
		Lives lives = new Lives();

		f15 = new Airplane(850, r.nextInt(450), 150, 25);
		JLabel airplane = new JLabel(f15);
		airplane.setBounds(0, 0, 1, 1);
		tomcat = new Airplane(800, r.nextInt(450), 150, 25);
		JLabel airplane2 = new JLabel(tomcat);
		airplane2.setBounds(0, 0, 1, 1);
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
		plainBackground.add(airplane2);
		plainBackground.setLayout(null);




		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(GAME_WIDTH , GAME_HEIGHT);
		screen.setVisible(true);

		while(screen.isVisible()) 
		{
			System.out.println("");
			if(crashF())
			{
				lives.lost();
				drone.reset();
				f15.reset();
			}
			if(crashT())
			{
				lives.lost();
				drone.reset();
				tomcat.reset();
			}
			if(lives.getLives() == 0)
			{
				lives.getALife();
				score.gameOver();
			}
			if(score.getWinner())
			{
				lives.won();
				drone.reset();
				f15.reset();
				
			}
		}
	}

	public boolean crashF() {
		//bottom of plane
		if (f15.getY()+20 < drone.getYs()-drone.getHeight()
				|| f15.getY()-f15.getIconHeight()-50 > drone.getYs()) {
			//top of plane
			return false;
		}
		//back of plane
		if (f15.getX()+f15.getIconWidth()-50 < drone.getXs()-drone.getHeight() 
				|| f15.getX()-f15.getIconHeight() > drone.getXs()+drone.getWidth()) {
			return false;
		}
		return true;
	}

	public boolean crashT()
	{
		if (tomcat.getY()+20 < drone.getYs()-drone.getHeight()
				|| tomcat.getY()-tomcat.getIconHeight()-50 > drone.getYs()) {
			//top of plane
			return false;
		}
		//back of plane
		if (tomcat.getX()+tomcat.getIconWidth()-50 < drone.getXs()-drone.getHeight() 
				|| tomcat.getX()-tomcat.getIconHeight() > drone.getXs()+drone.getWidth()) {
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
