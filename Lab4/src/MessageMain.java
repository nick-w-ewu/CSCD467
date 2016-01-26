/**
 * Created by Nicholas Witmer on 1/25/2016 for CSCD467 at EWU.
 */
public class MessageMain
{
    public static void main(String[] args)
    {
        int numThreads;
        if (args.length < 1)
        {
            System.out.println("Usage: MessageMain numThread");
            System.out.println("Using 3 as default");
            numThreads = 3;
        }
        else
        {
            numThreads = Integer.parseInt(args[0]);
        }
        Thread[] threads = new Thread[numThreads];
        Monitor monitor = new Monitor(numThreads);
        for (int i = 0; i < numThreads; i++)
        {
            threads[i] = new Thread(new MessageRunner(monitor, i));
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++)
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
