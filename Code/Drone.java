import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Drone extends ImageIcon
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y;
	private JPanel panel = new JPanel();
	private ImageIcon drone;
	
	public Drone() throws IOException
	{
		super("Files/drone2.png");	
	}

	public ImageIcon getDrone()
	{
		return drone;
	}
	
	public void moveUp()
	{
		y--;
	}

	public void moveDown()
	{
		y++;
	}



	
	
}
