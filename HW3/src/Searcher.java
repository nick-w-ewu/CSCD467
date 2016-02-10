
public class Searcher extends Thread
{
    private SharedQueue sharedQueue;
    private String pattern;
    private int count = 0;

    public Searcher(SharedQueue q, String p)
    {
        this.sharedQueue = q;
        this.pattern = p;
    }

    public void run()
    {
        String line;
        while(true)
        {
            line = sharedQueue.dequeueLine();
            if(line == null)
            {
                break;
            }
            this.count += SerialSearchFile.searchLine(line, pattern);
        }
    }

    public int getCount()
    {
        return this.count;
    }
}
