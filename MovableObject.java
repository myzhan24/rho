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
	private float angle;
	private float xRatio;
	private float yRatio;
	private float centerX;
	private float centerY;
	private int face;
	public float mass;
	// current location of object
	private float x;
	private float y;
	private int width;
	private int height;
	private float accelerationX;
	private float accelerationY;
	private float vectorX;
	private float vectorY;
	public float globalCD;
	public float harpoonCD;
	public float cloudCD;
	public float shankCD;
	public float blackholeCD;
	public float grenadeCD;
	public float shackleCD;
	public boolean rooted=false;
	public boolean waterBending = false;
	public boolean avatarBending = false;
	public float timeSinceCollision;
	public boolean ground;
	public boolean hooked = false;
	public boolean grappling = false;
	public boolean grapplingSwitch = false;
	public boolean isJumping=false;
	public boolean decay = false;
	public boolean hookedCalc = false;
  	public boolean grappleArmed = false;
  	public boolean harpoonArmed= false;
  	public boolean rebound =false;
  	public boolean reboundX = false;
  	public boolean hookedPlayer = false;
	public float maxSpeed;
	public float gravity;
	public boolean jumping;
	public int reboundCount;
	public float totalDistance;
	public ArrayList <MovableObject> platformsIntersecting;
	public ArrayList <MovableObject> bullets;
	public ArrayList <MovableObject> harpoons;
	public ArrayList <MovableObject> fadeHarpoons;
	public ArrayList <MovableObject> shanks;
	public ArrayList <MovableObject> clouds;
	public ArrayList <MovableObject> rains;
	public ArrayList <MovableObject> grenades;
	public ArrayList <MovableObject> blackholes;
	public ArrayList <MovableObject> blackholesActive;
	public ArrayList <MovableObject> waters;
	public ArrayList <MovableObject> watersBall;
	public ArrayList <ArrayList> shackles;
	public ArrayList <MovableObject> shacklesHooks;
	public ArrayList <String> keyBinds;
	public MovableObject hook;
	public String bulletName = "playerv2.png";
	public String rainName= "player1rainv3.png";
	public String cloudName ="cloud3.png";
	public float health=100f;
		public MovableObject crosshair;
	
	// amount of time on the screen
	public float time;
	
	// speed of object
	
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
    public MovableObject(JPanel mainPanel, String filename, float x, float y, int width, int height)
    {
		xRatio = 3;
		yRatio = 4;
		this.mainPanel = null; // make sure you call setPanel
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		time = 0;
		angle=0f;
		imageList = null;
		currentImage = null;
		imageFilenames = null;
		currentFilename = "";
		setPanel(mainPanel);
		setCurrentFilename(filename);
		accelerationX =0;
		accelerationY =0;
		ground = true;
		this.centerX = x - width/2;
		this.centerY = y - height/2;
		platformsIntersecting = new ArrayList<MovableObject>(3);
		reboundCount=0;
 	}
	public void setPanel(JPanel mainPanel)
	{
		this.mainPanel = mainPanel;
	}

	public JPanel getPanel()
	{
		return mainPanel;
	}
	public void decayVectorX()
	{
		if(this.getVectorX()<0f)
		{
			this.setAccelX(6.7f);
		}
		else if(this.getVectorX()>0f)
		{
			this.setAccelX(-6.7f);
		}	
		if(Math.abs(this.getVectorX()) <= 0.5f )
		{	
			this.setAccelX(0f);
			this.setVectorX(0f);
		}	
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
		//setVectorY(this.getVectorY() + 11.23f);
		setVectorY( 11.23f);
		ground = false;
		isJumping=true;
	}
		public void unhook()
	{
		if(hooked||hookedPlayer)
		{
			hooked=false;
			hookedCalc=false;
		    setAccelY(-.44f);
		    hookedPlayer=false;
		}
	}
	public void grapple()
	{
		grappling  = true;
		hook.setX(getX() +  getWidth()/2f - hook.getWidth()/2f);
		hook.setY( getY() +  getHeight()/2f - hook.getHeight()/2f);
		if( getFace()==0)
		{
		
		hook.setVectorX((float)( 0.5f*getVectorX() + 30f*Math.cos(-1f* getAngle()/180f * Math.PI)));
		hook.setVectorY((float)( 0.5f*getVectorY() +30f*Math.sin(-1f* getAngle()/180f * Math.PI)));
		}
		else
		{
		hook.setVectorX((float)( 0.5f*getVectorX() + -30f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		hook.setVectorY((float)( 0.5f*getVectorY()+30f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
	}
	public void shootWater()
	{
		double radius =( Math.random() * (65.0)) + 16.0;
		double an =  (Math.random() * ( 2.0 * Math.PI));
		float sideX = (float) (Math.cos(an) * radius);
		float sideY = (float) (Math.sin(an) * radius);
		float cLength = (float) (Math.sqrt(sideX * sideX + sideY * sideY));
	
		MovableObject bullet = new MovableObject(mainPanel, rainName, 0,0, 6,12);
		bullet.setX(getX()+getWidth()/2f+sideX-bullet.getWidth()/2f);
		bullet.setY(getY()+getHeight()/2f+sideY-bullet.getHeight()/2f);
		sideX = sideX - bullet.getWidth()/2f;
		sideY = sideY - bullet.getHeight()/2f;
		cLength = (float) (Math.sqrt(sideX * sideX + sideY * sideY));
		float V = (float) Math.sqrt(170.667f /(cLength));
		bullet.setVectorX((sideY / cLength) * V);
		bullet.setVectorY((sideX / cLength) *-1f* V);
		waters.add(bullet);
	}
	public void shootWaterBall()
	{
		for(int i = 0; i < watersBall.size(); i++)
		{
			MovableObject water = watersBall.get(i);
			if(!water.waterBending)
			{
			water.waterBending =true;
			
		
			if( getFace()==0)
			{
			water.setVectorX((float)(  getVectorX() *0.075f+    20f*Math.cos(-1f*(getAngle())/180f * Math.PI)));
			water.setVectorY((float)(  getVectorY()*0.075f+    20f*Math.sin(-1f*( getAngle())/180f * Math.PI)));
			}
			else
			{
			water.setVectorX((float)(  getVectorX()*0.075f+   -20f*Math.cos(( getAngle()-180)/180f * Math.PI)));
			water.setVectorY((float)(  getVectorY()*0.075f+    20f*Math.sin(( getAngle()-180)/180f * Math.PI)));
			}
			}
		}
	}
	public void rain()
	{
		MovableObject bullet = new MovableObject(mainPanel, rainName, (int)(getX() + getWidth()/2),(int) (getY() + getHeight()/2), 6,12);
		bullet.setX((int)(getX() + Math.random()*getWidth()));
		bullet.setVectorX(0f);
		bullet.setAccelX(0f);
		bullet.setVectorY(0f);
		bullet.setAccelY(-.44f);
		bullet.rainName = rainName;
		bullets.add(bullet);
	}
	public void shoot()
	{
		MovableObject bullet = new MovableObject(mainPanel, bulletName, (int)( crosshair.getX() +crosshair.getWidth()/2f),(int) (crosshair.getY() + crosshair.getHeight()/2f), 2,2);
		if( getFace()==0)
		{
		bullet.setVectorX((float)(  getVectorX()*0.075f+    15f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()*0.075f+    15f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		bullet.setVectorX((float)(  getVectorX()*0.075f+   -15f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()*0.075f+    15f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		bullet.mass=1f;
		bullets.add(bullet);
	}
	public void shootGrenade()
	{
		MovableObject bullet = new MovableObject(mainPanel, "grenadev2.png", (int)( crosshair.getX() +crosshair.getWidth()/2f),(int) (crosshair.getY() + crosshair.getHeight()/2f), 10,10);
		if( getFace()==0)
		{
		bullet.setVectorX((float)(  getVectorX()*0.5f+    15f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()*0.5f+    15f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		bullet.setVectorX((float)(  getVectorX()*0.5f+   -15f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()*0.5f+    15f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		grenades.add(bullet);
	}
	public void shootShackle()
	{
		ArrayList temp =  new ArrayList <MovableObject>(2);
		double radius = 80.0;
		double an=0.0;
		if(getFace()==0)
		{
			an = Math.toRadians(getAngle() -90.0) ;
			float sideX = (float) (Math.cos(an) * radius);
			float sideY = (float) (Math.sin(an) * radius);	
			MovableObject bullet = new MovableObject(mainPanel, "hook.png", 0,0,16,16);
			bullet.setX( (getX()+getWidth()/2f)  + sideX - bullet.getWidth()/2f);
			bullet.setY( (getY()+getHeight()/2f)  + sideY - bullet.getHeight()/2f);
			//sideX = sideX - bullet.getWidth()/2f;
			//sideY = sideY - bullet.getHeight()/2f;
			float cLength = (float) (Math.sqrt(sideX * sideX + sideY * sideY));
			float V = (float) Math.sqrt( 200000f /(cLength));
			bullet.setVectorX((sideY/cLength)*-1f*V);
			bullet.setVectorY((sideX/cLength) * V);
			temp.add(bullet);
			bullet = new MovableObject(mainPanel,"hook.png", 0,0,16,16);
			bullet.setX(getX() +getWidth()/2f - bullet.getWidth()/2f);
			bullet.setY(getY() +getHeight()/2f - bullet.getHeight()/2f);
			bullet.hooked=false;
			bullet.hookedPlayer=false;
			bullet.time=0f;
			bullet.totalDistance=0f;
			temp.add(bullet);
			shackles.add(temp);
			setVectorX((float)(  getVectorX()-    1.678f*Math.cos((-1f* getAngle())/180f * Math.PI)));
			setVectorY((float)(  getVectorY()-    1.678f*Math.sin((-1f* getAngle())/180f * Math.PI)));
			}
		else if(getFace()==1)
		{
			an = Math.toRadians(getAngle() -90.0) ;
			float sideX = (float) (Math.cos(( (getAngle()-90f)/180f )* Math.PI) *-1f* radius);
			float sideY = (float) (Math.sin(( (getAngle()-90f)/180f  )* Math.PI) *-1f* radius);	
			MovableObject bullet = new MovableObject(mainPanel, "hook.png", 0,0,16,16);
			bullet.setX( (getX()+getWidth()/2f)  + sideX - bullet.getWidth()/2f);
			bullet.setY( (getY()+getHeight()/2f)  + sideY - bullet.getHeight()/2f);
			//sideX = sideX - bullet.getWidth()/2f;
			//sideY = sideY - bullet.getHeight()/2f;
			float cLength = (float) (Math.sqrt(sideX * sideX + sideY * sideY));
			float V = (float) Math.sqrt( 200000f /(cLength)) *-1f;
			bullet.setVectorX((sideY/cLength)*-1f*V);
			bullet.setVectorY((sideX/cLength) * V);
			temp.add(bullet);
			bullet = new MovableObject(mainPanel,"hook.png", 0,0, 16,16);
			bullet.setX(getX() +getWidth()/2f - bullet.getWidth()/2f);
			bullet.setY(getY() +getHeight()/2f - bullet.getHeight()/2f);
			bullet.hooked=false;
			bullet.hookedPlayer=false;
			temp.add(bullet);
			shackles.add(temp);
			setVectorX((float)(  getVectorX()-   -1.678f*Math.cos(( getAngle()-180)/180f * Math.PI)));
			setVectorY((float)(  getVectorY()-    1.678f*Math.sin(( getAngle()-180)/180f * Math.PI)));
				}
		

		
		
	}
	public void shootShank()
	{
		MovableObject bullet = new MovableObject(mainPanel, "shank.png",(int)( crosshair.getX() +crosshair.getWidth()/2f),(int) (crosshair.getY() + crosshair.getHeight()/2f), 80,30);
		bullet.setX(bullet.getX() - bullet.getWidth()/2);
		bullet.setY(bullet.getY() - bullet.getHeight()/2);
		if( getFace()==0)
		{
		bullet.setVectorX((float)(     25f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		bullet.setVectorY((float)(     25f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		bullet.setVectorX((float)(    -25f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		bullet.setVectorY((float)(     25f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		bullet.timeSinceCollision=0f;
		bullet.setAngle(getAngle());
		bullet.setFace(getFace());
		shanks.add(bullet);
	}
	public void shootCloud()
	{
		MovableObject bullet = new MovableObject(mainPanel, cloudName, (int)( crosshair.getX() +crosshair.getWidth()/2f),(int) (crosshair.getY() + crosshair.getHeight()/2f), 90,65);
		bullet.bullets = new ArrayList <MovableObject>(40);
		bullet.setX(bullet.getX() - bullet.getWidth()/2);
		bullet.setY(bullet.getY() - bullet.getHeight()/2);
		if( getFace()==0)
		{
		bullet.setVectorX((float)(    15f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		bullet.setVectorY((float)(    15f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		bullet.setVectorX((float)(     -15f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		bullet.setVectorY((float)(   15f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		bullet.setAngle(getAngle());
		bullet.timeSinceCollision =0f;
		//health+=2f;
		bullet.rainName = rainName;
		clouds.add(bullet);
	}
	public void shootBlackhole()
	{
		MovableObject bullet = new MovableObject(mainPanel, "blackhole4.png", (int)( crosshair.getX() +crosshair.getWidth()/2f),(int) (crosshair.getY() + crosshair.getHeight()/2f), 100,100);
		bullet.bullets = new ArrayList <MovableObject>(40);
		bullet.setX(bullet.getX() - bullet.getWidth()/2);
		bullet.setY(bullet.getY() - bullet.getHeight()/2);
		if( getFace()==0)
		{
		bullet.setVectorX((float)( getVectorX() +   20f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		bullet.setVectorY((float)( getVectorY() +  20f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		bullet.setVectorX((float)(  getVectorX() +     -20f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		bullet.setVectorY((float)(   getVectorY() +  20f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		bullet.timeSinceCollision =0f;
		bullet.setAngle(0f);
		blackholes.add(bullet);
	}
	public void shootHarpoon()
	{
		
		MovableObject harpoon = new MovableObject(mainPanel, "harpoon.png", (int)( crosshair.getX() +crosshair.getWidth()/2f),(int) (crosshair.getY() + crosshair.getHeight()/2f), 225,25);
		harpoon.setAngle(this.getAngle());
		harpoon.totalDistance=0f;
		harpoon.setX(harpoon.getX() - harpoon.getWidth()/2);
		harpoon.setY(harpoon.getY() - harpoon.getHeight()/2);
		if( getFace()==0)
		{
		harpoon.setVectorX((float)(  getVectorX()+    31f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		harpoon.setVectorY((float)(  getVectorY()+    31f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		setVectorX((float)(  getVectorX()-    11f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		setVectorY((float)(  getVectorY()-    11f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		harpoon.setVectorX((float)(  getVectorX()+   -31f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		harpoon.setVectorY((float)(  getVectorY()+    31f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		setVectorX((float)(  getVectorX()-   -11f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		setVectorY((float)(  getVectorY()-    11f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		harpoon.setFace(getFace());
		harpoons.add(harpoon);
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
	public int getFace()
	{
		return face;
	}
	public void setFace(int x)
	{
		face = x;
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
	
	public void setAngle(float x)
	{
		this.angle= x;
	}
	public float getAngle()
	{
		return this.angle;
	}
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
	public void adjustAccelX(float x)
	{
		setAccelX(getAccelX() + x);
	}
	public void adjustAccelY(float y)
	{
		setAccelY(getAccelY() + y);
	}
	public void adjustVectorX(float x)
	{
		setVectorX(getVectorX() + x);
	}
	public void adjustVectorY(float y)
	{
		setVectorY(getVectorY() + y);
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
    public void setCenterX(float x)
    {
    	centerX = x;
    }
    public void setCenterY(float y)
    {
    	centerY = y;
    }
    public float getCenterX()
    {
    	return centerX;
    }
    public float getCenterY()
    {
    	return centerY;
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

	// FINISH ME
 
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

	// -1 if no intersection, 0 if from top
	public int intersectsWhere(MovableObject other,int deep)
	{ /*
		if (getRect().intersects(other.getRect()))
		{
			// check from top
			Rectangle myRect = new Rectangle(getX(),getY()+getHeight()+deep,getWidth(),deep+1);
			if (myRect.intersects(other.getRect()))
			
			return	0;
			
		}*/
		return -1;
	}

	public Point getPoint()
	{
		return new Point(getXRounded(),getYRounded());
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

	
	// moves toward the other object using the speed
	// uses similar triangles

    // draws the image using the direction
	public void draw2(Graphics g)
	{
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(getX(), getY());
        //transform.rotate(degreesToRadians(getDirection()),24,24);
        transform.rotate(degreesToRadians(angle),getWidth()/2,getHeight()/2); //this does not work
        
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
 
