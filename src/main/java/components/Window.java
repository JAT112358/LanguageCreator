package components;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import display.Menu;

public class Window extends JFrame 
{
	private static final long	serialVersionUID	= - 8641413596663241575L;
	private static Window		instance;
	private final Menu		menu;

	private Window()
	{
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage((new ImageIcon("img/app-icon.png")).getImage());
		setSize(800, 600);
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		setTitle("Language Creator Pro");
		this.menu = new Menu();
		setJMenuBar(menu);
	}
	
	public void minimize()
	{
		instance.setExtendedState(JFrame.ICONIFIED);
	}
	
	public void maximize()
	{
		instance.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void fullScreen(boolean enable)
	{
		if(enable)
		{
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
		}
		else
		{
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
		}
	}
	
	public void updateMenu() {
		instance.setJMenuBar(new Menu());
	}
	
	public static Window getInstance()
	{
		if (instance == null)
		{
			instance = new Window();
		}
		return instance;
	}
}