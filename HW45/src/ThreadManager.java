/**
 * Created by nicho_000 on 2/12/2016.
 */
public class ThreadManager extends Thread
{
    private ThreadPool pool;
    private SharedQueue monitor;
    private final int THRESHOLD_1 = 10;
    private final int THRESHOLD_2 = 20;

    public ThreadManager(ThreadPool p, SharedQueue m)
    {
        this.monitor = m;
        this.pool = p;
    }

    public void run()
    {
        while(!monitor.getShutdown())
        {
            int jobs = monitor.getCurrentSize();
            int threads = pool.getCurrentCompacity();
            if(jobs > THRESHOLD_1 && jobs <= THRESHOLD_2 && threads < THRESHOLD_1)
            {
                pool.increaseThreadsInPool();
            }
            else if(jobs > THRESHOLD_2 && threads < THRESHOLD_2)
            {
                pool.increaseThreadsInPool();
            }
            else if(jobs <= THRESHOLD_1 && threads > THRESHOLD_1)
            {
                pool.decreaseThreadsInPool();
            }
            else if(jobs <= THRESHOLD_2 && jobs > THRESHOLD_1 && threads > THRESHOLD_2)
            {
                pool.decreaseThreadsInPool();
            }

            try
            {
                sleep(500);
            }
            catch(InterruptedException e)
            {

            }
        }
    }

}
