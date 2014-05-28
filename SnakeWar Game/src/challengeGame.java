import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.bluetooth.*;
import java.util.*;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.game.*;


public class challengeGame implements CommandListener 
{

 public challengeCanvas mSnakeCanvas;
 
  public Command mExitCommand,mStartCommand,restart,resume,soundONOFF;
	
	private SnakeWar parent;
	
	
	public int are,nump,col,spe,et,ti;
	
	public challengeGame(SnakeWar parent)
	{
		this.parent=parent;
		
  restart = new Command("Restart", Command.SCREEN, 0);
		resume = new Command("Resume", Command.SCREEN, 0);
      		soundONOFF = new Command("Sound ON/OFF", Command.SCREEN, 0);
		
		are=parent.SPGameArena;

		col=parent.SPSnakeColor;
		spe=parent.SPGameSpeed;
		et=parent.enter;
				ti=parent.timed;
		int i=0;
    
    
		
		 if (mSnakeCanvas == null) {
      try {
        mSnakeCanvas = new challengeCanvas(are,col,spe,et,ti,parent);
        mExitCommand = new Command("Exit", Command.EXIT, 0);
        mStartCommand = new Command("Start", Command.SCREEN, 0);
        mSnakeCanvas.addCommand(mExitCommand);
        mSnakeCanvas.addCommand(mStartCommand);
        
        mSnakeCanvas.setCommandListener(this);
      }
      catch (IOException ioe) {
        System.out.println(ioe);
      }
    }

	}
	
	public void commandAction(Command c, Displayable s) {
    if (c.getCommandType() == Command.EXIT) {
    	try{
			parent.gs.stop();
			parent.gs.close();
			}catch(Exception sdf){}
     parent.show();
     	mSnakeCanvas.playing=false;
    }
    else if (c == mStartCommand) {
    	mSnakeCanvas.removeCommand(mExitCommand);
    	mSnakeCanvas.removeCommand(mStartCommand);
    		mSnakeCanvas.addCommand(resume);
        mSnakeCanvas.addCommand(restart);
                mSnakeCanvas.addCommand(soundONOFF);
    	mSnakeCanvas.addCommand(mExitCommand);
    	 mSnakeCanvas.setCommandListener(this);
    	mSnakeCanvas.setFullScreenMode(true);
    	mSnakeCanvas.start();
         }
          else if (c == resume) {
	}
	else if (c == restart) {
		mSnakeCanvas.playing=false;
			try{
			parent.gs.stop();
			parent.gs.close();
			}catch(Exception sdf){}
		try{
			parent.CHgetvalue();
        	parent.showCHGame();
                mSnakeCanvas = new challengeCanvas(are,col,spe,et,ti,parent);}catch(Exception ds){}     	
		mSnakeCanvas.removeCommand(mExitCommand);
    	mSnakeCanvas.removeCommand(mStartCommand);
    		mSnakeCanvas.addCommand(resume);
        mSnakeCanvas.addCommand(restart);
                mSnakeCanvas.addCommand(soundONOFF);
    	mSnakeCanvas.addCommand(mExitCommand);
    	 mSnakeCanvas.setCommandListener(this);
    	mSnakeCanvas.setFullScreenMode(true);

	}
	
	else if(c==soundONOFF)
	{
		if(parent.isMusic==true)
		{
			parent.isMusic=false;
			try{
			parent.gs.stop();
			parent.gs.close();
			}catch(Exception sdf){}
		}
		else if(parent.isMusic==false)
		{
			parent.isMusic=true;
			parent.gameSound(mSnakeCanvas.aaa);
		}
	}
   
  }


	
}