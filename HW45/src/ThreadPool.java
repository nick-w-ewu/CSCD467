import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by nicho_000 on 2/11/2016.
 */
public class ThreadPool
{
    private int maxCompacity = 20;
    ArrayList<CommandProcessor> processors;
    SharedQueue monitor;
    int processorNum = 0;

    public ThreadPool(SharedQueue monitor)
    {
        this.monitor = monitor;
        this.processors = new ArrayList<>();
        for (processorNum = 0; processorNum < 5; processorNum++)
        {
            this.processors.add(new CommandProcessor(monitor, processorNum));
        }
    }

    public void startPool()
    {
        for (CommandProcessor p : processors)
        {
            p.start();
        }
    }

    public synchronized void increaseThreadsInPool()
    {
        int current = this.processors.size();
        CommandProcessor temp;
        for (int i = 0; i < current; i++)
        {
            temp = new CommandProcessor(monitor, processorNum++);
            temp.start();
            this.processors.add(temp);
        }
        System.out.println("Number of threads in threadpool is now " + this.processors.size());
    }

    public synchronized void decreaseThreadsInPool()
    {
        int current = this.processors.size();
        int half = (current - 1) / 2;
        CommandProcessor temp;
        for (int i = this.processors.size() - 1; i > half; i--)
        {
            temp = this.processors.get(i);
            System.out.println("removing processor " + temp.getThreadNum());
            temp.setKilledByManager();
            temp.interrupt();
            this.processors.remove(i);
        }
        System.out.println("Number of threads in threadpool is now " + this.processors.size());
    }

    public void shutdownThreads()
    {
        for (CommandProcessor p : processors)
        {
            p.interrupt();
        }
    }

    public synchronized int getCurrentCompacity()
    {
        return this.processors.size();
    }

    public synchronized int getMaxCompacity()
    {
        return this.maxCompacity;
    }
}
