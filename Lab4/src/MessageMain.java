/**
 * Created by nicho_000 on 1/25/2016.
 */
public class MessageMain
{
    public static void main(String[] args)
    {
        int numThreads = 3;
        Thread[] threads = new Thread[numThreads];
        Monitor monitor = new Monitor(numThreads);
        for(int i = 0; i < numThreads; i++)
        {
            threads[i] = new Thread(new MessageRunner(monitor, i));
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++)
        {
            try
            {
                threads[i].join();
            }
            catch (InterruptedException e)
            {

            }
        }
    }
}
