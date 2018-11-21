import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Scores extends JLabel {
	
	JLabel label = this;
	
	int seconds = 0;
	int gamesWon = 0;
	int gamesPlayed = 1;
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			if(seconds < 90)
				seconds++;
			else
			{
				seconds = 0;
				gamesWon++;
				gamesPlayed++;
				label.setText("Score: " + Integer.toString(gamesWon) + " out of " + Integer.toString(gamesPlayed));
			}
			
			
		}
	};
	
	public Scores()
	{
		label.setText("Score: 0 out of 1");
		timer.scheduleAtFixedRate(task, 1000, 1000);
		this.setBounds(485, 380, 150, 150);
	}
}