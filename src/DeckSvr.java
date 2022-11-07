import java.awt.event.KeyAdapter;
 import java.awt.event.KeyEvent;
import java.io.*;  
import java.net.*;
import java.lang.*; 
import java.awt.Robot;
import java.io.DataInputStream;
import java.awt.Robot;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

 public class DeckSvr extends KeyAdapter implements WindowListener
{
	InputStream in;
	int x;
	int y;
	int c;
	DataInputStream dis;
	Robot robot;
	String read;
	int k;
	ServerSocket serverSocket;
	Socket sock;
	JTextPane tp;

   	public DeckSvr() throws Exception
	{
		robot = new Robot();   
  		//JFrame jframe = new JFrame();
  		//tp = new JTextPane();
  		//tp.setText("");
  		//jframe.add(tp);
  		//jframe.addWindowListener(this);
  		//jframe.setSize(400, 350);
  		//jframe.setVisible(true);
		startSvr();
		k = 0;
		while(true)
		{
			read = dis.readUTF();
			//System.out.println(read);
			
			if(read.startsWith("MX"))
			{
				x = Integer.parseInt(read.substring(2, read.length()));
			}
			
			if(read.startsWith("MY"))
			{
				y = Integer.parseInt(read.substring(2, read.length()));
				robot.mouseMove(x, y);
			}
			
			if(read.startsWith("CP1"))
			{
				robot.mousePress(InputEvent.BUTTON1_MASK);
			}
			
			if(read.startsWith("CP2"))
			{
				robot.mousePress(InputEvent.BUTTON2_MASK);
			}
			
			if(read.startsWith("CP3"))
			{
				robot.mousePress(InputEvent.BUTTON3_MASK);
			}
			
			if(read.startsWith("CR1"))
			{
    				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			
			if(read.startsWith("CR2"))
			{
    				robot.mouseRelease(InputEvent.BUTTON2_MASK);
			}
			
			if(read.startsWith("CR3"))
			{
    				robot.mouseRelease(InputEvent.BUTTON3_MASK);
			}
			
			if(read.startsWith("K"))
			{
				k = Integer.parseInt(read.substring(1, read.length()));
				robot.keyPress(k);
				robot.keyRelease(k);
			}
			
			if(read.startsWith("close"))
			{
				dis.close();
				in.close();
				serverSocket.close();
				startSvr();
				
			}
			Thread.sleep(2);
		}
   	}
   	
   	public void startSvr()
   	{
   	try
   	{
   		serverSocket = new ServerSocket(8888);
        	sock = serverSocket.accept();
        	tp.setText(InetAddress.getLocalHost().toString());
		in = sock.getInputStream();
		dis = new DataInputStream(in);
		}
		catch(Exception ex)
		{
		  startSvr();
		}
	}
   	
   	public static void main(String args[]) throws Exception
   	{
   		DeckSvr deck = new DeckSvr();
   	}
   	
  public void windowActivated(WindowEvent we)
  {}
  
  public void windowDeactivated(WindowEvent we)
  {}
  
  public void windowDeiconified(WindowEvent we)
  {}
  
  public void windowIconified(WindowEvent we)
  {}
  
  public void windowClosed(WindowEvent we)
  {}
  
  public void windowOpened(WindowEvent we)
  {}
  
  public void windowClosing(WindowEvent we)
{
try
{
if(sock.isConnected())
{
	dis.close();
	in.close();
	sock.close();
}
    System.exit(0);
    }
    catch(Exception ex)
    {}
}
 }
