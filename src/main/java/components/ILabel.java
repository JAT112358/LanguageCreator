package components;

import javax.swing.JLabel;

public class ILabel extends JLabel implements Internationalizable
{
	private static final long serialVersionUID = 6023673062423620356L;

	public ILabel()
	{
		super();
	}
	
	public ILabel(String text)
	{
		super(text);
	}
	
	public void changeLanguage(String text) 
	{
		this.setText(text);
	}
}