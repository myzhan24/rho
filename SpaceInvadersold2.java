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
  
  // create all of the different array lists here to hold aliens, alien bullets, player bullets, etc. and the player
  MovableObject player;
  
  
  ArrayList <MovableObject> playerBullets;
  ArrayList <MovableObject> platforms;
  ArrayList <MovableObject> platformsIntersecting;
  
  
  // thread for the runButton
  Thread runThread = null;
  int threadDelay = 16;  // the paintComponent method will be called every 25 milliseconds

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
	playerBullets = new ArrayList<MovableObject>(200);
	platforms = new ArrayList<MovableObject>(5);
	platformsIntersecting = new ArrayList<MovableObject>(5);
	// FINISH ME
	// FIND SUITABLE PICTURES FOR YOUR player, ALIEN, BULLETS, AND EXPLOSIONS 
	// your image files can be of type  gif, jpg, or png
	
	// create the space player and aliens
	// replace Actor.gif with your image
	player = new MovableObject(centerPanel, "player.png", screenWidth/2-25,screenHeight-380, 30,50);
	player.setY(500 - player.getHeight());
	player.setAccelX(0);
	player.setAccelY(-0.5f);
	player.gravity = -0.5f;
	player.jumping=false;
	MovableObject platform  = new MovableObject(centerPanel, "player.png", 0,500, 800,50);
	// create 2 aliens and add them to the array
	// replace Actor.gif with your image
	platforms.add(platform);

//aliens.add(alien);
	

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

	northText = new JLabel("");
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
		player.setAccelX(-4.2f);
		player.setCurrentFilename("player.png");
	}
	else if ((keyString.equals("D"))&& !keys[2])
	{
		keys[3]=true;
		player.setAccelX(4.2f);
	player.setCurrentFilename("player.png");
	}
	else if (keyString.equals("SPACE"))
	{
		keys[4]=true;
		if(player.ground)
		{
		
		player.jump();
		player.jumping=true;
		if(player.crouch)
			player.setCurrentFilename("player.png"); 
		player.crouch= false;
		
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
		player.crouch=false;
		if(!keys[2] && !keys[3])
		player.setCurrentFilename("player.png"); 
		else
			player.setCurrentFilename("player.png"); 
		keys[1]=false;
	
	}
	else if (keyString.equals("A"))
	{
		keys[2]=false;
		player.setCurrentFilename("player.png"); 
	}
	else if (keyString.equals("D"))
	{
		keys[3]=false;
		player.setCurrentFilename("player.png"); 
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
		if(keys[2] == false&&keys[3] == false&&!(player.getVectorX()==0))
		{
			
				
			if(!(player.getVectorX()==0f))
			{
				if(player.getVectorX()<0f)
				{
					player.setAccelX(6.7f);
				}
				else if(player.getVectorX()>0f)
				{
					player.setAccelX(-6.7f);
				}
				
				if(Math.abs(player.getVectorX()) <= 0.5f )
				{	
					player.setAccelX(0f);
					player.setVectorX(0f);
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
		//System.out.println("VectorX: "+player.getVectorX()+" Acceleration: "+player.getAccelX());
		
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
		// move the player or fire a bullet
		
		// COLLISIONS OF PLATFORMSFHSDGI
		for(int i = 0; i < platforms.size(); i++)
		{
			if( !platformsIntersecting.contains(platforms.get(i))  &&player.getRect().intersects(platforms.get(i).getRect()))
				platformsIntersecting.add(platforms.get(i));
		}
		for(int i = 0; i < platformsIntersecting.size(); i++)
		{
			MovableObject plat = platformsIntersecting.get(i);
			if(player.getY() <= plat.getY() )
			{
				if(!player.jumping)
				{
					player.setAccelY(0f);
					player.setVectorY(0f);
				}
				player.setY(plat.getY() - player.getHeight() +1);
				player.ground=true;
			}
		}
		for(int i = 0; i < platformsIntersecting.size(); i++)
		{
			System.out.println("sHE");
			if(!(player.getRect().intersects(platformsIntersecting.get(i).getRect())))
			{
				platformsIntersecting.remove(i);
				i--;
				System.out.println("HE");
			}
		}
		if(platformsIntersecting.size() ==0)
		{
			player.setAccelY(player.gravity);
			player.ground=false;
		}
		System.out.println(""+platformsIntersecting.size());
		System.out.println(""+ player.ground);
    	if (keys[0] && player != null) // UP
    	{
    		//player.setDirection(0);
    		//player.moveUp();	
    	}
    	else if (keys[1] && player != null) // DOWN
    	{
    		//player.setDirection(180);
    		if(player.ground)
    		{
    		
    		player.crouch = true;	 
    		player.setCurrentFilename("player.png"); 
    		}
    	}
    	else if (keys[2] && !keys[3] && player != null) // LEFT
    	{
    		//player.setDirection(270);
    		player.moveLeft();	  
    	}
    	else if (keys[3] && !keys[2]&& player != null) // RIGHT
    	{
    		//player.setDirection(90);
    		player.moveRight();
     	}
    	else if (keys[4] && player != null) // SPACE
    	{
    		// FINISH ME to fire a bullet
    		// create a bullet and add it to your player bullet list
    		// do NOT draw it here!!!!!!!!!!
     	}

	

		// randomly create new aliens and randomly place them in the sky
	
		
		// FINISH ME
		// randomly have the aliens fire bullets
		// loop through all the aliens, get a random number, check for one number,
		// and if =, then create a bullet and add it to the alien bullet list
		// make sure that the coordinates of the bullet will place the bullet
		// in front of the alien
		// then set the speedY value to be faster than the alien
		
		
  		// move the aliens and remove them if they go off the screen
  
 
		// FINISH ME
 		// move the alien bullets and remove them if they go off the screen
 		// you will need a loop to loop through all the bullets
		// do not draw them 

		 		
		// FINISH ME
 		// move the player bullets and remove them if they go off the screen
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
		
		// check to see if a player and an alien have collided

		// FINISH ME
		// check to see if a player and an alien bullet have collided
 		// you will need a loop to loop through all the alien bullets
  		// if so, replace the player with an explosion and set it's time to 1.5 seconds
		
 		
 		
		// FINISH ME
		// check to see if an alien and a player bullet have collided
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
		
	
		player.setX(player.getX() + player.getVectorX());
		player.setY(player.getY() - player.getVectorY());
		player.setVectorY(player.getVectorY() + player.getAccelY());
		if(player.getVectorX() <= player.maxSpeed && player.getVectorX() >=0)
			player.setVectorX(player.getVectorX() + (0.1f)*player.getAccelX());
			
		else if(player.getVectorX() >= (-1f *player.maxSpeed) && player.getVectorX() <=0)
			player.setVectorX(player.getVectorX() + (0.1f)*player.getAccelX());
	
		if(player.getVectorX() > player.maxSpeed)
			player.setVectorX(player.maxSpeed);
			
		else if(player.getVectorX() < -1f*player.maxSpeed)
			player.setVectorX(-1f*player.maxSpeed);
		/*if(player.getY() > 350)
		{
			player.setVectorY(0);
			player.ground = true;
			player.setY(350);
		}*/

		// draw your player
		if (player != null)
		{
			player.draw(gMemory);
		}
		for(int i = 0 ; i < platforms.size() ; i++)
		{
			platforms.get(i).draw(gMemory);
		}

		// FINISH ME
		// draw your player's bullets
		

		// draw your aliens
   		
   		
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
  	if (player == null)
  		return;

    // another way to handle the keys, but no repeat factor
    if (keyString.equals("UP"))
    {
    	player.setDirection(0);
    	player.moveUp();	
    }
    else if (keyString.equals("DOWN"))
    {
    	player.setDirection(180);
    	player.moveDown();	   	
    }
    else if (keyString.equals("LEFT"))
    {
    	player.setDirection(270);
    	player.moveLeft();	  
    }
    else if (keyString.equals("RIGHT"))
    {
    	player.setDirection(90);
    	player.moveRight();
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
  	if (player==null)
  		return;
    if (player.getRect().contains(e.getX(),e.getY()))
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
  {
  	player.setAccelY(player.gravity);
    dragging = false;
    startMouseDragX = -1;
    startMouseDragY = -1;
 	if (player==null)
  		return;
    int xPos = e.getX();
    int yPos = e.getY();
    	
    if (player.getRect().contains(xPos,yPos))
    {
		// FINISH ME
		// optional
    	// fire a bullet  CREATE A BULLET AND ADD TO THE LIST OF player BULLETS
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
  	player.setAccelY(0f);
    int xPos = e.getX();
    int yPos = e.getY();
    testXY = "X=" + xPos + "  Y=" + yPos;
 	if (player==null)
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

    if ( dragging && allowDragging && player!=null && 
    	 player.getX()>-1 && player.getX()+player.getWidth()<this.getWidth()
    	 && player.getY() > -1 && player.getY()+player.getHeight()<this.getHeight()
       )
    {
    	int distanceX;
    	int distanceY;
    	int newX=player.getXRounded();
    	int newY=player.getYRounded();
    	boolean playerMoved = false;

    	if (xPos < startMouseDragX)
    	{
    		distanceX = startMouseDragX - xPos;
    		newX = player.getXRounded() - distanceX;
    		playerMoved = true;
    	}
    	else if (xPos > startMouseDragX)
    	{
    		distanceX = xPos - startMouseDragX;
    		newX = player.getXRounded() + distanceX;
    		playerMoved = true;
    	}
 
    	if (yPos < startMouseDragY)
    	{
    		distanceY = startMouseDragY - yPos;
    		newY = player.getYRounded() - distanceY;
    		playerMoved = true;
    	}
    	else if (yPos > startMouseDragY)
    	{
    		distanceY = yPos - startMouseDragY;
    		newY = player.getYRounded() + distanceY;
    		playerMoved = true;
    	}
   		
   		if (playerMoved)
   		{
   			if (newX<0)
   				newX = 0;
   			if (newX+player.getWidth()>=this.getWidth()-1)
   				newX = this.getWidth()-player.getWidth()-1;
   			if (newY<0)
   				newY = 0;
   			if (newY+player.getHeight()>=this.getHeight()-1)
   				newY = this.getHeight()-player.getHeight()-1;
    		player.setX(xPos- player.getWidth()/2);
   			player.setY(yPos- player.getHeight()/2);
   			startMouseDragX = xPos;
   			startMouseDragY = yPos;
   		} // end of if (playerMoved) 
   		
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
