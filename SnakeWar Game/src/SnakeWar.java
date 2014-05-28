import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;
import javax.microedition.media.*;
import javax.microedition.rms.*;


public class SnakeWar extends MIDlet implements CommandListener 
{
public int SCORE=5;

public String MyPlayerName;
public boolean isVibrate;
public boolean isBacklight;
public boolean isMusic;

public String GameID;
public String GamePass;
public int GameSpeed,SPGameSpeed;
public int SnakeColor,SPSnakeColor;
public boolean isTimed=false;
public int numofplayer,spnumofplayer;
public int GameArena,SPGameArena;
public int typeofgame,timed;

 public InputStream is;
public welMenu www;

private RecordStore rs = null;
static final String SET_SAV = "settings";

public loadGame lg;

public int level=0;
public int enter=1,currAre=0;

private List mList;
private Command mExitCommand, mNextCommand;
public Player player,gs;
private Setting set;
private Help help1;

private SinglePlayerMenu spmenu;
public stageClear sclr;
private SinglePlayerGame spg;
private challengeGame chg;
private cheat Cheat;
private stageMenu sm;
public HScore hscor;
public winner winER;

public SnakeWar() 
{
	openRecStore();   // Create the record store
   
   readRecords();
    
    closeRecStore();  // Close record store
winER=new winner(this);
	lg=new loadGame(this);
			set=new Setting(this);
		sclr=new stageClear(this);
	help1=new Help(this);
	www=new welMenu(this);


	
		
				Cheat=new cheat(this);
	
playMenuMusic();
	
mList = new List("SnakeWar V1.0", List.IMPLICIT);
mList.append("Arcade",null);
mList.append("Championship",null);
mList.append("Settings",null);
mList.append("High Score",null);
mList.append("Help",null);
mList.append("Exit",null);
mNextCommand = new Command("Next", Command.SCREEN, 0);
mExitCommand = new Command("Exit", Command.EXIT, 0);



mList.addCommand(mNextCommand);
mList.addCommand(mExitCommand);
mList.setCommandListener(this);


}


public void saveSetting()
{
	
isVibrate=set.isVibrate;
isMusic=set.isMusic;
isBacklight=set.isBacklight;
MyPlayerName=set.playerName;

}

	
public void startApp() {
	try{
		player.setLoopCount(10);
	 player.start();   }catch(Exception as){}
//playMenuMusic();
	 Display.getDisplay(this).setCurrent(www.welCome);
}


public void playMenuMusic()
{if(isMusic==true)
{ 
try{
 is = getClass().getResourceAsStream("/menu.midi");
            player = Manager.createPlayer(is, "audio/midi");
            player.prefetch();	
}catch(Exception ed){}
}
} 

public void gameSound(int stg)
{
if(isMusic==true)
{ 
try{
	
	if(stg==1)
	{
 is = getClass().getResourceAsStream("/S1.midi");
	}
	else if(stg==2)
	{
 is = getClass().getResourceAsStream("/S2.midi");
	}
	else if(stg==3)
	{
 is = getClass().getResourceAsStream("/S3.midi");
	}
	else if(stg==4)
	{
 is = getClass().getResourceAsStream("/S4.midi");
	}
	else if(stg==5)
	{
 is = getClass().getResourceAsStream("/S5.midi");
	}
	else if(stg==6)
	{
 is = getClass().getResourceAsStream("/S6.midi");
	}
	else if(stg==7)
	{
 is = getClass().getResourceAsStream("/S7.midi");
	}
	else if(stg==8)
	{
 is = getClass().getResourceAsStream("/S8.midi");
	}
	else if(stg==9)
	{
 is = getClass().getResourceAsStream("/S9.midi");
	}
	
	
            gs = Manager.createPlayer(is, "audio/midi");
            gs.prefetch();
            gs.setLoopCount(10);
            gs.start();	
}catch(Exception ed){}
}
}

public void changeArena()
{
	SPGameArena=currAre;
	    	SPGameArena++;
	    		chg=new challengeGame(this);
}

public void CHgetvalue()
{
	    level=sm.sa.getSelectedIndex();
	    
	    if(level==0)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=3;


			enter=1;
	    	timed=1;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
			
		else if(level==1)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=3;
	    	spnumofplayer=2;
	    	typeofgame=0;
	    	typeofgame++;
	    	timed=1;
	    				enter=2;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
	    else if(level==2)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=4;
	    	spnumofplayer=2;
	    	typeofgame=0;
	    	typeofgame++;
	    	timed=0;
	    				enter=3;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
	    else if(level==3)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=5;
	    	spnumofplayer=2;
	    	typeofgame=0;
	    	typeofgame++;
	    	timed=0;
	    				enter=4;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
	        	        	
	chg=new challengeGame(this);
}

public void CHgetvalue1()
{

	    
	    if(level==0)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=1;


			enter=1;
	    	timed=1;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
			
		else if(level==1)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=2;
	    	spnumofplayer=2;
	    	typeofgame=0;
	    	typeofgame++;
	    	timed=1;
	    				enter=2;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
	    else if(level==2)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=3;
	    	spnumofplayer=2;
	    	typeofgame=0;
	    	typeofgame++;
	    	timed=0;
	    				enter=3;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
	    else if(level==3)
	    {
	    	SPGameArena=currAre;
	    	SPGameArena++;
	    	SPGameSpeed=4;
	    	spnumofplayer=2;
	    	typeofgame=0;
	    	typeofgame++;
	    	timed=0;
	    				enter=4;
	    	SPSnakeColor=sm.sc.getSelectedIndex();
        	SPSnakeColor++;
	    }
	        	        	
	chg=new challengeGame(this);

}


public void SPgetvalue()
{
			SPGameArena=spmenu.sa.getSelectedIndex();
        	SPSnakeColor=spmenu.sc.getSelectedIndex();
        	SPGameSpeed=spmenu.ss.getValue();
        	spnumofplayer=spmenu.nup.getSelectedIndex();
        	typeofgame=spmenu.tg.getSelectedIndex();
        	timed=spmenu.tim.getSelectedIndex();
        	SPGameArena++;
        	spnumofplayer++;
        	SPSnakeColor++;
        	typeofgame++;
	
       		
	spg=new SinglePlayerGame(this);
}


public void show()
{
Display.getDisplay(this).setCurrent(mList);
}
public void showLG()
{
Display.getDisplay(this).setCurrent(lg.mList);
}

public void showCheat()
{
Display.getDisplay(this).setCurrent(Cheat.che);
}


public void showSM()
{
	sm=new stageMenu(this);
	 Display.getDisplay(this).setCurrent(sm.SPMForm);
}

public void showWinner()
{

	 Display.getDisplay(this).setCurrent(winER.help);
}


public void showSC()
{

	 Display.getDisplay(this).setCurrent(sclr.stage);
}

public void showSPGame()
{

	 Display.getDisplay(this).setCurrent(spg.mSnakeCanvas);
}

public void showCHGame()
{

	 Display.getDisplay(this).setCurrent(chg.mSnakeCanvas);
}




public void vibration(int sec)
{
	if(isVibrate==true)
	{
	Display.getDisplay(this).vibrate(50);
	}
}
public void backlight(int tim)
{
	if(isBacklight==true)
	{
	Display.getDisplay(this).flashBacklight(100);
	}
}



public void commandAction(Command c, Displayable s) 
{
if (c == mNextCommand || c == List.SELECT_COMMAND) {
int index = mList.getSelectedIndex();
if(index==0)
{
	spmenu=new SinglePlayerMenu(this);
Display.getDisplay(this).setCurrent(spmenu.SPMForm);	
}
else if(index==1)
{
showSM();	
}

else if(index==2)
{

Display.getDisplay(this).setCurrent(set.setting);	
}
else if(index==4)
{
Display.getDisplay(this).setCurrent(help1.help);	
}
else if(index==3)
{
	hscor=new HScore(this);
Display.getDisplay(this).setCurrent(hscor.mList);	
}

else if(index==5)
{
		try{
this.player.stop();
}catch(Exception sds){} 
notifyDestroyed();
}
}

else if (c == mExitCommand)
{
	try{
this.player.stop();
gs.stop();
gs.close();
}catch(Exception sds){}              

notifyDestroyed();
}
}

public void pauseApp() {}
public void destroyApp(boolean unconditional) {
	try{
	player.stop();
		player.close();
}catch(Exception sdf){}
	try{
	gs.stop();
	gs.close();
}catch(Exception sdf){}
	javaWise();}
	
	
	public void javaWise() 
	{ //shows the number of bytes used in the current run 
	Runtime rt = Runtime.getRuntime(); System.out.println(" Used: "+(rt.totalMemory()-rt.freeMemory())); 
}
   
public void openRecStore()
  {
    try
    {
      // The second parameter indicates that the record store
      // should be created if it does not exist
      rs = RecordStore.openRecordStore(SET_SAV, true );
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }    


 public void closeRecStore()
  {
    try
    {
      rs.closeRecordStore();
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }


 public void deleteRecStore()
  {
    if (RecordStore.listRecordStores() != null)
    {
      try
      {
        RecordStore.deleteRecordStore(SET_SAV);
      }
      catch (Exception e)
      {
      System.out.println(e.toString());
      }
    }      
  }

public void writeRecord(String str)
  {
    byte[] rec = str.getBytes();

    try
    {
      rs.addRecord(rec, 0, rec.length);
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }

 public void readRecords()
  {		String str;
    try
    {
      // Intentionally make this too small to test code below
      byte[] recData = new byte[5]; 
      int len;

      for (int i = 1; i <= rs.getNumRecords(); i++)      
      {
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];
       
        len = rs.getRecord(i, recData, 0);
        
        str=new String(recData, 0, len);
        
        MyPlayerName=str.substring(str.indexOf("~")+1,str.indexOf("^"));
		String s1=str.substring(str.indexOf("^")+1,str.indexOf("B"));
		if(Integer.parseInt(s1)==0)
		{
			isMusic=true;
		}
		else if(Integer.parseInt(s1)==1)
		{
			isMusic=false;
		}
		
		s1=str.substring(str.indexOf("B")+1,str.indexOf("C"));
		if(Integer.parseInt(s1)==0)
		{
			isVibrate=true;
		}
		else if(Integer.parseInt(s1)==1)
		{
			isVibrate=false;
		}
		
		s1=str.substring(str.indexOf("C")+1,str.indexOf("D"));
		if(Integer.parseInt(s1)==0)
		{
			isBacklight=true;
		}
		else if(Integer.parseInt(s1)==1)
		{
			isBacklight=false;
		}
		
      }
    }
    catch (Exception e)
    {
      System.out.println(e.toString());

    }

  }
   
                    
}

