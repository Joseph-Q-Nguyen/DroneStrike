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
	private Airplane f15, tomcat, dog, topgun;
	
	private Airplane[] airforce = new Airplane[6];
	
	public DroneGame() throws IOException, InterruptedException
	{
		JFrame screen = new JFrame("Drone Strike");
		MovingPlain plainBackground = new MovingPlain();
		r = new Random();
		Lives lives = new Lives();

		drone = new Drone(50, 150);
		
		f15 = new Airplane(1000, r.nextInt(450), 150, 25);
		JLabel airplane = new JLabel(f15);
		airplane.setBounds(0, 0, 1, 1);
		tomcat = new Airplane(1050, r.nextInt(450), 150, 25);
		JLabel airplane2 = new JLabel(tomcat);
		airplane2.setBounds(0, 0, 1, 1);
		dog = new Airplane(8750, r.nextInt(450), 150, 25);
		JLabel airplane3 = new JLabel(dog);
		airplane3.setBounds(0, 0, 1, 1);
		topgun = new Airplane(16000, r.nextInt(450), 150, 25);
		JLabel airplane4 = new JLabel(topgun);
		airplane4.setBounds(0, 0, 1, 1);
		
		airforce[0] = f15;
		airforce[1] = tomcat;
		airforce[2] = dog;
		airforce[3] = topgun;
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
		plainBackground.add(airplane3);
		plainBackground.add(airplane4);
		plainBackground.setLayout(null);

		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setSize(GAME_WIDTH , GAME_HEIGHT);
		screen.setVisible(true);

		while(screen.isVisible()) 
		{
			System.out.print("");
			for(int i = 0; i < 4; i++)
			{
				if(airforce[i].crashF(drone))
				{
					playHitSound();
					lives.lost();
					drone.reset();
					airforce[i].reset();
				}
				if (drone.laserHit(airforce[i]))
				{
					airforce[i].reset();
					drone.resetLaser();
					playHitSound();
				}
			}
			
			if(lives.getLives() == 0)
			{
				lives.getALife();
				time.timeReset();
				score.gameOver();
				for(int i = 0; i < 4; i++)
					airforce[i].reset();
				airforce[2].setX(8750);
				airforce[3].setX(16000);
				playGenericSound("Files\\smb_bowserfalls.wav");
			}
			
			if(score.getWinner())
			{
				lives.won();
				time.timeReset();
				drone.reset();
				for(int i = 0; i < 4; i++)
					airforce[i].reset();
				airforce[2].setX(8750);
				airforce[3].setX(16000);
				playScoreSound(); 
				score.reset();
			}
		}
	}
	
	public void playHitSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Files\\canon.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void playScoreSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Files\\smb_powerup.wav").getAbsoluteFile());
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
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Files\\mario.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        Thread.sleep(500);
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void playGenericSound(String path) {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
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