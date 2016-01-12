

public class MessageMain
{
	public static void main(String[] args)
	{
		Thread waiter = new Thread(new WaitRunner());
		Thread printer = new Thread(new MessageRunner(waiter));
		waiter.start();
		printer.start();
		try
		{
			waiter.join();
			printer.join();
		} catch (InterruptedException e)
		{
			
		}
		System.out.println("Both Waiter and Printer have finished their work!");
	}

}
