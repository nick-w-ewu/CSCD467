import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nicho on 2/10/2016.
 */
public class CommandProcessor extends Thread
{
    private SharedQueue monitor;
    private int threadNum;
    private boolean killedByManager = false;

    public CommandProcessor(SharedQueue monitor, int num)
    {
        this.monitor = monitor;
        this.threadNum = num;
    }

    @Override
    public void run()
    {
        Job job;
        String[] commandParts;
        int int1, int2, result;

        while(true)
        {
            job = monitor.dequeueJob();

            if (job == null)
            {
                System.out.println("Shutting down processor " + this.threadNum);
                break;
            }
            commandParts = job.getAction().split(",");
            if (commandParts.length == 3)
            {
                int1 = Integer.parseInt(commandParts[1]);
                int2 = Integer.parseInt(commandParts[2]);
                switch (commandParts[0])
                {
                    case ("ADD"):
                    {
                        result = int1 + int2;
                        job.printToClient(result + "");
                        log(commandParts[0], result);
                        break;
                    }
                    case ("SUB"):
                    {
                        result = int1 - int2;
                        job.printToClient(result + "");
                        log(commandParts[0], result);
                        break;
                    }
                    case ("MUL"):
                    {
                        result = int1 * int2;
                        job.printToClient(result + "");
                        log(commandParts[0], result);
                        break;
                    }
                    case ("DIV"):
                    {
                        result = int1 / int2;
                        job.printToClient(result + "");
                        log(commandParts[0], result);
                        break;
                    }
                    default:
                    {
                        job.printToClient("Invalid Command");
                        log(commandParts[0], 0);
                    }
                }
                job.jobCompleated();
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    break;
                }
            }
            else
            {
                job.printToClient("Invalid Command");
                log(commandParts[0], 0);
            }
        }
    }

    private void log(String command, int result)
    {
        Thread parent = Thread.currentThread();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Command " + command + " was processed with result " + result + " by CommandProcessor " + this.threadNum
                            + " at " + dateFormat.format(date));
    }

    public int getThreadNum()
    {
        return threadNum;
    }

    public synchronized boolean getKilledByManager()
    {
        return this.killedByManager;
    }

    public synchronized void setKilledByManager()
    {
        this.killedByManager = true;
    }
}
