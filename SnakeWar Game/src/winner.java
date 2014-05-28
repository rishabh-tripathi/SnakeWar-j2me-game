import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public class winner implements CommandListener 
{
public Form help;
public Command BackCommand;
public StringItem helpcont;

private SnakeWar parent;
public Image sc1;
public ImageItem iicc;

public winner(SnakeWar parent) 
{
this.parent=parent;

help = new Form("Winner !!!");
try{
sc1 = Image.createImage("/win.png");
}catch(Exception dss){}
iicc=new ImageItem("",sc1,0,"Winner");
help.append(iicc);
helpcont=new StringItem("Congratulation !!!","Developed By : Rishabh Tripathi \n\n Powered by : www.1mobileclick.com");
help.append(helpcont);

BackCommand = new Command("End", Command.SCREEN, 0);
help.addCommand(BackCommand);
help.setCommandListener(this);
}



public void commandAction(Command c, Displayable s) 
{
	    if (c == BackCommand) {
            parent.show();
             }
       
}



}