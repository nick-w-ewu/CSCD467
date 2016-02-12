import java.io.BufferedReader;
import java.io.IOException;
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
public class CommandServer
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
        ThreadPool pool = new ThreadPool(monitor);
        pool.startPool();
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
//                out.println("Welcome to the Calculation Server");
//                out.println("You can use the ADD, MUL, SUB, or DIV commands to process data");
//                out.println("You may stop the server by issuing the KILL command");
                command = in.readLine();
                if(command.equalsIgnoreCase("kill"))
                {
                    monitor.setShutdown();
                    System.out.println("Killing server");
                    pool.shutdownThreads();
                    throw new IOException();
                }
                else
                {
                    if(!monitor.enqueueJob(new Job(socket, command)))
                    {
                        out.println("Server is full, please try again later");
                        out.close();
                        in.close();
                        socket.close();
                    }
                }
            }
        }
        catch(IOException e)
        {

        }
        finally
        {
            listener.close();
        }
    }
}
