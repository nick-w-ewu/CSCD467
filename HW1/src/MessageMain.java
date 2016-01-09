import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MessageMain
{
	public static void main(String[] args)
	{
		Inputter input = new Inputter("Nicholas Witmer Homework 1");
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
