package display;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.JList;
import components.Window;
import entities.LangDirectory;

public class LabelsPanel extends JPanel
{
	private static final long 	serialVersionUID = -9174933733872586642L;
	
	private DefaultListModel<String> 	listModel;
	private JList<String>				listLabels;
	
	public LabelsPanel(LangEditor langEditor) 
	{		
		setOpaque(false);
		setBorder(new TitledBorder(null, "Labels", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newLabel = JOptionPane.showInputDialog(Window.getInstance(), "Insert a new label", "New Label", JOptionPane.PLAIN_MESSAGE);
				if(newLabel != null)
				{
					if(LangDirectory.getInstance().existLabel(newLabel))
					{
						JOptionPane.showMessageDialog(Window.getInstance(), "Label exists!", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						LangDirectory.getInstance().addLabel(newLabel);
						listModel.addElement(newLabel);
						listLabels.setSelectedIndex(listLabels.getModel().getSize());
					}
				}
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		add(btnAdd, gbc_btnAdd);
		
		JButton btnMod = new JButton("Mod");
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = LabelsPanel.this.listLabels.getSelectedIndex();
				if(selected >= 0)
				{
					String aux = LabelsPanel.this.listLabels.getSelectedValue();
					String modLabel = (String) JOptionPane.showInputDialog(Window.getInstance(), "Edit a label", "Edit Label", JOptionPane.PLAIN_MESSAGE, null, null, aux);
					if(modLabel != null && ! modLabel.equals(aux))
					{
						if(LangDirectory.getInstance().existLabel(modLabel))
						{
							JOptionPane.showMessageDialog(Window.getInstance(), "Label exists!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							for(int i=0; i<LangDirectory.getInstance().directoryFilesHashMap.size(); i++)
							{
								String value = LangDirectory.getInstance().getDirectoryFilesHashMap().get(i).get(aux);
								LangDirectory.getInstance().getDirectoryFilesHashMap().get(i).remove(aux);
								LangDirectory.getInstance().getDirectoryFilesHashMap().get(i).put(modLabel, value);
							}
							update();
							listLabels.setSelectedIndex(selected);
						}
					}
				}  else {
					JOptionPane.showMessageDialog(Window.getInstance(), "Select a label to edit!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnMod = new GridBagConstraints();
		gbc_btnMod.insets = new Insets(0, 0, 5, 5);
		gbc_btnMod.gridx = 1;
		gbc_btnMod.gridy = 0;
		add(btnMod, gbc_btnMod);
		
		JButton btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LabelsPanel.this.listLabels.getSelectedIndex() >= 0) {			
					LangDirectory.getInstance().deleteLabel(LabelsPanel.this.listLabels.getSelectedValue());
					listModel.removeElement(LabelsPanel.this.listLabels.getSelectedValue());
				} else {
					JOptionPane.showMessageDialog(Window.getInstance(), "Select a label to delete!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnDel = new GridBagConstraints();
		gbc_btnDel.insets = new Insets(0, 0, 5, 0);
		gbc_btnDel.gridx = 2;
		gbc_btnDel.gridy = 0;
		add(btnDel, gbc_btnDel);
		
		JScrollPane scrollPaneLabels = new JScrollPane();
		GridBagConstraints gbc_scrollPaneLabels = new GridBagConstraints();
		gbc_scrollPaneLabels.gridwidth = 3;
		gbc_scrollPaneLabels.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneLabels.gridx = 0;
		gbc_scrollPaneLabels.gridy = 1;
		add(scrollPaneLabels, gbc_scrollPaneLabels);	
		
		listModel = new DefaultListModel<String>();
		listLabels = new JList<String>(listModel);
		listLabels.setDragEnabled(true);
		listLabels.addMouseListener(langEditor);
		listLabels.addKeyListener(langEditor);
		listLabels.setFont(new Font("Calibri", Font.PLAIN, 15));
		scrollPaneLabels.setViewportView(listLabels);
	}
	
	public void update()
	{
		Vector<String> labels = new Vector<String>();
		for(int i=0; i<LangDirectory.getInstance().directoryFilesHashMap.size(); i++)
		{
			final Iterator<Entry<String, String>> it = LangDirectory.getInstance().directoryFilesHashMap.get(i).entrySet().iterator();
			while (it.hasNext())
			{
				final Map.Entry<String, String> e = it.next();
				if( ! labels.contains(e.getKey()))
				{
					labels.add(e.getKey());
				}
			}
		}		
		listLabels.removeAll();		
		listModel = new DefaultListModel();
		for(String label: labels)
		{
			listModel.addElement(label);
		}
		listLabels.setModel(listModel);
	}

	public JList getLabelsList()
	{
		return this.listLabels;
	}
}