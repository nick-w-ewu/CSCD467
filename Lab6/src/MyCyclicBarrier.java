import java.util.concurrent.BrokenBarrierException;

/**
 * Created by Nicholas Witmer for CSCD 467 2/4/2016, with some ideas from Tony's Solution.
 */

public class MyCyclicBarrier
{
    private int parties;
    private Runnable action;
    private int remainingCount;
    private boolean isBroken = false;
    private boolean preparePause = false;

    public MyCyclicBarrier(int parties, Runnable barrierAction)
    {
        this.parties = parties;
        this.action = barrierAction;
        this.remainingCount = parties;
    }

    public synchronized int await() throws InterruptedException, BrokenBarrierException
    {
        int index = this.remainingCount;
        if(!preparePause)
        {
            this.preparePause = true;
        }
        if(remainingCount > 1)
        {
            this.remainingCount--;
            while(preparePause)
            {
                try
                {
                    wait();
                }
                catch(InterruptedException e)
                {
                    this.isBroken = true;
                    throw new InterruptedException();
                }
                if(this.isBroken)
                {
                    notifyAll();
                    throw new BrokenBarrierException();
                }
            }
        }
        else if(this.remainingCount == 1)
        {
            this.preparePause = false;
            remainingCount = parties;
            this.action.run();
            notifyAll();
        }
        return index-1;
    }
}