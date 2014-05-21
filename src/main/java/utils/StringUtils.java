package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class StringUtils 
{
	private StringUtils()
	{
		
	}

	private static String toHex(final byte[] data)
	{
		final StringBuffer buf = new StringBuffer();
		for (final byte element: data)
		{
			int halfbyte = (element >>> 4) & 0x0F;
			int two_halfs = 0;
			do
			{
				if ((0 <= halfbyte) && (halfbyte <= 9))
				{
					buf.append((char) ('0' + halfbyte));
				}
				else
				{
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = element & 0x0F;
			}
			while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static String sha1()
	{
		return sha1("");
	}

	public static String sha1(final String str)
	{
		byte[] sha1hash = new byte[40];
		try
		{
			final MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes("UTF-8"), 0, str.length());
			sha1hash = md.digest();
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (final UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		return toHex(sha1hash);
	}

	public static String firstToUpper(final String s)
	{
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}