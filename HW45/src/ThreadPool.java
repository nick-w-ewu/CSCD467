/**
 * Created by nicho_000 on 2/11/2016.
 */
public class ThreadPool
{
    int maxCompacity = 50;
    int currentCompacity;
    CommandProcessor[] processors;
    SharedQueue monitor;

    public ThreadPool(SharedQueue monitor)
    {
        this.monitor = monitor;
        this.processors = new CommandProcessor[this.maxCompacity];
        this.currentCompacity = 5;
        for(int i = 0; i < currentCompacity; i++)
        {
            this.processors[i] = new CommandProcessor(monitor, i);
        }
    }

    public void startPool()
    {
        for(int i = 0; i < currentCompacity; i++)
        {
            this.processors[i].start();
        }
    }

    public void shutdownThreads()
    {
        for(int i = 0; i < this.currentCompacity; i++)
        {
            this.processors[i].interrupt();
        }
    }


}
