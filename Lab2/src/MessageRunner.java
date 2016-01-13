import javax.swing.JTextArea;

public class MessageRunner implements Runnable
{
	private Thread waiter;
	public MessageRunner(Thread waiter)
	{
		super();
		this.waiter = waiter;
	}

	@Override
	public void run()
	{
		int i;
		for(i = 0; i < 50; i++)
		{
			System.out.printf("Message i = %d, from Thread Printer\n", i+1);
			waiter.interrupt();
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				return;
			}
		}
	}
	
	
}
