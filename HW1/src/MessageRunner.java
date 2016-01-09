import javax.swing.JTextArea;

public class MessageRunner implements Runnable
{
	private JTextArea output;
	private String message;

	public MessageRunner(JTextArea output, String message)
	{
		super();
		this.output = output;
		this.message = message;
	}

	@Override
	public void run()
	{
		while(true)
		{
			this.output.append(message + "\n");
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				return;
			}
		}
	}
	
	
}
