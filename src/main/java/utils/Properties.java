package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Vector;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Properties implements Serializable 
{
	private static final long	serialVersionUID	= - 1140374742103678200L;
	
	private static Properties	properties;
	private Locale				locale;
	private String				lookAndFeelClass;
	private Vector<String>		recentlyPaths;

	private Properties(final Locale locale, final String lookAndFeelClass, final Vector<String> recentlyPaths)
	{
		this.locale = locale;
		this.lookAndFeelClass = lookAndFeelClass;
		this.recentlyPaths = recentlyPaths;
	}

	private void update()
	{
		ObjectOutputStream oos;
		try
		{
			oos = new ObjectOutputStream(new FileOutputStream("conf.properties"));
			oos.writeObject(properties);
			oos.close();
		}
		catch (final IOException e)
		{
			e.printStackTrace();

			properties = new Properties(Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), new Vector<String>());
		}
	}

	private static void init()
	{
		ObjectInputStream ois;
		try
		{
			ois = new ObjectInputStream(new FileInputStream("conf.properties"));
			properties = (Properties) ois.readObject();
			ois.close();
		}
		catch (IOException | ClassNotFoundException e)
		{
			if ( ! (e instanceof FileNotFoundException))
			{
				e.printStackTrace();
			}

			properties = new Properties(Locale.getDefault(),
			UIManager.getSystemLookAndFeelClassName(), new Vector<String>());
			properties.update();
		}
	}

	/**
	 * @return Current locale
	 */
	public static Locale getLocale()
	{
		if (properties == null)
		{
			init();
		}
		return properties.locale;
	}

	/**
	 * @return Current locale
	 */
	public static String getLookAndFeel()
	{
		if (properties == null)
		{
			init();
		}
		return properties.lookAndFeelClass;
	}

	/**
	 * @param l Locale to set as default
	 */
	public static void setLocale(final Locale l)
	{
		if (properties == null)
		{
			init();
		}
		if (Lang.getAvailableLocales().contains(l))
		{
			properties.locale = l;
		}
		else
		{
			properties.locale = Lang.getDefaultLocale();
		}
		properties.update();
	}

	/**
	 * @param lf The new Look and feel to set
	 */
	public static void setLookAndFeelClass(final String lf)
	{
		if (properties == null)
		{
			init();
		}
		if (isLFAvailable(lf))
		{
			properties.lookAndFeelClass = lf;
		}
		properties.update();
	}

	private static boolean isLFAvailable(final String lf)
	{
		final LookAndFeelInfo lfs[] = UIManager.getInstalledLookAndFeels();
		for (final LookAndFeelInfo lf2: lfs)
		{
			if (lf2.getClassName().equals(lf))
			{
				return true;
			}
		}
		return false;
	}
	
	public static Vector<String> getRecentlyFiles()
	{
		if (properties == null)
		{
			init();
		}
		return properties.recentlyPaths;
	}
	
	public static void addRecentlyPath(String path)
	{
		if (properties == null)
		{
			init();
		}
		if(properties.recentlyPaths.contains(path)) {
			properties.recentlyPaths.remove(path);
		}
		properties.recentlyPaths.add(path);
		properties.update();
	}
	
	public static void removeRecentlyFiles()
	{
		if (properties == null)
		{
			init();
		}
		properties.recentlyPaths.removeAllElements();
		properties.update();
	}
}