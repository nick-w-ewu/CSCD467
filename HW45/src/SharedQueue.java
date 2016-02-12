


public class SharedQueue
{
    private final int MAX_SIZE = 100;
    private Job[] jobs;
    private boolean shutdownCommand = false;
    private boolean writeable = true;
    private boolean readable = false;
    private int head = 0, tail = 0;

    public SharedQueue()
    {
        this.jobs = new Job[MAX_SIZE];
    }

    public synchronized void enqueueJob(Job job)
    {
        while(!writeable)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {

            }
        }
        jobs[tail] = job;
        readable = true;
        tail = (tail +1) % MAX_SIZE;
        if(head == tail)
        {
            writeable = false;
        }
        notify();
    }

    public synchronized Job dequeueJob()
    {
        Job temp;
        while(!readable)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {

            }
        }
        writeable = true;
        temp = jobs[head];
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

    public synchronized void setShutdown()
    {
        this.shutdownCommand = true;
    }
}
