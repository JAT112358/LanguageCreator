package display;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import javax.swing.JSplitPane;

import utils.StringUtils;

import entities.LangDirectory;

public class LangEditor extends JPanel implements ActionListener, MouseListener, KeyListener
{
	private static final long 				serialVersionUID = 3314021843671839997L;
		
	private LabelsPanel						labelsPanel;
	private ValuesPanel 					valuesPanel;

	public LangEditor(LangDirectory langDirectory) 
	{		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(15, 20, 15, 20);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		add(splitPane, gbc_splitPane);
		
		valuesPanel = new ValuesPanel(this);
		splitPane.setRightComponent(valuesPanel);
		
		labelsPanel = new LabelsPanel(this);
		splitPane.setLeftComponent(labelsPanel);
		labelsPanel.update();
	}
	
	public void changeValuesPanel()
	{
		String[] header = {"Language", "Value"};
		String[][] content = new String[LangDirectory.getInstance().filesDirectory.size()][2];

		for(int i=0; i<LangDirectory.getInstance().filesDirectory.size(); i++)
		{
			Locale l = new Locale(LangDirectory.getInstance().getFilesDirectory().get(i).getName().substring(0, 2), LangDirectory.getInstance().getFilesDirectory().get(i).getName().substring(3, 5));
			content[i][0] = StringUtils.firstToUpper(l.getDisplayLanguage()) + " (" + l.getDisplayCountry() + ")";
			content[i][1] = LangDirectory.getInstance().getDirectoryFilesHashMap().get(i).get(labelsPanel.getLabelsList().getSelectedValue());
		}
		valuesPanel.setTableData(header, content);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
	}

	public void mouseClicked(MouseEvent e) 
	{
		if(labelsPanel.getLabelsList() == e.getSource())
		{
			if(labelsPanel.getLabelsList().getSelectedIndex() >= 0)
			{
				changeValuesPanel();
			}
		}
	}

	public void mousePressed(MouseEvent e) 
	{
		
	}

	public void mouseReleased(MouseEvent e) 
	{
		
	}

	public void mouseEntered(MouseEvent e) 
	{
		
	}

	public void mouseExited(MouseEvent e) 
	{
		
	}

	public void keyTyped(KeyEvent e) 
	{
		
	}

	public void keyPressed(KeyEvent e) 
	{
		
	}

	public void keyReleased(KeyEvent e) 
	{
		if(labelsPanel.getLabelsList() == e.getSource())
		{
			if(labelsPanel.getLabelsList().getSelectedIndex() >= 0)
			{
				changeValuesPanel();
			}
		}
	}
}