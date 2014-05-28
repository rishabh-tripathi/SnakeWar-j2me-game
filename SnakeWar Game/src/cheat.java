import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.*;


public class cheat implements CommandListener 
{
public Form che;
public Command SaveCommand, BackCommand;

public TextField playername;
public Image sc1;
public ImageItem iicc;

private SnakeWar parent;

private RecordStore rs = null;
static final String UNLOCK = "unlock";


public cheat(SnakeWar parent) 
{
this.parent=parent;

che = new Form("Enter Cheat");
playername=new TextField("Enter Cheat Code :","SnakeWar",10,TextField.ANY);
try{
sc1 = Image.createImage("/omc.png");
}catch(Exception dss){}
iicc=new ImageItem("",sc1,0,"Powered By: OneMobileClick");
che.append(playername);
StringItem ss=new StringItem("Developed By :","Rishabh Tripathi");
che.append(ss);
che.append(iicc);

SaveCommand = new Command("Save", Command.SCREEN, 0);
BackCommand = new Command("Back", Command.SCREEN, 0);
che.addCommand(SaveCommand);
che.addCommand(BackCommand);
che.setCommandListener(this);
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


 public void deleteRecStore()
  {
    if (RecordStore.listRecordStores() != null)
    {
      try
      {
        RecordStore.deleteRecordStore(UNLOCK);
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
        else if(c==SaveCommand)
        {
        	if(playername.getString().equals("icanwin"))
        	{
        	deleteRecStore();
        	openRecStore();
        	writeRecord(String.valueOf(8));
        	closeRecStore();
        	parent.show();
        	}
        	else if(playername.getString().equals("iwin"))
        	{
        	parent.SCORE=100;
        	parent.show();
        	}
        	else if(playername.getString().equals("iwilltry"))
        	{
        	deleteRecStore();
        	openRecStore();
        	writeRecord(String.valueOf(2));
        	closeRecStore();
        	parent.show();
        	}
        }
}



}