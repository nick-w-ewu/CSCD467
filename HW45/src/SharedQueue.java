


public class SharedQueue
{
    private final int MAX_SIZE = 100;
    private Job[] jobs;
    private boolean shutdownCommand = false;
    private boolean writeable = true;
    private boolean readable = false;
    private int head = 0, tail = 0, currentSize = 0;

    public SharedQueue()
    {
        this.jobs = new Job[MAX_SIZE];
    }

    public synchronized boolean enqueueJob(Job job)
    {
        if(writeable)
        {
            jobs[tail] = job;
            readable = true;
            tail = (tail + 1) % MAX_SIZE;
            if (head == tail)
            {
                writeable = false;
            }
            notify();
            currentSize++;
            return true;
        }
        return false;
    }

    public synchronized Job dequeueJob()
    {
        CommandProcessor me = (CommandProcessor)Thread.currentThread();
        Job temp;
        while(!readable)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {
                if(this.shutdownCommand || me.getKilledByManager())
                {
                    return null;
                }
            }

        }
        writeable = true;
        temp = jobs[head];
        currentSize--;
        head = (head +1) % MAX_SIZE;
        if(head == tail)
        {
            if(this.shutdownCommand)
            {
                return null;
            }
            readable = false;
        }
        notify();
        return temp;
    }

    public synchronized int getCurrentSize()
    {
        return this.currentSize;
    }

    public synchronized void setShutdown()
    {
        this.shutdownCommand = true;
    }

    public synchronized boolean getShutdown()
    {
        return this.shutdownCommand;
    }
}
