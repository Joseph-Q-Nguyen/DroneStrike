import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


// Joseph's note: anything added to be displayed on screen must be added to the Moving Plain
public class DroneGame 
{
	public final static int GAME_WIDTH = 1000;
	public final static int GAME_HEIGHT = 500;
	
	public static void main(String[] args) throws IOException
	{
		JFrame screen = new JFrame("Drone Strike");
		MovingPlain plainBackground = new MovingPlain();
		
		Airplane f15 = new Airplane(500, 250, 150, 25);
		JLabel airplane = new JLabel(f15);
		
		Drone drone = new Drone(200, 100, 150, 25);
		JLabel label = new JLabel(drone);
		
		screen.add(plainBackground);
		
		//BufferedImage image = ImageIO.read(new File("Files/drone3.png"));
	    //drone.add(label);
		//drone.setSize(5, 5);
		
		plainBackground.add(label);
		plainBackground.add(airplane);
		
		

		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(GAME_WIDTH , GAME_HEIGHT);
		screen.setVisible(true);
	}	
}
