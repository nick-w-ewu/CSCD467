
public class SharedQueue
{
    private final int MAX_SIZE = 100;
    private String[] lines;
    private boolean endFile = false;
    private boolean writeable = true;
    private boolean readable = false;
    private int head = 0, tail = 0;

    public SharedQueue()
    {
        this.lines = new String[MAX_SIZE];
    }

    public synchronized void enqueueLine(String line)
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
        lines[tail] = line;
        readable = true;
        tail = (tail +1) % MAX_SIZE;
        if(head == tail)
        {
            writeable = false;
        }
        notify();
    }

    public synchronized String dequeueLine()
    {
        String temp;
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
        temp = lines[head];
        head = (head +1) % MAX_SIZE;
        if(head == tail)
        {
            if(this.endFile)
            {
                return null;
            }
            readable = false;
        }
        notify();
        return temp;
    }

    public synchronized void setEndFile()
    {
        this.endFile = true;
    }
}
