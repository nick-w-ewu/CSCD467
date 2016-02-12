import java.io.IOException;
import java.util.Scanner;

/**
 * Created by nicho_000 on 2/11/2016.
 */
public class CommandTester
{
    public static void main(String[] args)
    {
        try
        {
            CommandClient tester = new CommandClient("localhost", 9898);
            Scanner input = new Scanner(System.in);
            String command = "test";

            while(true)
            {
                command = input.nextLine();
                tester.sendCommand(command);
            }
        }
        catch (IOException e)
        {

        }
    }
}
