/**
 * @(#)Platform.java
 *
 *
 * @author 
 * @version 1.00 2012/2/23
 */
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Platform {
        private float x1,y1,x2,y2;
    /**
     * Creates a new instance of <code>Platform</code>.
     */
    public Platform() {
    	x1=0;
    	y1=0;
    	y2=0;
    	x2=0;
    }
    public void setImageFilenames(String [] filenames)
	{
		imageFilenames = filenames;
		imageList = new ArrayList<Image>();
		for (int i=0; i < filenames.length; i++)
		{
			try
			{
				Image pic = Toolkit.getDefaultToolkit().getImage(filenames[i]);
				imageList.add(pic);
			}
			catch (Exception e)
			{
				// FINISH ME
				// print out an error message
			}
		}
		if (filenames.length > 0)
		{
			currentFilename = filenames[0];
			setImage();
		}
	}
	private void setImage()
	{
		if (currentFilename != null && currentFilename != "")
		{
			try
			{
				currentImage = Toolkit.getDefaultToolkit().getImage(currentFilename);
				//System.out.println("toolkit ok");
			}
			catch (Exception e)
			{
				currentImage = null;
				currentFilename = "";
				System.out.println("error getImage with toolkit");
			}
		}
	}

	public void setCurrentFilename(String filename)
	{
		currentFilename = filename;
		setImage();
	}

	public void setCurrentFilenamePosition(int position)
	{
		if (imageFilenames == null)
			return;
		if (position < imageFilenames.length && position > -1)
			{
				currentFilename = imageFilenames[position];
				setImage();
			}
		else
			{
				currentFilename = "";
				currentImage = null;
			}
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
