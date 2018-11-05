import java.awt.*;
import javax.swing.*;


// Joseph's note: anything added to be displayed on screen must be added to the Moving Plain
public class DroneGame 
{
	public final static int GAME_WIDTH = 1000;
	public final static int GAME_HEIGHT = 500;
	
	public static void main(String[] args)
	{
		JFrame screen = new JFrame("Drone Strike");
		
		Airplane f15 = new Airplane(500, 250, 150, 25);
		JLabel airplane = new JLabel(f15);
		MovingPlain plainBackground = new MovingPlain();
		
		screen.add(plainBackground);
		
		plainBackground.add(airplane);
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(GAME_WIDTH , GAME_HEIGHT);
		screen.setVisible(true);
	}	
}
