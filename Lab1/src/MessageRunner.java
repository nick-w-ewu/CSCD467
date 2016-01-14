//Nicholas Witmer
//CSCD 467 Lab1

import javax.swing.JTextArea;

public class MessageRunner implements Runnable
{
	private JTextArea output;

	public MessageRunner(JTextArea output)
	{
		super();
		this.output = output;
	}

	@Override
	public void run()
	{
		while(true)
		{
			this.output.append("Message from thread---->" + Thread.currentThread().getName() + "\n");
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				this.output.append(Thread.currentThread().getName() + " Gets Interrupted! Terminate!\n");
				return;
			}
		}
	}	
}
