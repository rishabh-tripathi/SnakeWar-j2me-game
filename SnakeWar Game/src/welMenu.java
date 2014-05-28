import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import java.util.*;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.game.*;


public class welMenu implements CommandListener 
{

 public welcome welCome;
 
  public Command mStartCommand;
	
	private SnakeWar parent;
	
	
	
	
	public welMenu(SnakeWar parent)
	{
		this.parent=parent;
		

		
		
    
		
		 if (welCome == null) {
    //  try {
        welCome = new welcome();
        mStartCommand = new Command("Menu", Command.SCREEN, 0);

        welCome.addCommand(mStartCommand);
        welCome.setCommandListener(this);
  //    }
    //  catch (IOException ioe) {
      //  System.out.println(ioe);
     // }
    }

	}
	
	public void commandAction(Command c, Displayable s) {
    if (c == mStartCommand) {
    	parent.show();
         }
   
  }


	
}