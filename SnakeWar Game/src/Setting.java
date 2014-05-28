import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.*;


public class Setting implements CommandListener 
{
public Form setting;
public Command SaveCommand, BackCommand,cheatCommand;
public ChoiceGroup ve,ble,se;
public TextField playername;
public Image sc1;
public ImageItem iicc;
public boolean isVibrate,isMusic,isBacklight;
public String playerName;

private SnakeWar parent;

private RecordStore rs = null;
static final String SET_SAV = "settings";


public Setting(SnakeWar parent) 
{
this.parent=parent;
try{
sc1 = Image.createImage("/omc.png");
}catch(Exception dss){}
iicc=new ImageItem("",sc1,0,"Powered By: OneMobileClick");
setting = new Form("Settings");
playername=new TextField("Player Name",parent.MyPlayerName,10,TextField.ANY);

setting.append(playername);


se=new ChoiceGroup("Sound Effect",ChoiceGroup.EXCLUSIVE);
se.append("Yes",null);
se.append("No",null);
if(parent.isMusic==false)
{
se.setSelectedIndex(1,true);
}
setting.append(se);
ble=new ChoiceGroup("Backlight Effect",ChoiceGroup.EXCLUSIVE);
ble.append("Yes",null);
ble.append("No",null);
if(parent.isBacklight==false)
{
ble.setSelectedIndex(1,true);
}
setting.append(ble);
ve=new ChoiceGroup("Vibration",ChoiceGroup.EXCLUSIVE);
ve.append("Yes",null);
ve.append("No",null);
if(parent.isVibrate==false)
{
ve.setSelectedIndex(1,true);
}
setting.append(ve);
setting.append(iicc);

SaveCommand = new Command("Save", Command.SCREEN, 0);
BackCommand = new Command("Back", Command.SCREEN, 0);
cheatCommand = new Command("Cheat", Command.SCREEN, 0);
setting.addCommand(SaveCommand);
setting.addCommand(BackCommand);
setting.addCommand(cheatCommand);
setting.setCommandListener(this);
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



public void commandAction(Command c, Displayable s) 
{
	    if (c == BackCommand) {
            parent.show();
             }
             else if (c == cheatCommand) {
            parent.showCheat();
             }
        else if(c==SaveCommand)
        {
        	isMusic=se.isSelected(0);
        	isVibrate=ve.isSelected(0);
        	isBacklight=ble.isSelected(0);	
        	playerName=playername.getString();
        	if(playerName==null)
        	{
        		playerName="Player1";
        	}
        	
        	int vib,mus,bl;
        	if(isMusic==true)
        	{
        		mus=0;
        		parent.playMenuMusic();
        	}
        	else
        	{
        		mus=1;
        		try{
				parent.player.stop();
				}catch(Exception sdf){}
        	}
        	if(isVibrate==true)
        	{
        		vib=0;
        	}
        	else
        	{
        		vib=1;
        	}
        	if(isBacklight==true)
        	{
        		bl=0;
        	}
        	else
        	{
        		bl=1;
        	}
        	
        	String str="~"+playerName+"^"+String.valueOf(mus)+"B"+String.valueOf(vib)+"C"+String.valueOf(bl)+"D";
        	deleteRecStore();
        	openRecStore();   // Create the record store
    		 // Remove the record store 
    // Write a few records and read them back
    writeRecord(str);       
    closeRecStore();  // Close record store
   
        	parent.saveSetting();
        	parent.show();
        }
}



}