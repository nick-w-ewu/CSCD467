//Nicholas Witmer
//CSCD 467 Lab1
//Basic GUI Code, in constructor, provided by Tony

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
	private Thread thread2;
	private int keyboardCount = 1;

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
	
	public void setThreads(Thread thread1, Thread thread2)
	{
		this.thread1 = thread1;
		this.thread2 = thread2;
		thread1.start();
		thread2.start();
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
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(keyboardCount == 1)
		{
			thread1.interrupt();
		}
		else if(keyboardCount == 2)
		{
			thread2.interrupt();
		}
		else if(keyboardCount == 3)
		{
			output.append("All Threads are interrupted\n");
		}
		keyboardCount++;
	}
}
