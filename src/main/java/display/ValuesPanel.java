package display;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import components.TableModel;

public class ValuesPanel extends JPanel 
{
	private static final long 	serialVersionUID = 7652478486621255201L;
	
	private TableModel			tableModel;
	private JTable 				tableValues;

	public ValuesPanel(LangEditor langEditor) 
	{
		setOpaque(false);
		setBorder(new TitledBorder(null, "Values", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableModel = new TableModel();
		tableValues = new JTable(tableModel);
		tableValues.getTableHeader().setReorderingAllowed(false);
		tableValues.setShowVerticalLines(true);
		tableValues.setShowHorizontalLines(true);
		tableValues.setDragEnabled(false);
		tableValues.setSelectionForeground(Color.WHITE);
		tableValues.setSelectionBackground(Color.BLUE);
		tableValues.setForeground(Color.BLACK);
		tableValues.setBackground(Color.WHITE);
		tableValues.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		tableValues.setRowHeight(30);
		tableValues.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		
		scrollPane.setViewportView(tableValues);	
	}
		
	public void setTableData(String[] header, String [][] content)
	{
		tableValues.removeAll();
		tableModel.setDataVector(content, header);
		tableValues.setModel(tableModel);
	}
}