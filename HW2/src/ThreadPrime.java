public class ThreadPrime extends Thread
{
    private int low;
    private int high;
    private int numFound = 0;
    private Counter c;

    // each thread only  takes care of one subrange (low, high)
    public ThreadPrime(int lowLocal, int highLocal, Counter ct)
    {
        this.low = lowLocal;
        this.high = highLocal;
        c = ct;
    }

    public void run()
    {
        // write me here
        for(int i = low; i <= high; i++)
        {
            if(SerialPrime.isPrime(i))
            {
                numFound++;
            }
        }
        this.c.increment(numFound);
    }
}
