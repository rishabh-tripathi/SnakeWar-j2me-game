import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.*;


public class HScore implements CommandListener 
{
public Command  Back,del;
public highScore sg;
private SnakeWar parent;
public List mList;

public HScore(SnakeWar parent) 
{
this.parent=parent;
sg=new highScore();

mList = new List("High Score", List.IMPLICIT);
Back = new Command("Back", Command.EXIT, 0);
del = new Command("Clear All", Command.SCREEN, 0);

update();

mList.addCommand(Back);

mList.setCommandListener(this);
}

public void update()
{
try
{
sg.openRecStore();
sg.readRecords();
String str;
if(sg.high[0]!=null)
{
	for(int i=0;i<11;i++)
	{
		String sst=sg.high[i].substring(sg.high[i].indexOf("*")+1,sg.high[i].indexOf("~"))+"   :   "+sg.high[i].substring(sg.high[i].indexOf("~")+1,sg.high[i].indexOf("`"));
		mList.append(sst,null);
		mList.addCommand(del);
	}
}
else
{
			mList.append("No High Score ???",null);
}

sg.closeRecStore();  // Close record stor
}catch(Exception sd)
{
			
}

}

public void commandAction(Command c, Displayable s) 
{
	    if (c == Back) {
            parent.show();
             }
        else if (c == del) {
        	sg.closeRecStore();
            sg.deleteRecStore();
            parent.show();
             }
             
		
}

}