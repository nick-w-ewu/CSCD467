import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by nicho_000 on 2/11/2016.
 */
public class CommandClient
{
    private String ip;
    private int port;
    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public CommandClient(String ip, int port) throws IOException
    {
        this.ip = ip;
        this.port = port;
    }

    private void connectServer() throws IOException
    {
        this.server = new Socket(ip, port);
        in = new BufferedReader(
                new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);
    }


    public void sendCommand(String command) throws IOException
    {
        connectServer();
        this.out.println(command);
        System.out.println(in.readLine());
    }

}
