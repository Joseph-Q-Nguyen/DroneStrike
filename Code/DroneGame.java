import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;


// Joseph's note: anything added to be displayed on screen must be added to the Moving Plain
public class DroneGame //implements KeyListener
{
	public final static int GAME_WIDTH = 1000;
	public final static int GAME_HEIGHT = 500;
	private Random r;
	private int x;
	
	public DroneGame() throws IOException
	{
		JFrame screen = new JFrame("Drone Strike");
		MovingPlain plainBackground = new MovingPlain();
		r = new Random();
		
		Airplane f15 = new Airplane(750, r.nextInt(450), 150, 25);
		JLabel airplane = new JLabel(f15);
		Airplane tomcat = new Airplane(800, r.nextInt(450), 150, 25);
		JLabel airplane2 = new JLabel(tomcat);
		
		Drone drone = new Drone(100, 250);
		
		screen.add(plainBackground);
		//screen.getContentPane().add(tracer);
		
		plainBackground.add(drone);
		plainBackground.add(airplane);
		plainBackground.add(airplane2);
				
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(GAME_WIDTH , GAME_HEIGHT);
		screen.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException
	{
		DroneGame MegaDimensionNeptuniaVII = new DroneGame();
	}

}
