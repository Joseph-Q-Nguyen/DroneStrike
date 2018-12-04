import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


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
		
		f15 = new Airplane(850, r.nextInt(450), 150, 25);
		JLabel airplane = new JLabel(f15);
		airplane.setBounds(0, 0, 1, 1);
		tomcat = new Airplane(800, r.nextInt(450), 150, 25);
		JLabel airplane2 = new JLabel(tomcat);
		airplane2.setBounds(0, 0, 1, 1);
		
		
		
		airforce[0] = f15;
		airforce[1] = tomcat;
		//airforce[2] = airplane3;
		
		Time time = new Time();
		Scores score = new Scores();

		

		screen.add(plainBackground);
		playSound();


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
					playHitSound();
					lives.lost();
					drone.reset();
					airforce[i].reset();
				}
				
				
			}
			
			if(lives.getLives() == 0)
			{
				lives.getALife();
				time.timeReset();
				score.gameOver();
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
	
	public void playHitSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/adamgolab/Desktop/151project/sounds/canon.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	
	public void playSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/adamgolab/Desktop/151project/sounds/mario.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        Thread.sleep(500);
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}


	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		DroneGame MegaDimensionNeptuniaVII = new DroneGame();
		//System.out.println(drone.getWidth());
	}

}