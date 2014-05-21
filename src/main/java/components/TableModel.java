package components;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel 
{
	private static final long	serialVersionUID	= 1L;

	public boolean isCellEditable(final int row, final int column)
	{
		if (column == 0)
		{
			return false;
		}
		return true;
	}
}