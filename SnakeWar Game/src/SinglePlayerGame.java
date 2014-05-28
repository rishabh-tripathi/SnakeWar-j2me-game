import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.bluetooth.*;
import java.util.*;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.game.*;


public class SinglePlayerGame implements CommandListener 
{

 public SinglePlayerCanvas mSnakeCanvas;
 
  public Command mExitCommand,mStartCommand,restart,resume,soundONOFF;
	
	private SnakeWar parent;
	
	
	public int are,nump,col,spe,tog,ti;
	
	public SinglePlayerGame(SnakeWar parent)
	{
		this.parent=parent;
		

		
		are=parent.SPGameArena;
		nump=parent.spnumofplayer;
		col=parent.SPSnakeColor;
		spe=parent.SPGameSpeed;
		tog=parent.typeofgame;
				ti=parent.timed;
		int i=0;
    
    
		
		 if (mSnakeCanvas == null) {
      try {
        mSnakeCanvas = new SinglePlayerCanvas(are,col,nump,spe,tog,ti,parent);
      //  System.out.println(nump);
        mExitCommand = new Command("Exit", Command.EXIT, 0);
        mStartCommand = new Command("Start", Command.SCREEN, 0);
        restart = new Command("Restart", Command.SCREEN, 0);
		resume = new Command("Resume", Command.SCREEN, 0);
		soundONOFF = new Command("Sound ON/OFF", Command.SCREEN, 0);
        
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
	mSnakeCanvas.playing=false;
     parent.show();
    }
    else if (c == mStartCommand) {
    	mSnakeCanvas.start();
    	mSnakeCanvas.removeCommand(mExitCommand);
    	mSnakeCanvas.removeCommand(mStartCommand);
    	mSnakeCanvas.addCommand(resume);
        mSnakeCanvas.addCommand(restart);
        mSnakeCanvas.addCommand(soundONOFF);
    	mSnakeCanvas.addCommand(mExitCommand);
    	mSnakeCanvas.setFullScreenMode(true);
         }
   else if (c == resume) {
	}
	else if (c == restart) {
		try{
	parent.gs.stop();
	parent.gs.close();
	}catch(Exception sdf){}
	
		mSnakeCanvas.playing=false;
		try{
			parent.SPgetvalue();

		 	mSnakeCanvas = new SinglePlayerCanvas(are,col,nump,spe,tog,ti,parent);
		 				parent.showSPGame();
		 				}catch(Exception ds){}     	

    	mSnakeCanvas.removeCommand(mExitCommand);
    	mSnakeCanvas.removeCommand(mStartCommand);
    	mSnakeCanvas.addCommand(resume);
       mSnakeCanvas.addCommand(restart);
                mSnakeCanvas.addCommand(soundONOFF);
    	mSnakeCanvas.addCommand(mExitCommand);
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