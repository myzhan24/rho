// Java GUI Game Assignment 
// finish all segments of code marked by  // FINISH ME

// FINISH ME - add a scoring system (maybe 5 points for blowing up an alien,
// and -1 if your bullet misses the alien)
// if your score goes over a 100, you win!
// Keep track of wins and losses and display them on the screen or in the northPanel
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;


public class SpaceInvaders extends JFrame 
{
  // screen constants
  
  private int screenWidth  = 800;
  private int screenHeight = 600;

  private int startMouseDragX = -1;
  private int startMouseDragY = -1;
  
  private boolean gameOver;

  // for buffering
  private BufferedImage back;

  // keys for movement
  private boolean[] keys;  

  // ***** declaration of JFrame variables *****

  // define a mainPanel for components
  JPanel mainPanel;

  // define JPanels for a BorderLayout
  JPanel     northPanel;   // this is the message panel
  SouthPanel southPanel;   // put your buttons on this panel
  JPanel     westPanel;    // this panel will be empty for now
  boolean    showWestPanel = false;
  JPanel     eastPanel;    // this panel will be empty for now
  boolean    showEastPanel = false;

  DrawPanel  centerPanel;  // this will be the panel with all the drawing of MovableObjects

  // title in northPanel
  JLabel northText;

  // Buttons
  JButton runButton;
  JButton stopButton;
  JButton exitButton;

  
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *** declare your arrays and beginning MovableObjects ********************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  
  // create all of the different array lists here to hold aliens, alien bullets, ship bullets, etc. and the ship
  MovableObject ship;
  
  ArrayList <MovableObject> aliens;
  ArrayList <MovableObject> alienBullets;
  ArrayList <MovableObject> shipBullets;
  ArrayList <MovableObject> explosions;
  
  private int newAlienCreateValue = 250;  // 1/250 chance of creating a new alien
  
  // thread for the runButton
  Thread runThread = null;
  int threadDelay = 25;  // the paintComponent method will be called every 25 milliseconds

  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *** create your arrays and beginning MovableObjects (new) ***************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************

  public void initMovableObjects()
  {
	// create all the ArrayList objects to  hold aliens, bullets, etc.
	aliens = new ArrayList<MovableObject>(20);
	alienBullets = new ArrayList<MovableObject>(200);
	shipBullets = new ArrayList<MovableObject>(200);
	explosions = new ArrayList<MovableObject>(20);
	
	// FINISH ME
	// FIND SUITABLE PICTURES FOR YOUR SHIP, ALIEN, BULLETS, AND EXPLOSIONS 
	// your image files can be of type  gif, jpg, or png
	
	// create the space ship and aliens
	// replace Actor.gif with your image
	ship = new MovableObject(centerPanel, "zombie.png", screenWidth/2-25,screenHeight-380, 100,117);
	ship.setY(500 - ship.getHeight());
	ship.setAccelX(0);
	ship.setAccelY(-0.88f);
	ship.maxSpeed = 2f;

	
	// create 2 aliens and add them to the array
	// replace Actor.gif with your image
	MovableObject alien = new MovableObject(centerPanel, "zombie.png", 100,510, 50,50);	
	alien.setDirection(45);
//	aliens.add(alien);
	
	alien = new MovableObject(centerPanel, "Actor.gif", 300,510, 50,50);
	alien.setDirection(350);	
	//aliens.add(alien);
	
  	
  }

  // ***** public void initialize *****  
  public void initialize( )
  {
  	back = null;
  	gameOver = true;
  	screenWidth = 800;
  	screenHeight = 600;
	keys = new boolean[5];
  	
  	// create the buttons
    runButton  = new JButton("Run");
    stopButton  = new JButton("Stop");
    exitButton  = new JButton("Exit");

    // create a mainPanel for components
    mainPanel = new JPanel();

    // ***** create JPanels for a BorderLayout *****
    northPanel   = new JPanel();
    southPanel   = new SouthPanel();
	southPanel.setListeners();
	
	if (showWestPanel)
	{
		westPanel    = new JPanel();
	}
	if (showEastPanel)
	{
		eastPanel    = new JPanel();
	}
	
    centerPanel  = new DrawPanel();

    mainPanel.setLayout(new BorderLayout());
	southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	setCenterPanelColor(Color.black);
    northPanel.setBackground(new Color(115,205,255));
    southPanel.setBackground(new Color(115,205,255));
	if (showWestPanel)
	{
	    westPanel.setBackground(new Color(115,205,255));
	}
	if (showEastPanel)
	{
	    eastPanel.setBackground(new Color(115,205,255));
	}


	// add buttons to southPanel
	southPanel.add(runButton);
	southPanel.add(stopButton);
	southPanel.add(exitButton);

	northText = new JLabel("psy");
	northPanel.add(northText);

	//centerPanel.setFocusable(true);
	//getContentPane().requestFocus();

    // ***** add the panels to the mainPanel 5 areas *****
    mainPanel.add(northPanel,BorderLayout.NORTH);
    mainPanel.add(southPanel,BorderLayout.SOUTH);
	if (showEastPanel)
	{
	    mainPanel.add(eastPanel,BorderLayout.EAST);
	}
	if (showWestPanel)
	{
	    mainPanel.add(westPanel,BorderLayout.WEST);
	}
    mainPanel.add(centerPanel,BorderLayout.CENTER);

	southPanel.setFocusable(true);
	southPanel.requestFocus();

	initMovableObjects();
    centerPanel.repaint();

    // make the mainPanel be the main content area and show it
    setContentPane(mainPanel);
    setVisible(true);  // always show the screen last


	// focus the southPanel so that we can receive key strokes
	southPanel.setFocusable(true);
	southPanel.requestFocus();
  }   // end of public void initialize

  // ***** default constructor *****
  public SpaceInvaders( )
  {
    setSize(screenWidth,screenHeight);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Space Invaders");

    // initialize variables
	initialize( );
  }

  public void setMessage(String message)
  {
  	northText.setText(message);
  }

  public String getMessage()
  {
  	return northText.getText();
  }

  public void setThreadDelay(int threadDelay)
  {
  	this.threadDelay = threadDelay;
  }

  public void setNorthPanelColor(Color color)
  {
		northPanel.setBackground(color);
  }

  public void setSouthPanelColor(Color color)
  {
		southPanel.setBackground(color);
  }

  public void setCenterPanelColor(Color color)
  {
		centerPanel.setBackground(color);
  }

  public void setWestPanelColor(Color color)
  {
	if (showWestPanel)
	{
		westPanel.setBackground(color);
	}
  }

  public void setEastPanelColor(Color color)
  {
	if (showEastPanel)
	{
		eastPanel.setBackground(color);
	}
  }



  // ***** main method *****
  public static void main(String[] arguments)
  {
    SpaceInvaders mySpaceInvaders = new SpaceInvaders( );
  }


class SouthPanel extends JPanel implements KeyListener,ActionListener, Runnable
{
  // start of actionPerformed (ActionListener interface)
  // handle button clicks here
  public SouthPanel()
  {
    // allow buttons to listen for clicks
    super();
  }
  
  public void setListeners()
  {
	runButton.addActionListener(this);
	stopButton.addActionListener(this);
	exitButton.addActionListener(this);
	addKeyListener(this);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    Object source = e.getSource();
    if (source==exitButton)
    {
    	gameOver = true;
    	if (runThread!=null)
		{
			runThread.stop();
			runThread = null;
		}
    	System.exit(0);
    }
    else if (source==runButton)
    {
    	if (runThread != null)
    		if (runThread.isAlive())
    			return;  // if the game is active, ignore the click
    	if (runThread==null)
		{
			runThread = new Thread(this); // the game is inactive, so let's play
		}
		// init the screen
		initialize( );
		
		// start the game
		if (!runThread.isAlive())
			runThread.start();
	  	gameOver = false;

	}
    else if (source==stopButton)  // they want to stop the game
    {
   	  	gameOver = true;
    	if (runThread!=null)
		{
			runThread.stop();
			runThread = null;
		}
	}
  }  // end of actionPerformed

  // thread to delay for the runButton
  // do it all here so we have control of the buttons
  public void run()
  {
   	try
   	{
   		while(true)
   		{   		   
   		   // this will redraw everything on the centerPanel
		   if (!gameOver)
		   {
	           centerPanel.repaint();
		   }

 		   setFocusable(true);
		   requestFocus();
   		   
   		   
  		   Thread.currentThread().sleep(threadDelay);
  		    		   
          }
      }catch(Exception e)
      {
      }
  }

  // start of keyTyped (KeyListener interface)
  public void keyTyped(KeyEvent e)
  {
  }  // end of keyTyped(KeyEvent e)

  // start of keyPressed (KeyListener interface)
  public void keyPressed(KeyEvent e)
  {
  	if (gameOver)
  		return;
  	
    int key = e.getKeyCode();
    String keyString = e.getKeyText(key);
    keyString = keyString.toUpperCase();

	if (keyString.equals("W"))
	{
		keys[0]=true;
	}
	else if (keyString.equals("S"))
	{
		keys[1]=true;
	}
	else if ((keyString.equals("A"))&& !keys[3])
	{
		keys[2]=true;
		ship.setAccelX(-4.2f);
		ship.setCurrentFilename("Actor.gif");
	}
	else if ((keyString.equals("D"))&& !keys[2])
	{
		keys[3]=true;
		ship.setAccelX(4.2f);
	ship.setCurrentFilename("Actor.gif");
	}
	else if (keyString.equals("SPACE"))
	{
		keys[4]=true;
		if(ship.ground)
		{
		
		ship.jump();
		if(ship.crouch)
			ship.setCurrentFilename("Actor.gif"); 
		ship.crouch= false;
		
		}
	}
	
  }  // end of keyPressed(KeyEvent e)

  // start of keyReleased (KeyListener interface)
  public void keyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();
    String keyString = e.getKeyText(key);
    keyString = keyString.toUpperCase();

	if (keyString.equals("W"))
	{
		keys[0]=false;
	}
	else if (keyString.equals("S"))
	{
		ship.crouch=false;
		if(!keys[2] && !keys[3])
		ship.setCurrentFilename("zombie.png"); 
		else
			ship.setCurrentFilename("Actor.gif"); 
		keys[1]=false;
	
	}
	else if (keyString.equals("A"))
	{
		keys[2]=false;
		ship.setCurrentFilename("zombie.png"); 
	}
	else if (keyString.equals("D"))
	{
		keys[3]=false;
		ship.setCurrentFilename("zombie.png"); 
	}
	else if (keyString.equals("SPACE"))
	{
		keys[4]=false;
	}
  }  // end of keyReleased(KeyEvent e)

	
	
} // end of centerPanel class



// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
// this is the panel for the game  (this is an inner class)
// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
class DrawPanel extends JPanel implements  MouseListener, MouseMotionListener
{
	String testXY="X= Y=";
	boolean dragging = false;
	
	public DrawPanel()
	{
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void decay()
	{
		if(keys[2] == false&&keys[3] == false&&!(ship.getVectorX()==0))
		{
			
				
			if(!(ship.getVectorX()==0f))
			{
				if(ship.getVectorX()<0f)
				{
					ship.setAccelX(6.7f);
				}
				else if(ship.getVectorX()>0f)
				{
					ship.setAccelX(-6.7f);
				}
				
				if(Math.abs(ship.getVectorX()) <= 0.5f )
				{	
					ship.setAccelX(0f);
					ship.setVectorX(0f);
				}
			}
			
		}
	}
    public void update(Graphics window)
    {
	   paintComponent(window);
    }
   
	public void paintComponent(Graphics g)
	{
		super.paintComponent((Graphics2D)g);
		Graphics2D g2 = (Graphics2D) g;
		
		decay();
		System.out.println("VectorX: "+ship.getVectorX()+" Acceleration: "+ship.getAccelX());
		if (gameOver)
		{
			return;
		}
		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		//if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics gMemory = back.createGraphics();

		// clear the screen
		gMemory.setColor(Color.BLACK);
		gMemory.fillRect(0,0,getWidth(),getHeight());

		gMemory.setColor(Color.RED);
		gMemory.drawString(testXY,10,50);
		gMemory.setColor(Color.BLACK);


		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// *** Move all your objects here ******************************************
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// move the ship or fire a bullet
    	if (keys[0] && ship != null) // UP
    	{
    		//ship.setDirection(0);
    		//ship.moveUp();	
    	}
    	else if (keys[1] && ship != null) // DOWN
    	{
    		//ship.setDirection(180);
    		if(ship.ground)
    		{
    		
    		ship.crouch = true;	 
    		ship.setCurrentFilename("Bug.gif"); 
    		}
    	}
    	else if (keys[2] && !keys[3] && ship != null) // LEFT
    	{
    		//ship.setDirection(270);
    		ship.moveLeft();	  
    	}
    	else if (keys[3] && !keys[2]&& ship != null) // RIGHT
    	{
    		//ship.setDirection(90);
    		ship.moveRight();
     	}
    	else if (keys[4] && ship != null) // SPACE
    	{
    		// FINISH ME to fire a bullet
    		// create a bullet and add it to your ship bullet list
    		// do NOT draw it here!!!!!!!!!!
     	}

	

		// randomly create new aliens and randomly place them in the sky
		int randomX = (int) (Math.random()*newAlienCreateValue);
		if (randomX == 0)
		{
			// FINISH ME
			// create a random x value to place it in the sky
			// create a new alien
			// and add it to the alien list
			// do not draw it here !!!!!!!!!!!!
			// your alien's y value should probably be off the screen (ex. -100)
			MovableObject alien = new MovableObject(centerPanel, "Actor.gif", 100,10, 50,50);	
			alien.setXRatio(getWidth());
			alien.setYRatio(getHeight());
			aliens.add(alien);
				
		}
		
		
		// FINISH ME
		// randomly have the aliens fire bullets
		// loop through all the aliens, get a random number, check for one number,
		// and if =, then create a bullet and add it to the alien bullet list
		// make sure that the coordinates of the bullet will place the bullet
		// in front of the alien
		// then set the speedY value to be faster than the alien
		
		
  		// move the aliens and remove them if they go off the screen
   		for (int i=0; i < aliens.size(); i++)
   		{
   		   		MovableObject alien = aliens.get(i);
   		   		if (ship != null)
   		   		{
   		   			alien.moveInCurrentDirection(); //    moveDown();
   		   		}
    		   	if (alien.getY() > getHeight()+alien.getHeight())
   		   		{
   		   			aliens.remove(i);
   		   			i--;
   		   		}
   		}
 
		// FINISH ME
 		// move the alien bullets and remove them if they go off the screen
 		// you will need a loop to loop through all the bullets
		// do not draw them 

		 		
		// FINISH ME
 		// move the ship bullets and remove them if they go off the screen
 		// you will need a loop to loop through all the bullets
		// do not draw them 

 		
		// FINISH ME
 		// update your explosions (decrease their time by 25) and remove them if their time is <= 0 
 		// call your explosion's setTime method
 		// call your explosion's getTime method and subtract the timer time (threadDelay) (25)
 		// you will need a loop to loop through all the explosions
		// do not draw them 
 		
 
 
 
 		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// ***  check for collisions here  *****************************************
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		
		// check to see if a ship and an alien have collided
		if (ship!=null)
 			for (int i=0; i < aliens.size(); i++)
   			{
   		   		MovableObject alien = aliens.get(i);
   		   		if (ship.intersects(alien))
   		   		{
   		   			// FINISH ME
   		   			// CREATE AN EXPLOSION AND ADD IT TO YOUR EXPLOSION LIST
   		   			// SET THE EXPLOSION TIME TO 1500 MILLISECONDS (call setTime)
   		   			ship = null;
   		   			aliens.remove(i);
   		   			i--;

   		   			break;
   		   		}
   			}

		// FINISH ME
		// check to see if a ship and an alien bullet have collided
 		// you will need a loop to loop through all the alien bullets
  		// if so, replace the ship with an explosion and set it's time to 1.5 seconds
		
 		
 		
		// FINISH ME
		// check to see if an alien and a ship bullet have collided
 		// you will need nested loops to loop through all the aliens and bullets
  		// if so, replace the alien with an explosion and set it's time to 1.5 seconds



		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		// *** redraw all your objects here using gMemory **************************
		// *** or use any graphic commands to draw lines, ovals, rects, etc. *******
		// *************************************************************************
		// *************************************************************************
		// *************************************************************************
		
	
		ship.setX(ship.getX() + ship.getVectorX());
		ship.setY(ship.getY() - ship.getVectorY());
		ship.setVectorY(ship.getVectorY() + ship.getAccelY());
		if(ship.getVectorX() <= ship.maxSpeed && ship.getVectorX() >=0)
			ship.setVectorX(ship.getVectorX() + (0.1f)*ship.getAccelX());
			
		else if(ship.getVectorX() >= (-1f *ship.maxSpeed) && ship.getVectorX() <=0)
			ship.setVectorX(ship.getVectorX() + (0.1f)*ship.getAccelX());
	
		if(ship.getVectorX() > ship.maxSpeed)
			ship.setVectorX(ship.maxSpeed);
			
		else if(ship.getVectorX() < -1f*ship.maxSpeed)
			ship.setVectorX(-1f*ship.maxSpeed);
		if(ship.getY() > 350)
		{
			ship.setVectorY(0);
			ship.ground = true;
			ship.setY(350);
		}

		// draw your ship
		if (ship != null)
		{
			ship.draw(gMemory);
		}


		// FINISH ME
		// draw your ship's bullets
		

		// draw your aliens
   		for (int i=0; i < aliens.size(); i++)
   		{
   		   		MovableObject alien = aliens.get(i);
   		   		alien.draw(gMemory);
   		}
   		
		// FINISH ME
   		// draw your alien bullets
   		
		// FINISH ME
   		// draw your explosions
   		
   		
   		
		// *** show the screen by copying the image to the graphics display ********
   		g2.drawImage(back, null, 0, 0);	
 	}  // end of public void paintComponent(Graphics g)

 
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *** if a key is pressed, handle the movement here ***********************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  public void keyPressed(String keyString)
  {
  	if (ship == null)
  		return;

    // another way to handle the keys, but no repeat factor
    if (keyString.equals("UP"))
    {
    	ship.setDirection(0);
    	ship.moveUp();	
    }
    else if (keyString.equals("DOWN"))
    {
    	ship.setDirection(180);
    	ship.moveDown();	   	
    }
    else if (keyString.equals("LEFT"))
    {
    	ship.setDirection(270);
    	ship.moveLeft();	  
    }
    else if (keyString.equals("RIGHT"))
    {
    	ship.setDirection(90);
    	ship.moveRight();
     }

    // check to see if you need to fire
  } // end of method public void keyPressed(String keyString)


  // ***** MouseListener interface methods *****


  // start of mouseClicked(MouseEvent e) (MouseListener interface)
  public void mouseClicked(MouseEvent e) 
  {
    //int xPos = e.getX();
    //int yPos = e.getY();
  }  // end of public void mouseClicked(MouseEvent e) 


  // start of mousePressed(MouseEvent e) (MouseListener interface)
  public void mousePressed(MouseEvent e) 
  {
  	if (ship==null)
  		return;
    if (ship.getRect().contains(e.getX(),e.getY()))
    {
 	    startMouseDragX = e.getX();
    	startMouseDragY = e.getY();
    	dragging = true;
    }
  }  // end of public void mousePressed(MouseEvent e)



  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *** if the mouse is released, fire a bullet  ****************************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  // *************************************************************************
  public void mouseReleased(MouseEvent e) 
  {ship.setAccelY(-9.8f);
  
    dragging = false;
    startMouseDragX = -1;
    startMouseDragY = -1;
 	if (ship==null)
  		return;
    int xPos = e.getX();
    int yPos = e.getY();
    	
    if (ship.getRect().contains(xPos,yPos))
    {
		// FINISH ME
		// optional
    	// fire a bullet  CREATE A BULLET AND ADD TO THE LIST OF SHIP BULLETS
    	// DO NOT CALL PAINTCOMPONENT HERE, YOU WILL DRAW IT IN THE 
    	// PAINTCOMPONENT METHOD
    }
  }  // end of public void mouseReleased(MouseEvent e)


  public void mouseEntered(MouseEvent e) 
  {

  }  // end of public void mouseEntered(MouseEvent e)


  public void mouseExited(MouseEvent e) 
  {

  }  // end of public void mouseExited(MouseEvent e)


  // ***** MouseMotionListener interface methods *****


  public void mouseMoved(MouseEvent e) 
  {
    int xPos = e.getX();
    int yPos = e.getY();
    testXY = "X=" + xPos + "  Y=" + yPos;
  }  // end of public void mouseMoved(MouseEvent e) 


  public void mouseDragged(MouseEvent e) 
  {
  	ship.setAccelY(0f);
    int xPos = e.getX();
    int yPos = e.getY();
    testXY = "X=" + xPos + "  Y=" + yPos;
 	if (ship==null)
  		return;
    
    boolean allowDragging = true;

    if (xPos < 0)
    	allowDragging = false;
    else if (xPos >= this.getWidth())
    	allowDragging = false;
    if (yPos < 0)
    	allowDragging = false;
    else if (yPos >= this.getHeight())
    	allowDragging = false;

    if ( dragging && allowDragging && ship!=null && 
    	 ship.getX()>-1 && ship.getX()+ship.getWidth()<this.getWidth()
    	 && ship.getY() > -1 && ship.getY()+ship.getHeight()<this.getHeight()
       )
    {
    	int distanceX;
    	int distanceY;
    	int newX=ship.getXRounded();
    	int newY=ship.getYRounded();
    	boolean shipMoved = false;

    	if (xPos < startMouseDragX)
    	{
    		distanceX = startMouseDragX - xPos;
    		newX = ship.getXRounded() - distanceX;
    		shipMoved = true;
    	}
    	else if (xPos > startMouseDragX)
    	{
    		distanceX = xPos - startMouseDragX;
    		newX = ship.getXRounded() + distanceX;
    		shipMoved = true;
    	}
 
    	if (yPos < startMouseDragY)
    	{
    		distanceY = startMouseDragY - yPos;
    		newY = ship.getYRounded() - distanceY;
    		shipMoved = true;
    	}
    	else if (yPos > startMouseDragY)
    	{
    		distanceY = yPos - startMouseDragY;
    		newY = ship.getYRounded() + distanceY;
    		shipMoved = true;
    	}
   		
   		if (shipMoved)
   		{
   			if (newX<0)
   				newX = 0;
   			if (newX+ship.getWidth()>=this.getWidth()-1)
   				newX = this.getWidth()-ship.getWidth()-1;
   			if (newY<0)
   				newY = 0;
   			if (newY+ship.getHeight()>=this.getHeight()-1)
   				newY = this.getHeight()-ship.getHeight()-1;
    		ship.setX(xPos- ship.getWidth()/2);
   			ship.setY(yPos- ship.getHeight()/2);
   			startMouseDragX = xPos;
   			startMouseDragY = yPos;
   		} // end of if (shipMoved) 
   		
    } // end of if (dragging)
    
  }  // end of public void mouseDragged(MouseEvent e)

} // end of class DrawPanel
// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
// this is the END of class DrawPanel for the game
// *************************************************************************************
// *************************************************************************************
// *************************************************************************************




} // end of class SpaceInvaders
