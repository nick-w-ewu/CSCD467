import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Reader implements Runnable
{
    SharedQueue sharedQueue;
    private int lineCount = 0;
    private BufferedReader read;

    public Reader(SharedQueue q, String fileName) throws FileNotFoundException
    {
        this.sharedQueue = q;
        this.read = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public void run()
    {
        String line = "";
        while(line != null)
        {
            try
            {
                line = this.read.readLine();
                if(line != null)
                {
                    this.lineCount++;
                }
                sharedQueue.enqueueLine(line);
            }
            catch (IOException e)
            {

            }
        }
        sharedQueue.setEndFile();
    }

    public int getCount()
    {
        return this.lineCount;
    }
}
