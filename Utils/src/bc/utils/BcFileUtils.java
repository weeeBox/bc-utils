package bc.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class BcFileUtils
{
	public static String nameNoExt(File file)
	{
		return nameNoExt(file.getName());
	}

	public static String nameNoExt(String name)
	{
		int index = name.lastIndexOf('.');
		if (index != -1)
		{
			return name.substring(0, index);
		}
		return name;
	}
	
	public static String ext(File file)
	{
		return ext(file.getName());
	}
	
	public static String ext(String name)
	{
		int index = name.lastIndexOf('.');
		if (index != -1)
		{
			return name.substring(index).toLowerCase();
		}
		return "";
	}

	public static File[] listFiles(File dir, final String... extensions)
	{
		return dir.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				String name = pathname.getName();
				if (pathname.isDirectory())
				{
					return !BcStringUtils.equals(name, ".svn", ".git");
				}
				
				return BcStringUtils.equals(ext(pathname), extensions);
			}
		});
	}
	
	public static List<File> listFilesFlatten(File dir, final String... extensions)
	{
		List<File> files = new ArrayList<File>();
		addFiles(files, dir, extensions);
		return files;
	}

	private static void addFiles(List<File> list, File file, String[] extensions)
	{
		if (file.isDirectory())
		{
			File[] files = listFiles(file, extensions);
			for (File childFile : files)
			{
				addFiles(list, childFile, extensions);
			}
		}
		else
		{
			if (BcStringUtils.equals(ext(file), extensions))
			{
				list.add(file);
			}
		}
	}

	public static void writeLines(File file, String...lines) throws IOException
	{
		PrintStream stream = null;
		try
		{
			stream = new PrintStream(file);
			for (String line : lines)
			{
				stream.println(line);
			}
		}
		finally
		{
			if (stream != null)
				stream.close();
		}
	}
}
