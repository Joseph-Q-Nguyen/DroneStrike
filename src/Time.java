import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Time extends JLabel {
	
	JLabel label = this;
	
	public boolean drawn = false;
	
	int seconds = 30;
	int minutes = 1;
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			if(seconds > 0)
				seconds--;
			else if(minutes == 1)
			{
				minutes--;
				seconds = 59;
			}
			else
			{
				minutes = 0;
				seconds = 0;
			}
			if(seconds < 10 && minutes < 10)
				label.setText("Time: 0" + Integer.toString(minutes) + ":0" + Integer.toString(seconds));
			else if(minutes < 10)
				label.setText("Time: 0" + Integer.toString(minutes) + ":" + Integer.toString(seconds));
			else
				label.setText("Time: " + Integer.toString(minutes) + ":" + Integer.toString(seconds));
		}
	};
	
	public Time()
	{
		drawn = true;
		timer.scheduleAtFixedRate(task, 1000, 1000);
		this.setHorizontalAlignment(SwingConstants.RIGHT); 
	}
	
	public int getSeconds()
	{
		return seconds;
	}
	
	public int getMinutes()
	{
		return minutes;
	}
	
	public void timeReset() 
	{
		seconds = 30;
		minutes = 1;
	}
}
