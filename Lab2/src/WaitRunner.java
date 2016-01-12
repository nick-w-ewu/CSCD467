
public class WaitRunner implements Runnable
{
	
	public WaitRunner()
	{
		super();
	}

	@Override
	public void run()
	{
		int interuptedCount = 0;
		while(interuptedCount < 50)
		{
			try
			{
				Thread.sleep(10000);
			}
			catch(InterruptedException e)
			{
				interuptedCount++;
				if(interuptedCount == 25)
				{
					System.out.println("Printer has got his work half done!");
				}
			}
		}
		System.out.println("Waiter has done its work, terminating.");
	}

}
