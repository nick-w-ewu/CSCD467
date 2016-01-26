/**
 * Created by Nicholas Witmer on 1/25/2016 for CSCD467 at EWU.
 */
public class MessageRunner implements Runnable
{
    private Monitor monitor;
    private int threadNumber;

    public MessageRunner(Monitor monitor, int number)
    {
        this.monitor = monitor;
        this.threadNumber = number;
    }

    public void run()
    {
        for(int i = 0; i < 10; i++)
        {
           if(monitor.isTurn(threadNumber))
           {
               System.out.printf("%s Message from thread %d.\n", monitor.getMessageNum(), threadNumber);
               monitor.incrementCurrentThread();
           }
        }
    }
}
