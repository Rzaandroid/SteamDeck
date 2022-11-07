import java.awt.event.KeyAdapter;
 import java.awt.event.KeyEvent;
 import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;  
import java.net.*;
import java.lang.*; 
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.io.DataOutputStream;

 public class DeckSvrPC extends KeyAdapter implements MouseListener, MouseMotionListener, WindowListener
{
	static OutputStream out;
	static int x;
	static int y;
	static DataOutputStream dos;
	static JTextField ip;
	static String addr = "";
	String m;
	Socket sock;

   	public DeckSvrPC() throws Exception
	{
  		JTextField textField = new JTextField();
		JButton but = new JButton("CONNECT");
		ip = new JTextField("TYPE IP ADDRESS HERE");
		but.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
try
{
            System.out.println("Connect...");
		addr = ip.getText();
  sock = new Socket(addr,8888);
		out = sock.getOutputStream();
		dos = new DataOutputStream(out);
}
catch(Exception ex){}
         }          
      });
      /*
  		textField.addKeyListener(this);
  		textField.addMouseListener(this);
  		textField.addMouseMotionListener(this);
  		JFrame jframe = new JFrame();
  		jframe.add(textField);
  		jframe.addWindowListener(this);
jframe.add(but);
jframe.add(ip);
but.setVisible(true);
  		jframe.setSize(800, 800);
jframe.setLayout(new GridLayout(3, 1));
jframe.pack();
  		jframe.setVisible(true);
  		*/
  		
  		  		textField.addKeyListener(this);
  		textField.addMouseListener(this);
  		textField.addMouseMotionListener(this);
  		//textField.setSize(800,800);
  		//JPanel panel = new JPanel();
  		//panel.setLayout(new GridLayout(3,1));
  		BorderLayout layout = new BorderLayout();
  		layout.setHgap(10);
      layout.setVgap(10);
  		JFrame jframe = new JFrame();
  		jframe.setLayout(layout);
  		jframe.add(textField,BorderLayout.CENTER);
  		jframe.addWindowListener(this);
  		//panel.add(but);
  		//panel.add(ip);
  		jframe.add(ip,BorderLayout.NORTH);
jframe.add(but,BorderLayout.SOUTH);
but.setVisible(true);
  		//jframe.setSize(800, 800);
  		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
  		
  		

//jframe.pack();
  		jframe.setVisible(true);
  		
   	}
   	
   	public static void main(String args[]) throws Exception
   	{
   	  DeckSvrPC deck = new DeckSvrPC();
   	}

	public void keyPressed(KeyEvent event)
	{
  		int ch = event.getKeyCode();
  		System.out.println(event.getKeyChar());
		try
		{	
			String k = "K"+ch;
			dos.writeUTF(k);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
  	}
  	
  	public void mouseDragged(MouseEvent e) {

try
  {
			x = e.getX();
			y = e.getY();
			m = "M"+x+":"+y;
			dos.writeUTF("MX"+x);
			dos.writeUTF("MY"+y);
			System.out.println(m);
			Thread.sleep(5);
	}
	catch(Exception ex)
	{}

}

public void mouseMoved(MouseEvent e)
{
  try
  {
			x = e.getX();
			y = e.getY();
			m = "M"+x+":"+y;
			dos.writeUTF("MX"+x);
			dos.writeUTF("MY"+y);
			System.out.println(m);
			Thread.sleep(5);
	}
	catch(Exception ex)
	{}
}

    public void mousePressed(MouseEvent me) {
    try
    {
        if(me.getButton()==1)
        {
          dos.writeUTF("CP1");
        }
        if(me.getButton()==2)
        {
          dos.writeUTF("CP2");
        }
        if(me.getButton()==3)
        {
          dos.writeUTF("CP3");
        }
    }
    catch(Exception ex)
    {}
    }

    public void mouseReleased(MouseEvent me) {
    try
    {
      if(me.getButton()==1)
        {
          dos.writeUTF("CR1");
        }
        if(me.getButton()==2)
        {
          dos.writeUTF("CR2");
        }
        if(me.getButton()==3)
        {
          dos.writeUTF("CR3");
        }
    }
    catch(Exception ex)
    {}
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
    
    public void windowClosing(WindowEvent we)
{
try
{
if(sock.isConnected())
{
	dos.close();
	out.close();
	sock.close();
}
    System.exit(0);
    }
    catch(Exception ex)
    {}
}

public void windowActivated (WindowEvent arg0) {   
}    
public void windowClosed (WindowEvent arg0) {      
}       
public void windowDeactivated (WindowEvent arg0) {      
}    
public void windowDeiconified (WindowEvent arg0) {       
}    
public void windowIconified(WindowEvent arg0) {       
}    
public void windowOpened(WindowEvent arg0) {  
}
 }
