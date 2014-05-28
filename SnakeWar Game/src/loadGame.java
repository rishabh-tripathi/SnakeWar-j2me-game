import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.*;


public class loadGame implements CommandListener 
{
public Command load, Back,clear;
public saveGame sg;
private SnakeWar parent;
public List mList;


public loadGame(SnakeWar parent) 
{
this.parent=parent;
sg=new saveGame();

mList = new List("Load Game", List.IMPLICIT);
Back = new Command("Back", Command.EXIT, 0);
load = new Command("Load", Command.SCREEN, 0);
clear = new Command("Delete All", Command.SCREEN, 0);
sg.openRecStore();
sg.readRecords();
String str;
if(sg.gameData[0]!=null)
{
	for(int i=0;i<sg.index;i++)
	{
		mList.append("Save Game "+String.valueOf(i+1),null);
		mList.addCommand(load);
				mList.addCommand(clear);
	}
}
else
{
			mList.append("No saved game !!",null);
}





sg.closeRecStore();  // Close record store
mList.addCommand(Back);
mList.setCommandListener(this);
}



public void commandAction(Command c, Displayable s) 
{
	    if (c == Back) {
            parent.showSM();
             }
             
			else if (c == load || c == List.SELECT_COMMAND) {
			int index = mList.getSelectedIndex();
			String ss,le;
			ss=sg.gameData[index];
			        le=ss.substring(ss.indexOf("#")+1,ss.indexOf("!"));			    
			        int level=Integer.parseInt(le);
			        le=ss.substring(ss.indexOf("!")+1,ss.indexOf("^"));
			        int cur=Integer.parseInt(le);
			parent.currAre=cur;
			parent.level=level;
			parent.CHgetvalue1();
        	parent.showCHGame();
			try{
	parent.player.stop();
}catch(Exception sdf){}        
			}
			else if(c==clear)
			{
				sg.closeRecStore();
				sg.deleteRecStore();
				parent.showSM();
			}

}

}