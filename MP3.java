
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


public class MP3 implements  Runnable {
    private String filename;
    private Player player;
    Thread runThread = null;
    int threadDelay = 25;
	int numTimesToPlay = 1;
	int numTimesPlayed = 0;
	int numSecondsToPlay = 0;

    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
        runThread = null;
        numTimesToPlay = 1;
        numTimesPlayed = 0;
        numSecondsToPlay = 0;
    }

    public MP3(String filename, int numTimesToPlay) {
        this.filename = filename;
        runThread = null;
        this.numTimesToPlay = numTimesToPlay;
        numTimesPlayed = 0;
        numSecondsToPlay = 0;
    }

    public void close()
    {
    	if (player != null)
    		player.close();
    	player = null;
    	if (runThread != null)
    	{
    		runThread.stop();
    		runThread = null;
    	}
    	numTimesPlayed = 0;
		numSecondsToPlay = 0;
    }

    // play the MP3 file to the sound card
    public void play() {
    	numTimesPlayed = 0;
		numSecondsToPlay = 0;
		runThread = new Thread(this);
		runThread.start();
    }

    // play the MP3 file to the sound card
    public void play(int numSecondsToPlay) {
    	this.numSecondsToPlay = numSecondsToPlay;
    	numTimesPlayed = 0;
		runThread = new Thread(this);
		runThread.start();
    }

  public void run()
  {
   	try
   	{
   	  while (numTimesPlayed < numTimesToPlay)
   	  {

       try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
        if (player != null)
        {
        	if (numSecondsToPlay > 0)
        		player.play(numSecondsToPlay);
        	else
        		player.play();
        }

   		while(player != null && !player.isComplete())
   		{
   		   Thread.currentThread().sleep(threadDelay);
        }
        numTimesPlayed++;
       }
    }
    catch(Exception e)
    {
    }
  }


    // test client
    public static void main(String[] args) {
    	// this will not play wav files
        String filename = "fight.mp3"; //args[0];
        MP3 mp3 = new MP3(filename,2);
        mp3.play();

    }

}

