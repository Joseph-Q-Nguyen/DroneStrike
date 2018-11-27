import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Scores extends JLabel {
	
	JLabel label = this;
	
	int seconds = 0;
	int gamesWon = 0;
	int gamesPlayed = 0;
	boolean winner = false;
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() 
		{
			winner = false;
			if(seconds < 90)
				seconds++;
			else
			{
				seconds = 0;
				gamesWon++;
				gamesPlayed++;
				
				label.setText("Score: " + Integer.toString(gamesWon) + " out of " + Integer.toString(gamesPlayed));
				winner = true;
			}
			
			
		}
	};
	
	public Scores()
	{
		label.setText("Score: " + gamesWon + " out of " + gamesPlayed);
		timer.scheduleAtFixedRate(task, 1000, 1000);
		this.setBounds(485, 370, 150, 150);
	}
	
	public void reset()
	{		
		
	}
	
	public boolean getWinner()
	{
		return winner;
	}
	
	public void gameOver()
	{
		gamesPlayed++;
		label.setText("Score: " + gamesWon + " out of " + gamesPlayed);
	}
}
