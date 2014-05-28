import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.midlet.MIDlet;
import java.util.*;


class SinglePlayerCanvas extends GameCanvas implements Runnable
{

SnakeWar parent;
	public TiledLayer Background,Arena;
	public LayerManager mLayerManager;

	String imagename="/village.png";
	String imagename1="/town.png";

	Image backgroundImage,arenaImage;
public highScore hisc;

public boolean playing;

public boolean p2bypass=false,p2bypass1=false;
public boolean p3bypass=false,p3bypass1=false;
public boolean p4bypass=false,p4bypass1=false;

public int p2pDir=0;
public int p3pDir=0;
public int p4pDir=0;

public int p1S=2;
public int p2S=2;
public int p3S=2;
public int p4S=2;
//***************************************Game Variables**************************************************

///////////////////////////////////////////////

//Number of player
public int noPlayer;

//setting variable
public boolean isSound,isVibrate,isBackLight;

//Arena selected
public int whichArena;

public int again=800,gameOver=1000;

///////////////////////////////////////////////

//Players Positions
public int p1X=0;
public int p1Y=0;

public int p2X=0;
public int p2Y=0;

public int p3X=0;
public int p3Y=0;

public int p4X=0;
public int p4Y=0;

public int p1iniX=0;
public int p1iniY=0;

public int p2iniX=0;
public int p2iniY=0;

public int p3iniX=0;
public int p3iniY=0;

public int p4iniX=0;
public int p4iniY=0;

///////////////////////////////////////////////

//current direction;
public int p1Dir=3;
public int p2Dir=1;
public int p3Dir=2;
public int p4Dir=4;

public int p1iniDir,p2iniDir,p3iniDir,p4iniDir;

//////////////////////////////////////////////

//Motion Speed
public int speed=2;

/////////////////////////////////////////////

// Players score
public int p1Scr=5;
public int p2Scr=5;
public int p3Scr=5;
public int p4Scr=5;

////////////////////////////////////////////

// players color
public int p1Color=0;
public int p2Color=0;
public int p3Color=0;
public int p4Color=0;

public static int up=1;
public static int down=2;
public static int left=4;
public static int right=3;


//Food 
public int foodCounter=25;
public int speedUpCounter=5;
public int speedDownCounter=5;
public int megaPowerCounter=2;
public int poisonCounter=2;



////////////////////////////////////////////

//Food Position
public int foodX,foodY,surX,surY,timgam;

///////////////////////////////////////////

// players creation variables
public Image p1_head,p2_head,p3_head,p4_head,sudo_head;
public Sprite p11,p21,p31,p41,p21s,p31s,p41s;


public Image p1_body,p2_body,p3_body,p4_body;
public Image p1_tail,p2_tail,p3_tail,p4_tail;
public Sprite p12,p22,p32,p42;
public Sprite p13,p23,p33,p43;

///////////////////////////////////////////////

// score panel variable
public Image red_ball,green_ball,blue_ball,black_ball,blueFood_ball,dtime;

// food image variable
public Image food_ball,speedUp_ball,speedDown_ball,megaPower_ball,poison_ball;
public Sprite food,speedUp,speedDown,megaPower,poison;

public static int SnakeSpeed,nump,type;

public int surCount;
public boolean surIsDisp=false;
//*******************************************************************************************************

public Image youwon1,youloose1,pstp1;
public Sprite youwon,youloose,pstp;


public SinglePlayerCanvas(int whichArena,int whichColor,int noofplayer,int speed,int typgam,int timed,SnakeWar parent) throws IOException 
{
super(true);
this.parent=parent;
Graphics g = getGraphics();
mLayerManager = new LayerManager();
nump=noofplayer;
type=typgam;
timgam=timed;

if(type==2)
{
	p2Scr=15;
	p3Scr=15;
	p4Scr=15;
}

p1Scr=parent.SCORE;
hisc=new highScore();

creatArena ca=new creatArena();

ca.creatArena(whichArena);
Background=ca.Background;
Arena=ca.Arena;

SnakeSpeed=6-speed;
gettingStuff();
pstp.setPosition(70,100);
mLayerManager.append(Background);

mLayerManager.insert(Arena,0);
mLayerManager.insert(pstp,0);
mLayerManager.paint(g, 0, 0);
flushGraphics();
getFood();
placeFood();
getOtherSnakeColor(whichColor);
noofplayer++;
placePlayers(noofplayer);
placeScore();

}


 public void start() {
  		mLayerManager.remove(pstp);
    playing = true;
    Thread t = new Thread(this);
    t.start();
  }




 public void stop() {
    playing = false;
  }
  
  
  
  
  // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

 public void run() {
 	Graphics g = getGraphics();

while (playing) {
      if (isShown()) {
      	
      	if(p1X>0 && p1X<240 && p1Y>10 && p1Y<320)
      	{
      		if(p1Scr>0)
      		{
        int keyStates = getKeyStates();
        
        if ((keyStates & LEFT_PRESSED) != 0) {
  			if(p1Dir==up)
  			{
  	p11.setTransform(Sprite.TRANS_ROT180);
	p12.setTransform(Sprite.TRANS_ROT180);
	p13.setTransform(Sprite.TRANS_ROT180);
  			p1Dir=left;
  			}
  			else if(p1Dir==down)
  			{
  	p11.setTransform(Sprite.TRANS_ROT180);
	p12.setTransform(Sprite.TRANS_ROT180);
	p13.setTransform(Sprite.TRANS_ROT180);
  			p1Dir=left;
  			}		
        }
        
         
        else if ((keyStates & RIGHT_PRESSED) != 0) {
         	if(p1Dir==down)
  			{
  	p11.setTransform(Sprite.TRANS_NONE);
	p12.setTransform(Sprite.TRANS_NONE);
	p13.setTransform(Sprite.TRANS_NONE);
  			p1Dir=right;
  			}
  			else if(p1Dir==up)
  			{
  	p11.setTransform(Sprite.TRANS_NONE);
	p12.setTransform(Sprite.TRANS_NONE);
	p13.setTransform(Sprite.TRANS_NONE);
  			p1Dir=right;
  			}		
         }
        
        
        
        
        
        else if((keyStates & UP_PRESSED) != 0){
        if(p1Dir==right)
        {
    p11.setTransform(Sprite.TRANS_ROT270);
	p12.setTransform(Sprite.TRANS_ROT270);
	p13.setTransform(Sprite.TRANS_ROT270);	
        p1Dir=up;
        }
        else if(p1Dir==left)
        {
     p11.setTransform(Sprite.TRANS_ROT270);
	p12.setTransform(Sprite.TRANS_ROT270);
	p13.setTransform(Sprite.TRANS_ROT270);	    
        p1Dir=up;
        }
         
        }
		
		
		
		
		else if((keyStates & DOWN_PRESSED) != 0){
       if(p1Dir==right)
        {
   	p11.setTransform(Sprite.TRANS_ROT90);
	p12.setTransform(Sprite.TRANS_ROT90);
	p13.setTransform(Sprite.TRANS_ROT90);	
        p1Dir=down;
        }
        else if(p1Dir==left)
        {
     p11.setTransform(Sprite.TRANS_ROT90);
	p12.setTransform(Sprite.TRANS_ROT90);
	p13.setTransform(Sprite.TRANS_ROT90);	    
        p1Dir=down;
        }
        }	
		

        
		else {
			 
        
		

        }
	}
   }     
 }
 
 outOfScreen();



if(type==1)
{
	if(foodCounter>0)
	{
		artifitialInti(2,1);
		if(nump==2)
		{
		artifitialInti(3,1);     
		}
		else if(nump==3)
		{
 		artifitialInti(3,1);     
 		artifitialInti(4,1);
		}
		else
		{
		}
	}
	else
	{
		artifitialInti(2,2);
		if(nump==2)
		{
		artifitialInti(3,2);     
		}
		else if(nump==3)
		{
 		artifitialInti(3,2);     
 		artifitialInti(4,2);
		}
		else
		{
		}
	}
}
else if(type==2)
{
	artifitialInti(2,2);
	if(nump==2)
	{
	artifitialInti(3,2);     
	}
	else if(nump==3)
	{
	 artifitialInti(3,2);     
	 artifitialInti(4,2);
	}
	else
	{
	}
}





 

collisionDetectorP1();
if(nump==1)
{
	collisionDetectorP2();
} 
else if(nump==2)
{
	collisionDetectorP2();
	collisionDetectorP3();
}
else if(nump==3)
{
	collisionDetectorP2();
	collisionDetectorP3();
	collisionDetectorP4();
}
else
{
	System.out.println("Error in Detection of collision");
}




if(nump==1)
{
 if(p1Scr>0)
 {
 			movingHead(1);
			movingBody(1);
			movingTail(1);
 } 
 else 
 {
 	p1X=-500;
 	p1Y=-500;
p11.setPosition(p1X,p1Y);
p12.setPosition(p1X,p1Y);
p13.setPosition(p1X,p1Y); 
}

 if(p2Scr>0)
 {
			movingHead(2);
			movingBody(2);
			movingTail(2);
 }
 else
 {
 	p2X=-500;
 	p2Y=-500;
 	p21.setPosition(p2X,p2Y);
 	p21s.setPosition(p2X,p2Y);
p22.setPosition(p2X,p2Y);
p23.setPosition(p2X,p2Y); 
 } 	
}

else if(nump==2)
{
 if(p1Scr>0)
 {
 			movingHead(1);
			movingBody(1);
			movingTail(1);
 }
 else 
 {
 	 	p1X=-500;
 	p1Y=-500;
p11.setPosition(p1X,p1Y);
p12.setPosition(p1X,p1Y);
p13.setPosition(p1X,p1Y); 	
 }


 if(p2Scr>0)
 {
			movingHead(2);
			movingBody(2);
			movingTail(2);
 }
 else
 {
 	p2X=-500;
 	p2Y=-500;
 	p21.setPosition(p2X,p2Y);
 	p21s.setPosition(p2X,p2Y);
p22.setPosition(p2X,p2Y);
p23.setPosition(p2X,p2Y); 

 }

 if(p3Scr>0)
 {
			movingHead(3);
			movingBody(3);
			movingTail(3);
 }
 else
 {
 	p3X=-500;
 	p3Y=-500;
 	p31.setPosition(p3X,p3Y);
 	p31s.setPosition(p3X,p3Y);
p32.setPosition(p3X,p3Y);
p33.setPosition(p3X,p3Y); 
 }
}
 
 
else if(nump==3)
{
 if(p1Scr>0)
 {
 			movingHead(1);
			movingBody(1);
			movingTail(1);
 }
 else 
 {
 	 	p1X=-500;
 	p1Y=-500;
 	p11.setPosition(p1X,p1Y);
p12.setPosition(p1X,p1Y);
p13.setPosition(p1X,p1Y); 		        
 }


 if(p2Scr>0)
 {
			movingHead(2);
			movingBody(2);
			movingTail(2);
 }
 else
 {
 	p2X=-500;
 	p2Y=-500;
 	p21.setPosition(p2X,p2Y);
 	p21s.setPosition(p2X,p2Y);
p22.setPosition(p2X,p2Y);
p23.setPosition(p2X,p2Y); 
 }

 if(p3Scr>0)
 {
			movingHead(3);
			movingBody(3);
			movingTail(3);
 }
 else
 {
 	p3X=-500;
 	p3Y=-500;
 	p31.setPosition(p3X,p3Y);
 	p31s.setPosition(p3X,p3Y);
p32.setPosition(p3X,p3Y);
p33.setPosition(p3X,p3Y); 
 }

 if(p4Scr>0)
 {
			movingHead(4);
			movingBody(4);
			movingTail(4);
 }
 else
 {
 	p4X=-500;
 	p4Y=-500;
 	p41.setPosition(p4X,p4Y);
 	p41s.setPosition(p4X,p4Y);
p42.setPosition(p4X,p4Y);
p43.setPosition(p4X,p4Y); 
 }
}
else 
{
	System.out.println("rishabh error");
}
 


		placeScore();
		if(surCount<=0 && surIsDisp==true)
		{
			surX=500;
			surY=500;
			moveSur();
			surIsDisp=false;
		}
		
         mLayerManager.paint(g, 0, 0); 
         if(surIsDisp==true)
         {
         	surCount--;
         }
        try{
        Thread.sleep(10*SnakeSpeed);
    }catch(Exception ds){System.out.println(ds);}
    flushGraphics();

if(nump==1)
{
	if(p1Scr<=0 && p2Scr>0)
	{
		playing=false; //p2 win
		showWinner(2);

		try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
	}
	else if(p1Scr>0 && p2Scr<=0)
	{
		playing=false; //p1 win
		
		hisc.openRecStore();
		hisc.writeRecord("*"+parent.MyPlayerName+"~"+String.valueOf(p1Scr)+"`");
		hisc.closeRecStore();
		
		showWinner(1);
		try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
	}
	else
	{
	}
}
else if(nump==2)
{
	if(p1Scr<=0)
	{
		playing=false;
		System.out.println("You Loose");
		showWinner(2);
		try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
	}
	else if(p1Scr>0 && p2Scr<=0 && p3Scr<=0)
	{
			playing=false;
		
		hisc.openRecStore();
		hisc.writeRecord("*"+parent.MyPlayerName+"~"+String.valueOf(p1Scr)+"`");
		hisc.closeRecStore();
		
		showWinner(1);
		try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
	}
	else
	{
	}
}
else if(nump==3)
{
	if(p1Scr<=0)
	{
		playing=false;

		showWinner(2);
	try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
	}
	else if(p1Scr>0 && p2Scr<=0 && p3Scr<=0 && p4Scr<=0)
	{
			playing=false;
		hisc.openRecStore();
		hisc.writeRecord("*"+parent.MyPlayerName+"~"+String.valueOf(p1Scr)+"`");
		hisc.closeRecStore();
		
		showWinner(1);
		try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
	}
	else
	{
	}
}



if(foodCounter<=0)
{	again--;

	if(again==0)
	{
		foodCounter=10;
		placeFood();
	}
}


if(again<0)
{
	if(timgam==0)
	{
	gameOver--;
	if(gameOver<=0)
	{
		if(nump==1)
		{
			if(p1Scr>p2Scr)
			{
				playing=false;
				hisc.openRecStore();
		hisc.writeRecord("*"+parent.MyPlayerName+"~"+String.valueOf(p1Scr)+"`");
		hisc.closeRecStore();
		
				showWinner(1);
				try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();	
			}
			else if(p2Scr>p1Scr)
			{
				playing=false;
				showWinner(2);	
				try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
			}
			else
			{
				again=2;
			}
		}
		else if(nump==2)
		{
			if(p1Scr>p2Scr && p1Scr>p3Scr)
			{
				playing=false;
				hisc.openRecStore();
		hisc.writeRecord("*"+parent.MyPlayerName+"~"+String.valueOf(p1Scr)+"`");
		hisc.closeRecStore();
		
				showWinner(1);	
				try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
			}
			else if(p2Scr>p1Scr || p3Scr>p1Scr)
			{
				playing=false;
				showWinner(2);	
				try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
			}
			else
			{
				again=2;
			}
		}
		else if(nump==3)
		{
			if(p1Scr>p2Scr && p1Scr>p3Scr && p1Scr>p4Scr)
			{
				playing=false;
				hisc.openRecStore();
		hisc.writeRecord("*"+parent.MyPlayerName+"~"+String.valueOf(p1Scr)+"`");
		hisc.closeRecStore();
		
				showWinner(1);	
				try{ Thread.sleep(3000); }catch(Exception ds){}
				parent.show();
			}
			else if(p2Scr>p1Scr || p3Scr>p1Scr || p4Scr>p1Scr)
			{
				playing=false;
				showWinner(2);	
				try{ Thread.sleep(3000); }catch(Exception ds){}
		parent.show();
			}
			else
			{
				again=2;
			}
		}
	}
	}
	else
	{
		again=500;
	}	
}

}
}
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$



public void showWinner(int in)
{
	Graphics gs = getGraphics();
	if(in==1)
	{
		youwon.setPosition(40,100);
			mLayerManager.insert(youwon,0);
	mLayerManager.paint(gs, 0, 0);
	flushGraphics();
	}
	else if(in==2)
	{
		youloose.setPosition(40,100);
			mLayerManager.insert(youloose,0);
	mLayerManager.paint(gs, 0, 0);
	flushGraphics();
	}
}


//place score panel
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void placeScore()
{
	Graphics g = getGraphics();
	g.setColor(0,0,0);
	g.fillRect(0,0,240,10);
		g.drawRect(0,10,240,310);
		g.fillRect(240,0,40,360);
		g.fillRect(0,320,280,40);
try
{
red_ball = Image.createImage("/red_ball.png");
green_ball = Image.createImage("/green_ball.png");
blue_ball = Image.createImage("/blue_ball.png");
black_ball = Image.createImage("/black_ball.png");
blueFood_ball = Image.createImage("/blueFood_ball.png");
dtime = Image.createImage("/countdown.png");
}catch(Exception ds){}

		
if(nump==1)
{
	if(p1Color==1)
	{
		g.drawImage(red_ball,40,0,0);
	}
	else if(p1Color==2)
	{
		g.drawImage(green_ball,40,0,0);
	}
	else if(p1Color==4)
	{
		g.drawImage(blue_ball,40,0,0);
	}
	else if(p1Color==3)
	{
		g.drawImage(black_ball,40,0,0);
	}
	

	
	if(p2Color==1)
	{
		g.drawImage(red_ball,85,0,0);
	}
	else if(p2Color==2)
	{
		g.drawImage(green_ball,85,0,0);
	}
	else if(p2Color==4)
	{
		g.drawImage(blue_ball,85,0,0);
	}
	else if(p2Color==3)
	{
		g.drawImage(black_ball,85,0,0);
	}
	
	if(p1Scr<p2Scr)
	{
		g.setColor(255,0,0);
		g.drawString(String.valueOf(p1Scr),40+15,-3,0);
		g.setColor(0,255,0);
		g.drawString(String.valueOf(p2Scr),85+15,-3,0);	
	}
	else if(p2Scr<p1Scr)
	{
		g.setColor(0,255,0);
		g.drawString(String.valueOf(p1Scr),40+15,-3,0);
		g.setColor(255,0,0);
		g.drawString(String.valueOf(p2Scr),85+15,-3,0);	
	}
	else
	{
		g.setColor(255,255,255);
		g.drawString(String.valueOf(p1Scr),40+15,-3,0);
		g.drawString(String.valueOf(p2Scr),85+15,-3,0);	
	}

	

}

else if(nump==2)
{
		g.setColor(255,255,255);
	if(p1Color==1)
	{
		g.drawImage(red_ball,20,0,0);
	}
	else if(p1Color==2)
	{
		g.drawImage(green_ball,20,0,0);
	}
	else if(p1Color==4)
	{
		g.drawImage(blue_ball,20,0,0);
	}
	else if(p1Color==3)
	{
		g.drawImage(black_ball,20,0,0);
	}
	
	
	if(p1Scr<p2Scr && p1Scr<p3Scr)
	{
		g.setColor(255,0,0);
	g.drawString(String.valueOf(p1Scr),20+15,-3,0);
	}
	else if(p1Scr>p2Scr && p1Scr>p3Scr)
	{
		g.setColor(0,255,0);
	g.drawString(String.valueOf(p1Scr),20+15,-3,0);
	}
	else
	{
		g.setColor(255,255,255);
	g.drawString(String.valueOf(p1Scr),20+15,-3,0);
	}
	
	
	
	if(p2Color==1)
	{
		g.drawImage(red_ball,65,0,0);
	}
	else if(p2Color==2)
	{
		g.drawImage(green_ball,65,0,0);
	}
	else if(p2Color==4)
	{
		g.drawImage(blue_ball,65,0,0);
	}
	else if(p2Color==3)
	{
		g.drawImage(black_ball,65,0,0);
	}
	
	if(p2Scr<p1Scr && p2Scr<p3Scr)
	{
		g.setColor(255,0,0);
	g.drawString(String.valueOf(p2Scr),65+15,-3,0);
	}
	else if(p2Scr>p1Scr && p2Scr>p3Scr)
	{
		g.setColor(0,255,0);
	g.drawString(String.valueOf(p2Scr),65+15,-3,0);
	}
	else
	{
		g.setColor(255,255,255);
	g.drawString(String.valueOf(p2Scr),65+15,-3,0);
	}
	
	
	
	if(p3Color==1)
	{
		g.drawImage(red_ball,110,0,0);
	}
	else if(p3Color==2)
	{
		g.drawImage(green_ball,110,0,0);
	}
	else if(p3Color==4)
	{
		g.drawImage(blue_ball,110,0,0);
	}
	else if(p3Color==3)
	{
		g.drawImage(black_ball,110,0,0);
	}
	
	if(p3Scr<p1Scr && p3Scr<p2Scr)
	{
		g.setColor(255,0,0);
	g.drawString(String.valueOf(p3Scr),110+15,-3,0);
	}
	else if(p3Scr>p1Scr && p3Scr>p2Scr)
	{
		g.setColor(0,255,0);
	g.drawString(String.valueOf(p3Scr),110+15,-3,0);
	}
	else
	{
		g.setColor(255,255,255);
	g.drawString(String.valueOf(p3Scr),110+15,-3,0);
	}
	
}


else if(nump==3)
{
			g.setColor(255,255,255);
	if(p1Color==1)
	{
		g.drawImage(red_ball,0,0,0);
	}
	else if(p1Color==2)
	{
		g.drawImage(green_ball,0,0,0);
	}
	else if(p1Color==4)
	{
		g.drawImage(blue_ball,0,0,0);
	}
	else if(p1Color==3)
	{
		g.drawImage(black_ball,0,0,0);
	}
	
	if(p1Scr<p2Scr && p1Scr<p3Scr && p1Scr<p4Scr)
	{
		g.setColor(255,0,0);
	g.drawString(String.valueOf(p1Scr),15,-3,0);
	}
	else if(p1Scr>p2Scr && p1Scr>p3Scr && p1Scr>p4Scr)
	{
		g.setColor(0,255,0);
	g.drawString(String.valueOf(p1Scr),15,-3,0);
	}
	else
	{
		g.setColor(255,255,255);
	g.drawString(String.valueOf(p1Scr),15,-3,0);
	}

	
	
	
	if(p2Color==1)
	{
		g.drawImage(red_ball,40,0,0);
	}
	else if(p2Color==2)
	{
		g.drawImage(green_ball,40,0,0);
	}
	else if(p2Color==4)
	{
		g.drawImage(blue_ball,40,0,0);
	}
	else if(p2Color==3)
	{
		g.drawImage(black_ball,40,0,0);
	}
	

	if(p2Scr<p3Scr && p2Scr<p4Scr && p2Scr<p1Scr)
	{
		g.setColor(255,0,0);
	g.drawString(String.valueOf(p2Scr),40+15,-3,0);
	}
	else if(p2Scr>p3Scr && p2Scr>p4Scr && p2Scr>p1Scr)
	{
		g.setColor(0,255,0);
	g.drawString(String.valueOf(p2Scr),40+15,-3,0);
	}
	else
	{
		g.setColor(255,255,255);
	g.drawString(String.valueOf(p2Scr),40+15,-3,0);
	}


	
	
	if(p3Color==1)
	{
		g.drawImage(red_ball,85,0,0);
	}
	else if(p3Color==2)
	{
		g.drawImage(green_ball,85,0,0);
	}
	else if(p3Color==4)
	{
		g.drawImage(blue_ball,85,0,0);
	}
	else if(p3Color==3)
	{
		g.drawImage(black_ball,85,0,0);
	}
	
	
	
	if(p3Scr<p4Scr && p3Scr<p1Scr && p3Scr<p2Scr)
	{
		g.setColor(255,0,0);
	g.drawString(String.valueOf(p3Scr),85+15,-3,0);
	}
	else if(p3Scr>p4Scr && p3Scr>p1Scr && p3Scr>p2Scr)
	{
		g.setColor(0,255,0);
	g.drawString(String.valueOf(p3Scr),85+15,-3,0);
	}
	else
	{
		g.setColor(255,255,255);
	g.drawString(String.valueOf(p3Scr),85+15,-3,0);
	}
	
	
	
	if(p4Color==1)
	{
		g.drawImage(red_ball,130,0,0);
	}
	else if(p4Color==2)
	{
		g.drawImage(green_ball,130,0,0);
	}
	else if(p4Color==4)
	{
		g.drawImage(blue_ball,130,0,0);
	}
	else if(p4Color==3)
	{
		g.drawImage(black_ball,130,0,0);
	}
	
	if(p4Scr<p1Scr && p4Scr<p2Scr && p4Scr<p3Scr)
	{
		g.setColor(255,0,0);
	g.drawString(String.valueOf(p4Scr),130+15,-3,0);
	}
	else if(p4Scr>p1Scr && p4Scr>p2Scr && p4Scr>p3Scr)
	{
		g.setColor(0,255,0);
	g.drawString(String.valueOf(p4Scr),130+15,-3,0);
	}
	else
	{
		g.setColor(255,255,255);
	g.drawString(String.valueOf(p4Scr),130+15,-3,0);
	}
	


}
		if(foodCounter>=0)
		{
		g.drawImage(blueFood_ball,178,0,0);
		g.setColor(255,255,255);
		g.drawString(String.valueOf(foodCounter+1),178+15,-3,0);
		}
		else if(again<0)
		{
			if((gameOver%10)==0)
			{
			g.drawImage(dtime,178,0,0);
			}
		g.setColor(255,255,255);
		g.drawString(String.valueOf(gameOver),178+15,-3,0);
		
			
		}
}




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



public void getFood()
{
		try
		{
			food_ball=Image.createImage("/food.png");
			food = new Sprite(food_ball);
			
			speedUp_ball=Image.createImage("/speedUp.png");
			speedUp = new Sprite(speedUp_ball);
			
			speedDown_ball=Image.createImage("/speedDown.png");
			speedDown = new Sprite(speedDown_ball);
			
			megaPower_ball=Image.createImage("/megaPower.png");
			megaPower = new Sprite(megaPower_ball);
			
			poison_ball=Image.createImage("/poison.png");
			poison = new Sprite(poison_ball);
			
		}catch(Exception eds){System.out.println("Error in initialization of food");}
}



// place food 
public void placeFood()
{Graphics g = getGraphics();
	if(foodCounter>0)
	{
	foodX=getFoodX();
	foodY=getFoodY();
	food.setPosition(foodX,foodY);
	mLayerManager.insert(food,0);
	mLayerManager.paint(g, 0, 0);
if(food.collidesWith(Arena,true)==true)
        {
  placeFood();
        }
        
        placeSur();
if(speedUp.collidesWith(Arena,true)==true || speedDown.collidesWith(Arena,true)==true || poison.collidesWith(Arena,true)==true || megaPower.collidesWith(Arena,true)==true)
        {
  placeSur();
        }
else
{
}        


if(speedUp.collidesWith(food,true)==true || speedDown.collidesWith(food,true)==true || poison.collidesWith(food,true)==true || megaPower.collidesWith(food,true)==true)
        {
  placeSur();
        }
else
{
}        
        
        
	}
	else 
	{
		foodX=-400;
		foodY=-400;
		food.setPosition(foodX,foodY);
	}
}

public void placeSur()
{
if(surIsDisp==false)
{
	surCount=400;
	Graphics g=getGraphics();
       int rand=getRandSur();
       surX=getFoodX();
        surY=getFoodY();
        if(rand==11)
        {
        	if(speedUpCounter>0)
        	{
        		speedUp.setPosition(surX,surY);
        		mLayerManager.insert(speedUp,0);
        			mLayerManager.paint(g, 0, 0);
        			surIsDisp=true;
        	}
        }
        else if(rand==14)
        {
        	if(speedDownCounter>0)
        	{
        		speedDown.setPosition(surX,surY);
        		mLayerManager.insert(speedDown,0);
        			mLayerManager.paint(g, 0, 0);
        			surIsDisp=true;
        	}
        }
        else if(rand==15)
        {
        	if(poisonCounter>0)
        	{
        		poison.setPosition(surX,surY);
        		mLayerManager.insert(poison,0);
        			mLayerManager.paint(g, 0, 0);
        			surIsDisp=true;
        	}
        }
        else if(rand==19)
        {
        	if(megaPowerCounter>0)
        	{
        		megaPower.setPosition(surX,surY);
        		mLayerManager.insert(megaPower,0);
        			mLayerManager.paint(g, 0, 0);
        			surIsDisp=true;
        	}
        }
        else
        {
        }
}
}

public void moveSur()
{
	Graphics g=getGraphics();
	speedUp.setPosition(surX,surY);
	speedDown.setPosition(surX,surY);
	poison.setPosition(surX,surY);
	megaPower.setPosition(surX,surY);
	mLayerManager.insert(speedUp,0);
	mLayerManager.insert(speedDown,0);
	mLayerManager.insert(poison,0);
	mLayerManager.insert(megaPower,0);
        			mLayerManager.paint(g, 0, 0);
}

public int getFoodX()
{
	Random generatorX = new Random();
	generatorX.setSeed(System.currentTimeMillis());
	int fX = generatorX.nextInt(229-2)+2;
	return(fX);
}
public int getFoodY()
{
	Random generatorY = new Random();
	generatorY.setSeed(System.currentTimeMillis()); 
	int fY = generatorY.nextInt(309-11)+10;
			return(fY);
}

public int getRandSur()
{
		Random generatorY = new Random();
	generatorY.setSeed(System.currentTimeMillis()); 
	int fY = generatorY.nextInt(20-10)+10;
			return(fY);
}



public void movingHead(int sn)
{
	if(sn==1)
	{
if(p1Dir==1) // UP DIRECTION
{
	p1Y=p1Y-p1S;
p11.setPosition(p1X,p1Y);
}
else if(p1Dir==2) // DOWN DIRECTION
{
	p1Y=p1Y+p1S;
p11.setPosition(p1X,p1Y);
}
else if(p1Dir==3) // RIGHT DIRECTION
{
	p1X=p1X+p1S;
p11.setPosition(p1X,p1Y);
}
else if(p1Dir==4) // LEFT DIRECTION
{
	p1X=p1X-p1S;
p11.setPosition(p1X,p1Y);
}
		
	}
	
else if(sn==2)
	{
if(p2Dir==1) // UP DIRECTION
{
	p2Y=p2Y-p2S;
p21.setPosition(p2X,p2Y);
p21s.setPosition(p2X,p2Y);
}
else if(p2Dir==2) // DOWN DIRECTION
{
	p2Y=p2Y+p2S;
p21.setPosition(p2X,p2Y);
p21s.setPosition(p2X,p2Y);
}
else if(p2Dir==3) // RIGHT DIRECTION
{
	p2X=p2X+p2S;
p21.setPosition(p2X,p2Y);
p21s.setPosition(p2X,p2Y);
}
else if(p2Dir==4) // LEFT DIRECTION
{
	p2X=p2X-p2S;
p21.setPosition(p2X,p2Y);
p21s.setPosition(p2X,p2Y);
}
		
	}
	
	
else if(sn==3)
	{
if(p3Dir==1) // UP DIRECTION
{
	p3Y=p3Y-p3S;
p31.setPosition(p3X,p3Y);
p31s.setPosition(p3X,p3Y);
}
else if(p3Dir==2) // DOWN DIRECTION
{
	p3Y=p3Y+p3S;
p31.setPosition(p3X,p3Y);
p31s.setPosition(p3X,p3Y);
}
else if(p3Dir==3) // RIGHT DIRECTION
{
	p3X=p3X+p3S;
p31.setPosition(p3X,p3Y);
p31s.setPosition(p3X,p3Y);
}
else if(p3Dir==4) // LEFT DIRECTION
{
	p3X=p3X-p3S;
p31.setPosition(p3X,p3Y);
p31s.setPosition(p3X,p3Y);
}
		
	}
	
else if(sn==4)
	{
if(p4Dir==1) // UP DIRECTION
{
	p4Y=p4Y-p4S;
p41.setPosition(p4X,p4Y);
p41s.setPosition(p4X,p4Y);
}
else if(p4Dir==2) // DOWN DIRECTION
{
	p4Y=p4Y+p4S;
p41.setPosition(p4X,p4Y);
p41s.setPosition(p4X,p4Y);
}
else if(p4Dir==3) // RIGHT DIRECTION
{
	p4X=p4X+p4S;
p41.setPosition(p4X,p4Y);
p41s.setPosition(p4X,p4Y);

}
else if(p4Dir==4) // LEFT DIRECTION
{
	p4X=p4X-p4S;
p41.setPosition(p4X,p4Y);
p41s.setPosition(p4X,p4Y);
}
		
	}
}

public void movingBody(int sn)
{
	if(sn==1)
	{
if(p1Dir==1) // UP DIRECTION
{
p12.setPosition(p1X+1,p1Y+10);
}
else if(p1Dir==2) // DOWN DIRECTION
{
p12.setPosition(p1X+1,p1Y-10);
}
else if(p1Dir==3) // RIGHT DIRECTION
{
p12.setPosition(p1X-10,p1Y+1);
}
else if(p1Dir==4) // LEFT DIRECTION
{
p12.setPosition(p1X+10,p1Y+1);
}
		
	}


else if(sn==2)
	{
if(p2Dir==1) // UP DIRECTION
{
p22.setPosition(p2X+1,p2Y+10);
}
else if(p2Dir==2) // DOWN DIRECTION
{
p22.setPosition(p2X+1,p2Y-10);
}
else if(p2Dir==3) // RIGHT DIRECTION
{
p22.setPosition(p2X-10,p2Y+1);
}
else if(p2Dir==4) // LEFT DIRECTION
{
p22.setPosition(p2X+10,p2Y+1);
}
		
	}

else if(sn==3)
	{
if(p3Dir==1) // UP DIRECTION
{
p32.setPosition(p3X+1,p3Y+10);
}
else if(p3Dir==2) // DOWN DIRECTION
{
p32.setPosition(p3X+1,p3Y-10);
}
else if(p3Dir==3) // RIGHT DIRECTION
{
p32.setPosition(p3X-10,p3Y+1);
}
else if(p3Dir==4) // LEFT DIRECTION
{
p32.setPosition(p3X+10,p3Y+1);
}
		
	}


else if(sn==4)
	{
if(p4Dir==1) // UP DIRECTION
{
p42.setPosition(p4X+1,p4Y+10);
}
else if(p4Dir==2) // DOWN DIRECTION
{
p42.setPosition(p4X+1,p4Y-10);
}
else if(p4Dir==3) // RIGHT DIRECTION
{
p42.setPosition(p4X-10,p4Y+1);
}
else if(p4Dir==4) // LEFT DIRECTION
{
p42.setPosition(p4X+10,p4Y+1);
}
		
	}

}

public void movingTail(int sn)
{
	if(sn==1)
	{
if(p1Dir==1) // UP DIRECTION
{
p13.setPosition(p1X+1,p1Y+20);
}
else if(p1Dir==2) // DOWN DIRECTION
{
p13.setPosition(p1X+1,p1Y-20);
}
else if(p1Dir==3) // RIGHT DIRECTION
{
p13.setPosition(p1X-20,p1Y+1);
}
else if(p1Dir==4) // LEFT DIRECTION
{
p13.setPosition(p1X+20,p1Y+1);
}

	}


else if(sn==2)
	{
if(p2Dir==1) // UP DIRECTION
{
p23.setPosition(p2X+1,p2Y+20);
}
else if(p2Dir==2) // DOWN DIRECTION
{
p23.setPosition(p2X+1,p2Y-20);
}
else if(p2Dir==3) // RIGHT DIRECTION
{
p23.setPosition(p2X-20,p2Y+1);
}
else if(p2Dir==4) // LEFT DIRECTION
{
p23.setPosition(p2X+20,p2Y+1);
}

	}
	
else if(sn==3)
	{
if(p3Dir==1) // UP DIRECTION
{
p33.setPosition(p3X+1,p3Y+20);
}
else if(p3Dir==2) // DOWN DIRECTION
{
p33.setPosition(p3X+1,p3Y-20);
}
else if(p3Dir==3) // RIGHT DIRECTION
{
p33.setPosition(p3X-20,p3Y+1);
}
else if(p3Dir==4) // LEFT DIRECTION
{
p33.setPosition(p3X+20,p3Y+1);
}

	}

else if(sn==4)
	{
if(p4Dir==1) // UP DIRECTION
{
p43.setPosition(p4X+1,p4Y+20);
}
else if(p4Dir==2) // DOWN DIRECTION
{
p43.setPosition(p4X+1,p4Y-20);
}
else if(p4Dir==3) // RIGHT DIRECTION
{
p43.setPosition(p4X-20,p4Y+1);
}
else if(p4Dir==4) // LEFT DIRECTION
{
p43.setPosition(p4X+20,p4Y+1);
}

	}



}



//Place players 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void placePlayers(int numOfPlayer)
{
	if(numOfPlayer==4)
	{
	p1iniX=40;
	p1iniY=300;
	p2iniX=228;
	p2iniY=278;
	p3iniX=180;
	p3iniY=12;
	p4iniX=2;
	p4iniY=48;	
			
	p1X=40;
	p1Y=300;
	p2X=228;
	p2Y=278;
	p3X=180;
	p3Y=12;
	p4X=2;
	p4Y=48;	

p1Place(p1X,p1Y,right);
p1Dir=right;
p2Place(p2X,p2Y,up);
p2Dir=up;
p3Place(p3X,p3Y,left);
p3Dir=left;
p4Place(p4X,p4Y,down);
p4Dir=down;

p1iniDir=right;
p2iniDir=up;
p3iniDir=left;
p4iniDir=down;
	}
	else if(numOfPlayer==2)
	{
		p1iniX=40;
		p1iniY=300;
		p2iniX=180;
		p2iniY=12;
		
	p1X=40;
	p1Y=300;
	p2X=180;
	p2Y=12;
p1Place(p1X,p1Y,right);
p1Dir=right;
p2Place(p2X,p2Y,left);
p2Dir=left;
p1iniDir=right;
p2iniDir=left;
	}
	else if(numOfPlayer==3)
	{
		p1iniX=40;
		p1iniY=300;
		p2iniX=228;
		p2iniY=278;
		p3iniX=180;
		p3iniY=12;
		
	p1X=40;
	p1Y=300;
	p2X=228;
	p2Y=278;
	p3X=180;
	p3Y=12;
p1Place(p1X,p1Y,right);
p1Dir=right;
p2Place(p2X,p2Y,up);
p2Dir=up;
p3Place(p3X,p3Y,left);
p3Dir=left;
p1iniDir=right;
p2iniDir=up;
p3iniDir=left;
	}
	

}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
























// this function declear the color of other snakes
// 1=red ,2=green, 3=black , 4=blue
public void getOtherSnakeColor(int myColor)
{
	if(myColor==1)
	{	p1Color=1;
		p2Color=2;
		p3Color=3;
		p4Color=4;
		getPlayer1();
		getPlayer2();
		getPlayer3();
		getPlayer4();
	}
	else if(myColor==2)
	{	p1Color=2;
		p2Color=3;
		p3Color=4;
		p4Color=1;
				getPlayer1();
		getPlayer2();
		getPlayer3();
		getPlayer4();

	}
	else if(myColor==3)
	{	p1Color=3;
		p2Color=4;
		p3Color=1;
		p4Color=2;
				getPlayer1();
		getPlayer2();
		getPlayer3();
		getPlayer4();

	}
	else if(myColor==4)
	{	p1Color=4;
		p2Color=1;
		p3Color=2;
		p4Color=3;
				getPlayer1();
		getPlayer2();
		getPlayer3();
		getPlayer4();

	}
}


public void gettingStuff()
{
	try
	{
	youwon1 = Image.createImage("/youwon.png");
	youwon = new Sprite(youwon1);
	youloose1 = Image.createImage("/youloose.png");
	youloose = new Sprite(youloose1);
	pstp1 = Image.createImage("/pstp.png");
	 pstp = new Sprite(pstp1);
	}catch(Exception eds){} 
}

public void getPlayer1()
{
	
	// player 1
	if(p1Color!=0)
	{
	if(p1Color==1)
	{
	try{
	p1_head = Image.createImage("/red_head.png");
	p11 = new Sprite(p1_head);
	p1_body = Image.createImage("/red_body.png");
	p12 = new Sprite(p1_body);
	p1_tail = Image.createImage("/red_tail.png");
	p13 = new Sprite(p1_tail);
	}catch(Exception es){}
	}
	else if(p1Color==2)
	{
	try{
	p1_head = Image.createImage("/green_head.png");
	p11 = new Sprite(p1_head);
	p1_body = Image.createImage("/green_body.png");
	p12 = new Sprite(p1_body);
	p1_tail = Image.createImage("/green_tail.png");
	p13 = new Sprite(p1_tail);
	}catch(Exception es){}
	}
	else if(p1Color==3)
	{
	try{
	p1_head = Image.createImage("/black_head.png");
	p11 = new Sprite(p1_head);
	p1_body = Image.createImage("/black_body.png");
	p12 = new Sprite(p1_body);
	p1_tail = Image.createImage("/black_tail.png");
	p13 = new Sprite(p1_tail);
	}catch(Exception es){}
	}
	else if(p1Color==4)
	{
	try{
	p1_head = Image.createImage("/blue_head.png");
	p11 = new Sprite(p1_head);
	p1_body = Image.createImage("/blue_body.png");
	p12 = new Sprite(p1_body);
	p1_tail = Image.createImage("/blue_tail.png");
	p13 = new Sprite(p1_tail);
	}catch(Exception es){}
	}
	
	}
		
}


public void getPlayer2()
{
	
	if(p2Color!=0)
	{
	if(p2Color==1)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p21s=new Sprite(sudo_head);
	p2_head = Image.createImage("/red_head.png");
	p21 = new Sprite(p2_head);
	p2_body = Image.createImage("/red_body.png");
	p22 = new Sprite(p2_body);
	p2_tail = Image.createImage("/red_tail.png");
	p23 = new Sprite(p2_tail);
	}catch(Exception es){}
	}
	else if(p2Color==2)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p21s=new Sprite(sudo_head);
	p2_head = Image.createImage("/green_head.png");
	p21 = new Sprite(p2_head);
	p2_body = Image.createImage("/green_body.png");
	p22 = new Sprite(p2_body);
	p2_tail = Image.createImage("/green_tail.png");
	p23 = new Sprite(p2_tail);
	}catch(Exception es){}
	}
	else if(p2Color==3)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p21s=new Sprite(sudo_head);
	p2_head = Image.createImage("/black_head.png");
	p21 = new Sprite(p2_head);
	p2_body = Image.createImage("/black_body.png");
	p22 = new Sprite(p2_body);
	p2_tail = Image.createImage("/black_tail.png");
	p23 = new Sprite(p2_tail);
	}catch(Exception es){}
	}
	else if(p2Color==4)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p21s=new Sprite(sudo_head);
	p2_head = Image.createImage("/blue_head.png");
	p21 = new Sprite(p2_head);
	p2_body = Image.createImage("/blue_body.png");
	p22 = new Sprite(p2_body);
	p2_tail = Image.createImage("/blue_tail.png");
	p23 = new Sprite(p2_tail);
	}catch(Exception es){}
	}
	
	}
		
}


public void getPlayer3()
{
		
	if(p3Color!=0)
	{
	if(p3Color==1)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p31s=new Sprite(sudo_head);
	p3_head = Image.createImage("/red_head.png");
	p31 = new Sprite(p3_head);
	p3_body = Image.createImage("/red_body.png");
	p32 = new Sprite(p3_body);
	p3_tail = Image.createImage("/red_tail.png");
	p33 = new Sprite(p3_tail);
	}catch(Exception es){}
	}
	else if(p3Color==2)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p31s=new Sprite(sudo_head);
	p3_head = Image.createImage("/green_head.png");
	p31 = new Sprite(p3_head);
	p3_body = Image.createImage("/green_body.png");
	p32 = new Sprite(p3_body);
	p3_tail = Image.createImage("/green_tail.png");
	p33 = new Sprite(p3_tail);
	}catch(Exception es){}
	}
	else if(p3Color==3)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p31s=new Sprite(sudo_head);
	p3_head = Image.createImage("/black_head.png");
	p31 = new Sprite(p3_head);
	p3_body = Image.createImage("/black_body.png");
	p32 = new Sprite(p3_body);
	p3_tail = Image.createImage("/black_tail.png");
	p33 = new Sprite(p3_tail);
	}catch(Exception es){}
	}
	else if(p3Color==4)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p31s=new Sprite(sudo_head);
	p3_head = Image.createImage("/blue_head.png");
	p31 = new Sprite(p3_head);
	p3_body = Image.createImage("/blue_body.png");
	p32 = new Sprite(p3_body);
	p3_tail = Image.createImage("/blue_tail.png");
	p33 = new Sprite(p3_tail);
	}catch(Exception es){}
	}
	
	}

}

public void getPlayer4()
{
		
	
	if(p4Color!=0)
	{
	if(p4Color==1)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p41s=new Sprite(sudo_head);
	p4_head = Image.createImage("/red_head.png");
	p41 = new Sprite(p4_head);
	p4_body = Image.createImage("/red_body.png");
	p42 = new Sprite(p4_body);
	p4_tail = Image.createImage("/red_tail.png");
	p43 = new Sprite(p4_tail);
	}catch(Exception es){}
	}
	else if(p4Color==2)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p41s=new Sprite(sudo_head);
	p4_head = Image.createImage("/green_head.png");
	p41 = new Sprite(p4_head);
	p4_body = Image.createImage("/green_body.png");
	p42 = new Sprite(p4_body);
	p4_tail = Image.createImage("/green_tail.png");
	p43 = new Sprite(p4_tail);
	}catch(Exception es){}
	}
	else if(p4Color==3)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p41s=new Sprite(sudo_head);
	p4_head = Image.createImage("/black_head.png");
	p41 = new Sprite(p4_head);
	p4_body = Image.createImage("/black_body.png");
	p42 = new Sprite(p4_body);
	p4_tail = Image.createImage("/black_tail.png");
	p43 = new Sprite(p4_tail);
	}catch(Exception es){}
	}
	else if(p4Color==4)
	{
	try{
		sudo_head=Image.createImage("/sudo_head.png");
		p41s=new Sprite(sudo_head);
	p4_head = Image.createImage("/blue_head.png");
	p41 = new Sprite(p4_head);
	p4_body = Image.createImage("/blue_body.png");
	p42 = new Sprite(p4_body);
	p4_tail = Image.createImage("/blue_tail.png");
	p43 = new Sprite(p4_tail);
	}catch(Exception es){}
	}
	
	}

}




///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//P1 snake placing ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void p1Place(int x,int y,int dir)
{
Graphics g = getGraphics();
if(dir==1) // UP DIRECTION
{
	p11.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p12.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p13.setTransform(Sprite.TRANS_MIRROR_ROT90);
p11.setPosition(x,y);
p12.setPosition(x+1,y+10);
p13.setPosition(x+1,y+20);
}
else if(dir==2) // DOWN DIRECTION
{
	p11.setTransform(Sprite.TRANS_ROT90);
	p12.setTransform(Sprite.TRANS_ROT90);
	p13.setTransform(Sprite.TRANS_ROT90);
p11.setPosition(x,y);
p12.setPosition(x+1,y-10);
p13.setPosition(x+1,y-20);

}
else if(dir==3) // RIGHT DIRECTION
{
p11.setPosition(x,y);
p12.setPosition(x-10,y+1);
p13.setPosition(x-20,y+1);
}
else if(dir==4) // LEFT DIRECTION
{
	p11.setTransform(Sprite.TRANS_MIRROR);
	p12.setTransform(Sprite.TRANS_MIRROR);
	p13.setTransform(Sprite.TRANS_MIRROR);
p11.setPosition(x,y);
p12.setPosition(x+10,y+1);
p13.setPosition(x+20,y+1);
}


mLayerManager.insert(p11,0);
mLayerManager.insert(p12,0);
mLayerManager.insert(p13,0);
mLayerManager.paint(g, 0, 0);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




//P2 snake placing ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void p2Place(int x,int y,int dir)
{
	Graphics g = getGraphics();
if(dir==1) // UP DIRECTION
{
	p21.setTransform(Sprite.TRANS_MIRROR_ROT90);
		p21s.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p22.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p23.setTransform(Sprite.TRANS_MIRROR_ROT90);
p21.setPosition(x,y);
p21s.setPosition(x,y);
p22.setPosition(x+1,y+10);
p23.setPosition(x+1,y+20);
}
else if(dir==2) // DOWN DIRECTION
{
	p21.setTransform(Sprite.TRANS_ROT90);
	p21s.setTransform(Sprite.TRANS_ROT90);
	p22.setTransform(Sprite.TRANS_ROT90);
	p23.setTransform(Sprite.TRANS_ROT90);
p21.setPosition(x,y);
p21s.setPosition(x,y);
p22.setPosition(x+1,y-10);
p23.setPosition(x+1,y-20);

}
else if(dir==3) // RIGHT DIRECTION
{
p21.setPosition(x,y);
p21s.setPosition(x,y);
p22.setPosition(x-13,y+1);
p23.setPosition(x-23,y+1);
}
else if(dir==4) // LEFT DIRECTION
{
	p21.setTransform(Sprite.TRANS_MIRROR);
	p21s.setTransform(Sprite.TRANS_MIRROR);
	p22.setTransform(Sprite.TRANS_MIRROR);
	p23.setTransform(Sprite.TRANS_MIRROR);
p21.setPosition(x,y);
p21s.setPosition(x,y);
p22.setPosition(x+10,y+1);
p23.setPosition(x+20,y+1);
}


mLayerManager.insert(p21s,0);
mLayerManager.insert(p21,0);
mLayerManager.insert(p22,0);
mLayerManager.insert(p23,0);
mLayerManager.paint(g, 0, 0);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//p3 snake placing ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void p3Place(int x,int y,int dir)
{	
Graphics g = getGraphics();
	
if(dir==1) // UP DIRECTION
{
	p31.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p31s.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p32.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p33.setTransform(Sprite.TRANS_MIRROR_ROT90);
p31.setPosition(x,y);
p31s.setPosition(x,y);
p32.setPosition(x+1,y+10);
p33.setPosition(x+1,y+20);
}
else if(dir==2) // DOWN DIRECTION
{
	p31.setTransform(Sprite.TRANS_ROT90);
	p31s.setTransform(Sprite.TRANS_ROT90);
	p32.setTransform(Sprite.TRANS_ROT90);
	p33.setTransform(Sprite.TRANS_ROT90);
p31.setPosition(x,y);
p31s.setPosition(x,y);
p32.setPosition(x+1,y-10);
p33.setPosition(x+1,y-20);

}
else if(dir==3) // RIGHT DIRECTION
{
p31.setPosition(x,y);
p31s.setPosition(x,y);
p32.setPosition(x-13,y+1);
p33.setPosition(x-23,y+1);
}
else if(dir==4) // LEFT DIRECTION
{
	p31.setTransform(Sprite.TRANS_MIRROR);
	p31s.setTransform(Sprite.TRANS_MIRROR);
	p32.setTransform(Sprite.TRANS_MIRROR);
	p33.setTransform(Sprite.TRANS_MIRROR);
p31.setPosition(x,y);
p31s.setPosition(x,y);
p32.setPosition(x+10,y+1);
p33.setPosition(x+20,y+1);
}


mLayerManager.insert(p31s,0);
mLayerManager.insert(p31,0);
mLayerManager.insert(p32,0);
mLayerManager.insert(p33,0);
mLayerManager.paint(g, 0, 0);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//p4 snake placing ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void p4Place(int x,int y,int dir)
{
Graphics g = getGraphics();	
	
if(dir==1) // UP DIRECTION
{
	p41.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p41s.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p42.setTransform(Sprite.TRANS_MIRROR_ROT90);
	p43.setTransform(Sprite.TRANS_MIRROR_ROT90);
p41.setPosition(x,y);
p41s.setPosition(x,y);
p42.setPosition(x+1,y+10);
p43.setPosition(x+1,y+20);
}
else if(dir==2) // DOWN DIRECTION
{
	p41.setTransform(Sprite.TRANS_ROT90);
	p41s.setTransform(Sprite.TRANS_ROT90);
	p42.setTransform(Sprite.TRANS_ROT90);
	p43.setTransform(Sprite.TRANS_ROT90);
p41.setPosition(x,y);
p41s.setPosition(x,y);
p42.setPosition(x+1,y-10);
p43.setPosition(x+1,y-20);

}
else if(dir==3) // RIGHT DIRECTION
{
p41.setPosition(x,y);
p41s.setPosition(x,y);
p42.setPosition(x-13,y+1);
p43.setPosition(x-23,y+1);
}
else if(dir==4) // LEFT DIRECTION
{
	p41.setTransform(Sprite.TRANS_MIRROR);
	p41s.setTransform(Sprite.TRANS_MIRROR);
	p42.setTransform(Sprite.TRANS_MIRROR);
	p43.setTransform(Sprite.TRANS_MIRROR);
p41.setPosition(x,y);
p41s.setPosition(x,y);
p42.setPosition(x+10,y+1);
p43.setPosition(x+20,y+1);

}


mLayerManager.insert(p41s,0);
mLayerManager.insert(p41,0);
mLayerManager.insert(p42,0);
mLayerManager.insert(p43,0);
mLayerManager.paint(g, 0, 0);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





public void outOfScreen()
{
	if(p1X>=280)
 {
 	p1X=0;
 }
 else if(p1X<-40)
 {
 	p1X=240;
 }
 else if(p1Y>350)
 {
 	p1Y=-11;
 }
 else if(p1Y<-40)
 {
 	p1Y=300;
 }
 
 if(p2X>=280)
 {
 	p2X=0;
 }
 else if(p2X<-40)
 {
 	p2X=240;
 }
 else if(p2Y>350)
 {
 	p2Y=-11;
 }
 else if(p2Y<-40)
 {
 	p2Y=300;
 }
 
 if(p3X>=280)
 {
 	p3X=0;
 }
 else if(p3X<-40)
 {
 	p3X=240;
 }
 else if(p3Y>350)
 {
 	p3Y=-11;
 }
 else if(p3Y<-40)
 {
 	p3Y=300;
 }

if(p4X>=280)
 {
 	p4X=0;
 }
 else if(p4X<-40)
 {
 	p4X=240;
 }
 else if(p4Y>350)
 {
 	p4Y=-11;
 }
 else if(p4Y<-40)
 {
 	p4Y=300;
 } 
}




public void p1initial()
{
	p1S=2;
	  p1X=p1iniX;
        p1Y=p1iniY;	
        p1Dir=p1iniDir;
    p11.setTransform(Sprite.TRANS_NONE);
	p12.setTransform(Sprite.TRANS_NONE);
	p13.setTransform(Sprite.TRANS_NONE);	    
}


public void p2initial()
{
	p2S=2;
	  p2X=p2iniX;
        p2Y=p2iniY;	
        p2Dir=p2iniDir;
    if(p2Dir==left)
       {
       	p21s.setTransform(Sprite.TRANS_ROT180);
    p21.setTransform(Sprite.TRANS_ROT180);
	p22.setTransform(Sprite.TRANS_ROT180);
	p23.setTransform(Sprite.TRANS_ROT180);
	   }
	   else if(p2Dir==up)
       {
       	p21s.setTransform(Sprite.TRANS_ROT270);
    p21.setTransform(Sprite.TRANS_ROT270);
	p22.setTransform(Sprite.TRANS_ROT270);
	p23.setTransform(Sprite.TRANS_ROT270);
	   }
	    else if(p2Dir==down)
       {
       	p21s.setTransform(Sprite.TRANS_ROT90);
    p21.setTransform(Sprite.TRANS_ROT90);
	p22.setTransform(Sprite.TRANS_ROT90);
	p23.setTransform(Sprite.TRANS_ROT90);
	   }
}

public void p3initial()
{
	p3S=2;
	    p3X=p3iniX;
        p3Y=p3iniY;	
        p3Dir=p3iniDir;
if(p3Dir==left)
       {
       	p31s.setTransform(Sprite.TRANS_ROT180);
    p31.setTransform(Sprite.TRANS_ROT180);
	p32.setTransform(Sprite.TRANS_ROT180);
	p33.setTransform(Sprite.TRANS_ROT180);
	   }
	   else if(p3Dir==up)
       {
       	p31s.setTransform(Sprite.TRANS_ROT270);
    p31.setTransform(Sprite.TRANS_ROT270);
	p32.setTransform(Sprite.TRANS_ROT270);
	p33.setTransform(Sprite.TRANS_ROT270);
	   }
	    else if(p3Dir==down)
       {
       	p31s.setTransform(Sprite.TRANS_ROT90);
    p31.setTransform(Sprite.TRANS_ROT90);
	p32.setTransform(Sprite.TRANS_ROT90);
	p33.setTransform(Sprite.TRANS_ROT90);
	   }
    
}

public void p4initial()
{
	p4S=2;
	  p4X=p4iniX;
        p4Y=p4iniY;	
        p4Dir=p4iniDir;
    if(p4Dir==left)
       {
       	p41s.setTransform(Sprite.TRANS_ROT180);
    p41.setTransform(Sprite.TRANS_ROT180);
	p42.setTransform(Sprite.TRANS_ROT180);
	p43.setTransform(Sprite.TRANS_ROT180);
	   }
	   else if(p4Dir==up)
       {
       	p41s.setTransform(Sprite.TRANS_ROT270);
    p41.setTransform(Sprite.TRANS_ROT270);
	p42.setTransform(Sprite.TRANS_ROT270);
	p43.setTransform(Sprite.TRANS_ROT270);
	   }
	    else if(p4Dir==down)
       {
       	p41s.setTransform(Sprite.TRANS_ROT90);
    p41.setTransform(Sprite.TRANS_ROT90); 
	p42.setTransform(Sprite.TRANS_ROT90);
	p43.setTransform(Sprite.TRANS_ROT90);
	   }
      
}








public void collisionDetectorP1()
{
	if(nump==1)
	{//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	   		 if(p11.collidesWith(Arena,true)==true)
       		 {
       		 p1initial();
			 p1Scr--;
			 parent.vibration(600);
			 parent.backlight(600);
        	 }
        	 
        	else if(p11.collidesWith(food,true)==true)
        	{
        	System.out.println("p1touch");
			placeFood(); 
			foodCounter--;     
        	p1Scr+=5;
        	}
        	else if(p11.collidesWith(speedUp,true)==true)
        	{
        		p1S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p1Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(speedDown,true)==true)
        	{
        		p1S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p1Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p1Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p1Scr+=10;
        	surIsDisp=false;
        	}
	   		else if(p11.collidesWith(p21,true)==true)
        	{	
       	
       		if(p1Scr>p2Scr)
       		{
       		p2initial();
       		p2Scr-=3;
       		p1Scr--;
      		}
      		
      		else if(p2Scr>p1Scr)
      		{
      		p1initial();
      		p2Scr--;
      		p1Scr-=3; 	
      		}
      		else
      		{
      		p1Scr--;
      		p2Scr--;
      		}
        	
        	}
       
        	else if(p11.collidesWith(p22,true)==true)
        	{
       		p1initial();
	   		p1Scr--;
        	}
        
        	else if(p11.collidesWith(p23,true)==true)
        	{
        	p1initial();
			p1Scr--;
        	}
        	else
	 		{
	 		}
	 
	}//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	
	else if(nump==2)
	{//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	   		 if(p11.collidesWith(Arena,true)==true)
       		 {
       		 p1initial();
			 p1Scr--;
			 parent.vibration(600);
			 parent.backlight(600);
        	 }
        	 
        	else if(p11.collidesWith(food,true)==true)
        	{
        	System.out.println("p1touch");
			placeFood(); 
			foodCounter--;     
        	p1Scr+=5;
        	}
        	else if(p11.collidesWith(speedUp,true)==true)
        	{
        		p1S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p1Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(speedDown,true)==true)
        	{
        		p1S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p1Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p1Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p1Scr+=10;
        	surIsDisp=false;
        	}
        	
	   		else if(p11.collidesWith(p21,true)==true)
        	{	
       	
       		if(p1Scr>p2Scr)
       		{
       		p2initial();
       		p2Scr-=3;
       		p1Scr--;
      		}
      		
      		else if(p2Scr>p1Scr)
      		{
      		p1initial();
      		p2Scr--;
      		p1Scr-=3; 	
      		}
      		else
      		{
      		p1Scr--;
      		p2Scr--;
      		}
        	
        	}
       
        	else if(p11.collidesWith(p22,true)==true)
        	{
       		p1initial();
	   		p1Scr--;
        	}
        
        	else if(p11.collidesWith(p23,true)==true)
        	{
        	p1initial();
			p1Scr--;
        	}
	 
	 		else if(p11.collidesWith(p31,true)==true)
        	{
			 if(p1Scr>p3Scr)
       		{
       		p3Scr-=3;
       		p1Scr--;
       		 p3initial();
      		}
      		else if(p3Scr>p1Scr){
      		p3Scr--;
      		p1Scr-=3; 
      		p1initial();	
      		}
      		else
      		{
      		p1Scr--;
      		p3Scr--;
      		}
        	}
        	else if(p11.collidesWith(p32,true)==true)
        	{
        	p1initial();
			p1Scr--;
        	}
        	else if(p11.collidesWith(p33,true)==true)
        	{
        	p1initial();
			p1Scr--;
        	}
	 		else
	 		{
	 		}
	}//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	   
	   
	else if(nump==3)
	{//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	   		 if(p11.collidesWith(Arena,true)==true)
       		 {
       		 p1initial();
			 p1Scr--;
			 parent.vibration(600);
			 parent.backlight(600);
        	 }
        	 
        	else if(p11.collidesWith(food,true)==true)
        	{
        	System.out.println("p1touch");
			placeFood(); 
			foodCounter--;     
        	p1Scr+=5;
        	}
        	else if(p11.collidesWith(speedUp,true)==true)
        	{
        		p1S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p1Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(speedDown,true)==true)
        	{
        		p1S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p1Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p1Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p11.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p1Scr+=10;
        	surIsDisp=false;
        	}
        	
	   		else if(p11.collidesWith(p21,true)==true)
        	{	
       	
       		if(p1Scr>p2Scr)
       		{
       		p2initial();
       		p2Scr-=3;
       		p1Scr--;
      		}
      		
      		else if(p2Scr>p1Scr)
      		{
      		p1initial();
      		p2Scr--;
      		p1Scr-=3; 	
      		}
      		else
      		{
      		p1Scr--;
      		p2Scr--;
      		}
        	
        	}
       
        	else if(p11.collidesWith(p22,true)==true)
        	{
       		p1initial();
	   		p1Scr--;
        	}
        
        	else if(p11.collidesWith(p23,true)==true)
        	{
        	p1initial();
			p1Scr--;
        	}
	 
	 		else if(p11.collidesWith(p31,true)==true)
        	{
			 if(p1Scr>p3Scr)
       		{
       		p3Scr-=3;
       		p1Scr--;
       		 p3initial();
      		}
      		else if(p3Scr>p1Scr){
      		p3Scr--;
      		p1Scr-=3; 
      		p1initial();	
      		}
      		else
      		{
      		p1Scr--;
      		p3Scr--;
      		}
        	}
        	else if(p11.collidesWith(p32,true)==true)
        	{
        	p1initial();
			p1Scr--;
        	}
        	else if(p11.collidesWith(p33,true)==true)
        	{
        	p1initial();
			p1Scr--;
        	}
	 		
	 		 else if(p11.collidesWith(p41,true)==true)
        	{
       		if(p1Scr>p4Scr)
       		{
       		p4Scr-=3;
       		p1Scr--;
      		 p4initial();
      		}
      		else if(p4Scr>p1Scr)
      		{
      		p4Scr--;
      		p1Scr-=3; 	
     		p1initial();
      		}
      		else
      		{
      		p1Scr--;
      		p4Scr--;
      		}
        	}
        
        
        
        	else if(p11.collidesWith(p42,true)==true)
        	{
        	p1initial();
			p1Scr--;
       	 	}
        	
        
       		else if(p11.collidesWith(p43,true)==true)
        	{
        	p1initial();
			p1Scr--;
       	 	}
        
	 		else
	 		{
	 		}
	 		
	}//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

}
	   
	   
	   
	   
public void collisionDetectorP2()
{
if(p2Scr>=0)
{
	if(nump==1)
	{//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		if(p21.collidesWith(food,true)==true)
        {
			placeFood(); 
			foodCounter--;     
        	p2Scr+=5;
        }
        	else if(p21.collidesWith(speedUp,true)==true)
        	{
        		p2S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p2Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(speedDown,true)==true)
        	{
        		p2S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p2Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p2Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p2Scr+=10;
        	surIsDisp=false;
        	}

        
        else if(p21.collidesWith(p12,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else if(p21.collidesWith(p13,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else 
        {
        }
        
	}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	else if(nump==2)
	{//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		if(p21.collidesWith(food,true)==true)
        {
        
			placeFood(); 
			foodCounter--;     
        	p2Scr+=5;
        }
        	else if(p21.collidesWith(speedUp,true)==true)
        	{
        		p2S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p2Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(speedDown,true)==true)
        	{
        		p2S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p2Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p2Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p2Scr+=10;
        	surIsDisp=false;
        	}

        else if(p21.collidesWith(p12,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else if(p21.collidesWith(p13,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        
        else if(p21.collidesWith(p31,true)==true)
        {
      	if(p2Scr>p3Scr)
      	 { 
      	 p3initial();
       	 p3Scr-=3;
         p2Scr--;
      	 }
      
      	else if(p3Scr>p2Scr)
      	{
      	 p2initial();
      	 p3Scr--;
      	 p2Scr-=3; 	
        }
        else
        {
      	p2Scr--;
      	p3Scr--;
        }
        }
        else if(p21.collidesWith(p32,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else if(p21.collidesWith(p33,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else 
        {
        }
	}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	else if(nump==3)
	{//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		if(p21.collidesWith(food,true)==true)
        {

			placeFood(); 
			foodCounter--;     
        	p2Scr+=5;
        }
        	else if(p21.collidesWith(speedUp,true)==true)
        	{
        		p2S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p2Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(speedDown,true)==true)
        	{
        		p2S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p2Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p2Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p21.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p2Scr+=10;
        	surIsDisp=false;
        	}

        else if(p21.collidesWith(p12,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else if(p21.collidesWith(p13,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        
        else if(p21.collidesWith(p31,true)==true)
        {
      	if(p2Scr>p3Scr)
      	 { 
      	 p3initial();
       	 p3Scr-=3;
         p2Scr--;
      	 }
      
      	else if(p3Scr>p2Scr)
      	{
      	 p2initial();
      	 p3Scr--;
      	 p2Scr-=3; 	
        }
        else
        {
      	p2Scr--;
      	p3Scr--;
        }
        }
        else if(p21.collidesWith(p32,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else if(p21.collidesWith(p33,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else if(p21.collidesWith(p41,true)==true)
        {
       	if(p2Scr>p4Scr)
       	{ 
        p4initial();
       	p4Scr-=3;
        p2Scr--;
        }
        else if(p4Scr>p2Scr)
        {
      	p2initial();
      	p4Scr--;
      	p2Scr-=3; 	
        }
        else
        {
      	p2Scr--;
      	p4Scr--;
        }
        }
        else if(p21.collidesWith(p42,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else if(p21.collidesWith(p43,true)==true)
        {
        p2initial();
		p2Scr--;
        }
        else 
        {
        }
	}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}
}
        
        
        
        
       
        
public void collisionDetectorP3()
{
if(p3Scr>=0)
{
	if(nump==2)
	{//########################################################
		if(p31.collidesWith(food,true)==true)
        {
        if(nump==3 || nump==2)
        {
			placeFood(); 
			foodCounter--;     
        	p3Scr+=5;
        }
    	}
    		else if(p31.collidesWith(speedUp,true)==true)
        	{
        		p3S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p3Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p31.collidesWith(speedDown,true)==true)
        	{
        		p3S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p3Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p31.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p3Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p31.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p3Scr+=10;
        	surIsDisp=false;
        	}

    	else if(p31.collidesWith(p12,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
        }
        }
         
        else if(p31.collidesWith(p13,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
        }
        }
        
        else if(p31.collidesWith(p22,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
    	}
        } 
        
        else if(p31.collidesWith(p23,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
    	}
        } 
        
        else
        {
        }
    	
	}//########################################################
	
	else if(nump==3)
	{//########################################################
		if(p31.collidesWith(food,true)==true)
        {
        if(nump==3 || nump==2)
        {
			placeFood(); 
			foodCounter--;     
        	p3Scr+=5;
        }
    	}
    		else if(p31.collidesWith(speedUp,true)==true)
        	{
        		p3S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p3Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p31.collidesWith(speedDown,true)==true)
        	{
        		p3S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p3Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p31.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p3Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p31.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p3Scr+=10;
        	surIsDisp=false;
        	}
    	
    	else if(p31.collidesWith(p12,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
        }
        }
         
        else if(p31.collidesWith(p13,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
        }
        }
        
        else if(p31.collidesWith(p22,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
    	}
        } 
        
        else if(p31.collidesWith(p23,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
    	}
        } 
        
        else if(p31.collidesWith(p41,true)==true)
        {
        if(nump==2 || nump==3)
        {	
        p3initial();
        p4initial();
 		if(p3Scr>p4Scr)
        {
       	p4Scr-=3;
        p3Scr--;
        }
        else if(p4Scr>p3Scr){
      	p4Scr--;
      	p3Scr-=3; 	
        }
        else
        {
      	p3Scr--;
      	p4Scr--;
        }
        }
        } 
        else if(p31.collidesWith(p42,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
    	}
        } 
        else if(p31.collidesWith(p43,true)==true)
        {
        if(nump==2 || nump==3)
        {
        p3initial();
		p3Scr--;
    	}
        } 
        else
        {
        }
    	
	}//########################################################
}
}       
        
        
public void collisionDetectorP4()
{      
if(p4Scr>=0)
{ 
	if(nump==3)
	{//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		if(p41.collidesWith(food,true)==true)
        {
        if(nump==3)
        {
			placeFood(); 
			foodCounter--;     
       		p4Scr+=5;
        }
    	}
    		else if(p41.collidesWith(speedUp,true)==true)
        	{
        		p4S=4;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedUpCounter--;     
        	p4Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p41.collidesWith(speedDown,true)==true)
        	{
        		p4S=1;
        	surX=500;
        	surY=500;
			moveSur(); 
			speedDownCounter--;     
        	p4Scr+=2;
        	surIsDisp=false;
        	}
        	else if(p41.collidesWith(poison,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			poisonCounter--;     
        	p4Scr-=5;
        	surIsDisp=false;
        	}
        	else if(p41.collidesWith(megaPower,true)==true)
        	{
        	surX=500;
        	surY=500;
			moveSur(); 
			megaPowerCounter--;     
        	p4Scr+=10;
        	surIsDisp=false;
        	}
    	else if(p41.collidesWith(p12,true)==true)
        {
        if(nump==3)
        {
        p4initial();
		p4Scr--;
        }
        } 
        else if(p41.collidesWith(p13,true)==true)
        {
        if(nump==3)
        {
        p4initial();
		p4Scr--;
        }
        } 
        else if(p41.collidesWith(p22,true)==true)
        {
        if(nump==3)
        {
        p4initial();
		p4Scr--;
        }
        } 
        else if(p41.collidesWith(p23,true)==true)
        {
        if(nump==3)
        {
        p4initial();
		p4Scr--;
        }
        } 
        else if(p41.collidesWith(p32,true)==true)
        {
        if(nump==3)
        {
        p4initial();
		p4Scr--;
        }
        } 
        else if(p41.collidesWith(p33,true)==true)
        {
        if(nump==3)
        {
        p4initial();
		p4Scr--;
        }
        }
        else
        {
        } 
    	
	}//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ 
}
}        
        


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
public void p2turnRight()
{
				p21s.setTransform(Sprite.TRANS_NONE);
				p21.setTransform(Sprite.TRANS_NONE);
				 p22.setTransform(Sprite.TRANS_NONE);
				 p23.setTransform(Sprite.TRANS_NONE);
				 p2Dir=right;
}
public void p2turnLeft()
{
				p21s.setTransform(Sprite.TRANS_ROT180);
			   	p21.setTransform(Sprite.TRANS_ROT180);
				 p22.setTransform(Sprite.TRANS_ROT180);
				 p23.setTransform(Sprite.TRANS_ROT180);
				 p2Dir=left;
}
public void p2turnUp()
{
				p21s.setTransform(Sprite.TRANS_ROT270);
				p21.setTransform(Sprite.TRANS_ROT270);
				 p22.setTransform(Sprite.TRANS_ROT270);
				 p23.setTransform(Sprite.TRANS_ROT270);
				 p2Dir=up;
}
public void p2turnDown()
{
				p21s.setTransform(Sprite.TRANS_ROT90);
				p21.setTransform(Sprite.TRANS_ROT90);
				 p22.setTransform(Sprite.TRANS_ROT90);
				 p23.setTransform(Sprite.TRANS_ROT90);
				 p2Dir=down;
}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
public void p3turnRight()
{
				p31s.setTransform(Sprite.TRANS_NONE);
				p31.setTransform(Sprite.TRANS_NONE);
				 p32.setTransform(Sprite.TRANS_NONE);
				 p33.setTransform(Sprite.TRANS_NONE);
				 p3Dir=right;
}
public void p3turnLeft()
{
				p31s.setTransform(Sprite.TRANS_ROT180);
			   	p31.setTransform(Sprite.TRANS_ROT180);
				 p32.setTransform(Sprite.TRANS_ROT180);
				 p33.setTransform(Sprite.TRANS_ROT180);
				 p3Dir=left;
}
public void p3turnUp()
{
				p31s.setTransform(Sprite.TRANS_ROT270);
				p31.setTransform(Sprite.TRANS_ROT270);
				 p32.setTransform(Sprite.TRANS_ROT270);
				 p33.setTransform(Sprite.TRANS_ROT270);
				 p3Dir=up;
}
public void p3turnDown()
{
				p31s.setTransform(Sprite.TRANS_ROT90);
				p31.setTransform(Sprite.TRANS_ROT90);
				 p32.setTransform(Sprite.TRANS_ROT90);
				 p33.setTransform(Sprite.TRANS_ROT90);
				 p3Dir=down;
}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
public void p4turnRight()
{
				p41s.setTransform(Sprite.TRANS_NONE);
				p41.setTransform(Sprite.TRANS_NONE);
				 p42.setTransform(Sprite.TRANS_NONE);
				 p43.setTransform(Sprite.TRANS_NONE);
				 p4Dir=right;
}
public void p4turnLeft()
{
				p41s.setTransform(Sprite.TRANS_ROT180);
			   	p41.setTransform(Sprite.TRANS_ROT180);
				 p42.setTransform(Sprite.TRANS_ROT180);
				 p43.setTransform(Sprite.TRANS_ROT180);
				 p4Dir=left;
}
public void p4turnUp()
{
				p41s.setTransform(Sprite.TRANS_ROT270);
				p41.setTransform(Sprite.TRANS_ROT270);
				 p42.setTransform(Sprite.TRANS_ROT270);
				 p43.setTransform(Sprite.TRANS_ROT270);
				 p4Dir=up;
}
public void p4turnDown()
{		
				p41s.setTransform(Sprite.TRANS_ROT90);
				p41.setTransform(Sprite.TRANS_ROT90);
				 p42.setTransform(Sprite.TRANS_ROT90);
				 p43.setTransform(Sprite.TRANS_ROT90);
				 p4Dir=down;
}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


      
//############################################################################################################
public void artifitialInti(int snk,int typ)
{

if(typ==1)
{	


	if(snk==2)
	{
		
		
		int xx=foodX-p2X;
		int yy=foodY-p2Y;
		
		if(p2bypass==false)
		{
			if(xx>3)
			{
				if(p2Dir!=right && p2Dir!=left)
				{
				p2turnRight();
				}
				else if(p2Dir==left)
				{
				p2turnRight();
				}
				else
				{
				}
			}
			else if(xx<-3)
			{
				if(p2Dir!=right && p2Dir!=left)
				{
				p2turnLeft();
				}
				else if(p2Dir==right)
				{
				p2turnLeft();
				}
				else
				{
				}
			}
			else
			{
				if(yy>3)
				{
					p2turnDown();
				}
				else if(yy<-3)
				{ 
					p2turnUp();
				}
				else
				{
			
				}
			}
		
		if(p21.collidesWith(Arena,false)==true)
        	{
        		p2bypass=true;
        		p2pDir=p2Dir;
        	}
		}
		
		else
		{
		if(p2bypass1==false)
		{	
			if(p2Dir==right)
			{
				p2turnDown();
			}
			else if(p2Dir==down)
			{
				p2turnLeft();
			}
			else if(p2Dir==left)
			{
				p2turnUp();
			}
			else 
			{
				p2turnRight();
			}
		}
		else
		{
		}
		if(p2pDir==right)
		{
			p2bypass1=true;
			p21s.setTransform(Sprite.TRANS_NONE);
		}
		else if(p2pDir==down)
		{
			p2bypass1=true;
			p21s.setTransform(Sprite.TRANS_ROT90);
		}
		else if(p2pDir==left)
		{
			p2bypass1=true;
			p21s.setTransform(Sprite.TRANS_ROT180);
		}
		else if(p2pDir==up)
 		{
 			p2bypass1=true;
 			p21s.setTransform(Sprite.TRANS_ROT270);
		}
		else
		{
		}
		
			if(p21s.collidesWith(Arena,false)==true)
        	{
        		p2bypass1=true;
        		p2bypass=true;
        	}
        	else
        	{
        		p2bypass=false;
        		p2bypass1=false;
        	}
		
		}
	}


	else if(snk==3)
	{
		int xx=foodX-p3X;
		int yy=foodY-p3Y;
		
		if(p3bypass==false)
		{
			if(xx>3)
			{
				if(p3Dir!=right && p3Dir!=left)
				{
				p3turnRight();
				}
				else if(p3Dir==left)
				{
				p3turnRight();
				}
				else
				{
				}
			}
			else if(xx<-3)
			{
				if(p3Dir!=right && p3Dir!=left)
				{
				p3turnLeft();
				}
				else if(p3Dir==right)
				{
				p3turnLeft();
				}
				else
				{
				}
			}
			else
			{
				if(yy>3)
				{
					p3turnDown();
				}
				else if(yy<-3)
				{ 
					p3turnUp();
				}
				else
				{
			
				}
			}
		
		if(p31.collidesWith(Arena,false)==true)
        	{
        		p3bypass=true;
        		p3pDir=p3Dir;
        	}
		}
		
		else
		{
		if(p3bypass1==false)
		{	
			if(p3Dir==right)
			{
				p3turnDown();
			}
			else if(p3Dir==down)
			{
				p3turnLeft();
			}
			else if(p3Dir==left)
			{
				p3turnUp();
			}
			else 
			{
				p3turnRight();
			}
		}
		else
		{
		}
		if(p3pDir==right)
		{
			p3bypass1=true;
			p31s.setTransform(Sprite.TRANS_NONE);
		}
		else if(p3pDir==down)
		{
			p3bypass1=true;
			p31s.setTransform(Sprite.TRANS_ROT90);
		}
		else if(p3pDir==left)
		{
			p3bypass1=true;
			p31s.setTransform(Sprite.TRANS_ROT180);
		}
		else if(p3pDir==up)
 		{
 			p3bypass1=true;
 			p31s.setTransform(Sprite.TRANS_ROT270);
		}
		else
		{
		}
		
			if(p31s.collidesWith(Arena,false)==true)
        	{
        		p3bypass1=true;
        		p3bypass=true;
        	}
        	else
        	{
        		p3bypass=false;
        		p3bypass1=false;
        	}
		
		}
		
		
	}
	
	
	else if(snk==4)
	{
		int xx=foodX-p4X;
		int yy=foodY-p4Y;
		
		if(p4bypass==false)
		{
			if(xx>3)
			{
				if(p4Dir!=right && p4Dir!=left)
				{
				p4turnRight();
				}
				else if(p4Dir==left)
				{
				p4turnRight();
				}
				else
				{
				}
			}
			else if(xx<-3)
			{
				if(p4Dir!=right && p4Dir!=left)
				{
				p4turnLeft();
				}
				else if(p4Dir==right)
				{
				p4turnLeft();
				}
				else
				{
				}
			}
			else
			{
				if(yy>3)
				{
					p4turnDown();
				}
				else if(yy<-3)
				{ 
					p4turnUp();
				}
				else
				{
			
				}
			}
		
		if(p41.collidesWith(Arena,false)==true)
        	{
        		p4bypass=true;
        		p4pDir=p4Dir;
        	}
		}
		
		else
		{
		if(p4bypass1==false)
		{	
			if(p4Dir==right)
			{
				p4turnDown();
			}
			else if(p4Dir==down)
			{
				p4turnLeft();
			}
			else if(p4Dir==left)
			{
				p4turnUp();
			}
			else 
			{
				p4turnRight();
			}
		}
		else
		{
		}
		if(p4pDir==right)
		{
			p4bypass1=true;
			p41s.setTransform(Sprite.TRANS_NONE);
		}
		else if(p4pDir==down)
		{
			p4bypass1=true;
			p41s.setTransform(Sprite.TRANS_ROT90);
		}
		else if(p4pDir==left)
		{
			p4bypass1=true;
			p41s.setTransform(Sprite.TRANS_ROT180);
		}
		else if(p4pDir==up)
 		{
 			p4bypass1=true;
 			p41s.setTransform(Sprite.TRANS_ROT270);
		}
		else
		{
		}
		
			if(p41s.collidesWith(Arena,false)==true)
        	{
        		p4bypass1=true;
        		p4bypass=true;
        	}
        	else
        	{
        		p4bypass=false;
        		p4bypass1=false;
        	}
		
		}
		
	}
}

else if(typ==2)
{
	
	if(snk==2)
	{
		
		
		int xx=p1X-p2X;
		int yy=p1Y-p2Y;
		
		if(p2bypass==false)
		{
			if(xx>3)
			{
				if(p2Dir!=right && p2Dir!=left)
				{
				p2turnRight();
				}
				else if(p2Dir==left)
				{
				p2turnRight();
				}
				else
				{
				}
			}
			else if(xx<-3)
			{
				if(p2Dir!=right && p2Dir!=left)
				{
				p2turnLeft();
				}
				else if(p2Dir==right)
				{
				p2turnLeft();
				}
				else
				{
				}
			}
			else
			{
				if(yy>3)
				{
					p2turnDown();
				}
				else if(yy<-3)
				{ 
					p2turnUp();
				}
				else
				{
			
				}
			}
		
		if(p21.collidesWith(Arena,false)==true)
        	{
        		p2bypass=true;
        		p2pDir=p2Dir;
        	}
		}
		
		else
		{
		if(p2bypass1==false)
		{	
			if(p2Dir==right)
			{
				p2turnDown();
			}
			else if(p2Dir==down)
			{
				p2turnLeft();
			}
			else if(p2Dir==left)
			{
				p2turnUp();
			}
			else 
			{
				p2turnRight();
			}
		}
		else
		{
		}
		if(p2pDir==right)
		{
			p2bypass1=true;
			p21s.setTransform(Sprite.TRANS_NONE);
		}
		else if(p2pDir==down)
		{
			p2bypass1=true;
			p21s.setTransform(Sprite.TRANS_ROT90);
		}
		else if(p2pDir==left)
		{
			p2bypass1=true;
			p21s.setTransform(Sprite.TRANS_ROT180);
		}
		else if(p2pDir==up)
 		{
 			p2bypass1=true;
 			p21s.setTransform(Sprite.TRANS_ROT270);
		}
		else
		{
		}
		
			if(p21s.collidesWith(Arena,false)==true)
        	{
        		p2bypass1=true;
        		p2bypass=true;
        	}
        	else
        	{
        		p2bypass=false;
        		p2bypass1=false;
        	}
		
		}
	}


	else if(snk==3)
	{
		int xx=p1X-p3X;
		int yy=p1Y-p3Y;
		
		if(p3bypass==false)
		{
			if(xx>3)
			{
				if(p3Dir!=right && p3Dir!=left)
				{
				p3turnRight();
				}
				else if(p3Dir==left)
				{
				p3turnRight();
				}
				else
				{
				}
			}
			else if(xx<-3)
			{
				if(p3Dir!=right && p3Dir!=left)
				{
				p3turnLeft();
				}
				else if(p3Dir==right)
				{
				p3turnLeft();
				}
				else
				{
				}
			}
			else
			{
				if(yy>3)
				{
					p3turnDown();
				}
				else if(yy<-3)
				{ 
					p3turnUp();
				}
				else
				{
			
				}
			}
		
		if(p31.collidesWith(Arena,false)==true)
        	{
        		p3bypass=true;
        		p3pDir=p3Dir;
        	}
		}
		
		else
		{
		if(p3bypass1==false)
		{	
			if(p3Dir==right)
			{
				p3turnDown();
			}
			else if(p3Dir==down)
			{
				p3turnLeft();
			}
			else if(p3Dir==left)
			{
				p3turnUp();
			}
			else 
			{
				p3turnRight();
			}
		}
		else
		{
		}
		if(p3pDir==right)
		{
			p3bypass1=true;
			p31s.setTransform(Sprite.TRANS_NONE);
		}
		else if(p3pDir==down)
		{
			p3bypass1=true;
			p31s.setTransform(Sprite.TRANS_ROT90);
		}
		else if(p3pDir==left)
		{
			p3bypass1=true;
			p31s.setTransform(Sprite.TRANS_ROT180);
		}
		else if(p3pDir==up)
 		{
 			p3bypass1=true;
 			p31s.setTransform(Sprite.TRANS_ROT270);
		}
		else
		{
		}
		
			if(p31s.collidesWith(Arena,false)==true)
        	{
        		p3bypass1=true;
        		p3bypass=true;
        	}
        	else
        	{
        		p3bypass=false;
        		p3bypass1=false;
        	}
		
		}
		
		
	}
	
	
	else if(snk==4)
	{
		int xx=p1X-p4X;
		int yy=p1Y-p4Y;
		
		if(p4bypass==false)
		{
			if(xx>3)
			{
				if(p4Dir!=right && p4Dir!=left)
				{
				p4turnRight();
				}
				else if(p4Dir==left)
				{
				p4turnRight();
				}
				else
				{
				}
			}
			else if(xx<-3)
			{
				if(p4Dir!=right && p4Dir!=left)
				{
				p4turnLeft();
				}
				else if(p4Dir==right)
				{
				p4turnLeft();
				}
				else
				{
				}
			}
			else
			{
				if(yy>3)
				{
					p4turnDown();
				}
				else if(yy<-3)
				{ 
					p4turnUp();
				}
				else
				{
			
				}
			}
		
		if(p41.collidesWith(Arena,false)==true)
        	{
        		p4bypass=true;
        		p4pDir=p4Dir;
        	}
		}
		
		else
		{
		if(p4bypass1==false)
		{	
			if(p4Dir==right)
			{
				p4turnDown();
			}
			else if(p4Dir==down)
			{
				p4turnLeft();
			}
			else if(p4Dir==left)
			{
				p4turnUp();
			}
			else 
			{
				p4turnRight();
			}
		}
		else
		{
		}
		if(p4pDir==right)
		{
			p4bypass1=true;
			p41s.setTransform(Sprite.TRANS_NONE);
		}
		else if(p4pDir==down)
		{
			p4bypass1=true;
			p41s.setTransform(Sprite.TRANS_ROT90);
		}
		else if(p4pDir==left)
		{
			p4bypass1=true;
			p41s.setTransform(Sprite.TRANS_ROT180);
		}
		else if(p4pDir==up)
 		{
 			p4bypass1=true;
 			p41s.setTransform(Sprite.TRANS_ROT270);
		}
		else
		{
		}
		
			if(p41s.collidesWith(Arena,false)==true)
        	{
        		p4bypass1=true;
        		p4bypass=true;
        	}
        	else
        	{
        		p4bypass=false;
        		p4bypass1=false;
        	}
		
		}
		
	}
}

}


//######################################################################################
      
        
        
        
        

       
        
        
        
        
        
        
        
    

}




