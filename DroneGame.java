import java.awt.*;
import javax.swing.*;

public class DroneGame 
{
	public static void main(String[] args)
	{
		JFrame screen = new JFrame("Drone Strike");
		
		Airplane f15 = new Airplane(500, 250, 150, 25);
		JLabel airplane = new JLabel(f15);
		
		screen.add(airplane);
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(1000, 500);
		screen.setVisible(true);
	}
}
