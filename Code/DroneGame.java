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
		Drone drone = new Drone(200, 100, 150, 25);
		JLabel label = new JLabel(drone);

		KeyListener f = new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent event) 
			{
				// TODO Auto-generated method stub
				if(event.getKeyCode() == KeyEvent.VK_UP)
				{
					System.out.println("up");
					label.setText("Pull Up!");
					label.setLocation((int)label.getAlignmentX(), label.getY()-1);
					
					label.repaint();
					
				}
				if(event.getKeyCode() == KeyEvent.VK_DOWN)
				{
					System.out.println("down");
					label.setText("dive! dive! dive!");
					
					label.setLocation((int)label.getAlignmentX(), label.getY()+1);
					label.repaint();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				// TODO Auto-generated method stub
				System.out.println("Done");
			}

			@Override
			public void keyTyped(KeyEvent event) 
			{
				// TODO Auto-generated method stub
				
			}
	
		};
		
		screen.add(plainBackground);
		screen.addKeyListener(f);
		label.requestFocus();
		
		
		plainBackground.add(label);
		plainBackground.add(airplane);
		plainBackground.add(airplane2);
				
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(GAME_WIDTH , GAME_HEIGHT);
		screen.setVisible(true);
	}
	
	
	public static void main(String[] args) throws IOException 
	{
		DroneGame persona5 = new DroneGame();
	}


}
