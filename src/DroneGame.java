import java.io.IOException;
import java.util.Random;
import javax.swing.*;


// Joseph's note: anything added to be displayed on screen must be added to the Moving Plain
public class DroneGame 
{
	public final static int GAME_WIDTH = 1000;
	public final static int GAME_HEIGHT = 525;

	private Random r;
	
	private Drone drone;
	private Airplane f15, tomcat, dog;
	
	private Airplane[] airforce = new Airplane[6];
	
	public DroneGame() throws IOException, InterruptedException
	{
		JFrame screen = new JFrame("Drone Strike");
		MovingPlain plainBackground = new MovingPlain();
		r = new Random();
		Lives lives = new Lives();

		drone = new Drone(50, 150);
		
		f15 = new Airplane(850, r.nextInt(450), 150, 25, false, drone);
		JLabel airplane = new JLabel(f15);
		airplane.setBounds(0, 0, 1, 1);
		tomcat = new Airplane(800, r.nextInt(450), 150, 25, false, drone);
		JLabel airplane2 = new JLabel(tomcat);
		airplane2.setBounds(0, 0, 1, 1);
		
		
		
		airforce[0] = f15;
		airforce[1] = tomcat;
		//airforce[2] = airplane3;
		
		Time time = new Time();
		Scores score = new Scores();

		

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
			for(int i = 0; i < 2; i++)
			{
				if(airforce[i].crashF(drone))
				{
					lives.lost();
					drone.reset();
					airforce[i].reset();
				}
				
				
			}
			
			/*if(crashF())
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
			if(crashD())
			{
				lives.lost();
				drone.reset();
				dog.reset();
			}*/
			if(lives.getLives() == 0)
			{
				lives.getALife();
				time.timeReset();
				score.gameOver();
			}
			if(time.getTime() == 0)
			{
				dog = new Airplane(900, r.nextInt(450), 150, 25, true, drone);
				JLabel airplane3 = new JLabel(dog);
				airplane3.setBounds(0, 0, 1, 1);
				plainBackground.add(airplane3);
				
			}
			if(score.getWinner())
			{
				lives.won();
				time.timeReset();
				drone.reset();
				f15.reset();
				
			}
		}
	}

	public boolean crashF() 
	{
		//bottom of plane
		if (f15.getY()+20 < drone.getYs()-drone.getHeight()
				|| f15.getY()-f15.getIconHeight()-50 > drone.getYs()) {
			//top of plane
			return false;
		}
		//back of plane
		if (f15.getX()+f15.getIconWidth()-50 < drone.getXs()-drone.getHeight() 
				|| f15.getX()-f15.getIconHeight() > drone.getXs()+drone.getWidth()) 
		{
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

	public boolean crashD()
	{
		if (dog.getY()+20 < drone.getYs()-drone.getHeight()
				|| dog.getY()-dog.getIconHeight()-50 > drone.getYs()) {
			//top of plane
			return false;
		}
		//back of plane
		if (dog.getX()+dog.getIconWidth()-50 < drone.getXs()-drone.getHeight() 
				|| dog.getX()-dog.getIconHeight() > drone.getXs()+drone.getWidth()) {
			return false;
		}
		return true;
	}
	
	public void detection()
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
