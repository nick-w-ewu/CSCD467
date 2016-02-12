import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  * A server program which accepts requests from clients to
 *  * capitalize strings.  When clients connect, a new thread is
 *  * started to handle an interactive dialog in which the client
 *  * sends in a string and the server thread sends back the
 *  * capitalized version of the string.
 *  *
 *  * The program is runs in an infinite loop, so shutdown in platform
 *  * dependent.  If you ran it from a console window with the "java"
 *  * interpreter, Ctrl+C generally will shut it down.
 *  
 */
public class CapitalizeServer
{

    /**
     *  * Application method to run the server runs in an infinite loop
     *  * listening on port 9898.  When a connection is requested, it
     *  * spawns a new thread to do the servicing and immediately returns
     *  * to listening.  The server keeps a unique client number for each
     *  * client that connects just to show interesting logging
     *  * messages.  It is certainly not necessary to do this.
     *  
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println("The capitalization server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        Socket socket;
        SharedQueue monitor = new SharedQueue();
        String command;
        BufferedReader in;
        PrintWriter out;
        try
        {
            while (true)
            {
                socket = listener.accept();
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                command = in.readLine();
                if(command.equalsIgnoreCase("kill"))
                {
                    break;
                }
                else
                {
                    monitor.enqueueJob(new Job(socket, command));
                }
            }
        }
        finally
        {
            listener.close();
        }
    }
}
