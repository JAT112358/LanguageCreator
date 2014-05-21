package components;

import interfaces.Internationalizable;

import javax.swing.JMenu;

public class IMenu extends JMenu implements Internationalizable
{
	private static final long serialVersionUID = -3619976635531376321L;

	public IMenu()
	{
		
	}
	
	public IMenu(String text)
	{
		super(text);
	}
	
	public void changeLanguage(String newText) 
	{
		this.setText(newText);
	}
}