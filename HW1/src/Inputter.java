

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


public class Inputter extends JFrame implements KeyListener
{

	private static final long serialVersionUID = 1L;

	private JTextArea output;
	private Thread thread1;
	private String message = "";
	private boolean display = false;

	public Inputter(String name)
	{
		super(name);


		output = new JTextArea(20,30);                      //create JTextArea in which all messages are shown.
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);  // JTextArea always set focus on the last message appended.

		add(new JScrollPane(output)); // add a Scroll bar to JFrame, scrolling associated with JTextArea object
		setSize(500, 500);            // when lines of messages exceeds the line capacity of JFrame, scroll bar scroll down.
		setVisible(true);
		output.addKeyListener(this);  // Adds the specified key listener to receive key events from this component.
	}

	public JTextArea getOutput()
	{
		return this.output;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(this.display)
		{
			this.thread1.interrupt();
			try
			{
				thread1.join();
			}
			catch (InterruptedException e1)
			{
				
			}
			this.message = "";
			this.display = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER)
		{
			if(message.compareToIgnoreCase("exit") == 0)
			{
				this.thread1.interrupt();
				System.exit(0);
			}
			else
			{
				this.thread1 = new Thread(new MessageRunner(output, message));
				this.thread1.start();
				this.display = true;
			}
		}
		else
		{
			this.message += e.getKeyChar();
		}
	}
}
