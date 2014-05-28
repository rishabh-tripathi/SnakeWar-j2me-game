import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.midlet.MIDlet;
import java.util.*;


class welcome extends GameCanvas 
{

public Image wel;

public welcome()
{
super(true);
	Graphics g = getGraphics();
	try{
	wel = Image.createImage("/welcome.png");
	}catch(Exception ed){}
			g.setColor(0,0,0);
			g.fillRect(0,0,400,500);
			g.drawImage(wel,0,0,0);

}


}