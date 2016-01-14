//Nicholas Witmer
//CSCD 467 Lab1
//Window Listener Code provided by Tony

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MessageMain
{
	public static void main(String[] args)
	{
		Inputter input = new Inputter("Nicholas Witmer Lab 1");
		Thread thread1 = new Thread(new MessageRunner(input.getOutput()));
		Thread thread2 = new Thread(new MessageRunner(input.getOutput()));
		input.setThreads(thread1, thread2);
		input.addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing( WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}
