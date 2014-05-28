import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public class Help implements CommandListener 
{
public Form help;
public Command BackCommand;
public StringItem helpcont,hc2,hc3,hc4,hc5,hc6,hc7;
public Image sc2,sc3;
public ImageItem iicc1,iicc2;

private SnakeWar parent;
public Image sc1;
public ImageItem iicc;
public Help(SnakeWar parent) 
{
this.parent=parent;

help = new Form("Help");
try{
sc1 = Image.createImage("/omc.png");
sc2 = Image.createImage("/foodNote.png");
sc3 = Image.createImage("/rule.png");
}catch(Exception dss){}
iicc=new ImageItem("",sc1,0,"Powered By: OneMobileClick");
iicc1=new ImageItem("Food And Their Responce:",sc2,0,"Powered By: OneMobileClick");
iicc2=new ImageItem("Game Rules",sc3,0,"Powered By: OneMobileClick");

helpcont=new StringItem("The Game","\nThis game is based on Ancient War situation, in which fighters are fighting face to face and the one who survives will win.");
hc3=new StringItem("How to play","\nIn this game snakes are fighting in an arena in which they have to secure their life first by getting more and more points by eating the food which can be situated any where in the arena.\nAfter getting the highest point the next challenge is to eliminate the others. This can be done by hitting the other snakes(Refer Game Rules).");
hc2=new StringItem("Time Breaker","\nIf a player will eat the TimeBreaker. He will get the power to pause the other snakes for 10 seconds by pressing KEY 5 or MENU KEY. But the power is valid only if any collision does not take place.");
hc6=new StringItem("Timed Game","\nIf timed game is selected then after finishing the first 10 extra food there will be a counter displaying in upper right corner and if in that grace period a winner is not declared then the player having the highest point will be declared as the winner.");
hc7=new StringItem("Player Enterence","\nWhile playing championship player entrance concept is used to distinguish the difficulty level. In amateur level the user will enter in arena first and the second player will enter after 10 second and the third will enter 10 seconds after the second and same goes for the fourth player. But in semipro, pro and veteran level that player entrance will be 2nd, 3rd and 4th accordingly.");
hc4=new StringItem("Key Configration","\n Left Nevigation Key OR NUM 6 = Move Left.\n Right Nevigation Key OR NUM 4 = Move Right.\n Up Nevigation Key OR NUM 2 = Move Up.\n Down Nevigation Key OR NUM 8 = Move Down\nMenu Key OR Key 5 = TimeBreaker \n\n\n For complete help or assistance refer to the USER GUIDE or Log on to www.1mobileclick.com\n\n");
hc5=new StringItem("SnakeWar (Basic)","\nVersion 1.0\nDeveloped by \n Rishabh Tripathi\n");
help.append(helpcont);
help.append(hc3);
help.append(iicc1);
help.append(iicc2);
help.append(hc2);
help.append(hc6);
help.append(hc7);
help.append(hc4);
help.append(hc5);
help.append(iicc);

BackCommand = new Command("Back", Command.SCREEN, 0);
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