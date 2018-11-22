import javax.swing.JLabel;

public class Lives extends JLabel 
{
	
	JLabel label = this;
	private int lives;
	
	public Lives() 
	{
		lives = 3;
		label.setText("Lives Left: " + lives + " out of 3");
	}

	public void lost()
	{
		lives--;
		label.setText("Lives Left: " + lives + " out of 3");
		
	}
	
	public void getALife()
	{
		lives = 3;
		label.setText("Lives Left: " + lives + " out of 3");
	}
	
	public int getLives()
	{
		return lives;
	}
	
}
