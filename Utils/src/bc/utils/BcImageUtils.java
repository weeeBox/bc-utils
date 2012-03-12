package bc.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BcImageUtils
{
	public static BufferedImage readImage(File file)
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
}
