public class MyPrimeTest
{
    public static void main(String[] args) throws InterruptedException
    {
/*        if (args.length < 3)
        {
            System.out.println("Usage: MyPrimeTest numThread low high \n");
            return;
        }*/
/*        int nthreads = Integer.parseInt(args[0]);
        int low = Integer.parseInt(args[1]);
        int high = Integer.parseInt(args[2]);*/
        int nthreads = 4;
        int low = 1;
        int high = 100000;
        Counter c = new Counter();
        ThreadPrime[] threads;

        //test cost of serial code
        long start = System.currentTimeMillis();
        int numPrimeSerial = SerialPrime.numSerailPrimes(low, high);
        long end = System.currentTimeMillis();
        long timeCostSer = end - start;
        System.out.println("Time cost of serial code: " + timeCostSer + " ms.");

        long timeCostCon, startCon, endCon;
        //test of concurrent code
        // **************************************
        // Write me here
        threads = new ThreadPrime[nthreads];
        int numPerThread = high/nthreads;
        int threadLow = low;
        int threadHigh;
        startCon = System.currentTimeMillis();
        for(int i = 0; i < nthreads-1; i++)
        {
            threadHigh = low + numPerThread;
            threads[i] = new ThreadPrime(threadLow, threadHigh, c);
            threads[i].start();
            threadLow = threadHigh+1;
        }
        threads[threads.length-1] = new ThreadPrime(threadLow, high, c);
        threads[threads.length-1].start();
        //startCon = System.currentTimeMillis();
/*        for(int i = 0; i < nthreads; i++)
        {
            threads[i].start();
        }*/

        for(int i = 0; i < nthreads; i++)
        {
            threads[i].join();
        }
        endCon = System.currentTimeMillis();
        timeCostCon = endCon - startCon;
        // **************************************
        System.out.println("Time cost of parallel code: " + timeCostCon + " ms.");
        System.out.format("The speedup ration is by using concurrent programming: %5.2f. %n", (double) timeCostSer / timeCostCon);

        System.out.println("Number prime found by serial code is: " + numPrimeSerial);
        System.out.println("Number prime found by parallel code is " + c.total());
    }
}
