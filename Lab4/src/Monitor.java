/**
 * Created by nicho_000 on 1/25/2016.
 */
public class Monitor
{
    private int numThreads;
    private int currentThread = 0;
    private int messageNum = 1;

    public Monitor(int threads)
    {
        this.numThreads = threads;
    }

    public synchronized boolean isTurn(int threadNum)
    {
        while(threadNum != currentThread)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }
        return true;
    }

    public synchronized  void incrementCurrentThread()
    {
        if(this.currentThread+1 >= numThreads)
        {
            this.currentThread = 0;
            this.messageNum++;
        }
        else
        {
            this.currentThread++;
        }
        notifyAll();
    }

    public synchronized String getMessageNum()
    {
        if(messageNum == 1)
        {
            return 1+"st";
        }
        else if(messageNum == 2)
        {
            return 2+"nd";
        }
        else if(messageNum == 3)
        {
            return 3+"rd";
        }
        else
        {
            return this.messageNum+"th";
        }
    }
}
