package components;

import javax.swing.JMenuItem;

public class IMenuItem extends JMenuItem implements Internationalizable
{
	private static final long serialVersionUID = -7850706335297649613L;

	public IMenuItem()
	{
		super();
	}
	
	public IMenuItem(String text)
	{
		super(text);
	}

	public void changeLanguage(String text) 
	{
		this.setText(text);
	}
}