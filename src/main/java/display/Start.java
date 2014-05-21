package display;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Start extends JPanel 
{
	private static final long 	serialVersionUID = -6969744533822338215L;

	private LangEditor			langEditor;
	
	public Start() 
	{
		setLayout(new BorderLayout(0, 0));
	}
	
	public void setLangEditor(LangEditor langEditor)
	{
		if(this.langEditor != null)
		{
			remove(this.langEditor);
		}
		this.langEditor = langEditor;
		add(langEditor, BorderLayout.CENTER);
		updateUI();
	}
	
	public LangEditor getLangEditor()
	{
		return this.langEditor;
	}
}