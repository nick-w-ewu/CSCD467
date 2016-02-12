/**
 * Created by nicho on 2/10/2016.
 */
public class CommandProcessor implements Runnable
{
    private SharedQueue monitor;

    public CommandProcessor(SharedQueue monitor)
    {
        this.monitor = monitor;
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

            if(job ==  null)
            {
                break;
            }
            commandParts = job.getAction().split(",");
            int1 = Integer.parseInt(commandParts[1]);
            int2 = Integer.parseInt(commandParts[2]);
            switch (commandParts[0])
            {
                case ("ADD"):
                {
                    result = int1+int2;
                    job.printToClient(result+"");
                    break;
                }
                case ("SUB"):
                {
                    result = int1-int2;
                    job.printToClient(result+"");
                    break;
                }
                case ("MUL"):
                {
                    result = int1*int2;
                    job.printToClient(result+"");
                    break;
                }
                case ("DIV"):
                {
                    result = int1/int2;
                    job.printToClient(result+"");
                    break;
                }
                default:
                {
                    job.printToClient("Invalid Command");
                }
            }
            job.jobCompleated();
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                break;
            }
        }
    }
}
