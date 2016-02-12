import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by nicho on 2/10/2016.
 */
public class Job
{
    private Socket client;
    private String action;
    private PrintWriter out;

    public Job(Socket client, String action)
    {
        this.client = client;
        this.action = action;
        try
        {
            this.out = new PrintWriter(this.client.getOutputStream(), true);
        }
        catch(Exception e)
        {

        }
    }

    public Socket getClient()
    {
        return client;
    }

    public void setClient(Socket client)
    {
        this.client = client;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public void printToClient(String message)
    {
        out.println(message);
    }

    public void jobCompleated()
    {
        this.out.close();
        try
        {
            this.client.close();
        }
        catch (IOException e)
        {

        }
    }
}
