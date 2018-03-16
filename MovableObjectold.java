// only fill in methods marked as  	// FINISH ME
// DO NOT MODIFY THE OTHER METHODS UNLESS YOU KNOW WHAT YOU ARE DOING!!!!

// all of your visible objects in the main program will be of type MovableObject
// examples of how to create a MovableObject are shown below
// ship = new MovableObject(centerPanel,"filename.png",x,y,width,height);
// alien = new MovableObject(centerPanel,"filename.png",x,y,width,height);
// bullet = new MovableObject(centerPanel,"filename.png",x,y,width,height);
// explosion = new MovableObject(centerPanel,"filename.png",x,y,width,height);
// 

// to do:




import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class MovableObject
{
	// direction to move
	// 0 to 360 compass direction
	private int direction;
	private int speed;
	private float xRatio;
	private float yRatio;
	
	// current location of object
	private float x;
	private float y;
	private int width;
	private int height;
	private float accelerationX;
	private float accelerationY;
	private float vectorX;
	private float vectorY;
	public boolean ground;
	public boolean crouch;
	public float maxSpeed;
	
	// amount of time on the screen
	private float time;
	
	// speed of object
 
	private int speedX;
	private int speedY;
	
	// list of possible images to use
	private ArrayList <Image> imageList;
	
	// current image being displayed
	private Image currentImage;

	// filename of current image being displayed
	private String currentFilename;
	
	// list of filenames for the images (jpg, ...)
	private String [] imageFilenames;
		
	// the JPanel that this object is being displayed on
	private JPanel mainPanel;

	// this is the only constructor that is offered
	// centerPanel should be passed as the first parameter when you create a MovableObject
    public MovableObject(JPanel mainPanel, String filename, int x, int y, int width, int height)
    {
		direction = 0; // Location.NORTH;
		speed = 3;
		xRatio = 3;
		yRatio = 4;
		this.mainPanel = null; // make sure you call setPanel
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		time = 0;
		speedX = 3;
		speedY = 3;
		imageList = null;
		currentImage = null;
		imageFilenames = null;
		currentFilename = "";
		setPanel(mainPanel);
		setCurrentFilename(filename);
		accelerationX =0;
		accelerationY =0;
		ground = true;
 	}
 	

	public void setPanel(JPanel mainPanel)
	{
		this.mainPanel = mainPanel;
	}

	public JPanel getPanel()
	{
		return mainPanel;
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
	public void jump()
	{
		setVectorY(this.getVectorY() + 15);
		ground = false;
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

 
	// FINISH ME (replace the 0)
    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
        this.direction = this.direction % 360;
    }

	// FINISH ME (replace the 0)
    public float getX()
    {
        return x;
    }

    public int getXRounded()
    {
        return Math.round(x);
    }

	// FINISH ME
	public void setVectorX(float x)
	{
		this.vectorX= x;
	}
	public float getVectorX()
	{
		return this.vectorX;
	}
	public void setVectorY(float x)
	{
		this.vectorY= x;
	}
	public float getVectorY()
	{
		return this.vectorY;
	}
	public void setAccelY(float x)
	{
		this.accelerationY= x;
	}
	public float getAccelY()
	{
		return this.accelerationY;
	}
	public void setAccelX(float x)
	{
		this.accelerationX= x;
	}
	public float getAccelX()
	{
		return this.accelerationX;
	}
    public void setX(float x)
    {
    	this.x = x;
    }

	// FINISH ME (replace the 0)
    public float getY()
    {
		return y;
    }

    public int getYRounded()
    {
		return Math.round(y);
    }

	// FINISH ME
    public void setY(float y)
    {
    	this.y = y;
    }

	// FINISH ME
    public void setXY(float x, float y)
    {
    	this.x = x;
    	this.y = y;
    }

	// FINISH ME (replace the 0)
    public int getWidth()
    {
        return width;
    }

	// FINISH ME
    public void setWidth(int width)
    {
    	this.width = width;
    }

	// FINISH ME (replace the 0)
    public int getHeight()
    {
        return height;
    }

	// FINISH ME
    public void setHeight(int height)
    {
    	this.height = height;
    }

	// FINISH ME (replace the 0)
    public int getSpeedX()
    {
        return speedX;
    }

	// FINISH ME
    public void setSpeedX(int speedX)
    {
    	this.speedX = speedX;
    }

	// FINISH ME (replace the 0)
    public int getSpeedY()
    {
        return speedY;
    }

	// FINISH ME
    public void setSpeed(int speed)
    {
    	this.speed = speed;
    }

	// FINISH ME (replace the 0)
    public int getSpeed()
    {
        return speed;
    }


	// FINISH ME
    public void setXRatio(float xRatio)
    {
    	this.xRatio = xRatio;
    }

	// FINISH ME (replace the 0)
    public float getXRatio()
    {
        return xRatio;
    }


	// FINISH ME
    public void setYRatio(float yRatio)
    {
    	this.yRatio = yRatio;
    }

	// FINISH ME (replace the 0)
    public float getYRatio()
    {
        return yRatio;
    }


	// FINISH ME
    public void setSpeedXY(int speedX, int speedY)
    {
    	this.speedX = speedX;
    	this.speedY = speedY;
    }

	// FINISH ME
    public void setSpeedY(int speedY)
    {
    	this.speedY = speedY;
    }

	// FINISH ME (replace the 0)
    public float getTime()
    {
    	return time;
    }

	// FINISH ME
    public void setTime(float time)
    {
    	this.time = time;
    }


	public void moveUp()
	{
		setY(getY() - speedY);
	}

	public void moveDown()
	{
		setY(getY() + speedY);
	}

	public void moveLeft()
	{
		setX(getX() - speedX);
	}

	public void moveRight()
	{
		setX(getX() + speedX);
	}

	public void moveTo(int x, int y)
	{
		setX(x);
		setY(y);
	}

	public Rectangle getRect()
	{
		return new Rectangle(getXRounded(),getYRounded(),getWidth(),getHeight());
	}

	public boolean intersects(Rectangle rect)
	{
		if (getRect().intersects(rect))
			return true;
		return false;
	}
	
	public boolean intersects(MovableObject other)
	{
		if (getRect().intersects(other.getRect()))
			return true;
		return false;
	}

	public Point getPoint()
	{
		return new Point(getXRounded(),getYRounded());
	}

	public void turnRight()
	{
		setDirection(getDirection()+90);
	}
	
	public boolean containsPoint(Point point)
	{
		if (getRect().contains(point))
			return true;
		return false;
	}


	public static float getDegreesFromCompassDirection(float compassDirection)
	{
		if (compassDirection >= 0 && compassDirection <= 90)		
		{
			float startDegrees = 90;
			float degrees = Math.abs(compassDirection - startDegrees);
			return degrees;
		}
		if (compassDirection > 90 && compassDirection <= 180)		
		{
			float degrees = Math.abs(360 - (compassDirection-90));
			return degrees;
		}
		
		if (compassDirection > 180 && compassDirection <= 270)		
		{
			float degrees = Math.abs(270 - (compassDirection-180));
			return degrees;
		}

		if (compassDirection > 270 && compassDirection <= 359)		
		{
			float degrees = Math.abs(180 - (compassDirection-270));
			return degrees;
		}

		return 0;	
	}


	public void moveInCurrentDirection()
	{
		int   currentDirection = getDirection();// get the compass direction
		float currentDegrees = getDegreesFromCompassDirection(currentDirection);
		
		double radDegrees = 0;
		
		if (speed > 0)
		{
			float newX = 0;
			float newY = 0;
						
			if (currentDirection >= 0 && currentDirection <= 90)
			{
				currentDegrees = 360-currentDegrees;
				radDegrees = degreesToRadians(currentDegrees);
				newX = xRatio*((float) Math.abs(Math.cos(radDegrees)*speed));
				newY = yRatio*((float) Math.abs(Math.sin(radDegrees)*speed));
				newX = getX() + newX;
				newY = getY() - newY;	
			}
			else if (currentDirection > 90 && currentDirection <= 180)
			{
				currentDegrees = 90-currentDegrees;
				radDegrees = degreesToRadians(currentDegrees);
				newX = xRatio*((float) Math.abs(Math.cos(radDegrees)*speed));
				newY = yRatio*((float) Math.abs(Math.sin(radDegrees)*speed));
				newX = getX() + newX;
				newY = getY() + newY;	
			}
			else if (currentDirection > 180 && currentDirection <= 270)
			{
				currentDegrees = 270-currentDegrees;
				radDegrees = degreesToRadians(currentDegrees);
				newX = xRatio*((float) Math.abs(Math.cos(radDegrees)*speed));
				newY = yRatio*((float) Math.abs(Math.sin(radDegrees)*speed));
				newX = getX() - newX;
				newY = getY() + newY;	
			}
			else if (currentDirection > 270 && currentDirection < 360)
			{
				currentDegrees = 180-currentDegrees;
				radDegrees = degreesToRadians(currentDegrees);
				newX = xRatio*((float) Math.abs(Math.cos(radDegrees)*speed));
				newY = yRatio*((float) Math.abs(Math.sin(radDegrees)*speed));
				newX = getX() - newX;
				newY = getY() - newY;	
			}
			
			setX(newX);
			setY(newY);
			
		}
		
		 
	}
	
	// moves toward the other object using the speed
	// uses similar triangles
	public void moveTowards(MovableObject other)
	{
		double sideX = Math.abs(other.getX() - getX());
		double sideY = Math.abs(other.getY() - getY());
		double d = Math.sqrt(sideX*sideX + sideY*sideY);
		
		if (d == 0)
			return;
			
		int speedX = (int)Math.round((getSpeed()*sideX)/d);
		int speedY = (int)Math.round((getSpeed()*sideY)/d);
		
		if (getX() < other.getX())
			setX(getX() + speedX);
		else if (getX() > other.getX())
			setX(getX() - speedX);
		
		if (getY() < other.getY())
			setY(getY() + speedY);
		else if (getY() > other.getY())
			setY(getY() - speedY);			
	
	}

    // draws the image using the direction
	public void draw2(Graphics g)
	{
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(getX(), getY());
        //transform.rotate(degreesToRadians(getDirection()),24,24);
        transform.rotate(degreesToRadians(getDirection()),getWidth()/2,getHeight()/2); //this does not work
        
        g2.drawImage(currentImage, transform, mainPanel);        
	}
    
	public double degreesToRadians(double degrees)
	{
     	return degrees*Math.PI/180;
	}
	
	// draws the image in its original direction
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.black);

		g2.drawImage(currentImage,
			getXRounded(), 
			getYRounded(), 
			getWidth(),
			getHeight(),
			mainPanel);
	}

}  // end of class MovableObject

/*
boolean drawImage(Image img, int x, int y, ImageObserver observer)
  boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
  boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer)
  boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer)
*/

