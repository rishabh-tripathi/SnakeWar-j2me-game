import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public class stageMenu implements CommandListener 
{
public Form SPMForm;
public Command StartCommand, BackCommand,LoadCommand;
public ChoiceGroup sa,sc;
private SnakeWar parent;
public Image sc1;
public ImageItem iicc;
public stageMenu(SnakeWar parent) 
{
this.parent=parent;
try{
sc1 = Image.createImage("/omc.png");
}catch(Exception dss){}
iicc=new ImageItem("",sc1,0,"Powered By: OneMobileClick");

SPMForm = new Form("Championship");
sa=new ChoiceGroup("Select Level",ChoiceGroup.EXCLUSIVE);
sa.append("Amateur",null);
sa.append("Semipro",null);
sa.append("Pro",null);
sa.append("Veteran",null);
SPMForm.append(sa);
sc=new ChoiceGroup("Select your Color",ChoiceGroup.POPUP);
sc.append("Red",null);
sc.append("Green",null);
sc.append("Black",null);
sc.append("Blue",null);
SPMForm.append(sc);
SPMForm.append(iicc);

StartCommand = new Command("Start", Command.SCREEN, 0);
BackCommand = new Command("Back", Command.SCREEN, 0);
LoadCommand = new Command("Load", Command.SCREEN, 0);
SPMForm.addCommand(StartCommand);
SPMForm.addCommand(BackCommand);
SPMForm.addCommand(LoadCommand);
SPMForm.setCommandListener(this);
}



public void commandAction(Command c, Displayable s) 
{
	    if (c == BackCommand) {
            parent.show();
             }
             else if (c == LoadCommand) {
            parent.showLG();
             }
        else if(c==StartCommand)
        {
        		parent.CHgetvalue();
        	parent.showCHGame();
        	try{
	parent.player.stop();
	parent.player.close();
}catch(Exception sdf){}
        }
}



}