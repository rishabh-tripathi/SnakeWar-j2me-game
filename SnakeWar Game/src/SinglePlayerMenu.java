import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.*;

public class SinglePlayerMenu implements CommandListener 
{
public Form SPMForm;
public Command StartCommand, BackCommand;
public ChoiceGroup sa,sc,nup,tg,tim;
public Gauge ss;
private SnakeWar parent;
private int current;
public Image sc1;
public ImageItem iicc;

private RecordStore rs = null;
static final String UNLOCK = "unlock";


public SinglePlayerMenu(SnakeWar parent) 
{
this.parent=parent;
try{
sc1 = Image.createImage("/omc.png");
}catch(Exception dss){}
iicc=new ImageItem("",sc1,0,"Powered By: OneMobileClick");

openRecStore();
readRecords();
closeRecStore();
        	
SPMForm = new Form("Game Configuration");
sa=new ChoiceGroup("Select Arena",ChoiceGroup.POPUP);
sa.append("Country Side I",null);
sa.append("Country Side II",null);
sa.append("Tunnel I",null);
if(current==3)
{
	sa.append("Country Side III",null); //s4
}
else if(current==4)
{
	sa.append("Country Side III",null); //s4
	sa.append("Tunnel II",null);//s5
}
else if(current==5)
{
	sa.append("Country Side III",null); //s4
	sa.append("Tunnel II",null);//s5
	sa.append("Tunnel III",null);//s6
}
else if(current==6)
{
	sa.append("Country Side III",null); //s4
	sa.append("Tunnel II",null);//s5
	sa.append("Tunnel III",null);//s6
	sa.append("Maze",null);//s7
}
else if(current==7)
{
	sa.append("Country Side III",null); //s4
	sa.append("Tunnel II",null);//s5
	sa.append("Tunnel III",null);//s6
	sa.append("Maze",null);//s7
	sa.append("Over The Bridge",null);//s8
}
else if(current==8)
{
	sa.append("Country Side III",null); //s4
	sa.append("Tunnel II",null);//s5
	sa.append("Tunnel III",null);//s6
	sa.append("Maze",null);//s7
	sa.append("Over The Bridge",null);//s8
	sa.append("Final Battle",null);//9
}

SPMForm.append(sa);
sc=new ChoiceGroup("Select Color",ChoiceGroup.POPUP);
sc.append("Red",null);
sc.append("Green",null);
sc.append("Black",null);
sc.append("Blue",null);
SPMForm.append(sc);
nup=new ChoiceGroup("Number of players",ChoiceGroup.POPUP);
nup.append("2",null);
nup.append("3",null);
nup.append("4",null);
SPMForm.append(nup);

ss=new Gauge("Select Speed",true,5,3);
SPMForm.append(ss);

tg=new ChoiceGroup("Select difficulty",ChoiceGroup.EXCLUSIVE);
tg.append("Play to win",null);
tg.append("Survival",null);

tim=new ChoiceGroup("Timed game",ChoiceGroup.EXCLUSIVE);
tim.append("Yes",null);
tim.append("No",null);

SPMForm.append(tg);
SPMForm.append(tim);


SPMForm.append(iicc);
StartCommand = new Command("Launch", Command.SCREEN, 0);
BackCommand = new Command("Back", Command.SCREEN, 0);
SPMForm.addCommand(StartCommand);
SPMForm.addCommand(BackCommand);
SPMForm.setCommandListener(this);
}



public void commandAction(Command c, Displayable s) 
{
	    if (c == BackCommand) {
            parent.show();
             }
        else if(c==StartCommand)
        {
        		parent.SPgetvalue();
        	parent.showSPGame();
        	try{
	parent.player.stop();
	parent.player.close();
}catch(Exception sdf){}
        }
}

public void openRecStore()
  {
    try
    {
      // The second parameter indicates that the record store
      // should be created if it does not exist
      rs = RecordStore.openRecordStore(UNLOCK, true );
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
        current=Integer.parseInt(str);
		
      }
    }
    catch (Exception e)
    {
      System.out.println(e.toString());

    }

  }


}