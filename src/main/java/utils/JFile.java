package utils;

import java.io.File;

import javax.swing.JFileChooser;

public class JFile
{
	public static File selectPath(int mode)
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(mode);
		File file = null;
		try
		{
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file.getAbsolutePath());
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return file;
	}
	
	public static boolean createFolder(File file)
	{
		return file != null && ! file.exists() && file.mkdir();
	}
	
	public static File openPath(int mode)
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(mode);
		File file = null;
		try
		{
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file.getAbsolutePath());
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return file;
	}
}