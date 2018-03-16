// Java GUI Game Assignment 
// finish all segments of code marked by  // FINISH ME
// FINISH ME - add a scoring system (maybe 5 points for blowing up an alien,
// and -1 if your bullet misses the alien)
// if your score goes over a 100, you win!
// Keep track of wins and losses and display them on the screen or in the northPanel
 
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;

public class rho extends JApplet
{
  // screen constants
  
  private int screenWidth  =1920;
  private int screenHeight = 950;
  private int startMouseDragX = -1;
  private int startMouseDragY = -1;
  private int p1score=0;
  private int p2score=0;
  private float ropeDistance = 0f;
  private float shrinkDistance = 0f;
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
  JLabel playerHealth;
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
  MovableObject player2;
 // MovableObject crosshair;
 // MovableObject crosshair2;
 // MovableObject hook;
  //MovableObject player2.hook;
  MovableObject platform;
  MP3 jump = new MP3("7.mp3",1);
  MP3 shoot = new MP3("3.mp3",0);
  MP3 grapple = new MP3("9.mp3",1);
  MP3 harpoon = new MP3("8.mp3",1);
  MP3 hit = new MP3("3.mp3",1);
  ArrayList <MovableObject> explosions;
  ArrayList <MovableObject> platforms;
  ArrayList <String> keyBinds = new ArrayList<String> (30);
  Scanner res;
  Scanner readKeys;
 
  // thread for the runButton
  Thread runThread = null;
  int threadDelay = 16;  // the paintComponent method will be called every 25 milliseconds


  public void initMovableObjects()
  {
  	try
  	{
  		res = new Scanner(new File("resolution.dat"));	
  	}
  	catch(Exception e)
  	{
  		
  	}
	// create all the ArrayList objects to  hold aliens, bullets, etc
	explosions = new ArrayList<MovableObject>(20);
	platforms = new ArrayList<MovableObject>(40);
	// FINISH ME
	// FIND SUITABLE PICTURES FOR YOUR player, ALIEN, BULLETS, AND EXPLOSIONS 
	// your image files can be of type  gif, jpg, or png
	
	// create the space player and aliens
	// replace playerv2.png with your image
	player = new MovableObject(centerPanel, "playerv2.png", 400,0, 25,40);
	player.setX(40);
	player.setY(500 - player.getHeight());
	player.setVectorY(0f);
	player.setAccelX(0);
	player.setAccelY(-0.44f);
	player.maxSpeed = 5f;
	player.setAngle(0f);
	player.setFace(0);
	player.ground=true;
	player.mass = 50f;
	player.timeSinceCollision = 20f;
	player.platformsIntersecting = new ArrayList<MovableObject>(3);
	player.bullets = new ArrayList <MovableObject>(40);
	player.rains = new ArrayList <MovableObject>(226);
	player.harpoons = new ArrayList <MovableObject>(1);
	player.fadeHarpoons = new ArrayList  <MovableObject>(1); 
	player.shanks = new ArrayList  <MovableObject>(1); 
	player.clouds = new ArrayList  <MovableObject>(1); 		
	player.grenades = new ArrayList  <MovableObject>(1);	
	player.blackholes = new ArrayList  <MovableObject>(1);	
	player.blackholesActive = new ArrayList  <MovableObject>(1);	
	player.hook=new MovableObject(centerPanel,"hook.png", 0,0, 11,11);
	player.crosshair = new MovableObject(centerPanel, "crosshair.png", screenWidth/2-25,screenHeight-380, 10,10);
	player.globalCD=5f;
	player.harpoonCD=200f;
	player.cloudCD= 600f;
	player.shankCD = 100f;
	player.grenadeCD = 300f;
	player.blackholeCD = 900f;
	player.keyBinds = new ArrayList<String> (30);
	
	player2 = new MovableObject(centerPanel, "player2v2.png",450,0, 25,40);
	player2.setX(1000);
	player2.setY(500 - player2.getHeight());
	player2.setVectorY(0f);
	player2.setAccelX(0);
	player2.setAccelY(-0.44f);
	player2.maxSpeed = 5f;
	player2.setAngle(0f);
	player2.setFace(0);
	player2.ground=true;
	player2.mass = 50f;
	player2.timeSinceCollision = 0f;
	player2.platformsIntersecting = new ArrayList<MovableObject>(3);
	player2.bullets = new ArrayList <MovableObject>(40);
	player2.rains = new ArrayList <MovableObject>(226);
	player2.harpoons = new ArrayList <MovableObject>(1);
	player2.fadeHarpoons = new ArrayList  <MovableObject>(1); 
	player2.shanks = new ArrayList  <MovableObject>(1); 
	player2.clouds = new ArrayList  <MovableObject>(1); 
	player2.grenades = new ArrayList  <MovableObject>(1);
	player2.blackholes = new ArrayList  <MovableObject>(1);	
	player2.blackholesActive = new ArrayList  <MovableObject>(1);		
	player2.hook=new MovableObject(centerPanel,"hook.png", 0,0, 11,11);	
	player2.crosshair = new MovableObject(centerPanel, "crosshair.png", screenWidth/2-25,screenHeight-380, 10,10);
	player2.bulletName = "bullet2.png";
	player2.rainName="player2rainv6.png";
	player2.globalCD=5f;
	player2.harpoonCD=200f;
	player2.cloudCD= 60f;
	player2.shankCD = 100f;
	player2.grenadeCD = 300f;
	player2.keyBinds = new ArrayList<String> (30);
	player2.blackholeCD = 900f;
	player2.cloudName = "cloud3p2.png";
	////keybinds from keys.dat
	try
  	{
  	
 	readKeys = new Scanner(new File("keys.dat"));
 	boolean a = true;
 	boolean b = true;
 	String nextLine;
  	while(a)
  	{
  		nextLine = readKeys.nextLine();
  		if(nextLine.equals("===end==="))
  			a=false;
  		else
  		player.keyBinds.add(nextLine);
  	}
  	if(!a)
  	{
  		while(b)
  		{
  		nextLine = readKeys.nextLine();
  		if(nextLine.equals("===end===")) 		
  			b=false;
  		else
  		player2.keyBinds.add(nextLine);
  		} 
  	}
  	}
  	catch(Exception e)
  	{
  		
  	}
	player.crosshair.setX((float)(player.getX() + player.getWidth()/2 + Math.sin(player.getAngle()/180f*Math.PI)*50f));
	player.crosshair.setY((float)(player.getY() + player.getHeight()/2 + Math.cos(player.getAngle()/180f*Math.PI)*50f));
	player2.crosshair.setX((float)(player2.getX() + player2.getWidth()/2 + Math.sin(player2.getAngle()/180f*Math.PI)*50f));
	player2.crosshair.setY((float)(player2.getY() + player2.getHeight()/2 + Math.cos(player2.getAngle()/180f*Math.PI)*50f));
	
	platform = new MovableObject(centerPanel, "platform.png", 300,500, 350,20);
	platform.setAccelY(1.55f);
	platforms.add(platform);
    platform = new MovableObject(centerPanel, "platform.png", 300,520, 350,20);
	platform.setAccelY(-0.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 295,505, 10,35);
	platform.setAccelX(-0.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 645,505, 10,35);
	platform.setAccelX(0.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 700,400, 350,20);
	platform.setAccelY(1.55f);
	platforms.add(platform);
    platform = new MovableObject(centerPanel, "platform.png", 700,420, 350,20);
	platform.setAccelY(-3.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 695,405, 10,35);
	platform.setAccelX(-0.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 1045,405, 10,35);
	platform.setAccelX(0.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 0,screenHeight-100,screenWidth,100);
 	platform.setAccelY(1.55f);
 	platforms.add(platform);
  	platform = new MovableObject(centerPanel, "platform.png", 0,-20,screenWidth,40);
	platform.setAccelY(-1.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", -40,0,40,screenHeight);
	platform.setAccelX(1.55f);
 	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", screenWidth,0,40,screenHeight);
	platform.setAccelX(-1.55f);
 	platforms.add(platform);
 /*	
 	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-200, 30,5);
	platform.setAccelY(20.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-240, 30,5);
	platform.setAccelY(20.55f);
	platforms.add(platform); 	
 	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-280, 30,5);
	platform.setAccelY(20.55f);
	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-320, 30,5);
	platform.setAccelY(20.55f);
	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-360, 30,5);
	platform.setAccelY(20.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-400, 30,5);
	platform.setAccelY(20.55f);
	platforms.add(platform); 	
 	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-440, 30,5);
	platform.setAccelY(20.55f);
	platforms.add(platform);
 	platform = new MovableObject(centerPanel, "platform.png", 40,screenHeight-480, 30,5);
	platform.setAccelY(4.55f);
	platforms.add(platform);
 	*/
 /*	platform = new MovableObject(centerPanel, "platform.png",screenWidth-300,screenHeight-350, 100,10);
	platform.setAccelY(20.55f);
	platforms.add(platform);
 	*/
 	platform = new MovableObject(centerPanel, "platform.png", screenWidth-450,screenHeight-110, 50,10);
	platform.setAccelY(1.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", screenWidth-400,screenHeight-120, 50,20);
	platform.setAccelY(1.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", screenWidth-350,screenHeight-130, 50,30);
	platform.setAccelY(1.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", screenWidth-300,screenHeight-140, 50,40);
	platform.setAccelY(1.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", screenWidth-250,screenHeight-150, 50,50);
	platform.setAccelY(1.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", screenWidth-200,screenHeight-160, 50,60);
	platform.setAccelY(1.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", screenWidth-150,screenHeight-170, 50,70);
	platform.setAccelY(1.55f);
	platforms.add(platform);
	platform = new MovableObject(centerPanel, "platform.png", screenWidth-100,screenHeight-180, 100,80);
	platform.setAccelY(1.55f);
	platforms.add(platform);



	//platforms.add(platform);
	// create 2 aliens and add them to the array
	// replace playerv2.png with your image

  	
  }

  // ***** public void initialize *****  
  public void initialize( )
  {
  	p1score=0;
  	p2score=0;
  	back = null;
	keys = new boolean[30];
  	
  	// create the buttons
    runButton  = new JButton("Run");
    stopButton  = new JButton("Stop");
    exitButton  = new JButton("Exit");

    // create a mainPanel for components
    mainPanel = new JPanel();

    // ***** create JPanels for a BorderLayout *****
    //northPanel   = new JPanel();
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
    //northPanel.setBackground(new Color(115,205,255));
    southPanel.setBackground(new Color(200,205,255));
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

//	northText = new JLabel("psy");
	playerHealth = new JLabel("100");
	
	//northPanel.add(northText);
	
	//centerPanel.setFocusable(true);
	//getContentPane().requestFocus();

    // ***** add the panels to the mainPanel 5 areas *****
    //mainPanel.add(northPanel,BorderLayout.NORTH);
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
  public rho( )
  {
  //setIconImage(Toolkit.getDefaultToolkit().getImage("rhoupsize.png"));
    //setSize(screenWidth,screenHeight);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setTitle("Rho");
	
    // initialize variables
	initialize( );	
		this.startThread();
  }
  public rho(int w,int h)
  {
  	//setIconImage(Toolkit.getDefaultToolkit().getImage("rhoupsize.png"));
    //setSize(w,h); 
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setTitle("Rho              v 0.24");
    // initialize variables
	initialize( );
	//screenHeight = h;
  	//screenWidth = w;
	this.startThread();
  }
public void startThread()
{
	    if (runThread != null)
    	{
    		return ;
    	}
    	if (runThread==null)
		{
			runThread = new Thread(southPanel);
		}
		// init the screen
		initialize( );
		// start the game
		runThread.start();
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
  	int w =1650;
  	int h=950;
  	try{		
	Scanner res = new Scanner(new File("resolution.dat"));
	w = res.nextInt();
	res.nextLine();
	h = res.nextInt();	
	res.close();
	}
	catch(Exception e)
	{	}
    rho myrho = new rho(w,h);
    
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
	  
	}
    else if (source==stopButton)  // they want to stop the game
    {
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
		  
	           centerPanel.repaint();
		   
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
  
  	
    int key = e.getKeyCode();
    String keyString = e.getKeyText(key);
    keyString = keyString.toUpperCase();

	if (keyString.equals(player.keyBinds.get(0)))
	{
		keys[0]=true;
	}
	if (keyString.equals(player.keyBinds.get(1)))
	{
		keys[1]=true;
	}
	if ((keyString.equals(player.keyBinds.get(2))))
	{
		keys[3] = false;
			
		keys[2]=true;
		//player.setAccelX(-4.2f);
	
		if(player.getFace()==0){
		
			player.setAngle(180f-player.getAngle());
		player.setFace(1);
		}
		
		
	/*	else if(Math.abs( player.getAccelX()) <= 1f )
			player.setAccelX(player.getAccelX() + -.5f);*/
	}
	if ((keyString.equals(player.keyBinds.get(3))))
	{
		keys[2]=false;
		keys[3]=true;
		//player.setAccelX(4.2f);
		/*	else if(Math.abs( player.getAccelX()) <= 1f )
			player.setAccelX(player.getAccelX() + .5f);*/
	if(player.getFace()==1)
	{
		player.setAngle(-1f*(player.getAngle()-180f));
		player.setFace(0);
	}
	}
	if(keyString.equals(player.keyBinds.get(6))  &&player.globalCD ==0f)
	{
		keys[5]=true;
		
	}
	if (keyString.equals(player.keyBinds.get(5)))
	{
	
		keys[6]=true;
		player.grappleArmed=true;
	}
	if (keyString.equals(player.keyBinds.get(4)))
	{
		keys[4]=true;
		//System.out.println(""+player.ground);
		if(player.hooked||player.hookedPlayer)
		{
			player.hooked=false;
			player.hookedPlayer=false;
			player.hookedCalc=false;
			player.setVectorY(player.getVectorY() + 6f);
		}
		
		else if(player.timeSinceCollision < 20f  )
		{
			player.timeSinceCollision =20f;
			jump.play();
			player.jump();
		}
		
	}
	if (keyString.equals(player.keyBinds.get(8))  && player.harpoonCD ==0f  &&player.globalCD ==0f)
	{
		keys[14]=true;
		player.harpoonArmed=true;
	}
	if(keyString.equals(player.keyBinds.get(7)) &&player.globalCD ==0f)
	{
		keys[15]=true;
	}
	if(keyString.equals(player.keyBinds.get(9))  && player.cloudCD ==0f  &&player.globalCD ==0f)
	{
		keys[16]=true;
	}
	if(keyString.equals(player.keyBinds.get(10)) &&player.grenadeCD ==0f&&player.globalCD ==0f )
	{
		keys[20]=true;
	}
	if(keyString.equals(player.keyBinds.get(11))&&player.blackholeCD==0f &&player.globalCD ==0f )
	{
		keys[21]=true;
	}
	if(keyString.equals(player2.keyBinds.get(10)) &&player2.grenadeCD==0f&&player2.globalCD ==0f )
	{
		keys[22]=true;
	}
	if(keyString.equals(player2.keyBinds.get(11))&&player2.blackholeCD==0f  &&player2.globalCD ==0f )
	{
		keys[23]=true;
	}
	 if (keyString.equals(player2.keyBinds.get(0)))
	{
		// 0=7, 1=8, 2=9,3=10, 4=11, 5=12
		keys[7]=true;
	}
	if (keyString.equals(player2.keyBinds.get(1)))
	{
		keys[8]=true;
	}
	if ((keyString.equals(player2.keyBinds.get(2))))
	{
		keys[10] = false;
		keys[9]=true;
		//player.setAccelX(-4.2f);
	
		if(player2.getFace()==0){
		
			player2.setAngle(180f-player2.getAngle());
		player2.setFace(1);
		}
		
		
	/*	else if(Math.abs( player.getAccelX()) <= 1f )
			player.setAccelX(player.getAccelX() + -.5f);*/
	}
	 if ((keyString.equals(player2.keyBinds.get(3))))
	{
		keys[9]=false;
		keys[10]=true;	
		if(player2.getFace()==1)
		{
			player2.setAngle(-1f*(player2.getAngle()-180f));
			player2.setFace(0);
		}
	}
	 if (keyString.equals(player2.keyBinds.get(6)))
	{
		keys[12]=true;
	}
	if (keyString.equals(player2.keyBinds.get(8)) && player2.harpoonCD ==0f  &&player2.globalCD ==0f)
	{
		keys[17]=true;
		player2.harpoonArmed=true;
	}
	if (keyString.equals(player2.keyBinds.get(7)))
	{
		keys[18]=true;
	}
	if (keyString.equals(player2.keyBinds.get(9))&& player2.cloudCD ==0f  &&player2.globalCD ==0f)
	{
		keys[19]=true;
	}
	if (keyString.equals(player2.keyBinds.get(5)))
	{
	
		keys[13]=true;
		player2.grappleArmed=true;
	}
	if (keyString.equals(player2.keyBinds.get(4)))
	{
		keys[11]=true;
		if(player2.hooked||player2.hookedPlayer)
		{
			player2.hooked=false;
			player2.hookedCalc=false;
			player2.hookedPlayer=false;
			player2.setVectorY(player2.getVectorY() + 6f);
		}
		
		else if(player2.timeSinceCollision < 20f )
		{
			player2.timeSinceCollision =20f;
			jump.play();
			player2.jump();
		}
		
	}
	
  }  // end of keyPressed(KeyEvent e)

  // start of keyReleased (KeyListener interface)
  public void keyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();
    String keyString = e.getKeyText(key);
    keyString = keyString.toUpperCase();

	if (keyString.equals(player.keyBinds.get(0)))
	{
		keys[0]=false;
	}
	
	if (keyString.equals(player.keyBinds.get(1)))
	{
		keys[1]=false;
	
	}
	if (keyString.equals(player.keyBinds.get(2)))
	{
		keys[2]=false;
		
	}
	if (keyString.equals(player.keyBinds.get(3)))
	{
		keys[3]=false;
	 
	}
	if (keyString.equals(player.keyBinds.get(4)))
	{
		keys[4]=false;
	}
	if (keyString.equals(player.keyBinds.get(6)))
	{
		keys[5]=false;
	}
	 if (keyString.equals(player.keyBinds.get(5)))
	{
		keys[6]=false;
		grapple.play();
	}
	 if (keyString.equals(player.keyBinds.get(8)))
	{
		keys[14]=false;
		
	}
	if (keyString.equals(player.keyBinds.get(7)))
	{
		keys[15]=false;
	}
	if (keyString.equals(player.keyBinds.get(9)))
	{
		keys[16]=false;
	}
	 if (keyString.equals(player.keyBinds.get(10)))
	{
		keys[20]=false;
		
	}
	 if (keyString.equals(player.keyBinds.get(11)))
	{
		keys[21]=false;
		
	}
	 if (keyString.equals(player2.keyBinds.get(10)))
	{
		keys[22]=false;
		
	}
	 if (keyString.equals(player2.keyBinds.get(11)))
	{
		keys[23]=false;
		
	}
	if (keyString.equals(player2.keyBinds.get(0)))
	{
		keys[7]=false;
	}
	
	if (keyString.equals(player2.keyBinds.get(1)))
	{
		keys[8]=false;
	}
	if (keyString.equals(player2.keyBinds.get(2)))
	{
		keys[9]=false;
		
	}
	if (keyString.equals(player2.keyBinds.get(3)))
	{
		keys[10]=false;
	 
	}
	if (keyString.equals(player2.keyBinds.get(4)))
	{
		keys[11]=false;
	}
	 if (keyString.equals(player2.keyBinds.get(6)))
	{
		keys[12]=false;
	}
	if (keyString.equals(player2.keyBinds.get(8)))
	{
		keys[17]=false;
	}
	if (keyString.equals(player2.keyBinds.get(7)))
	{
		keys[18]=false;
	}
	if (keyString.equals(player2.keyBinds.get(9)))
	{
		keys[19]=false;
	}
	if (keyString.equals(player2.keyBinds.get(5)))
	{
		keys[13]=false;
		grapple.play();
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
	//String testXY="X= Y=";
	String CDS = "ASDF";
	String CDS2 = "ASDF";
	String playerOneHealth="P1 HP: ";
	String playerTwoHealth="P2 HP: ";
	String scoreBoard="P1: ";
	boolean dragging = false;
	
	public DrawPanel()
	{
		super();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public float distanceBetween(MovableObject a, MovableObject b)
	{
		return (float) Math.sqrt(  ( (a.getX() +a.getWidth()/2) - (b.getX() +b.getWidth()/2 ) )* ( (a.getX() +a.getWidth()/2) - (b.getX() +b.getWidth()/2 )  )     +   ( (a.getY()) +a.getHeight()/2 - ( b.getY() + b.getHeight()/2 )) * ( (a.getY()) + a.getHeight()/2 - ( b.getY() + b.getHeight()/2 ))   );
	
	}
	public float distanceYBetween(MovableObject a, MovableObject b)
	{
		return (float) (a.getY() + a.getHeight()/2 - (b.getY() + b.getHeight()/2) ); 
	}
	public float distanceXBetween(MovableObject a, MovableObject b)
	{
		return (float) (a.getX() + a.getWidth()/2 - (b.getX() + b.getWidth()/2) );
	}
	public void decay()
	{
		if(keys[2] == false&&keys[3] == false&&!(player.getVectorX()==0))
		{
			
				
			if(!(player.getVectorX()==0f))
			{
				if(player.getVectorX()<0f)
				{
					player.setAccelX(.67f);
				}
				else if(player.getVectorX()>0f)
				{
					player.setAccelX(-.67f);
				}
				
			/*	if(Math.abs(player.getVectorX()) <= 0.05f )
				{	
					player.setAccelX(0f);
					player.setVectorX(0f);
				}*/
			}
			
		}
	}
    public void update(Graphics window)
    {
	   paintComponent(window);
    }
   
	public void paintComponent(Graphics g)
	{
		if(player.globalCD >0f)
			player.globalCD--;	
		if(player.harpoonCD>0f)
			player.harpoonCD--;
		if(player.cloudCD>0f)
			player.cloudCD--;
		if(player.shankCD>0f)
			player.shankCD--;
		if(player.blackholeCD>0f)
			player.blackholeCD--;
		if(player.grenadeCD>0f)
			player.grenadeCD--;
			
		if(player2.globalCD >0f)
			player2.globalCD--;	
		if(player2.harpoonCD>0f)
			player2.harpoonCD--;
		if(player2.cloudCD>0f)
			player2.cloudCD--;
		if(player2.shankCD>0f)
			player2.shankCD--;	
		if(player2.blackholeCD>0f)
			player2.blackholeCD--;
		if(player2.grenadeCD>0f)
			player2.grenadeCD--;
				
		player.timeSinceCollision += 1f;
		player2.timeSinceCollision+=1f;
		super.paintComponent((Graphics2D)g);
		Graphics2D g2 = (Graphics2D) g;
		
		//decay();
		//System.out.println("VectorX: "+player.getVectorX()+" Acceleration: "+player.getAccelX());
	/*	if(Math.abs(player.getVectorX() )<= 0.05f)
		{
			player.setVectorX(0f);
		}
		if(Math.abs(player.getVectorY()) <=  0.05f)
		{
			player.setVectorY(0f);
		}*/
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
	//	gMemory.drawString(testXY,10,50);
		gMemory.setColor(Color.BLACK);

		gMemory.setColor(Color.white);
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
		//System.out.println(""+player.getAngle());
		//System.out.println(""+player.reboundX);
    	if (keys[0] && player != null) // UP
    	{
    		//player.setDirection(0);
    		//player.moveUp();
    		if(player.getFace()==0)
    		{
    		
    		if(player.getAngle()>-90)
    		player.setAngle(player.getAngle()-5f);	
    		
    		}
    		else
    		{
    			if(player.getAngle() < 270)
    			player.setAngle(player.getAngle()+5f);
    		}
    	}
    	
    	else if (keys[1] && player != null) // DOWN
    	{
    		if(player.getFace()==0)
    		{
    		
    		if(player.getAngle()<70)
    		player.setAngle(player.getAngle()+5f);
    		}
    		else
    		{
    			if(player.getAngle()>110)
    			player.setAngle(player.getAngle()-5f);
    		}
    	}
    	else if (keys[4] && player != null) // SPACE
    	{
    		player.unhook();
     	}
    	if (keys[2] && player != null) // LEFT
    	{
    		if(player.timeSinceCollision > 20f && !player.reboundX  && Math.abs( player.getVectorX()) < 8f)
    		{
    			player.setAccelX(-1f);
    		}	
    		else if(! (player.hooked||player.hookedPlayer)  && !player.reboundX)
    		{
    			if(player.getVectorX() > -6f)
    			player.setAccelX(-1f);
    			//player.setVectorX(-5f);
    		}
    		
				
    		player.setFace(1);
    	}
    	if (keys[3]&& player != null) // RIGHT
    	{
    		if(player.timeSinceCollision > 20f && !player.reboundX  && Math.abs( player.getVectorX()) < 8f)
    		{
    			player.setAccelX(1f);
    		}
    		else if(!(player.hooked||player.hookedPlayer)&&!player.reboundX)
			{
    			if(player.getVectorX() <6f)
    			player.setAccelX(1f);
    			//player.setVectorX(5f);
    		}
			
    		player.setFace(0);
     	}
    	
		if (keys[5]&& player !=null)
    	{
    		keys[15]=false;
    		player.shoot();
    		shoot.play();
    	}
    	if(keys[15]  && player.shanks.size() < 1 && player.shankCD==0f&& player.globalCD ==0f )
    	{
    		keys[5]=false;
    		player.shootShank();
    		player.shankCD=100f;
    	}
    	if(keys[16]  && player.clouds.size() < 1&&player.globalCD==0f)
    	{
    		player.shootCloud();
    		player.cloudCD=600f;
    		player.globalCD=5f;
    	}
    	if(keys[21]  && player.blackholes.size() < 1 &&player.globalCD==0f)
    	{
    		player.shootBlackhole();
    		player.blackholeCD=900f;
	        player.globalCD=5f;
    	}
    	if(keys[20]&& player.grenades.size()<1&& player.globalCD ==0f)
    	{
    		player.shootGrenade();
    		player.grenadeCD =300f;
    		player.globalCD=5f;
    	}
    	if(keys[23]  && player2.blackholes.size() < 1 && player2.globalCD==0f)
    	{
    		player2.shootBlackhole();
    		player2.blackholeCD=900f;
	        player2.globalCD=5f;
    	}
    	if(keys[22]&& player2.grenades.size()<1 && player2.globalCD ==0f)
    	{
    		player2.shootGrenade();
    		player2.grenadeCD =300f;
    		player2.globalCD=5f;
    	}
    	if(keys[14])
    	{
    		keys[5]=false;
    		keys[15]=false;
    		player.globalCD=5f;
    	}
    	if (keys[14]==false && player!=null && player.harpoonArmed )
    	{
    		player.harpoonArmed=false;
    		player.harpoonCD = 200f;
    		player.globalCD =5f;
    		player.shootHarpoon();
    		harpoon.play();
    	}
    	if(keys[6]==false && player.grappleArmed)
    	{
    		player.grappleArmed =false;
    		player.unhook();
    		player.grapple();
    	}
    	/////////////////
		if (keys[7] && player2 != null) // UP
    	{
    		if(player2.getFace()==0)
    		{
    		
    		if(player2.getAngle()>-90)
    		player2.setAngle(player2.getAngle()-5f);	
    		
    		}
    		else
    		{
    			if(player2.getAngle() < 270)
    			player2.setAngle(player2.getAngle()+5f);
    		}
    	}
    	
    	else if (keys[8] && player2 != null) // DOWN
    	{
    		if(player2.getFace()==0)
    		{
    		
    		if(player2.getAngle()<70)
    		player2.setAngle(player2.getAngle()+5f);
    		}
    		else
    		{
    			if(player2.getAngle() >110)
    			player2.setAngle(player2.getAngle()-5f);
    		}
    	}
    	else if (keys[11] && player2 != null) // SPACE
    	{
    		player2.unhook();
     	}
    	if (keys[9] && player != null) // LEFT
    	{
    		if(player2.timeSinceCollision > 20f && !player2.reboundX  && Math.abs( player2.getVectorX()) < 8f)
    		{
    			player2.setAccelX(-1f);
    		}	
    		else if(!(player2.hooked||player2.hookedPlayer) && !player2.reboundX)
    		{
    			if(player2.getVectorX() > -6f)
    			player2.setAccelX(-1f);
    			//player2.setVectorX(-5f);
    			
    		}
    		
				
    		player2.setFace(1);
    	}
    	if (keys[10]&& player2 != null) // RIGHT
    	{
    		if(player2.timeSinceCollision > 20f && !player2.reboundX  && Math.abs( player2.getVectorX()) < 8f)
    		{
    			player2.setAccelX(1f);
    		}
    		else if(!(player2.hooked||player2.hookedPlayer)&&!player2.reboundX)
			{
    			if(player2.getVectorX() <6f)
    			player2.setAccelX(1f);
    			//player2.setVectorX(5f);
    		}
			
    		player2.setFace(0);
     	}
    	
		if (keys[12]&& player !=null)
    	{
    		player2.shoot();
    		shoot.play();
    	}
    	if (keys[13]&& player !=null)
    	{
    		
    	}
    	if(keys[13]==false && player2.grappleArmed)
    	{
    		player2.grappleArmed =false;
    		player2.unhook();
    		player2.grapple();
    	}
		if(keys[17])
    	{
    		keys[12]=false;
    		keys[18]=false;
    	}
    	if (keys[17]==false && player2!=null && player2.harpoonArmed )
    	{
    		player2.harpoonArmed=false;
    		player2.harpoonCD = 200f;
    		player2.globalCD =5f;
    		player2.shootHarpoon();
    		harpoon.play();
    	}
		if(keys[18] && player2.shanks.size() < 1&& player2.shankCD==0f&& player2.globalCD ==0f )
		{
    		keys[12]=false;
    		player2.shootShank();
    		player2.shankCD =100f;
    	
		}
		if(keys[19]  && player2.clouds.size() < 1&&player2.globalCD==0f)
    	{
    		player2.shootCloud();
    		player2.cloudCD=600f;
    		player2.globalCD=5f;
    	}
		if(player.grappling)
		{
			float a = (player.hook.getX() + player.hook.getWidth()/2 - (player.getX() + player.getWidth()/2));
			float b = (player.hook.getY() + player.hook.getHeight()/2 - (player.getY() + player.getHeight()/2));
			if(Math.abs( Math.sqrt((a*a)+(b*b) )) > 800)
				player.grappling = false;
		}
		if(player2.grappling)
		{
			float a = (player2.hook.getX() + player2.hook.getWidth()/2 - (player2.getX() + player2.getWidth()/2));
			float b = (player2.hook.getY() + player2.hook.getHeight()/2 - (player2.getY() + player2.getHeight()/2));
			if(Math.abs( Math.sqrt((a*a)+(b*b) )) > 800)
				player2.grappling = false;
		}
		
		////////
		
		
		
		///////
		if( player.grappling&&player.hook.intersects(player2.getRect()))
		{
			player.grappling = false;
			player.hookedPlayer = true;
			player.hook.setVectorX(0);
			player.hook.setVectorY(0);
			ropeDistance = distanceBetween(player,player.hook);
			shrinkDistance = ropeDistance;
		}
		else
		for(int i = 0; i < platforms.size(); i++)
		{

		if(player.hook.getRect().intersects(platforms.get(i).getRect())  && player.grappling)
		{
			player.grappling = false;
			player.hooked = true;
			player.hook.setVectorX(0);
			player.hook.setVectorY(0);
			ropeDistance = distanceBetween(player,player.hook);
			shrinkDistance = ropeDistance;
			//System.out.println(""+ropeDistance);
		}
		}
		if( player2.grappling&&player2.hook.intersects(player.getRect()))
		{
			player2.grappling = false;
			player2.hookedPlayer = true;
			player2.hook.setVectorX(0);
			player2.hook.setVectorY(0);
			ropeDistance = distanceBetween(player2,player2.hook);
			shrinkDistance = ropeDistance;
		}
		else
		for(int i = 0; i < platforms.size(); i++)
		{

		if(player2.hook.getRect().intersects(platforms.get(i).getRect())  && player2.grappling)
		{
			player2.grappling = false;
			player2.hooked = true;
			player2.hook.setVectorX(0);
			player2.hook.setVectorY(0);
			ropeDistance = distanceBetween(player2,player2.hook);
			shrinkDistance = ropeDistance;
			//System.out.println(""+ropeDistance);
		}
		}
		
		///////
		
		for(int i = 0; i < platforms.size(); i++)
		{
				if( !player.platformsIntersecting.contains(platforms.get(i))&& player.getRect().intersects(platforms.get(i).getRect())    )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					player.platformsIntersecting.add(plat);
				}
		}
		
		
		for(int i = 0; i < platforms.size(); i++)
		{
				if( !player2.platformsIntersecting.contains(platforms.get(i))&& player2.getRect().intersects(platforms.get(i).getRect())    )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					player2.platformsIntersecting.add(plat);
				}
		}
		
		
		/////////
		float aX=0f;
		float aY=0f;
		float bX=0f;
		float bY=0f;
		for(int i = 0; i< player.platformsIntersecting.size();i++)
		{
			if(!player.platformsIntersecting.get(i).rebound)
			{
				aX+=player.platformsIntersecting.get(i).getAccelX();
				aY+=player.platformsIntersecting.get(i).getAccelY();
				player.platformsIntersecting.get(i).rebound=true;
				player.rebound=true;
			}
		}
		for(int j = 0; j< player2.platformsIntersecting.size();j++)
		{
			if(!player2.platformsIntersecting.get(j).rebound)
			{
				bX+=player2.platformsIntersecting.get(j).getAccelX();
				bY+=player2.platformsIntersecting.get(j).getAccelY();
				player2.platformsIntersecting.get(j).rebound=true;
				player2.rebound=true;
				
			}
		}
		

		//////////
		
		if(aX !=0f)
		{
			player.setAccelX(aX);
			if(aY==0f)
			player.setVectorX(0f);
			if(player.getVectorY() >0f)
			player.setVectorY(player.getVectorY()*0.75f);
			player.reboundX=true;
			if(player.hooked || player.hookedPlayer)
				player.setVectorY( player.getVectorY()*.75676f);
		}
		if(aY !=0f)
		{
			player.ground=true;
			player.setAccelY(aY);
			player.setVectorY(0f);
			if(aY >=0)
				player.timeSinceCollision = 0f;
			
			if(player.hooked || player.hookedPlayer)
				player.setVectorX( player.getVectorX()*.75676f);
		}
		if(bX !=0f)
		{
			player2.setAccelX(bX);
			if(bY==0f)
			player2.setVectorX(0f);
			if(player2.getVectorY() >0f)
			player2.setVectorY(player2.getVectorY()*0.75f);
			player2.reboundX=true;
				if(player2.hooked||player2.hookedPlayer)
				player2.setVectorY( player2.getVectorY()*.75676f);
		}
		if(bY !=0f)
		{
			player2.ground = true;
			player2.setAccelY(bY);
			player2.setVectorY(0f);
			if(bY >=0)
				player2.timeSinceCollision = 0f;
				
				if(player2.hooked||player2.hookedPlayer)
				player2.setVectorX( player2.getVectorX()*.75676f);
		}
		////////////////////////////////
		
		
		
		////////////
		
		for(int i = 0; i< player.platformsIntersecting.size();i++)
		{
			if( !player.getRect().intersects(player.platformsIntersecting.get(i).getRect()))
			{
				player.platformsIntersecting.get(i).rebound=false;
				player.platformsIntersecting.get(i).reboundX=false;
				player.platformsIntersecting.remove(i);
				i--;
			}
		}
		for(int i = 0; i< player2.platformsIntersecting.size();i++)
		{
			if( !player2.getRect().intersects(player2.platformsIntersecting.get(i).getRect()))
			{
				player2.platformsIntersecting.get(i).rebound=false;
				player2.platformsIntersecting.get(i).reboundX=false;
				player2.platformsIntersecting.remove(i);
				i--;
			}
		}
		
		/////////////
		
		if(player.platformsIntersecting.size() ==0)
		{
				player.setAccelY(-.44f);
				player.rebound = false;
				player.ground=false;
				player.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< player.platformsIntersecting.size();i++)
		{
			if( player.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 player.reboundX=false;
		
		if(j==player.platformsIntersecting.size())
			player.ground=false;
		}
		
		if(player2.platformsIntersecting.size() ==0)
		{
				player2.setAccelY(-.44f);
				player2.rebound = false;
				player2.ground=false;
				player2.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< player2.platformsIntersecting.size();i++)
		{
			if( player2.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 player2.reboundX=false;
			 
		if(j==player2.platformsIntersecting.size())
			player2.ground=false;
		}
		
		//////////
		
		
		///////////////////////
	
		if(player.hooked || player.hookedPlayer)
		{		
		/*	if(distanceBetween(player,player.hook) > 10 )
			{
				player.setAccelX(   distanceBetween(player,player.hook)/-600f * (player.getX() + player.getWidth()/2f  - (player.hook.getX() + player.hook.getWidth()/2f)   )/ ( distanceBetween(player,player.hook)  ));	
				player.setAccelY(   distanceBetween(player,player.hook)/500f * (player.getY() + player.getHeight()/2f  - (player.hook.getY() + player.hook.getHeight()/2f)   )/ (  distanceBetween(player,player.hook)  ) -.44f);
				if((player.getY() + player.getHeight()/2) <( player.hook.getY() + player.hook.getHeight()/2))
				{
					player.setAccelY(   -.44f + (distanceBetween(player,player.hook)/-750f * (   (player.hook.getY() + player.hook.getHeight()/2f)  - (player.getY() + player.getHeight()/2f)   )/ (  distanceBetween(player,player.hook) ) ));
				}
			}
			else
			{
				player.setAccelX( distanceBetween(player,player.hook)/-150f * (player.getX() + player.getWidth()/2f  - (player.hook.getX() + player.hook.getWidth()/2f)   )/ ( distanceBetween(player,player.hook)  ));		
				player.setAccelY( -.44f);
			}*/
				player.setAccelX(   distanceBetween(player,player.hook)/-600f * (player.getX() + player.getWidth()/2f  - (player.hook.getX() + player.hook.getWidth()/2f)   )/ ( distanceBetween(player,player.hook)  ));	
				player.setAccelY(   distanceBetween(player,player.hook)/600f * (player.getY() + player.getHeight()/2f  - (player.hook.getY() + player.hook.getHeight()/2f)   )/ (  distanceBetween(player,player.hook)  ) -.02f );
				if((player.getY() + player.getHeight()/2) <( player.hook.getY() + player.hook.getHeight()/2))
				{
					player.setAccelY(   -.44f + (distanceBetween(player,player.hook)/-750f * (   (player.hook.getY() + player.hook.getHeight()/2f)  - (player.getY() + player.getHeight()/2f)   )/ (  distanceBetween(player,player.hook) ) ));
				}
			
		}
		if(player2.hooked|| player2.hookedPlayer)
		{		
			player2.setAccelX(   distanceBetween(player2,player2.hook)/-600f * (player2.getX() + player2.getWidth()/2f  - (player2.hook.getX() + player2.hook.getWidth()/2f)   )/ ( distanceBetween(player2,player2.hook)  ));	
				player2.setAccelY(   distanceBetween(player2,player2.hook)/600f * (player2.getY() + player2.getHeight()/2f  - (player2.hook.getY() + player2.hook.getHeight()/2f)   )/ (  distanceBetween(player2,player2.hook)  ) -.02f);
				if((player2.getY() + player2.getHeight()/2) <( player2.hook.getY() + player2.hook.getHeight()/2))
				{
					player2.setAccelY(   -.44f + (distanceBetween(player2,player2.hook)/-750f * (   (player2.hook.getY() + player2.hook.getHeight()/2f)  - (player2.getY() + player2.getHeight()/2f)   )/ (  distanceBetween(player2,player2.hook) ) ));
				}
		}
		
		///////////
		
		
		if((player.hooked||player.hookedPlayer) && player.rebound)
		{
			for(int i = 0; i< player.platformsIntersecting.size();i++)
			{
				if(player.platformsIntersecting.get(i).getAccelY()!=0f)
				if(player.platformsIntersecting.get(i).getAccelY() <0)
				player.setAccelY( player.platformsIntersecting.get(i).getAccelY());
				else
				{
					player.setAccelY(   player.platformsIntersecting.get(i).getAccelY() -  distanceBetween(player,player.hook)/750f * (player.getY() + player.getHeight()/2f  - (player.hook.getY() + player.hook.getHeight()/2f)   )/ (  distanceBetween(player,player.hook)  ));
				}
				else if(player.platformsIntersecting.get(i).getAccelX()!=0f)
				{
					player.setAccelX(player.platformsIntersecting.get(i).getAccelX());
				}
			}
		}
		if((player2.hooked||player2.hookedPlayer) && player2.rebound)
		{
			for(int i = 0; i< player2.platformsIntersecting.size();i++)
			{
				if(player2.platformsIntersecting.get(i).getAccelY()!=0f)
				if(player2.platformsIntersecting.get(i).getAccelY() <0)
				player2.setAccelY( player2.platformsIntersecting.get(i).getAccelY());
				else
				{
					player2.setAccelY(   player2.platformsIntersecting.get(i).getAccelY() -  distanceBetween(player2,player2.hook)/750f * (player2.getY() + player2.getHeight()/2f  - (player2.hook.getY() + player2.hook.getHeight()/2f)   )/ (  distanceBetween(player2,player2.hook)  ));
				}
				else if(player2.platformsIntersecting.get(i).getAccelX()!=0f)
				{
					player2.setAccelX(player2.platformsIntersecting.get(i).getAccelX());
				}
				
			}
		}
		
	/////////BULLETSSSSSSSS///////
	///Blackholes
	for(int w = 0; w < player.blackholes.size();w++)
	{
		MovableObject cloud = player.blackholes.get(w);
		cloud.timeSinceCollision++;
		for(int i =0;i<platforms.size();i++)
		{
			if( !cloud.platformsIntersecting.contains(platforms.get(i))&&cloud.intersects(platforms.get(i).getRect()))
			{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					cloud.platformsIntersecting.add(plat);
			}
		}
		for(int i = 0; i< cloud.platformsIntersecting.size();i++)
		{
			if(!cloud.platformsIntersecting.get(i).rebound)
			{
				cloud.platformsIntersecting.get(i).rebound=true;
				cloud.rebound=true;
				cloud.reboundCount++;
				if(cloud.platformsIntersecting.get(i).getAccelX()< 0  )
				{
					if(cloud.getVectorX() > 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelX()> 0)	
				{	
					if( cloud.getVectorX() < 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				if(cloud.platformsIntersecting.get(i).getAccelY()< 0  )
				{
					if(cloud.getVectorY() > 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelY()> 0)	
				{	
					if( cloud.getVectorY() < 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
			}
		}
		for(int i = 0; i<cloud.platformsIntersecting.size();i++)
		{
			if( !cloud.intersects(cloud.platformsIntersecting.get(i)))
			{
				cloud.platformsIntersecting.get(i).rebound=false;
				cloud.platformsIntersecting.get(i).reboundX=false;
				cloud.platformsIntersecting.remove(i);
				i--;
			}
		}
		cloud.setAngle(cloud.getAngle()+6f + cloud.timeSinceCollision/10f);
		cloud.setVectorX(cloud.getVectorX() *.95f);
		cloud.setVectorY(cloud.getVectorY() *.95f);
		cloud.setX(cloud.getX() + cloud.getVectorX());
		cloud.setY(cloud.getY() - cloud.getVectorY());
	}
	for(int w =0; w< player.blackholes.size();w++)
	{
		MovableObject blackhole = player.blackholes.get(w);
		if(blackhole.timeSinceCollision > 60f)
		{	
			player.blackholesActive.add(blackhole);
			player.blackholes.remove(w);
			w--;
		}
	}
	for(int w =0; w< player.blackholes.size();w++)
	{
		MovableObject blackhole = player.blackholes.get(w);
		blackhole.draw2(gMemory);
	}
	for(int w = 0; w < player2.blackholes.size();w++)
	{
		MovableObject cloud = player2.blackholes.get(w);
		cloud.timeSinceCollision++;
		for(int i =0;i<platforms.size();i++)
		{
			if( !cloud.platformsIntersecting.contains(platforms.get(i))&&cloud.intersects(platforms.get(i).getRect()))
			{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					cloud.platformsIntersecting.add(plat);
			}
		}
		for(int i = 0; i< cloud.platformsIntersecting.size();i++)
		{
			if(!cloud.platformsIntersecting.get(i).rebound)
			{
				cloud.platformsIntersecting.get(i).rebound=true;
				cloud.rebound=true;
				cloud.reboundCount++;
				if(cloud.platformsIntersecting.get(i).getAccelX()< 0  )
				{
					if(cloud.getVectorX() > 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelX()> 0)	
				{	
					if( cloud.getVectorX() < 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				if(cloud.platformsIntersecting.get(i).getAccelY()< 0  )
				{
					if(cloud.getVectorY() > 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelY()> 0)	
				{	
					if( cloud.getVectorY() < 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
			}
		}
		for(int i = 0; i<cloud.platformsIntersecting.size();i++)
		{
			if( !cloud.intersects(cloud.platformsIntersecting.get(i)))
			{
				cloud.platformsIntersecting.get(i).rebound=false;
				cloud.platformsIntersecting.get(i).reboundX=false;
				cloud.platformsIntersecting.remove(i);
				i--;
			}
		}
		cloud.setAngle(cloud.getAngle()+6f + cloud.timeSinceCollision/10f);
		cloud.setVectorX(cloud.getVectorX() *.95f);
		cloud.setVectorY(cloud.getVectorY() *.95f);
		cloud.setX(cloud.getX() + cloud.getVectorX());
		cloud.setY(cloud.getY() - cloud.getVectorY());
	}
	for(int w =0; w< player2.blackholes.size();w++)
	{
		MovableObject blackhole = player2.blackholes.get(w);
		if(blackhole.timeSinceCollision > 60f)
		{	
			player2.blackholesActive.add(blackhole);
			player2.blackholes.remove(w);
			w--;
		}
	}
	for(int w =0; w< player2.blackholes.size();w++)
	{
		MovableObject blackhole = player2.blackholes.get(w);
		blackhole.draw2(gMemory);
	}
	///Blackholes Active
	for(int w = 0; w < player.blackholesActive.size();w++)
	{ 
		MovableObject hole = player.blackholesActive.get(w);
		hole.timeSinceCollision++;
		hole.setAngle(hole.getAngle()+12f + hole.timeSinceCollision/10f);
		MovableObject shrapnel = new MovableObject(mainPanel, "blackholeparticle.png", (hole.getX() + hole.getWidth()/2f + (float)Math.random()*401f -201f),(hole.getY() + hole.getHeight()/2f+ (float)Math.random()*401f - 201f), 5,5);
		shrapnel.mass=5f;
		player.bullets.add(shrapnel);
		hole.draw2(gMemory);
	}
	for(int w =0; w< player.blackholesActive.size();w++)
	{
		MovableObject blackhole = player.blackholesActive.get(w);
		if(blackhole.timeSinceCollision > 400f)
		{	
			player.blackholesActive.remove(w);
			w--;
		}
	}
	for(int w = 0; w < player2.blackholesActive.size();w++)
	{ 
		MovableObject hole = player2.blackholesActive.get(w);
		hole.timeSinceCollision++;
		hole.setAngle(hole.getAngle()+12f + hole.timeSinceCollision/10f);
		MovableObject shrapnel = new MovableObject(mainPanel, "blackholeparticle.png", (hole.getX() + hole.getWidth()/2f + (float)Math.random()*401f -201f),(hole.getY() + hole.getHeight()/2f+ (float)Math.random()*401f - 201f), 5,5);
		shrapnel.mass=5f;
		player2.bullets.add(shrapnel);
		hole.draw2(gMemory);
	}
	for(int w =0; w< player2.blackholesActive.size();w++)
	{
		MovableObject blackhole = player2.blackholesActive.get(w);
		if(blackhole.timeSinceCollision > 400f)
		{	
			player2.blackholesActive.remove(w);
			w--;
		}
	}
	///GRENADE
	for(int w =0; w < player2.grenades.size();w++)
		{
			MovableObject bullet = player2.grenades.get(w);
			bullet.timeSinceCollision++;
			for(int i =0;i<platforms.size();i++)
			{
				if(!bullet.platformsIntersecting.contains(platforms.get(i)) && bullet.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					bullet.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< bullet.platformsIntersecting.size();i++)
		{
			if(!bullet.platformsIntersecting.get(i).rebound)
			{
				aX+=bullet.platformsIntersecting.get(i).getAccelX();
				aY+=bullet.platformsIntersecting.get(i).getAccelY();
				bullet.platformsIntersecting.get(i).rebound=true;
				bullet.rebound=true;
				bullet.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			bullet.setAccelX(aX);
			bullet.setVectorX(0f);
			if(bullet.getVectorY() >0f)
			bullet.setVectorY(bullet.getVectorY()*0.75f);
			bullet.reboundX=true;
		}
		if(aY !=0f)
		{
			bullet.ground=true;
			bullet.setAccelY(aY);
			bullet.setVectorY(0f);
			bullet.setVectorX(bullet.getVectorX() * 0.75f);
		}
	
		
		for(int i = 0; i<bullet.platformsIntersecting.size();i++)
		{
			if( !bullet.getRect().intersects(	bullet.platformsIntersecting.get(i).getRect()))
			{
				bullet.platformsIntersecting.get(i).rebound=false;
				bullet.platformsIntersecting.get(i).reboundX=false;
				bullet.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	bullet.platformsIntersecting.size() ==0)
		{
				bullet.setAccelY(-.44f);
				bullet.rebound = false;
				bullet.ground=false;
				bullet.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	bullet.platformsIntersecting.size();i++)
		{
			if(	bullet.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	bullet.reboundX=false;
		
		if(j==	bullet.platformsIntersecting.size())
			bullet.ground=false;
		}
		
		if(!bullet.reboundX)
			bullet.setAccelX(0f);
			
			bullet.setVectorX(bullet.getVectorX() + bullet.getAccelX());
			bullet.setVectorY(bullet.getVectorY() + bullet.getAccelY());
			bullet.setX(bullet.getX() + bullet.getVectorX());
			bullet.setY(bullet.getY() - bullet.getVectorY());
			
	
   	
   		   	bullet.draw(gMemory);
   		
		}
		for(int w =0; w < player.grenades.size();w++)
		{
			MovableObject bullet = player.grenades.get(w);
			bullet.timeSinceCollision++;
			for(int i =0;i<platforms.size();i++)
			{
				if(!bullet.platformsIntersecting.contains(platforms.get(i)) && bullet.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					bullet.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< bullet.platformsIntersecting.size();i++)
		{
			if(!bullet.platformsIntersecting.get(i).rebound)
			{
				aX+=bullet.platformsIntersecting.get(i).getAccelX();
				aY+=bullet.platformsIntersecting.get(i).getAccelY();
				bullet.platformsIntersecting.get(i).rebound=true;
				bullet.rebound=true;
				bullet.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			bullet.setAccelX(aX);
			bullet.setVectorX(0f);
			if(bullet.getVectorY() >0f)
			bullet.setVectorY(bullet.getVectorY()*0.75f);
			bullet.reboundX=true;
		}
		if(aY !=0f)
		{
			bullet.ground=true;
			bullet.setAccelY(aY);
			bullet.setVectorY(0f);
			bullet.setVectorX(bullet.getVectorX() * 0.75f);
		}
	
		
		for(int i = 0; i<bullet.platformsIntersecting.size();i++)
		{
			if( !bullet.getRect().intersects(	bullet.platformsIntersecting.get(i).getRect()))
			{
				bullet.platformsIntersecting.get(i).rebound=false;
				bullet.platformsIntersecting.get(i).reboundX=false;
				bullet.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	bullet.platformsIntersecting.size() ==0)
		{
				bullet.setAccelY(-.44f);
				bullet.rebound = false;
				bullet.ground=false;
				bullet.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	bullet.platformsIntersecting.size();i++)
		{
			if(	bullet.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	bullet.reboundX=false;
		
		if(j==	bullet.platformsIntersecting.size())
			bullet.ground=false;
		}
		
		if(!bullet.reboundX)
			bullet.setAccelX(0f);
			
			bullet.setVectorX(bullet.getVectorX() + bullet.getAccelX());
			bullet.setVectorY(bullet.getVectorY() + bullet.getAccelY());
			bullet.setX(bullet.getX() + bullet.getVectorX());
			bullet.setY(bullet.getY() - bullet.getVectorY());
			
	
   	
   		   	bullet.draw(gMemory);
   		
		}
	///shanks
	/*for(int i = 0; i < player.shanks.size();i++)
	{
		player.shanks.get(i).setX(player.crosshair.getX() +player.crosshair.getWidth()/2- player.shanks.get(i).getWidth()/2);
		player.shanks.get(i).setY(player.crosshair.getY() +player.crosshair.getHeight()/2- player.shanks.get(i).getHeight()/2);
		player.shanks.get(i).setAngle(player.getAngle());
		player.shanks.get(i).timeSinceCollision +=1f;
		if(player.shanks.get(i).timeSinceCollision >15f)
		{
		
			player.shanks.remove(i);
			i--;
		}
			
	}
	*/
	////clouds
	for(int w = 0; w < player.clouds.size();w++)
	{
		MovableObject cloud = player.clouds.get(w);
		cloud.timeSinceCollision++;
		for(int i =0;i<platforms.size();i++)
		{
			if( !cloud.platformsIntersecting.contains(platforms.get(i))&&cloud.intersects(platforms.get(i).getRect()))
			{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					cloud.platformsIntersecting.add(plat);
			}
		}
		for(int i = 0; i< cloud.platformsIntersecting.size();i++)
		{
			if(!cloud.platformsIntersecting.get(i).rebound)
			{
				cloud.platformsIntersecting.get(i).rebound=true;
				cloud.rebound=true;
				cloud.reboundCount++;
				if(cloud.platformsIntersecting.get(i).getAccelX()< 0  )
				{
					if(cloud.getVectorX() > 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelX()> 0)	
				{	
					if( cloud.getVectorX() < 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				if(cloud.platformsIntersecting.get(i).getAccelY()< 0  )
				{
					if(cloud.getVectorY() > 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelY()> 0)	
				{	
					if( cloud.getVectorY() < 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
			}
		}
		for(int i = 0; i<cloud.platformsIntersecting.size();i++)
		{
			if( !cloud.intersects(cloud.platformsIntersecting.get(i)))
			{
				cloud.platformsIntersecting.get(i).rebound=false;
				cloud.platformsIntersecting.get(i).reboundX=false;
				cloud.platformsIntersecting.remove(i);
				i--;
			}
		}
		cloud.setX(cloud.getX() + cloud.getVectorX());
		cloud.setY(cloud.getY() - cloud.getVectorY());
		//cloud.rain();
		MovableObject test = new MovableObject(mainPanel, player.rainName, 0,(int) (cloud.getY() + cloud.getHeight()/2), 6,12);
		test.setX((int)(cloud.getX() + Math.random()*cloud.getWidth()));
		test.setVectorX(0f);
		test.setAccelX(0f);
		test.setVectorY(0f);
		test.setAccelY(-.44f);
		player.rains.add(test);
		for(int d =0; d < cloud.bullets.size(); d++)
		{
			
			MovableObject rain = cloud.bullets.get(d);
			rain.setVectorY(rain.getVectorY() + rain.getAccelY());
			rain.setVectorX(rain.getVectorX() + rain.getAccelX());
			rain.setX(rain.getX() + rain.getVectorX());
			rain.setY(rain.getY() - rain.getVectorY());
			for(int i =0;i<platforms.size();i++)
			{
				if(!rain.platformsIntersecting.contains(platforms.get(i)) && rain.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					rain.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< rain.platformsIntersecting.size();i++)
		{
			if(!rain.platformsIntersecting.get(i).rebound)
			{
				aX+=rain.platformsIntersecting.get(i).getAccelX();
				aY+=rain.platformsIntersecting.get(i).getAccelY();
				rain.platformsIntersecting.get(i).rebound=true;
				rain.rebound=true;
				rain.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			rain.setAccelX(aX);
			rain.setVectorX(0f);
			if(rain.getVectorY() >0f)
			rain.setVectorY(rain.getVectorY()*0.75f);
			rain.reboundX=true;
		}
		if(aY !=0f)
		{
			rain.ground=true;
			rain.setAccelY(aY);
			rain.setVectorY(0f);
			if(aY >=0)
				rain.timeSinceCollision = 0f;
		}
	
		
		for(int i = 0; i<rain.platformsIntersecting.size();i++)
		{
			if( !rain.getRect().intersects(	rain.platformsIntersecting.get(i).getRect()))
			{
				rain.platformsIntersecting.get(i).rebound=false;
				rain.platformsIntersecting.get(i).reboundX=false;
				rain.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	rain.platformsIntersecting.size() ==0)
		{
				rain.setAccelY(-.44f);
				rain.rebound = false;
				rain.ground=false;
				rain.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	rain.platformsIntersecting.size();i++)
		{
			if(	rain.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	rain.reboundX=false;
		
		if(j==	rain.platformsIntersecting.size())
			rain.ground=false;
		}
		
		if(!rain.reboundX)
			rain.setAccelX(0f);
		
		for(int a =0; a< cloud.bullets.size();a++)
		{
			if(cloud.bullets.get(a).reboundCount > 30)
			{	cloud.bullets.remove(a);
			a--;
			}
			
				
		}
		}
	}
	for(int w =0; w< player.clouds.size();w++)
	{
			if(player.clouds.get(w).timeSinceCollision > 400f)
			{
				for(int i = 0; i < player.clouds.get(w).bullets.size(); i++)
				{
					player.rains.add(player.clouds.get(w).bullets.get(i));
				}		
				player.clouds.remove(w);
				w--;
			}
	}
		
		///p2
		for(int w = 0; w < player2.clouds.size();w++)
	{
		MovableObject cloud = player2.clouds.get(w);
		cloud.timeSinceCollision++;
		for(int i =0;i<platforms.size();i++)
		{
			if( !cloud.platformsIntersecting.contains(platforms.get(i))&&cloud.intersects(platforms.get(i).getRect()))
			{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					cloud.platformsIntersecting.add(plat);
			}
		}
		for(int i = 0; i< cloud.platformsIntersecting.size();i++)
		{
			if(!cloud.platformsIntersecting.get(i).rebound)
			{
				cloud.platformsIntersecting.get(i).rebound=true;
				cloud.rebound=true;
				cloud.reboundCount++;
				if(cloud.platformsIntersecting.get(i).getAccelX()< 0  )
				{
					if(cloud.getVectorX() > 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelX()> 0)	
				{	
					if( cloud.getVectorX() < 0)
					cloud.setVectorX(cloud.getVectorX()*-1f);
				}
				if(cloud.platformsIntersecting.get(i).getAccelY()< 0  )
				{
					if(cloud.getVectorY() > 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
				else if(cloud.platformsIntersecting.get(i).getAccelY()> 0)	
				{	
					if( cloud.getVectorY() < 0)
					cloud.setVectorY(cloud.getVectorY()*-1f);
				}
			}
		}
		for(int i = 0; i<cloud.platformsIntersecting.size();i++)
		{
			if( !cloud.intersects(cloud.platformsIntersecting.get(i)))
			{
				cloud.platformsIntersecting.get(i).rebound=false;
				cloud.platformsIntersecting.get(i).reboundX=false;
				cloud.platformsIntersecting.remove(i);
				i--;
			}
		}
		cloud.setX(cloud.getX() + cloud.getVectorX());
		cloud.setY(cloud.getY() - cloud.getVectorY());
		//cloud.rain();
		MovableObject test = new MovableObject(mainPanel, player2.rainName, 0,(int) (cloud.getY() + cloud.getHeight()/2), 6,12);
		test.setX((int)(cloud.getX() + Math.random()*cloud.getWidth()));
		test.setVectorX(0f);
		test.setAccelX(0f);
		test.setVectorY(0f);
		test.setAccelY(-.44f);
		player2.rains.add(test);
		for(int d =0; d < cloud.bullets.size(); d++)
		{
			
			MovableObject rain = cloud.bullets.get(d);
			rain.setVectorY(rain.getVectorY() + rain.getAccelY());
			rain.setVectorX(rain.getVectorX() + rain.getAccelX());
			rain.setX(rain.getX() + rain.getVectorX());
			rain.setY(rain.getY() - rain.getVectorY());
			for(int i =0;i<platforms.size();i++)
			{
				if(!rain.platformsIntersecting.contains(platforms.get(i)) && rain.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					rain.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< rain.platformsIntersecting.size();i++)
		{
			if(!rain.platformsIntersecting.get(i).rebound)
			{
				aX+=rain.platformsIntersecting.get(i).getAccelX();
				aY+=rain.platformsIntersecting.get(i).getAccelY();
				rain.platformsIntersecting.get(i).rebound=true;
				rain.rebound=true;
				rain.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			rain.setAccelX(aX);
			rain.setVectorX(0f);
			if(rain.getVectorY() >0f)
			rain.setVectorY(rain.getVectorY()*0.75f);
			rain.reboundX=true;
		}
		if(aY !=0f)
		{
			rain.ground=true;
			rain.setAccelY(aY);
			rain.setVectorY(0f);
			if(aY >=0)
				rain.timeSinceCollision = 0f;
		}
	
		
		for(int i = 0; i<rain.platformsIntersecting.size();i++)
		{
			if( !rain.getRect().intersects(	rain.platformsIntersecting.get(i).getRect()))
			{
				rain.platformsIntersecting.get(i).rebound=false;
				rain.platformsIntersecting.get(i).reboundX=false;
				rain.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	rain.platformsIntersecting.size() ==0)
		{
				rain.setAccelY(-.44f);
				rain.rebound = false;
				rain.ground=false;
				rain.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	rain.platformsIntersecting.size();i++)
		{
			if(	rain.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	rain.reboundX=false;
		
		if(j==	rain.platformsIntersecting.size())
			rain.ground=false;
		}
		
		if(!rain.reboundX)
			rain.setAccelX(0f);
		
		for(int a =0; a< cloud.bullets.size();a++)
		{
			if(cloud.bullets.get(a).reboundCount > 30)
			{	cloud.bullets.remove(a);
			a--;
			}
			
				
		}
		}
	}
	for(int w =0; w< player2.clouds.size();w++)
		{
			if(player2.clouds.get(w).timeSinceCollision > 400f)
			{
				for(int i = 0; i < player2.clouds.get(w).bullets.size(); i++)
				{
					player2.rains.add(player2.clouds.get(w).bullets.get(i));
				}		
				player2.clouds.remove(w);
				w--;
			}
		}
	////rains
	for(int w =0; w < player.rains.size();w++)
		{
			MovableObject bullet = player.rains.get(w);
			for(int i =0;i<platforms.size();i++)
			{
				if(!bullet.platformsIntersecting.contains(platforms.get(i)) && bullet.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					bullet.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< bullet.platformsIntersecting.size();i++)
		{
			if(!bullet.platformsIntersecting.get(i).rebound)
			{
				aX+=bullet.platformsIntersecting.get(i).getAccelX();
				aY+=bullet.platformsIntersecting.get(i).getAccelY();
				bullet.platformsIntersecting.get(i).rebound=true;
				bullet.rebound=true;
				bullet.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			bullet.setAccelX(aX);
			bullet.setVectorX(0f);
			if(bullet.getVectorY() >0f)
			bullet.setVectorY(bullet.getVectorY()*0.75f);
			bullet.reboundX=true;
		}
		if(aY !=0f)
		{
			bullet.ground=true;
			bullet.setAccelY(aY);
			bullet.setVectorY(0f);
			bullet.setVectorX(bullet.getVectorX()*0.75f);
			if(aY >=0)
				bullet.timeSinceCollision = 0f;
		}
	
		
		for(int i = 0; i<bullet.platformsIntersecting.size();i++)
		{
			if( !bullet.getRect().intersects(	bullet.platformsIntersecting.get(i).getRect()))
			{
				bullet.platformsIntersecting.get(i).rebound=false;
				bullet.platformsIntersecting.get(i).reboundX=false;
				bullet.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	bullet.platformsIntersecting.size() ==0)
		{
				bullet.setAccelY(-.44f);
				bullet.rebound = false;
				bullet.ground=false;
				bullet.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	bullet.platformsIntersecting.size();i++)
		{
			if(	bullet.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	bullet.reboundX=false;
		
		if(j==	bullet.platformsIntersecting.size())
			bullet.ground=false;
		}
		
		if(!bullet.reboundX)
			bullet.setAccelX(0f);
			bullet.setVectorX(bullet.getVectorX() + bullet.getAccelX());
			bullet.setVectorY(bullet.getVectorY() + bullet.getAccelY());
			bullet.setX(bullet.getX() + bullet.getVectorX());
			bullet.setY(bullet.getY() - bullet.getVectorY());
		}	
		for(int w =0; w< player.rains.size();w++)
		{
			if(player.rains.get(w).reboundCount > 30)
			{	player.rains.remove(w);
			w--;
			}
			
				
		}
		//p2
		for(int w =0; w < player2.rains.size();w++)
		{
			MovableObject bullet = player2.rains.get(w);
			for(int i =0;i<platforms.size();i++)
			{
				if(!bullet.platformsIntersecting.contains(platforms.get(i)) && bullet.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					bullet.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< bullet.platformsIntersecting.size();i++)
		{
			if(!bullet.platformsIntersecting.get(i).rebound)
			{
				aX+=bullet.platformsIntersecting.get(i).getAccelX();
				aY+=bullet.platformsIntersecting.get(i).getAccelY();
				bullet.platformsIntersecting.get(i).rebound=true;
				bullet.rebound=true;
				bullet.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			bullet.setAccelX(aX);
			bullet.setVectorX(0f);
			if(bullet.getVectorY() >0f)
			bullet.setVectorY(bullet.getVectorY()*0.75f);
			bullet.reboundX=true;
		}
		if(aY !=0f)
		{
			bullet.ground=true;
			bullet.setAccelY(aY);
			bullet.setVectorY(0f);
			bullet.setVectorX(bullet.getVectorX()*0.75f);
			if(aY >=0)
				bullet.timeSinceCollision = 0f;
		}
	
		
		for(int i = 0; i<bullet.platformsIntersecting.size();i++)
		{
			if( !bullet.getRect().intersects(	bullet.platformsIntersecting.get(i).getRect()))
			{
				bullet.platformsIntersecting.get(i).rebound=false;
				bullet.platformsIntersecting.get(i).reboundX=false;
				bullet.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	bullet.platformsIntersecting.size() ==0)
		{
				bullet.setAccelY(-.44f);
				bullet.rebound = false;
				bullet.ground=false;
				bullet.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	bullet.platformsIntersecting.size();i++)
		{
			if(	bullet.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	bullet.reboundX=false;
		
		if(j==	bullet.platformsIntersecting.size())
			bullet.ground=false;
		}
		
		if(!bullet.reboundX)
			bullet.setAccelX(0f);
			
			bullet.setVectorX(bullet.getVectorX() + bullet.getAccelX());
			bullet.setVectorY(bullet.getVectorY() + bullet.getAccelY());
			bullet.setX(bullet.getX() + bullet.getVectorX());
			bullet.setY(bullet.getY() - bullet.getVectorY());
		}	
		for(int w =0; w< player2.rains.size();w++)
		{
			if(player2.rains.get(w).reboundCount > 30)
			{	player2.rains.remove(w);
			w--;
			}
			
				
		}
	///Harpoons VVV
		for(int i = 0; i<player.harpoons.size();i++)
			{
				
				player.harpoons.get(i).setVectorY(player.harpoons.get(i).getVectorY() + player.harpoons.get(i).getAccelY());
				player.harpoons.get(i).setVectorX(player.harpoons.get(i).getVectorX() + player.harpoons.get(i).getAccelX());
				
				player.harpoons.get(i).totalDistance += Math.sqrt( player.harpoons.get(i).getVectorX()*player.harpoons.get(i).getVectorX()+player.harpoons.get(i).getVectorY()*player.harpoons.get(i).getVectorY() ); 
				
				player.harpoons.get(i).setX(player.harpoons.get(i).getX() + player.harpoons.get(i).getVectorX());
				player.harpoons.get(i).setY(player.harpoons.get(i).getY() - player.harpoons.get(i).getVectorY());
				
			}
		for(int w =0; w < player.harpoons.size();w++)
		{
			MovableObject bullet = player.harpoons.get(w);
			
			bullet.setVectorY(bullet.getVectorY() + -.44f);
			if(bullet.getY() >( screenHeight+200))
			{
				player.harpoons.remove(bullet);
				w--;
			}
		
		}
		
		for(int i = 0; i<player2.harpoons.size();i++)
			{
				
				player2.harpoons.get(i).setVectorY(player2.harpoons.get(i).getVectorY() + player2.harpoons.get(i).getAccelY());
				player2.harpoons.get(i).setVectorX(player2.harpoons.get(i).getVectorX() + player2.harpoons.get(i).getAccelX());
				
				player2.harpoons.get(i).totalDistance += Math.sqrt( player2.harpoons.get(i).getVectorX()*player2.harpoons.get(i).getVectorX()+player2.harpoons.get(i).getVectorY()*player2.harpoons.get(i).getVectorY() ); 
				
				player2.harpoons.get(i).setX(player2.harpoons.get(i).getX() + player2.harpoons.get(i).getVectorX());
				player2.harpoons.get(i).setY(player2.harpoons.get(i).getY() - player2.harpoons.get(i).getVectorY());
				
			}
		for(int w =0; w < player2.harpoons.size();w++)
		{
			MovableObject bullet = player2.harpoons.get(w);
			
			bullet.setVectorY(bullet.getVectorY() + -.44f);
			if(bullet.getY() >( screenHeight+200))
			{
				player2.harpoons.remove(bullet);
				w--;
			}
		
		}
		/// pea shooter VVV
		for(int w =0; w < player.bullets.size();w++)
		{
			MovableObject bullet = player.bullets.get(w);
			for(int i =0;i<platforms.size();i++)
			{
				if(!bullet.platformsIntersecting.contains(platforms.get(i)) && bullet.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					bullet.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< bullet.platformsIntersecting.size();i++)
		{
			if(!bullet.platformsIntersecting.get(i).rebound)
			{
				aX+=bullet.platformsIntersecting.get(i).getAccelX();
				aY+=bullet.platformsIntersecting.get(i).getAccelY();
				bullet.platformsIntersecting.get(i).rebound=true;
				bullet.rebound=true;
				bullet.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			bullet.setAccelX(aX);
			bullet.setVectorX(0f);
			if(bullet.getVectorY() >0f)
			bullet.setVectorY(bullet.getVectorY()*0.75f);
			bullet.reboundX=true;
		}
		if(aY !=0f)
		{
			bullet.ground=true;
			bullet.setAccelY(aY);
			bullet.setVectorY(0f);
			if(aY >=0)
				bullet.timeSinceCollision = 0f;
		}
	
		
		for(int i = 0; i<bullet.platformsIntersecting.size();i++)
		{
			if( !bullet.getRect().intersects(	bullet.platformsIntersecting.get(i).getRect()))
			{
				bullet.platformsIntersecting.get(i).rebound=false;
				bullet.platformsIntersecting.get(i).reboundX=false;
				bullet.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	bullet.platformsIntersecting.size() ==0)
		{
				bullet.setAccelY(-.44f);
				bullet.rebound = false;
				bullet.ground=false;
				bullet.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	bullet.platformsIntersecting.size();i++)
		{
			if(	bullet.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	bullet.reboundX=false;
		
		if(j==	bullet.platformsIntersecting.size())
			bullet.ground=false;
		}
		
		if(!bullet.reboundX)
			bullet.setAccelX(0f);
			
			bullet.setVectorX(bullet.getVectorX() + bullet.getAccelX());
			bullet.setVectorY(bullet.getVectorY() + bullet.getAccelY());
			bullet.setX(bullet.getX() + bullet.getVectorX());
			bullet.setY(bullet.getY() - bullet.getVectorY());
		}
		for(int w =0; w< player.bullets.size();w++)
		{
			if(player.bullets.get(w).reboundCount > 20)
			{	player.bullets.remove(w);
			w--;
			}
			
				
		}
		//////////player 2 bullets
		for(int w =0; w < player2.bullets.size();w++)
		{
			MovableObject bullet = player2.bullets.get(w);
			for(int i =0;i<platforms.size();i++)
			{
				if(!bullet.platformsIntersecting.contains(platforms.get(i)) && bullet.intersects(platforms.get(i).getRect())   )
				{
					MovableObject plat = new MovableObject(centerPanel, "platform.png", 500,400, 700,40);
					plat.setX(platforms.get(i).getX());
					plat.setY(platforms.get(i).getY());
					plat.setWidth((platforms.get(i).getWidth()));
					plat.setHeight(platforms.get(i).getHeight());
					plat.setAccelY((platforms.get(i).getAccelY()));
					plat.setAccelX((platforms.get(i).getAccelX()));
					bullet.platformsIntersecting.add(plat);
				}
			}
		
		aX=0;
		aY=0;
		for(int i = 0; i< bullet.platformsIntersecting.size();i++)
		{
			if(!bullet.platformsIntersecting.get(i).rebound)
			{
				aX+=bullet.platformsIntersecting.get(i).getAccelX();
				aY+=bullet.platformsIntersecting.get(i).getAccelY();
				bullet.platformsIntersecting.get(i).rebound=true;
				bullet.rebound=true;
				bullet.reboundCount++;
			}
		}
		if(aX !=0f)
		{
			bullet.setAccelX(aX);
			bullet.setVectorX(0f);
			if(bullet.getVectorY() >0f)
			bullet.setVectorY(bullet.getVectorY()*0.75f);
			bullet.reboundX=true;
		}
		if(aY !=0f)
		{
			bullet.ground=true;
			bullet.setAccelY(aY);
			bullet.setVectorY(0f);
			if(aY >=0)
				bullet.timeSinceCollision = 0f;
		}
	
		
		for(int i = 0; i<bullet.platformsIntersecting.size();i++)
		{
			if( !bullet.getRect().intersects(	bullet.platformsIntersecting.get(i).getRect()))
			{
				bullet.platformsIntersecting.get(i).rebound=false;
				bullet.platformsIntersecting.get(i).reboundX=false;
				bullet.platformsIntersecting.remove(i);
				i--;
			}
		}
		if(	bullet.platformsIntersecting.size() ==0)
		{
				bullet.setAccelY(-.44f);
				bullet.rebound = false;
				bullet.ground=false;
				bullet.reboundX=false;
		}
		else
		{
		int j=0;
		for(int i = 0; i< 	bullet.platformsIntersecting.size();i++)
		{
			if(	bullet.platformsIntersecting.get(i).getAccelX()!=0 )
			j++;
		}
		if(j==0)
			 	bullet.reboundX=false;
		
		if(j==	bullet.platformsIntersecting.size())
			bullet.ground=false;
		}
		
		if(!bullet.reboundX)
			bullet.setAccelX(0f);
			
			bullet.setVectorX(bullet.getVectorX() + bullet.getAccelX());
			bullet.setVectorY(bullet.getVectorY() + bullet.getAccelY());
			bullet.setX(bullet.getX() + bullet.getVectorX());
			bullet.setY(bullet.getY() - bullet.getVectorY());
		}
		for(int w =0; w< player2.bullets.size();w++)
		{
			if(player2.bullets.get(w).reboundCount > 20)
			{	player2.bullets.remove(w);
			w--;
			}
			
				
		} 
		
		/////////Damage for Bullets////
		///blackholes Active
		for(int w = 0; w< player.blackholesActive.size();w++)
		{
			MovableObject bullet = player.blackholesActive.get(w);
		/*	player.adjustAccelX(   distanceBetween(player,bullet)/-600f * (player.getX() + player.getWidth()/2f  - (bullet.getX() + bullet.getWidth()/2f)   )/ ( distanceBetween(player,bullet)  ));	
			if((player.getY() + player.getHeight()/2) <( player.hook.getY() + player.hook.getHeight()/2))
			{
				player.adjustAccelY(   -.43f + distanceBetween(player,bullet)/600f * (player.getY() + player.getHeight()/2f  - (bullet.getY() + bullet.getHeight()/2f)   )/ (  distanceBetween(player,bullet)  )) ;
			}
			else
				player.adjustAccelY(   distanceBetween(player,bullet)/600f * (player.getY() + player.getHeight()/2f  - (bullet.getY() + bullet.getHeight()/2f)   )/ (  distanceBetween(player,bullet)  ) +.43f );
			
		*/
	
					player.setVectorX(player.getVectorX() - (178f/distanceBetween(player,bullet)) * distanceXBetween(player,bullet) / distanceBetween(player,bullet));
					player.setVectorY(player.getVectorY() + (178f/distanceBetween(player,bullet)) * distanceYBetween(player,bullet) / distanceBetween(player,bullet));
					if(player.intersects(bullet))
					player.health = player.health - .0923456f*(float)Math.sqrt(178f/distanceBetween(player,bullet));
			
					player2.setVectorX(player2.getVectorX() - (178f/distanceBetween(player2,bullet)) * distanceXBetween(player2,bullet) / distanceBetween(player2,bullet));
					player2.setVectorY(player2.getVectorY() + (178f/distanceBetween(player2,bullet)) * distanceYBetween(player2,bullet) / distanceBetween(player2,bullet));
					if(player2.intersects(bullet))
					player2.health = player2.health - .0923456f*(float)Math.sqrt(178f/distanceBetween(player2,bullet));
					
			
				for(int i =0; i < player.rains.size();i++)
				{
					MovableObject rain = player.rains.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() +  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player2.rains.size();i++)
				{
					MovableObject rain = player2.rains.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() +  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player.bullets.size();i++)
				{
					MovableObject rain = player.bullets.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
				}
				for(int i =0; i < player2.bullets.size();i++)
				{
					MovableObject rain = player2.bullets.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
				}
				for(int i =0; i < player.grenades.size();i++)
				{
					MovableObject rain = player.grenades.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
				for(int i =0; i < player2.grenades.size();i++)
				{
					MovableObject rain = player2.grenades.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
				for(int i =0; i < player.harpoons.size();i++)
				{
					MovableObject rain = player.harpoons.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
				for(int i =0; i < player2.harpoons.size();i++)
				{
					MovableObject rain = player2.harpoons.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
		}
		for(int w = 0; w< player2.blackholesActive.size();w++)
		{
			MovableObject bullet = player2.blackholesActive.get(w);
			
					player.setVectorX(player.getVectorX() - (178f/distanceBetween(player,bullet)) * distanceXBetween(player,bullet) / distanceBetween(player,bullet));
					player.setVectorY(player.getVectorY() + (178f/distanceBetween(player,bullet)) * distanceYBetween(player,bullet) / distanceBetween(player,bullet));
					if(player.intersects(bullet))
					player.health = player.health - .23456f*(float)Math.sqrt(178f/distanceBetween(player,bullet));
			
					player2.setVectorX(player2.getVectorX() - (178f/distanceBetween(player2,bullet)) * distanceXBetween(player2,bullet) / distanceBetween(player2,bullet));
					player2.setVectorY(player2.getVectorY() + (178f/distanceBetween(player2,bullet)) * distanceYBetween(player2,bullet) / distanceBetween(player2,bullet));
					if(player2.intersects(bullet))
					player2.health = player2.health - .23456f*(float)Math.sqrt(178f/distanceBetween(player2,bullet));
					
			
				for(int i =0; i < player.rains.size();i++)
				{
					MovableObject rain = player.rains.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() +  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player2.rains.size();i++)
				{
					MovableObject rain = player2.rains.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() +  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player.bullets.size();i++)
				{
					MovableObject rain = player.bullets.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
				}
				for(int i =0; i < player2.bullets.size();i++)
				{
					MovableObject rain = player2.bullets.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
				}
				for(int i =0; i < player.grenades.size();i++)
				{
					MovableObject rain = player.grenades.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
				for(int i =0; i < player2.grenades.size();i++)
				{
					MovableObject rain = player2.grenades.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
				for(int i =0; i < player.harpoons.size();i++)
				{
					MovableObject rain = player.harpoons.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
				for(int i =0; i < player2.harpoons.size();i++)
				{
					MovableObject rain = player2.harpoons.get(i);
					//if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() -  (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet) );
						rain.setVectorY(rain.getVectorY() + (float) Math.sqrt(178f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) );
					}
				}
		}
		///Grenades
		for(int w = 0; w< player.grenades.size();w++)
		{
			MovableObject bullet = player.grenades.get(w);
			if(bullet.timeSinceCollision > 85f)//grenade explosion
			{
				harpoon.play();
				MovableObject shrapnel = new MovableObject(mainPanel, player.bulletName, (bullet.getX() + bullet.getWidth()/2f + (float)Math.random()*21f -11f),(bullet.getY() + bullet.getHeight()/2f+ (float)Math.random()*21f - 11f), 2,2);
				for(int asdf = 0; asdf < 1500; asdf++)
				{
				shrapnel = new MovableObject(mainPanel, player.bulletName, (bullet.getX() + bullet.getWidth()/2f + (float)Math.random()*21f -11f),(bullet.getY() + bullet.getHeight()/2f+ (float)Math.random()*21f - 11f), 2,2);
				shrapnel.mass=5f;
				player.bullets.add(shrapnel);
				}
				if(distanceBetween(player,bullet) < 400)
				{
					player.setVectorX(player.getVectorX() + (float) Math.sqrt(57823f/distanceBetween(player,bullet)) * distanceXBetween(player,bullet) / distanceBetween(player,bullet));
					player.setVectorY(player.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(player,bullet)) * distanceYBetween(player,bullet) / distanceBetween(player,bullet));
					player.health = player.health - 1.43456f*(float)Math.sqrt(57823f/distanceBetween(player,bullet));
				}
				if(distanceBetween(player2,bullet) < 400)
				{
					player2.setVectorX(player2.getVectorX() + (float) Math.sqrt(57823f/distanceBetween(player2,bullet)) * distanceXBetween(player2,bullet) / distanceBetween(player2,bullet));
					player2.setVectorY(player2.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(player2,bullet)) * distanceYBetween(player2,bullet) / distanceBetween(player2,bullet));
					player2.health = player2.health - 1.43456f*(float)Math.sqrt(57823f/distanceBetween(player2,bullet));
				}
				for(int q = 0; q < player.grenades.size();q++)
				{
					MovableObject rain = player.grenades.get(q);
					if(!bullet.equals(rain))
					{
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
					}	
				}
				for(int q = 0; q < player2.grenades.size();q++)
				{
					MovableObject rain = player2.grenades.get(q);
					if(!bullet.equals(rain))
					{
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
					}	
				}
				for(int i =0; i < player.rains.size();i++)
				{
					MovableObject rain = player.rains.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player2.rains.size();i++)
				{
					MovableObject rain = player2.rains.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player.bullets.size();i++)
				{
					MovableObject rain = player.bullets.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet)/rain.mass );
						rain.setVectorY(rain.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) /rain.mass);
					}
				}
				for(int i =0; i < player2.bullets.size();i++)
				{
					MovableObject rain = player2.bullets.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet)/rain.mass );
						rain.setVectorY(rain.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) /rain.mass);
					}
				}
				player.grenades.remove(w);
				w--;
			}
		}
		for(int w = 0; w< player2.grenades.size();w++)
		{
			MovableObject bullet = player2.grenades.get(w);
			if(bullet.timeSinceCollision > 85f)//grenade explosion
			{
				harpoon.play();
				MovableObject shrapnel = new MovableObject(mainPanel, player.bulletName, (bullet.getX() + bullet.getWidth()/2f + (float)Math.random()*21f -11f),(bullet.getY() + bullet.getHeight()/2f+ (float)Math.random()*21f - 11f), 2,2);
				for(int asdf = 0; asdf < 15; asdf++)
				{
				shrapnel = new MovableObject(mainPanel, player.bulletName, (bullet.getX() + bullet.getWidth()/2f + (float)Math.random()*21f -11f),(bullet.getY() + bullet.getHeight()/2f+ (float)Math.random()*21f - 11f), 2,2);
				shrapnel.mass=5f;
				player.bullets.add(shrapnel);
				}
				if(distanceBetween(player,bullet) < 400)
				{
					player.setVectorX(player.getVectorX() + (float) Math.sqrt(57823f/distanceBetween(player,bullet)) * distanceXBetween(player,bullet) / distanceBetween(player,bullet));
					player.setVectorY(player.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(player,bullet)) * distanceYBetween(player,bullet) / distanceBetween(player,bullet));
					player.health = player.health - 1.43456f*(float)Math.sqrt(57823f/distanceBetween(player,bullet));
				}
				if(distanceBetween(player2,bullet) < 400)
				{
					player2.setVectorX(player2.getVectorX() + (float) Math.sqrt(57823f/distanceBetween(player2,bullet)) * distanceXBetween(player2,bullet) / distanceBetween(player2,bullet));
					player2.setVectorY(player2.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(player2,bullet)) * distanceYBetween(player2,bullet) / distanceBetween(player2,bullet));
					player2.health = player2.health - 1.43456f*(float)Math.sqrt(57823f/distanceBetween(player2,bullet));
				}
				for(int q = 0; q < player.grenades.size();q++)
				{
					MovableObject rain = player.grenades.get(q);
					if(!bullet.equals(rain))
					{
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
					}	
				}
				for(int q = 0; q < player2.grenades.size();q++)
				{
					MovableObject rain = player2.grenades.get(q);
					if(!bullet.equals(rain))
					{
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					}
					}	
				}
				for(int i =0; i < player.rains.size();i++)
				{
					MovableObject rain = player.rains.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player2.rains.size();i++)
				{
					MovableObject rain = player2.rains.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet));
						rain.setVectorY(rain.getVectorY() -  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet));
					
					}
				}
				for(int i =0; i < player.bullets.size();i++)
				{
					MovableObject rain = player.bullets.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet)/rain.mass );
						rain.setVectorY(rain.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) /rain.mass);
					}
				}
				for(int i =0; i < player2.bullets.size();i++)
				{
					MovableObject rain = player2.bullets.get(i);
					if(distanceBetween(rain,bullet) < 400)
					{
						rain.setVectorX(rain.getVectorX() +  (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceXBetween(rain,bullet) / distanceBetween(rain,bullet)/rain.mass );
						rain.setVectorY(rain.getVectorY() - (float) Math.sqrt(57823f/distanceBetween(rain,bullet)) * distanceYBetween(rain,bullet) / distanceBetween(rain,bullet) /rain.mass);
					}
				}
				player2.grenades.remove(w);
				w--;
			}
		}
		///clouds & rain
		for(int i = 0; i < player.clouds.size(); i++)
		{
			MovableObject cloud = player.clouds.get(i);
			if(player2.intersects(cloud))
			{
				player2.health = player2.health-5f;
			}
			if(player.intersects(cloud))
			{	
				player.health = player.health+0f;
			}
		}
		//p2
		for(int i = 0; i < player2.clouds.size(); i++)
		{
			MovableObject cloud = player2.clouds.get(i);
			if(player.intersects(cloud))
			{
				player.health = player.health-5f;
			}
			if(player2.intersects(cloud))
			{
				player2.health = player2.health+0f;
			}
		}
		////rain
		for(int j =0; j < player.rains.size();j++)
		{
			MovableObject rain = player.rains.get(j);
			if(player2.intersects(rain))
				{
					player2.health = player2.health-1f;
					player.rains.remove(j);
					j--;
				}
				else if(player.intersects(rain) &&(player.health<250f))
				{
					player.health = player.health+0.5f;
					player.rains.remove(j);
					j--;
				}
		}
		//p2
		for(int j =0; j < player2.rains.size();j++)
		{
			MovableObject rain = player2.rains.get(j);
			if(player.intersects(rain))
				{
					player.health = player.health-1f;
					player2.rains.remove(j);
					j--;
				}
				else if(player2.intersects(rain) &&(player2.health<250f))
				{
					player2.health = player2.health+0.5f;
					player2.rains.remove(j);
					j--;
				}
		}
		///shanks
		for(int i = 0; i < player.shanks.size();i++)
		{
			MovableObject shank = player.shanks.get(i);
			if(player2.intersects(shank))
			{	
				if(player2.getFace() == shank.getFace())
				{
					shank.setCurrentFilename("shankbackstab3.png");
					player2.health = player2.health - 24f;
					player2.setVectorX(player2.getVectorX()+shank.getVectorX()/2f);
					player2.setVectorY(player2.getVectorY()+shank.getVectorY()/2f);
				}
				else
				{
					player2.health = player2.health - 13f;
					player2.setVectorX(player2.getVectorX()+shank.getVectorX()/5f);
					player2.setVectorY(player2.getVectorY()+shank.getVectorY()/5f);
				}
					
				player.fadeHarpoons.add(shank);
				player.shanks.remove(i);
				i--;
			}	
		}
		for(int i = 0; i < player2.shanks.size();i++)
		{
			MovableObject shank = player2.shanks.get(i);
			if(player.intersects(shank))
			{
				if(player.getFace() == shank.getFace())
				{
					shank.setCurrentFilename("shankbackstab3.png");
					player.health = player.health - 24f;
					player.setVectorX(player.getVectorX()+shank.getVectorX()/2f);
					player.setVectorY(player.getVectorY()+shank.getVectorY()/2f);
				}
				else
				{
					player.health = player.health - 13f;
					player.setVectorX(player.getVectorX()+shank.getVectorX()/5f);
					player.setVectorY(player.getVectorY()+shank.getVectorY()/5f);
				}
				player2.fadeHarpoons.add(shank);
				player2.shanks.remove(i);
				i--;
			}	
		}
	
		////harpoons
		for(int w = 0; w< player.harpoons.size();w++)
		{
			MovableObject bullet = player.harpoons.get(w);
			if(bullet.intersects(player2.getRect()))
			{
				player2.setVectorX( player2.getVectorX()+ bullet.getVectorX()*40f/(player2.mass));
				player2.setVectorY( player2.getVectorY()+bullet.getVectorY()*40f/(player2.mass));
				player2.health= player2.health - (float) Math.sqrt( bullet.getVectorX() * bullet.getVectorX() + bullet.getVectorY() * bullet.getVectorY())/5f - bullet.totalDistance/20f;
				bullet.timeSinceCollision=0f;
				bullet.setCurrentFilename("harpoonstabbed2.png");
				player.fadeHarpoons.add(bullet);
				player.harpoons.remove(w);
				w--;
				hit.play();
				if(player2.hookedPlayer)
					player2.hookedPlayer=false;
			}
		}
		for(int w = 0; w< player2.harpoons.size();w++)
		{
			MovableObject bullet = player2.harpoons.get(w);
			if(bullet.intersects(player.getRect()))
			{
				player.setVectorX( player.getVectorX()+ bullet.getVectorX()*40f/(player.mass));
				player.setVectorY( player.getVectorY()+bullet.getVectorY()*40f/(player.mass));
				player.health= player.health - (float) Math.sqrt( bullet.getVectorX() * bullet.getVectorX() + bullet.getVectorY() * bullet.getVectorY())/5f - bullet.totalDistance/20f;
				bullet.timeSinceCollision=0f;
				bullet.setCurrentFilename("harpoonstabbed2.png");
				player2.fadeHarpoons.add(bullet);
				player2.harpoons.remove(w);
				w--;
				hit.play();
				if(player.hookedPlayer)
					player.hookedPlayer=false;
			}
		}
		///peas
		for(int w = 0; w< player.bullets.size();w++)
		{
			MovableObject bullet = player.bullets.get(w);
			if(bullet.intersects(player2.getRect()))
			{
				player2.setVectorX( player2.getVectorX()+ bullet.getVectorX()/player2.mass);
				player2.setVectorY(player2.getVectorY()+bullet.getVectorY()/player2.mass);
				if(bullet.reboundCount <1)
					player2.health--;
				else
					player2.health=player2.health-0.5f;
				player.bullets.remove(w);
				w--;
			}
			else if(bullet.intersects(player.getRect()))
			{
				player.setVectorX( player.getVectorX()+ bullet.getVectorX()/player.mass);
				player.setVectorY(player.getVectorY()+bullet.getVectorY()/player.mass);
				if(bullet.reboundCount <1)
					player.health--;
				else
					player.health=player.health-0.5f;
				player.bullets.remove(w);
				w--;
			}
		}
		for(int w = 0; w< player2.bullets.size();w++)
		{
			MovableObject bullet = player2.bullets.get(w);
			if(bullet.intersects(player.getRect()))
			{
				player.setVectorX( player.getVectorX()+ bullet.getVectorX()/player.mass);
				player.setVectorY(player.getVectorY()+bullet.getVectorY()/player.mass);
				if(bullet.reboundCount <1)
					player.health=player.health-1.1f;
				else
					player.health=player.health-0.5f;
					
				player2.bullets.remove(w);
				w--;
				
			}
			else if(bullet.intersects(player2.getRect()))
			{
				player2.setVectorX( player2.getVectorX()+ bullet.getVectorX()/player2.mass);
				player2.setVectorY(player2.getVectorY()+bullet.getVectorY()/player2.mass);
				if(bullet.reboundCount <1)
					player2.health=player2.health-1.1f;
				else
					player2.health=player2.health-0.5f;
				player2.bullets.remove(w);
				w--;
			}
		}
		///scoreboard update
		if(player.health <=0f && player2.health <=0f)
		{
			p1score++;
			p2score++;
			player.health=100f;
			player2.health=100f;
		}
		else if(player.health<=0f)
		{
			p2score++;
			player.health=100f;
		}
		else if(player2.health<=0f)
		{		
			p1score++;
			player2.health=100f;
		}	
		CDS = "global: "+player.globalCD+" || shank: "+player.shankCD+" || harpoon: "+player.harpoonCD+" || cloud: "+player.cloudCD + " || grenade: "+player.grenadeCD+" || blackhole: "+player.blackholeCD;	
		CDS2= "global: "+player2.globalCD+" || shank: "+player2.shankCD+" || harpoon: "+player2.harpoonCD+" || cloud: "+player2.cloudCD+ " || grenade: "+player2.grenadeCD+" || blackhole: "+player2.blackholeCD;	
		playerOneHealth = "HP: "+(int)player.health;
		playerTwoHealth = "HP: "+(int)player2.health;
		scoreBoard= "P1: "+p1score+"    P2: "+p2score;
		int r = (int)(Math.random()*94) + 97;
		int gg = (int)(Math.random()*66) + 188;
		int b = (int)(Math.random()*77) + 129;
		Color	col = new Color(r,gg,b);
		gMemory.setColor(col);
		gMemory.drawString(playerOneHealth,10,80);
		gMemory.drawString(CDS,100,80);
		gMemory.drawString(playerOneHealth, (int) ( player.getX() - 15), (int)(player.getY() - 20 ));
		r = (int)(Math.random()*93) + 104;
		gg = (int)(Math.random()*71) + 36;
	    b = (int)(Math.random()*79) + 86;
		col = new Color(r,gg,b);
		gMemory.setColor(col);
		gMemory.drawString(playerTwoHealth,10,95);
		gMemory.drawString(CDS2,100,95);
		gMemory.drawString(playerTwoHealth, (int) ( player2.getX() -15), (int)(player2.getY() - 20 ));
		r = (int)(Math.random()*20) + 235;
		gg = (int)(Math.random()*20) + 235;
	    b = (int)(Math.random()*20) + 235;
		col = new Color(r,gg,b);
		gMemory.setColor(col);
		gMemory.drawString(scoreBoard,screenWidth/2,80);
		//System.out.println("player acceleration X,Y (" + player.getAccelX() +", "+player.getAccelY()+")");
		

		/*for(int i = 0; i < platforms.size(); i++)
		{
		
		if(player.getRect().intersects(platforms.get(i).getRect()))
		{
			
			if(  !rebound && (player.getY() + player.getHeight() ) >= platforms.get(i).getY()     &&   (player.getY()  ) <= (platforms.get(i).getY() + platforms.get(i).getHeight())     )
			{
				
			//	System.out.println("ASDF");
				player.setAccelY(1f);
				player.setVectorY(player.getVectorY() * 0.0f);
				rebound = true;
				player.ground=true;
				i = platforms.size();
				
			}
		}
		else 
		{
				rebound = false;
				player.setAccelY(-.44f);
		}
		}*/
	
		
		/*if(player.getVectorX() <= player.maxSpeed && player.getVectorX() >=0)
			player.setVectorX(player.getVectorX() + (0.1f)*player.getAccelX());
			
		else if(player.getVectorX() >= (-1f *player.maxSpeed) && player.getVectorX() <=0)
			player.setVectorX(player.getVectorX() + (0.1f)*player.getAccelX());
	
	
		if(player.getVectorX() > player.maxSpeed)
			player.setVectorX(player.maxSpeed);
			
		else if(player.getVectorX() < -1f*player.maxSpeed)
			player.setVectorX(-1f*player.maxSpeed);*/
		
			{
	    player.setVectorX(player.getVectorX() + player.getAccelX());
	    player.setVectorY(player.getVectorY() + player.getAccelY());
		player.setX(player.getX() + player.getVectorX());
		player.setY(player.getY() - player.getVectorY());
		
		player2.setVectorX(player2.getVectorX() + player2.getAccelX());
	    player2.setVectorY(player2.getVectorY() + player2.getAccelY());
		player2.setX(player2.getX() + player2.getVectorX());
		player2.setY(player2.getY() - player2.getVectorY());

		}
		 r = (int)(Math.random()*94) + 97;
		 gg = (int)(Math.random()*66) + 188;
		 b = (int)(Math.random()*77) + 129;
		 col = new Color(r,gg,b);
		 if(player.hookedPlayer)
		 {
		 	player.hook.setX(player2.getX() + player2.getWidth()/2);
		 	player.hook.setY(player2.getY() + player2.getHeight()/2);
		 }
		if(player.grappling)
		{
			player.hook.setVectorX(player.hook.getVectorX() + player.hook.getAccelX());
	    	player.hook.setVectorY(player.hook.getVectorY() + player.hook.getAccelY() + -.44f);
			player.hook.setX(player.hook.getX() + player.hook.getVectorX());
			player.hook.setY(player.hook.getY() - player.hook.getVectorY());
			//System.out.println(""+hook.getX());
			//hook.setVectorY(hook.getVectorX() + player.getAccelY());
			//gMemory.setColor(new Color(191,254,206));	
			gMemory.setColor(col);
			gMemory.drawLine((int)(player.hook.getX() + player.hook.getWidth()/2), (int)(player.hook.getY() + player.hook.getHeight()/2), (int)(player.getX() + player.getWidth()/2), (int)(player.getY() + player.getHeight()/2));
			player.hook.draw(gMemory);
			
		}
		else if(player.hooked||player.hookedPlayer)
		{
			//System.out.println("test" + player.timeSinceCollision);
			//gMemory.setColor(new Color(97,188,129));	
			gMemory.setColor(col);
			gMemory.drawLine((int)(player.hook.getX() + player.hook.getWidth()/2), (int)(player.hook.getY() + player.hook.getHeight()/2), (int)(player.getX() + player.getWidth()/2), (int)(player.getY() + player.getHeight()/2));
				player.hook.draw(gMemory);
		}
		r = (int)(Math.random()*93) + 104;
		gg = (int)(Math.random()*71) + 36;
	    b = (int)(Math.random()*79) + 86;
		 col = new Color(r,gg,b);
		//157, 67, 125
		//84,16,66
		if(player2.hookedPlayer)
		 {
		 	player2.hook.setX(player.getX() + player.getWidth()/2);
		 	player2.hook.setY(player.getY() + player.getHeight()/2);
		 }
		if(player2.grappling)
		{
			player2.hook.setX(player2.hook.getX() + player2.hook.getVectorX());
			player2.hook.setY(player2.hook.getY() - player2.hook.getVectorY());
			gMemory.setColor(col);	
			gMemory.drawLine((int)(player2.hook.getX() + player2.hook.getWidth()/2), (int)(player2.hook.getY() + player2.hook.getHeight()/2), (int)(player2.getX() + player2.getWidth()/2), (int)(player2.getY() + player2.getHeight()/2));
			player2.hook.draw(gMemory);
			
		}
		else if(player2.hooked||player2.hookedPlayer)
		{
			gMemory.setColor(col);	
			gMemory.drawLine((int)(player2.hook.getX() + player2.hook.getWidth()/2), (int)(player2.hook.getY() + player2.hook.getHeight()/2), (int)(player2.getX() + player2.getWidth()/2), (int)(player2.getY() + player2.getHeight()/2));
			player2.hook.draw(gMemory);
		}
		
		if((keys[2]||keys[3]) &&!(player.hooked||player.hookedPlayer ))
		{
			player.decay = false;
		}
		else if (!(keys[2]&&keys[3])   &&!(player.hooked||player.hookedPlayer ) )
		{
			player.decay = true;
		}
		if((keys[9]||keys[10]) &&!(player2.hooked||player2.hookedPlayer ))
		{
			player2.decay = false;
		}
		else if (!(keys[9]&&keys[10])   &&!(player2.hooked||player2.hookedPlayer ))
		{
			player2.decay = true;
		}
		if(player.timeSinceCollision >20f)
			player.decay = false;
			
		if(player2.timeSinceCollision >20f)
			player2.decay = false;
			
		if(player.decay)
		{
			player.setAccelX((.05676f)*player.getVectorX() * -1f);
		}
		else if(!player.decay)
		{
			player.setAccelX(0f);
		}
		if(player2.decay)
		{
			player2.setAccelX((.05676f)*player2.getVectorX() * -1f);
		}
		else if(!player2.decay)
		{
			player2.setAccelX(0f);
		}
		for(int i = 0; i< platforms.size(); i++)
		{
			platforms.get(i).draw(gMemory);
		}
		
		// draw your player
		
		if (player != null)
		{
			player.crosshair.setY((float)(player.getY()+player.getHeight()/2f + Math.sin(player.getAngle()/180f*Math.PI)*35f) - player.crosshair.getHeight()/2f);
			player.crosshair.setX((float)(player.getX()+player.getWidth()/2f + Math.cos(player.getAngle()/180f*Math.PI)*35f) - player.crosshair.getWidth()/2f);
			player.draw(gMemory);
			player.crosshair.draw(gMemory);
		}
		if(player2!=null)
		{
			player2.crosshair.setY((float)(player2.getY()+player2.getHeight()/2f + Math.sin(player2.getAngle()/180f*Math.PI)*35f) - player2.crosshair.getHeight()/2f);
			player2.crosshair.setX((float)(player2.getX()+player2.getWidth()/2f + Math.cos(player2.getAngle()/180f*Math.PI)*35f) - player2.crosshair.getWidth()/2f);
			player2.draw(gMemory);
			player2.crosshair.draw(gMemory);
		}

		// FINISH ME
		// draw your player's bullets
		

		/////////////Draw Bullets///////////////
		//grenades
		
		//shanks
		for(int i = 0; i < player.shanks.size();i++)
		{
	//	player.shanks.get(i).setX(player.crosshair.getX() +player.crosshair.getWidth()/2- player.shanks.get(i).getWidth()/2);
		//player.shanks.get(i).setY(player.crosshair.getY() +player.crosshair.getHeight()/2- player.shanks.get(i).getHeight()/2);
		player.shanks.get(i).setX(player.shanks.get(i).getX() + player.shanks.get(i).getVectorX());
		player.shanks.get(i).setY(player.shanks.get(i).getY() - player.shanks.get(i).getVectorY());
			
		//player.shanks.get(i).setAngle(player.getAngle());
		player.shanks.get(i).timeSinceCollision +=1f;
		if(player.shanks.get(i).timeSinceCollision >5f)
		{
			player.shanks.remove(i);
			i--;
		}
			
		}
		for(int i = 0 ; i < player.shanks.size();i++)
		{
		
			player.shanks.get(i).draw2(gMemory);
		}
		
		///////
		for(int i = 0; i < player2.shanks.size();i++)
		{
		player2.shanks.get(i).setX(player2.shanks.get(i).getX() + player2.shanks.get(i).getVectorX());
		player2.shanks.get(i).setY(player2.shanks.get(i).getY() - player2.shanks.get(i).getVectorY());		
		player2.shanks.get(i).timeSinceCollision +=1f;
		if(player2.shanks.get(i).timeSinceCollision >5f)
		{
		
			player2.shanks.remove(i);
			i--;
		}		
		}
		for(int i = 0 ; i < player2.shanks.size();i++)
		{
			player2.shanks.get(i).draw2(gMemory);
		}
		/////clouds
		
		//rains
		for(int i = 0; i < player.rains.size();i++)
		{
			player.rains.get(i).setAngle( (float)Math.toDegrees(Math.atan( player.rains.get(i).getVectorY() / player.rains.get(i).getVectorX() ))*-1f -90f);
			player.rains.get(i).draw2(gMemory);
		}
		for(int i = 0 ; i < player.clouds.size();i++)
		{
			
			for(int j = 0 ; j< player.clouds.get(i).bullets.size(); j++)
			{
				player.clouds.get(i).bullets.get(j).draw(gMemory);
			}
			player.clouds.get(i).draw(gMemory);
			
		}
		//p2
		for(int i = 0; i < player2.rains.size();i++)
		{
			player2.rains.get(i).setAngle( (float)Math.toDegrees(Math.atan( player2.rains.get(i).getVectorY() / player2.rains.get(i).getVectorX() ))*-1f -90f);
			player2.rains.get(i).draw2(gMemory);
		}
		for(int i = 0 ; i < player2.clouds.size();i++)
		{
			
			for(int j = 0 ; j< player2.clouds.get(i).bullets.size(); j++)
			{
				player2.clouds.get(i).bullets.get(j).draw(gMemory);
			}
			player2.clouds.get(i).draw(gMemory);
			
		}
		//harpoons
		if(player.harpoonArmed)
		{
			MovableObject harpoon = new MovableObject(mainPanel, "harpoon.png", (int)( player.crosshair.getX() +player.crosshair.getWidth()/2f),(int) (player.crosshair.getY() + player.crosshair.getHeight()/2f), 225,25);
			harpoon.setAngle(player.getAngle());
			harpoon.setX(harpoon.getX() - harpoon.getWidth()/2);
			harpoon.setY(harpoon.getY() - harpoon.getHeight()/2);
			harpoon.draw2(gMemory);
		
		}
		if(player2.harpoonArmed)
		{
			MovableObject harpoon = new MovableObject(mainPanel, "harpoon.png", (int)( player2.crosshair.getX() +player2.crosshair.getWidth()/2f),(int) (player2.crosshair.getY() + player2.crosshair.getHeight()/2f), 225,25);
			harpoon.setAngle(player2.getAngle());
			harpoon.setX(harpoon.getX() - harpoon.getWidth()/2);
			harpoon.setY(harpoon.getY() - harpoon.getHeight()/2);
			harpoon.draw2(gMemory);
		
		}
		for(int i = 0; i < player.harpoons.size(); i++)
		{
				MovableObject bullet = player.harpoons.get(i);
				if(bullet.getFace()==0)
				bullet.setAngle((float) Math.toDegrees(Math.atan( -1f *bullet.getVectorY()/bullet.getVectorX()  )));
				else
				bullet.setAngle((float) Math.toDegrees(Math.atan( -1f *bullet.getVectorY()/bullet.getVectorX()  )) +180f);
					
   		   		bullet.draw2(gMemory);
		}
		for(int i = 0; i < player.fadeHarpoons.size(); i++)
		{
				MovableObject bullet = player.fadeHarpoons.get(i);
   		   		bullet.draw2(gMemory);
   		   		bullet.setX(player2.getX() + player2.getWidth()/2 - bullet.getWidth()/2);
				bullet.setY(player2.getY() + player2.getHeight()/2 - bullet.getHeight()/2);
		}
		for(int w =0; w< player.fadeHarpoons.size();w++)
		{
			player.fadeHarpoons.get(w).timeSinceCollision = player.fadeHarpoons.get(w).timeSinceCollision +1;
			if(player.fadeHarpoons.get(w).timeSinceCollision  > 50f)
			{
			
				player.fadeHarpoons.remove(w);
				w--;
			}
		}
		
		//////
		for(int i = 0; i < player2.harpoons.size(); i++)
		{
				MovableObject bullet = player2.harpoons.get(i);
				if(bullet.getFace()==0)
				bullet.setAngle((float) Math.toDegrees(Math.atan( -1f *bullet.getVectorY()/bullet.getVectorX()  )));
				else
				bullet.setAngle((float) Math.toDegrees(Math.atan( -1f *bullet.getVectorY()/bullet.getVectorX()  )) +180f);
					
   		   		bullet.draw2(gMemory);
		}
		for(int i = 0; i < player2.fadeHarpoons.size(); i++)
		{
				MovableObject bullet = player2.fadeHarpoons.get(i);
   		   		bullet.draw2(gMemory);
   		   		bullet.setX(player.getX() + player.getWidth()/2 - bullet.getWidth()/2);
				bullet.setY(player.getY() + player.getHeight()/2 - bullet.getHeight()/2);
		}
		for(int w =0; w< player2.fadeHarpoons.size();w++)
		{
			player2.fadeHarpoons.get(w).timeSinceCollision = player2.fadeHarpoons.get(w).timeSinceCollision +1;
			if(player2.fadeHarpoons.get(w).timeSinceCollision  > 50f)
			{
				player2.fadeHarpoons.remove(w);
				w--;
			}
		}
		
		///peas
   		for (int i=0; i < player.bullets.size(); i++)
   		{
   		   		MovableObject bullet = player.bullets.get(i);
   		   		bullet.draw(gMemory);
   		}
   		for (int i=0; i < player2.bullets.size(); i++)
   		{
   		   		MovableObject bullet = player2.bullets.get(i);
   		   		bullet.draw(gMemory);
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
  	if (player == null)
  		return;

    // another way to handle the keys, but no repeat factor
    if (keyString.equals(player2.keyBinds.get(0)))
    {
    	
    }
    else if (keyString.equals(player2.keyBinds.get(1)))
    {
    }
    else if (keyString.equals(player2.keyBinds.get(2)))
    {
    
    }
    else if (keyString.equals(player2.keyBinds.get(3)))
    {
 
     }

    // check to see if you need to fire
  } // end of method public void keyPressed(String keyString)


  // ***** MouseListener interface methods *****


  // start of mouseClicked(MouseEvent e) (MouseListener interface)
  public void mouseClicked(MouseEvent e) 
  {
    //int xPos = e.getX();
    //int yPos = e.getY();
    //player.setVectorY(0f);
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
    //player.setVectorY(0f);
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
  {player.setAccelY(-.44f);
  
    dragging = false;
    startMouseDragX = -1;
    startMouseDragY = -1;
 	if (player==null)
  		return;
    int xPos = e.getX();
    int yPos = e.getY();
   // player.setVectorY(0f);	
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
    //testXY = "X=" + xPos + "  Y=" + yPos;
    
  }  // end of public void mouseMoved(MouseEvent e) 


  public void mouseDragged(MouseEvent e) 
  {
  	//player.setVectorY(0f);
  	player.setAccelY(0f);
    int xPos = e.getX();
    int yPos = e.getY();
    //testXY = "X=" + xPos + "  Y=" + yPos;
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
   		} 
   		
    } 
    
  }  

} 




} // end of class rho
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




 class MovableObject
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
			 
	public float timeSinceCollision;
	public boolean ground;
	public boolean hooked = false;
	public boolean grappling = false;
	public boolean grapplingSwitch = false;
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
	public ArrayList <String> keyBinds;
	public MovableObject hook;
	public String bulletName = "playerv2.png";
	public String rainName= "player1rainv3.png";
	public String cloudName ="cloud3.png";
	public float health=100f;
		public MovableObject crosshair;
	
	// amount of time on the screen
	private float time;
	
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
		setVectorY(this.getVectorY() + 11.23f);
		ground = false;
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
		hook.setX(getX() +  getWidth()/2);
		hook.setY( getY() +  getHeight()/2);
		if( getFace()==0)
		{
		
		hook.setVectorX((float)( getVectorX() + 30f*Math.cos(-1f* getAngle()/180f * Math.PI)));
		hook.setVectorY((float)( getVectorX() +30f*Math.sin(-1f* getAngle()/180f * Math.PI)));
		}
		else
		{
		hook.setVectorX((float)( getVectorY() + -30f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		hook.setVectorY((float)( getVectorY()+30f*Math.sin(( getAngle()-180)/180f * Math.PI)));
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
		bullet.setVectorX((float)(  getVectorX()+    15f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()+    15f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		bullet.setVectorX((float)(  getVectorX()+   -15f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()+    15f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		bullet.mass=1f;
		bullets.add(bullet);
	}
	public void shootGrenade()
	{
		MovableObject bullet = new MovableObject(mainPanel, "grenade.png", (int)( crosshair.getX() +crosshair.getWidth()/2f),(int) (crosshair.getY() + crosshair.getHeight()/2f), 10,10);
		if( getFace()==0)
		{
		bullet.setVectorX((float)(  getVectorX()+    15f*Math.cos((-1f* getAngle())/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()+    15f*Math.sin((-1f* getAngle())/180f * Math.PI)));
		}
		else
		{
		bullet.setVectorX((float)(  getVectorX()+   -15f*Math.cos(( getAngle()-180)/180f * Math.PI)));
		bullet.setVectorY((float)(  getVectorY()+    15f*Math.sin(( getAngle()-180)/180f * Math.PI)));
		}
		grenades.add(bullet);
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
 

