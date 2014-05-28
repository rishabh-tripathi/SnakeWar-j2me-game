import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.*;
import javax.microedition.lcdui.ImageItem.*;


public class stageClear implements CommandListener 
{
public Form stage;
public Command nextCommand, quitCommand;
public saveGame savGam;
public StringItem sclear;
public int current;
public Image sc;
public ImageItem iicc;
private RecordStore rs = null;
static final String UNLOCK = "unlock";

private SnakeWar parent;

public stageClear(SnakeWar parent) 
{
this.parent=parent;
savGam=new saveGame();

try{
sc = Image.createImage("/stageClear.png");
}catch(Exception dss){}

stage = new Form("Game Setting");
iicc=new ImageItem("",sc,0,"     Stage Clear !!!\n\n\n\n\n");
sclear=new StringItem("","Press NEXT to continue");
stage.append(iicc);
stage.append(sclear);


nextCommand = new Command("NEXT", Command.SCREEN, 0);
quitCommand = new Command("Save & Quit", Command.SCREEN, 0);
stage.addCommand(nextCommand);
stage.addCommand(quitCommand);
stage.setCommandListener(this);
}



public void commandAction(Command c, Displayable s) 
{
	    if (c == quitCommand) {
	    	parent.currAre++;
	    	
	    	savGam.openRecStore();
        	savGam.writeRecord("*"+parent.MyPlayerName+"#"+String.valueOf(parent.level)+"!"+String.valueOf(parent.currAre)+"^");
        	savGam.closeRecStore();
	    	
        	
        	openRecStore();
        	readRecords();
        	        		writeRecord(String.valueOf(8));
        	if(parent.currAre>current)
        	{
        		closeRecStore();
        		deleteRecStore();
        		openRecStore();
        		writeRecord(String.valueOf(parent.currAre));
        	}
        	else
        	{	closeRecStore();
        		deleteRecStore();
        		openRecStore();
        		writeRecord(String.valueOf(2));
			}
        	closeRecStore();
        	
            parent.show();
             }
        else if(c==nextCommand)
        {	
        	if(parent.currAre!=8)
        	{
        	parent.currAre++;
        	parent.changeArena();
        	
        	openRecStore();
        	readRecords();
        	if(parent.currAre>current)
        	{
        		closeRecStore();
        		deleteRecStore();
        		openRecStore();
        		writeRecord(String.valueOf(parent.currAre));
        	}
        	closeRecStore();
        	parent.showCHGame();
        	}
        	else
        	{
        		parent.showWinner();
        	}
        	
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

 public void readRecords()
  {		String str;
    try
    {
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